package Jeu;
import java.util.*;

public class Territoire {
	private int id;
	private static int nbterritoire = 0;
	private int idjoueur;
	private int force;
	private LinkedList<Integer> territoire_voisin;
	
	public Territoire(Joueur j, int de) {
		nbterritoire++;
		this.id = nbterritoire;
		this.idjoueur = j.getId();
		this.force = de; //le random du nombre de dé peut se faire dans le constructeur
	}
	
	
	public int getId() {
		return id;
	}
	
	public void setId(int nb) {
		this.id = nb;
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

	public LinkedList<Integer> getTerritoire_voisin() {
		return territoire_voisin;
	}

	public void setTerritoire_voisin(LinkedList<Integer> territoire_voisin) {
		this.territoire_voisin = territoire_voisin;
	}
}
