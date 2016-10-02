/**
 * Created by Пользователь on 22.09.2016.
 */
class Finder {
    static int Distance(int deltaX, int deltaY)
    {
        return (Math.abs(deltaX)+Math.abs(deltaY)+Math.abs(deltaX+deltaY))/2;
    }
}
