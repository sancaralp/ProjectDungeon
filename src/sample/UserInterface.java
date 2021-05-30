package sample;

import java.util.*;
public class UserInterface {

    Random roll = new Random();

    protected int D(int dice, int count, int mod){
        int x =0;
        for(int i=count; i>0; i--){x+=(roll.nextInt(dice)+1)+mod;}
        return x;
    }



    protected int mod(String x, Entity entity){
        switch (x) {
            case "str" -> {if(entity.stats[0]%2==1)return (int)Math.round((entity.stats[0] - 11.0) / 2.0); return (int)Math.round((entity.stats[0] - 10.0) / 2.0);}
            case "con" -> {if(entity.stats[1]%2==1)return (int)Math.round((entity.stats[1] - 11.0) / 2.0); return (int)Math.round((entity.stats[1] - 10.0) / 2.0);}
            case "dex" -> {if(entity.stats[2]%2==1)return (int)Math.round((entity.stats[2] - 11.0) / 2.0); return (int)Math.round((entity.stats[2] - 10.0) / 2.0);}
            case "arc" -> {if(entity.stats[3]%2==1)return (int)Math.round((entity.stats[3] - 11.0) / 2.0); return (int)Math.round((entity.stats[3] - 10.0) / 2.0);}
            default -> {return  0;}
                 }


    }
    protected int mod(int x){
        if(x%2==1)return (int)Math.round((x - 11.0) / 2.0); return (int)((x - 10.0) / 2.0);
    }
}
