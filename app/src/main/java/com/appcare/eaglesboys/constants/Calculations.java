package com.appcare.eaglesboys.constants;

/**
 * Created by Appcare on 31-10-2017.
 */

public class Calculations {
    int oldnum,newnum;
    String snewnum;
    public String plus(String currentitem)
    {
        oldnum=Integer.parseInt(currentitem.toString());
        newnum=oldnum+1;
         snewnum  = String.valueOf(newnum);
        return snewnum;
    }
    public  void minus()
    {

    }

}
