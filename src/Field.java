/**
 * Created by Пользователь on 22.09.2016.
 */

/**
 * -1:offset
 * 0:standard
 * 1:difficult
 * 2:dangerous
 * 3:unreached
 */
class Field {
    byte map[][];

    /**
     *
     * @param xSize map's height-VERTICAL
     * @param ySize map's weight-HORIZONTAL
     *
     * Math for offset (((xSize-i)/2)<=j && j<=(ySize - 1 + (xSize-i)/2))
     *              -1 is some magic
     *
     */
    Field(int xSize, int ySize)
    {
        //set offset for HORIZONTAL line
        map=new byte[xSize][ySize+xSize/2];
        Creation(xSize,ySize);
    }

    private void Creation(int xSize,int ySize)
    {
        for (int i=0;i<xSize;i++)
        {
            for (int j=0;j<(ySize+xSize/2);j++)
            {
                if(Main.offsetOut(xSize,ySize,i,j))
                {
                    map[i][j]=(byte)Math.round(Math.random()*3);
                }
                else
                {
                    map[i][j]=-1;
                }

            }
        }


    }
}
