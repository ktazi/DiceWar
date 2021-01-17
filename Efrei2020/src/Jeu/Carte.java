package Jeu;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Carte {

    private ArrayList<ArrayList<Territoire>> tab_territoire;


    public Carte(int nbdejoueurs, ArrayList<Joueur> tabJoueurs)
    {
        ArrayList<ArrayList<Territoire>> carte = new ArrayList<>(); //Cr�e un tableau 2D de listes, de taille nbdejoueurs*nbdejoueurs
        for(int i=0;i<nbdejoueurs;i++){
            carte.add(CreationDeTerritoire(nbdejoueurs, tabJoueurs)); //Pour un index, je lui donne une liste de territoire de taille 5
        }
        this.tab_territoire = carte;
        RemplirListeTerritoireVoisin(carte);
        RemplirLaForce(tabJoueurs,nbdejoueurs);
    }

    public Carte(int nbdejoueurs, ArrayList<Joueur> tabJoueurs, List<String> csv)
    { //CSV
        ArrayList<ArrayList<Territoire>> carte = new ArrayList<>(); //Cr�e un tableau 2D de listes, de taille nbdejoueurs*nbdejoueurs
        for(int i=0;i<nbdejoueurs;i++){
            carte.add(new ArrayList<Territoire>());
            for(int j=0;j<5;j++){
                String l = csv.get((5*i)+(j+1));
                Territoire temp = new Territoire(tabJoueurs.get((l.charAt(0) - '0')-1),l.charAt(2) - '0');
                carte.get(i).add(temp);
                tabJoueurs.get((l.charAt(0) - '0')-1).getListe_territoire().add(temp);
            }
        }

        this.tab_territoire = carte;
        RemplirListeTerritoireVoisin(carte);
    }





    ArrayList<Territoire> CreationDeTerritoire(int nbdejoueurs, ArrayList<Joueur> tabJoueurs) {
        //Variables
        Random rand = new Random();
        int numerojoueur;
        int nb = 5;


        //Liste de nombres pour faire un random � l'int�rieur
        ArrayList<Integer> listNbRnd = new ArrayList<Integer>(nbdejoueurs);
        for(int i=0;i<5;i++) {
            listNbRnd.add(i%nbdejoueurs);//Liste qui des nombres entre 1 et nbdejoueurs
        }


        //Une liste de 5 territoires o� on va placer les joueurs au hasard
        ArrayList<Territoire> territoire = new ArrayList<>();
        for(int i=0;i<nb;i++){
            if(listNbRnd.size()>0){
                numerojoueur = rand.nextInt(listNbRnd.size()); //donne une valeur al�atoire entre 0 et n joueurs
                int alea = listNbRnd.remove(numerojoueur);
                Territoire terrjoueur = new Territoire(tabJoueurs.get(alea),1);
                territoire.add(terrjoueur);
                tabJoueurs.get(alea).getListe_territoire().add(terrjoueur);//Je le mets dans la liste du territoire du joueur
            }
        }
        return territoire;
    }

    public static void RemplirListeTerritoireVoisin(ArrayList<ArrayList<Territoire>> territoire){
        for(int i=0;i<territoire.size();i++) {
            for(int j=0;j<5;j++) {
                if(i==0 && j==0){ //extr�mit� gauche haut
                    int bas = i + 1;
                    int droite = j + 1;
                    territoire.get(i).get(j).getTerritoire_voisin().add(territoire.get(i).get(droite));
                    territoire.get(i).get(j).getTerritoire_voisin().add(territoire.get(bas).get(j));
                }
                else if(i==territoire.size()-1 && j==0){ //extr�mit� gauche bas
                    int droite = j + 1;
                    int haut = i - 1;
                    territoire.get(i).get(j).getTerritoire_voisin().add(territoire.get(i).get(droite));
                    territoire.get(i).get(j).getTerritoire_voisin().add(territoire.get(haut).get(j));
                }
                else if(i==0 && j==5-1){ //extr�mit� droite haut
                    int gauche= j - 1;
                    int bas = i + 1;
                    territoire.get(i).get(j).getTerritoire_voisin().add(territoire.get(bas).get(j));
                    territoire.get(i).get(j).getTerritoire_voisin().add(territoire.get(i).get(gauche));
                }
                else if(i==territoire.size()-1 && j==5-1){ //extr�mit� droite bas
                    int gauche= j - 1;
                    int haut = i - 1;
                    territoire.get(i).get(j).getTerritoire_voisin().add(territoire.get(i).get(gauche));
                    territoire.get(i).get(j).getTerritoire_voisin().add(territoire.get(haut).get(j));
                }
                else if(i==0 && (j>0 && j<5-1)){ //premi�re ligne
                    int gauche = j - 1;
                    int droite = j + 1;
                    int bas = i + 1;
                    territoire.get(i).get(j).getTerritoire_voisin().add(territoire.get(i).get(gauche));
                    territoire.get(i).get(j).getTerritoire_voisin().add(territoire.get(i).get(droite));
                    territoire.get(i).get(j).getTerritoire_voisin().add(territoire.get(bas).get(j));
                }
                else if(i==territoire.size()-1 && (j>0 && j<5-1)){ //derni�re ligne
                    int gauche = j - 1;
                    int droite = j + 1;
                    int haut = i - 1;
                    territoire.get(i).get(j).getTerritoire_voisin().add(territoire.get(i).get(gauche));
                    territoire.get(i).get(j).getTerritoire_voisin().add(territoire.get(i).get(droite));
                    territoire.get(i).get(j).getTerritoire_voisin().add(territoire.get(haut).get(j));
                }
                else if((i>0 && i<territoire.size()-1) && j==0){ //premi�re colonne sans [0,0] et [territoire.size(),0]
                    int droite = j + 1;
                    int bas = i + 1;
                    int haut = i - 1;
                    territoire.get(i).get(j).getTerritoire_voisin().add(territoire.get(i).get(droite));
                    territoire.get(i).get(j).getTerritoire_voisin().add(territoire.get(bas).get(j));
                    territoire.get(i).get(j).getTerritoire_voisin().add(territoire.get(haut).get(j));
                }
                else if((i>0 && i<territoire.size()-1) && j==5-1){ //derni�re colonne sans 0,territoire.size() et [territoire.size(),territoiez.size()]
                    int gauche = j-1;
                    int bas = i + 1;
                    int haut = i - 1;
                    territoire.get(i).get(j).getTerritoire_voisin().add(territoire.get(i).get(gauche));
                    territoire.get(i).get(j).getTerritoire_voisin().add(territoire.get(bas).get(j));
                    territoire.get(i).get(j).getTerritoire_voisin().add(territoire.get(haut).get(j));
                }
                else if((i>0 && i<territoire.size()-1) && (j>0 && j<5)) { //le reste
                    int gauche = j - 1;
                    int droite = j + 1;
                    int bas = i + 1;
                    int haut = i - 1;
                    territoire.get(i).get(j).getTerritoire_voisin().add(territoire.get(i).get(gauche));
                    territoire.get(i).get(j).getTerritoire_voisin().add(territoire.get(i).get(droite));
                    territoire.get(i).get(j).getTerritoire_voisin().add(territoire.get(bas).get(j));
                    territoire.get(i).get(j).getTerritoire_voisin().add(territoire.get(haut).get(j));
                }
            }
        }
    }

    public void RemplirLaForce(ArrayList<Joueur> tabJoueurs, int nbdejoueurs) {
        Random rand = new Random();
        for(int i=0;i<nbdejoueurs;i++) {
            while(tabJoueurs.get(i).CompterLaForce()!=4*nbdejoueurs){ //Tous les joueurs auront le m�me nombre de d�s
                int aleajoueur = rand.nextInt(nbdejoueurs);
                tabJoueurs.get(i).getListe_territoire().get((aleajoueur%tabJoueurs.get(i).getListe_territoire().size())).setForce(1+rand.nextInt(8)); //Sur un territoire al�atoire, un nombre de d� al�atoire compris entre 1 et 8
                //tabJoueurs.get(i).getListe_territoire().get((aleajoueur%tabJoueurs.get(i).getListe_territoire().size())).getForce()+1
            }
        }
    }

    public ArrayList<ArrayList<Territoire>> getTab_territoire() {
        return tab_territoire;
    }
}
