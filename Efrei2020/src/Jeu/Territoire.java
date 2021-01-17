package Jeu;
import java.util.*;

public class Territoire {
	private int id;
	private static int nbterritoire = 0;
	private int idjoueur;
	private int force;
	private ArrayList<Territoire> territoire_voisin;

	public Territoire(Joueur j, int de) {
		nbterritoire++;
		this.id = nbterritoire;
		this.idjoueur = j.getId();
		this.force = de; //le random du nombre de d� peut se faire dans le constructeur
		this.territoire_voisin = new ArrayList<Territoire>();
	}

	public void PrintTerritoireVoisin() {
		for(int i=0;i<getTerritoire_voisin().size();i++) {
			System.out.print("["+getTerritoire_voisin().get(i).getId()+"]" + " ");
		}
	}

	public int CalculAleaDe() {
		int somme = 0;
		Random rand = new Random();
		for(int i=0;i<getForce();i++) {
			somme = somme + (1+rand.nextInt(6));
		}
		return somme;
	}


	public int getId() {
		return id;
	}

	public int getIdjoueur() {
		return idjoueur;
	}

	public void setIdjoueur(int idjoueur) {
		this.idjoueur = idjoueur;
	}

	public int getForce() {
		return force;
	}

	public void setForce(int force) {
		this.force = force;
	}

	public ArrayList<Territoire> getTerritoire_voisin() {
		return territoire_voisin;
	}

	public void setTerritoire_voisin(ArrayList<Territoire> territoire_voisin) {
		this.territoire_voisin = territoire_voisin;
	}
}
