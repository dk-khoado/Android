package com.example.thicuoiky.Module;
import android.content.Context;

import java.util.Random;
public class RandomMaSach {
    private Random random = new Random();
    private SQLControl sqlControl;
    public  RandomMaSach(Context context){
        sqlControl = new SQLControl(context);
    }
    public String Create(){
        StringBuilder number= new StringBuilder();
        StringBuilder Char= new StringBuilder();
        String KQ;
        do {
            for (int i = 0; i < 3; i++) {
                number.append(String.valueOf(random.nextInt(10)));
            }
            for (int i = 0; i < 3; i++) {
                Char.append(Character.toChars(65 + random.nextInt(25)));
            }
            KQ = Char.toString() + number.toString();
        }while (sqlControl.CheckMaSach(KQ));
        return KQ;
    }
}
