package Gameplay;

import java.util.ArrayList;

public class Dices {
    public static ArrayList<Integer> throwDices(int number){
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < number; i++){
            result.add(1 + (int)(Math.random()*6));
        }
        for(int i = result.size(); i<8; i++){
            result.add(0);
        }
        return result;
    }




}
