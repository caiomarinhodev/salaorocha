package models;

/**
 * Created by Caio on 20/04/2015.
 */
public class Util {

    public static String[] l = new String[]{"08:00","08:30","09:00","09:30","10:00","10:30","11:00","11:30","13:30",
    "14:00","14:30","15:00","15:30","16:00","16:30","17:00","17:30","18:00","18:30","19:00","19:30"};

    public static int getPos(String hora){
        for(int i=0;i<l.length;i++){
            if(l[i].equals(hora)){
                return i;
            }
        }return -1;
    }

    public static String getHora(int i){
        return l[i];
    }
}
