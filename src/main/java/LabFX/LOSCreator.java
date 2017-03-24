package LabFX;

import LabCore.Field;
import LabCore.LOSChecker;

import javax.swing.*;

/**
 * Created by alecxanrys
 */
public class LOSCreator extends Creator{
    @Override
    public Checker Get(Field field,JTextArea text){
        return LOSChecker.getSingle(field, text);
    }
}
