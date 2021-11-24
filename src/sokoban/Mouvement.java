package sokoban;
import java.util.ArrayList;
import elements.*;
/**
 * La classe mouvement permet de faire des test sur la partie. 
 * Est ce que la partie est bloquer, gagner et les mouvement posible
 * 
 * @author FOSSET Renan, OGOUWOLE Derrick, TANDA Mu'Izz, d'ALMEIDA Bernold
 *
 */
public class Mouvement {
	/**
	 * Conservation de l'etat avant les modification liée au test
	 */
	public Etat etatinitial;
	/**
	 * Liste des murs
	 */
	public ArrayList<Mur> murs;
	/**
	 * Liste des lieux de stockage
	 */
	public ArrayList<LieuStock> stock;
	
	public Mouvement(ArrayList<Mur> m, Etat etat, ArrayList<LieuStock> s) {
		this.etatinitial = etat;
		this.murs = m;
		this.stock = s;
	}

	/**
	 * Methode qui regarde si touts les boites sont ranger donc si la partie est finie
	 * @param etat Etat de la partie a tester
	 * @return vrai si c'est gagner sinon faux
	 */
	public boolean gagnerTest(Etat etat) {
		int nbPlace = 0;
    	for (int i=0; i<etat.boites.size();i++) {
    		for (int j=0; j<this.stock.size();j++) {
    			if (this.stock.get(j).getCol()==etat.boites.get(i).getCol()&& this.stock.get(j).getLigne()==etat.boites.get(i).getLigne()) {
    				nbPlace+=1;
    				
    			}
    			   			
    		}
    		
    	}
    	if (nbPlace==etat.boites.size()) {
    		return true;
    	}
    	
    	return false;
    }
	
	/**
	 * Methode qui permet de verifier dans un etat de partie une boites n'est pas bloquer et donc la partie serait perdu d'avance
	 * @param etat Etat de la partie a verifier
	 * @return Vrai si c'est perdu sinon faux
	 */
	public boolean deadlockTest(Etat etat) {
		for (Caisse box : etat.boites) {
			int col = box.getCol();
			int ligne = box.getLigne();
			if (!siBoitePlace(col, ligne)) {
				if (murSi( col-1, ligne)&&murSi( col, ligne-1)) {
					return true; //haut & gauche
				}
				if (murSi(col-1, ligne)&&murSi(col, ligne+1)) {
					return true; //bas & gauche
				}
				if (murSi(col+1, ligne)&&murSi( col, ligne-1)) {
					return true; //haut & droite
				}
				if (murSi( col+1, ligne)&&murSi( col, ligne+1)) {
					return true; //bas & droite
				}

				if (murSi(col-1, ligne-1)&&murSi( col-1, ligne)&&
						murSi( col-1, ligne+1)&&murSi( col, ligne-2)&&
						murSi(col, ligne+2)&&(!siBoitePlace( col, ligne-1))&&
								!siBoitePlace( col, ligne+1)) {
					return true; 
				}
				if (murSi(col+1, ligne-1)&&murSi(col+1, ligne)&&
						murSi( col+1, ligne+1)&&murSi( col, ligne-2)&&
						murSi( col, ligne+2)&&(!siBoitePlace( col, ligne-1))&&
								(!siBoitePlace( col, ligne+1))) {
					return true; 
				}
				if (murSi(col-1, ligne-1)&&murSi( col, ligne-1)&&
						murSi( col+1, ligne-1)&&murSi(col-2, ligne)&&
						murSi( col+2, ligne)&&(!siBoitePlace(col-1, ligne))&&
								(!siBoitePlace(col+1, ligne))) {
					return true; 
				}
				if (murSi(col-1, ligne+1)&&murSi( col, ligne+1)&&
						murSi(col+1, ligne+1)&&murSi(col-2, ligne)&&
						murSi( col+2, ligne)&&(!siBoitePlace( col-1, ligne))&&
								(!siBoitePlace(col+1, ligne))) {
					return true; 
				}
			}
		}
		return false;
	}
	/**
	 * Methode qui regarde les mouvements possible a cette etat de la partie
	 * @param etat Etat du jeu a chercher
	 * @return La liste des mouvement possible d, g, b, h
	 */
	public ArrayList<String> actions(Etat etat) {
		ArrayList<String> actionList = new ArrayList<String>();
		int col = etat.joueur.getCol();
		int ligne = etat.joueur.getLigne();
		ArrayList<Caisse> boxes = etat.boites;
		
		
		if (murSi(col,ligne-1)==false) {
			if (caisseSi(boxes,col,ligne-1)&&caisseSi(boxes,col,ligne-2)) {
				
			}
			else if (caisseSi(boxes,col,ligne-1)&&murSi(col,ligne-2)) {
				
			}
			
			else {
				actionList.add("h");
			}
		}
		
		if (murSi(col+1,ligne)==false) {
			if (caisseSi(boxes,col+1,ligne)&&(caisseSi(boxes,col+2,ligne))) {
				
			}
			else if (caisseSi(boxes,col+1,ligne)&&murSi(col+2,ligne)) {
				
			}
				
			else {
				actionList.add("r");
			}
		}
		
		if (murSi(col,ligne+1)==false) {
			if (caisseSi(boxes,col,ligne+1)&&(caisseSi(boxes,col,ligne+2))) {
				}
			else if (caisseSi(boxes,col,ligne+1)&&murSi(col,ligne+2)) {
				}	
			else {
				actionList.add("b");
			}
		}
		
		if (murSi(col-1,ligne)==false) {
			if (caisseSi(boxes,col-1,ligne)&&(caisseSi(boxes,col-2,ligne))) {
				
			}
			else if (caisseSi(boxes,col-1,ligne)&&murSi(col-2,ligne)) {
				
			}
			else {
				actionList.add("g");
			}
		}
		return actionList;
	}

	/**
	 * Regarde si un lieu de stockage se trouve a ces coordonner	
	 * @param col coordonner de la colonne
	 * @param ligne coordonner de la ligne
	 * @return vrai si il ya a un lieu de stockage sinon faux
	 */
	private boolean siBoitePlace( int col, int ligne) {
		for (int i=0; i<this.stock.size();i++) {
    		if (this.stock.get(i).getCol()==col && this.stock.get(i).getLigne()==ligne) {
    			return true;
    		}
    	}
    	return false;
	}
	/**
	 * Regarde si un mur se trouve a ces coordonner	
	 * @param col coordonner de la colonne
	 * @param ligne coordonner de la ligne
	 * @return vrai si il ya a un mur sinon faux
	 */
	 public boolean murSi(int col, int ligne){
	    	for (int i=0; i<this.murs.size();i++) {
	    		if (this.murs.get(i).getCol()==col && this.murs.get(i).getLigne()==ligne) {
	    			return true;
	    		}
	    	}
	    	return false;
	    }
	 /**
		 * Regarde si une boite se trouve a ces coordonner	
		 * @param col coordonner de la colonne
		 * @param ligne coordonner de la ligne
		 * @param boites liste des caisses
		 * @return vrai si il ya a une boite sinon faux
		 */
	 public boolean caisseSi(ArrayList<Caisse> boites,int col, int ligne){
	    	for (int i=0; i<boites.size();i++) {
	    		if (boites.get(i).getCol()==col && boites.get(i).getLigne()==ligne) {
	    			return true;
	    		}
	    	}
	    	return false;
	    }
	
	 

}
