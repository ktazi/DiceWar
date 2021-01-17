package Jeu;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class PartieCSV {

    //Affiche ma carte
    public static void PrintCarte(ArrayList<ArrayList<Territoire>> carteactuelle, int nbdejoueurs) {
        System.out.println("\nLa carte actuelle\n");
        int nterr = 1;
        for(int i=0;i<nbdejoueurs;i++) {
            //System.out.println("\n");
            for(int j=0;j<5;j++) {
                System.out.print(" ["+nterr+"]-");
                System.out.print("("+carteactuelle.get(i).get(j).getIdjoueur()+","+carteactuelle.get(i).get(j).getForce()+")" + " ");
                nterr++;
            }
            System.out.println("\n");
        }
    }

    public PartieCSV() throws ProbleemeException, IOException {

        Path path = Paths.get("carteCSV.csv");

        List<String> csv = Files.readAllLines(path);

        //Variables
        int nbjoueurs = 0, alea;
        int actuel_joueur = 0;
        boolean gameplaying = true;
        boolean playerplaying = true;
        boolean victoire = false;
        String ans;
        Random rand = new Random();


        //Choix du nombre des joueurs
        Scanner sc = new Scanner(System.in);
        nbjoueurs = csv.get(0).charAt(0) - '0';
        ArrayList<Joueur> joueurs = new ArrayList<Joueur>(); //Je cr�e mon tableau de joueurs
        for(int i=0;i<nbjoueurs;i++) {
            joueurs.add(new Joueur());//Cr�e plusieurs joueurs
        }


        //Cr�ation de la carte
        Carte carte = new Carte(nbjoueurs, joueurs, csv);
        ArrayList<ArrayList<Territoire>> carteactuelle = carte.getTab_territoire();
        PrintCarte(carteactuelle, nbjoueurs);


        //Ordonnancement des joueurs
        ArrayList<Integer> ListNbJ = new ArrayList<Integer>(nbjoueurs);
        for(int i=0;i<nbjoueurs;i++) {
            ListNbJ.add(i);
        }
        ArrayList<Integer> ListJoueurAlea = new ArrayList<Integer>(nbjoueurs);
        for(int i=0;i<nbjoueurs;i++){
            alea = rand.nextInt(ListNbJ.size());
            int numj = ListNbJ.remove(alea);
            ListJoueurAlea.add(numj);
        }


        //Commencement du jeu
        while(gameplaying){
            System.out.println("C'est au tour du joueur "+joueurs.get(ListJoueurAlea.get(actuel_joueur%nbjoueurs)).getId());
            do{
                joueurs.get(ListJoueurAlea.get(actuel_joueur%nbjoueurs)).PrintTerritoirePossede();
                joueurs.get(ListJoueurAlea.get(actuel_joueur%nbjoueurs)).PrintTerritoireVoisin();
                //joueurs.get(ListJoueurAlea.get(actuel_joueur%nbjoueurs)).BonusTerrContigus(joueurs.get(ListJoueurAlea.get(actuel_joueur%nbjoueurs)).getListe_territoire(),);;
                try {
                    joueurs.get(ListJoueurAlea.get(actuel_joueur%nbjoueurs)).attaqueTerritoire();
                } catch (ProbleemeException e) {
                    System.out.println(e.getMessage());
                }
                PrintCarte(carteactuelle, nbjoueurs);
                victoire = joueurs.get(ListJoueurAlea.get(actuel_joueur%nbjoueurs)).Gagner(carteactuelle);
                System.out.println("Voulez-vous continuer ?\n");
                ans = sc.next();
                if(ans.equals("oui")) {
                    continue;
                }else{
                    joueurs.get(ListJoueurAlea.get(actuel_joueur%nbjoueurs)).PrintTerritoirePossede();
                    joueurs.get(ListJoueurAlea.get(actuel_joueur%nbjoueurs)).PrintTerritoireVoisin();
                    playerplaying = joueurs.get(ListJoueurAlea.get(actuel_joueur%nbjoueurs)).terminerTour(joueurs.get(ListJoueurAlea.get(actuel_joueur%nbjoueurs)).getListe_territoire());
                    actuel_joueur++;
                }
            }while(playerplaying==true && victoire==false);
        }
        sc.close();
    }
}
