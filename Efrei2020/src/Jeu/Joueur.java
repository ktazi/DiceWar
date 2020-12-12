package Jeu;
import java.util.*;

public class Joueur {
	private static int nbjoueurs = 0;
	private int id;
	private ArrayList<Territoire> liste_territoire;
	
	public Joueur() {
		nbjoueurs++;
		this.id = nbjoueurs;
		this.liste_territoire = new ArrayList<Territoire>();
	}
	
	
	public void attaqueTerritoire(){
		int t_attaquant, t_attaque;
		Scanner sc = new Scanner(System.in);
		System.out.println("Territoire attaquant et territoire attaqué ?\n");
		t_attaquant = Integer.valueOf(sc.next());
		t_attaque = Integer.valueOf(sc.next());
		System.out.println("Territoire attaquant : "+t_attaquant+"\nTerritoire attaqué : "+t_attaque+"\n");
	}
	
	public boolean terminerTour() {
		return false;
	}

	public ArrayList<Territoire> getListe_territoire() {
		return liste_territoire;
	}
	
	public void setListe_territoire(ArrayList<Territoire> liste_territoire) {
		this.liste_territoire = liste_territoire;
	}


	public int getId() {
		return id;
	}

}
