package LabFX;

import LabCore.Field;
import LabCore.PathFinder;

import javax.swing.*;

public class PFCreator extends Creator
{

    @Override
    public Checker Get(Field field,JTextArea text){
        return PathFinder.getSingle(field, text);
    }
}
