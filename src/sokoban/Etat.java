package sokoban;
import java.util.ArrayList;
import elements.*;
/**
 * La classe Etat represente l'etat du jeu sokoban. 
 * 
 * 
 * @author FOSSET Renan, OGOUWOLE Derrick, TANDA Mu'Izz, d'ALMEIDA Bernold
 *
 */
public class Etat {
	/**
	 * Liste des caisses
	 */
	public ArrayList<Caisse> boites;
	/**
	 * Le joueru de cette partie
	 */
	public Joueur joueur;
	/**
	 * liste des lieux de rangement
	 */
	public ArrayList<LieuStock> emplacement;
	
	public Etat(ArrayList<Caisse> boxes, Joueur player,ArrayList<LieuStock> ep) {
		this.boites = boxes;
		this.joueur = player;
		this.emplacement=ep;
		
	}
	/**
	 * Methode qui permet de jouet et fait les changement de coordonner pour le joueur et la boite si besoin
	 * @param direction La direction du cout
	 * @param moovX nombre de collonne a se decaler
	 * @param moovYnombre de ligne a se decaler
	 */
	 public void play(String direction,int moovX, int moovY) {
		 this.joueur.setCol(this.joueur.getCol()+moovX);
		 this.joueur.setLigne(this.joueur.getLigne()+moovY);
		 this.joueur.changeImage(direction);
	    	Caisse siBoitePresent= caisseSiChangement(this.joueur.getCol(),this.joueur.getLigne());
	    	if (siBoitePresent!=null) {
	    		siBoitePresent.setCol(siBoitePresent.getCol()+moovX);
	    		siBoitePresent.setLigne(siBoitePresent.getLigne()+moovY);
	    		siBoitePresent.changerBoite(this.emplacement);
	    	}
	    	
	    }
	 /**
	  * Methode qui permet de faire la copie de la partie au moment sans aucun lien
	  * @return La copie de l'etat du jeu
	  */
	 public Etat getCopy() {
		 ArrayList<Caisse> boiteBis=new ArrayList<Caisse>();
		 Caisse boite;
		 for (int i=0;i<this.boites.size();i++) {
			 boite=new Caisse(this.boites.get(i).getCol(),this.boites.get(i).getLigne());
			 boite.changerBoite(emplacement);
			 boiteBis.add(boite);
		 }
		 Joueur joueurBis= new Joueur(this.joueur.getCol(),this.joueur.getLigne());
		 return new Etat(boiteBis,joueurBis,this.emplacement);
	 }
	 /**
	  * Methode qui retourn la caisse si il y a au coordonner sinon retourne null.
	  * @param x Coordonner de la colonne
	  * @param y Coordonner de la ligne
	  * @return La caisse si il y a
	  */
	 public Caisse caisseSiChangement(int x, int y){
	    	for (int i=0; i<this.boites.size();i++) {
	    		if (this.boites.get(i).getCol()==x && this.boites.get(i).getLigne()==y) {
	    			return this.boites.get(i);
	    		}
	    	}
	    	return null;
	    }
	
	@Override
	/**
     * Modification de la Methode hashCode pour pouvoir comparer
     */
	public int hashCode() {
		int result = 17;
		for (Caisse b : this.boites) {
			result = 37 * result + b.hashCode();
		}
		result = 37 * result + this.joueur.hashCode();
		return result;
	}
	
	@Override
	/**
     * Modification de la Methode equals pour pouvoir comparer des elements
     */

	public boolean equals(Object object){
		
	    if (object == null) return false;
	    if (object == this) return true;
	    if (this.getClass() != object.getClass()) return false;
	    Etat s = (Etat)object;
	    if(this.hashCode()== s.hashCode()) return true;
	    if((this.boites == s.boites) && (this.joueur == s.joueur)) return true;
	    return false;
	}

}
