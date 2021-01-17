package Jeu;
import java.util.*;

public class Joueur {

	private static int nbjoueurs = 0;
	private int id;
	private ArrayList<Territoire> liste_territoire; //territoire poss�d�

	public Joueur() {
		nbjoueurs++;
		this.id = nbjoueurs;
		this.liste_territoire = new ArrayList<Territoire>();
	}

	public void attaqueTerritoire() throws ProbleemeException{ //Red�finir equals pour comparer

		int getattacked, isattacking, attaquant = 50, attaque = 50;
		boolean in = false;
		System.out.println("Territoire attaquant et territoire attaqu�? S�par� d'un espace\n");
		Scanner sc = new Scanner(System.in);
		isattacking = sc.nextInt();
		getattacked = sc.nextInt();
		if(isattacking == 0 || getattacked == 0) {
			throw new ProbleemeException("Erreur de saisie\n");
		}

		//Chercher les lieux des territoires
		for(int i=0;i<this.getListe_territoire().size();i++) {//Va me servir � rep�rer quel territoire attaque et quel territoire se fait attaquer
			if(this.getListe_territoire().get(i).getId() == isattacking) {
				attaquant = i;
			}
		}
		if(attaquant == 50) {
			throw new ProbleemeException("Tu n'as pas ce territoire\n");
		}
		for(int i=0;i<this.getListe_territoire().get(attaquant).getTerritoire_voisin().size();i++){
			if(this.getListe_territoire().get(attaquant).getTerritoire_voisin().get(i).getId() == getattacked){
				attaque = i;
			}
		}
		if(attaque == 50) {
			throw new ProbleemeException("Les territoires ne sont pas voisins, ou erreur de saisie\n");
		}
		//


		if(isattacking == getattacked){ //S'il attaque son propre territoire
			throw new ProbleemeException("Vous attaquez le m�me territoire !\n");
		}

		else if(this.getListe_territoire().get(attaquant).getForce() == 1) { //Si le territoire a un seul d�
			throw new ProbleemeException("Ce territoire n'a pas assez de force\n");
		}
		else if(this.getListe_territoire().get(attaquant).getIdjoueur() == this.getListe_territoire().get(attaquant).getTerritoire_voisin().get(attaque).getIdjoueur()){
			throw new ProbleemeException("Vous attaquez votre propre territoire !\n");
		}
		isattacking = this.getListe_territoire().get(attaquant).CalculAleaDe();
		getattacked = this.getListe_territoire().get(attaquant).getTerritoire_voisin().get(attaque).CalculAleaDe();
		System.out.println("Nombre de l'attaquant: "+isattacking);
		System.out.println("Nombre de l'attaqu�: "+getattacked);
		if(isattacking > getattacked) {
			System.out.println("Le joueur "+this.id+" l'emporte");
			int force_attaquant = getListe_territoire().get(attaquant).getForce(); //Force du territoire attaquant
			this.getListe_territoire().get(attaquant).getTerritoire_voisin().get(attaque).setForce(force_attaquant-1); //On la met - 1 dans le territoire perdant
			this.getListe_territoire().get(attaquant).setForce(1); // On laisse seulement 1 d� sur le territoire gagnant
			this.getListe_territoire().get(attaquant).getTerritoire_voisin().get(attaque).setIdjoueur(getListe_territoire().get(attaquant).getIdjoueur()); //On change l'id du territoire
			this.getListe_territoire().add(this.getListe_territoire().get(attaquant).getTerritoire_voisin().get(attaque));//On ajoute � sa liste les territoires gagnants
		}
		else { //Le territoire attaquant a perdu
			System.out.println("Le joueur "+this.id+" perd");
			this.getListe_territoire().get(attaquant).setForce(1);
		}
	}

	public boolean Gagner(ArrayList<ArrayList<Territoire>> territoire) {
		int nbdeterritoire = territoire.size() * 5;
		int compteur = 0;
		for(int i=0;i<territoire.size();i++) {
			for(int j=0;j<5;j++) {
				if(territoire.get(i).get(j).getIdjoueur() == this.id) {
					compteur++;
				}
			}
		}
		if(compteur == nbdeterritoire) {
			return true;
		}else {
			return false;
		}
	}

	public boolean terminerTour(ArrayList<Territoire> territoire) {
		if(this.CompterLaForce() > 1) { //S'il a encore des territoires
			this.Contigus();
			return false;
		}else {
			return false;
		}
	}

	public ArrayList<Territoire> getListe_territoire() {
		return liste_territoire;
	}

	public void setListe_territoire(ArrayList<Territoire> liste_territoire) {
		this.liste_territoire = liste_territoire;
	}

	public int CompterLaForce() {
		int somme_de = 0;
		for(int i=0;i<getListe_territoire().size();i++) {
			somme_de = somme_de + getListe_territoire().get(i).getForce();
		}
		return somme_de;
	}

	public void PrintTerritoirePossede() {
		System.out.print("Liste des territoires du joueur "+this.id+" : ");
		for(int i=0;i<getListe_territoire().size();i++) {
			System.out.print("["+getListe_territoire().get(i).getId()+"]" + " ");
		}
		System.out.println("\n");
	}

	public void PrintTerritoireVoisin() {
		System.out.print("Liste des territoires voisin du joueur "+this.id+" : \n");
		for(Territoire t : getListe_territoire()) {
			t.PrintTerritoireVoisin(); //Fonction de la classe Territoire
			System.out.println();
		}
	}

	public void Contigus() {
		int compteur = 0;
		int terralea = 0;
		int stock = 0;
		Random rand = new Random();
		for(int i=0;i<this.getListe_territoire().size();i++) {//Liste de territoire poss�d� par le joueur
			int contigus = 0;
			for(int j=0;j<this.getListe_territoire().get(i).getTerritoire_voisin().size();j++)//Liste des territoires voisins de chaque territoire du joueur
				if(this.getListe_territoire().get(i).getTerritoire_voisin().get(j).getIdjoueur() == this.id) { //Si je trouve l'id du joueur parmis ses territoires voisins, donc ils sont contigus
					contigus++;
				}
			if(contigus >= stock) {
				stock = contigus;
			}else {
				continue;
			}
		}

		int contigus = stock;
		while(compteur < contigus){
			terralea = 1 + rand.nextInt(this.getListe_territoire().size() - 2);
			if(this.getListe_territoire().get(terralea).getForce() < 8) {
				this.getListe_territoire().get(terralea).setForce(this.getListe_territoire().get(terralea).getForce()+1); //Sur un territoire al�atoire, un nombre de d� al�atoire compris entre 1 et 8
				System.out.println("Le territoire "+this.getListe_territoire().get(terralea).getId()+" a re�u un bonus !\n");
				compteur++;
			}
		}
	}

	public int getId() {
		return id;
	}
}
