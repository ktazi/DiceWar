package Jeu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
import java.awt.Color;

public class MainConsole {


    public static void main(String[] args) throws ProbleemeException, IOException {

        Scanner sc = new Scanner(System.in);

        int rep;
        System.out.println("Que voulez-vous faire ?\n1 : nouvelle partie\n2 : partie via vcs");
        do {
            rep = sc.nextInt();
        }while(rep < 1 || rep > 2);

        if(rep == 1){
            NouvellePartie p = new NouvellePartie();
        }else{
            PartieCSV p = new PartieCSV();
        }

    }



}
