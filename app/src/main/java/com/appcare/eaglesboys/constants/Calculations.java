package com.appcare.eaglesboys.constants;

/**
 * Created by Appcare on 31-10-2017.
 */

public class Calculations {
   static int oldnum,newnum;
     static String snewnum;
    public static String plus(String currentitemplus)
    {
        oldnum=Integer.parseInt(currentitemplus);
        newnum=oldnum+1;
         snewnum  = String.valueOf(newnum);
        return snewnum;
    }
    public  static  String minus(String currentitemminus) {
        oldnum = Integer.parseInt (currentitemminus);
        if (!((oldnum) <= 1)) {
            newnum = oldnum - 1;
            snewnum = String.valueOf (newnum);

        }
        return snewnum;
    }
}
