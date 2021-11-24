package ia;
import java.util.ArrayList;
import elements.*;
import sokoban.*;
/**
 * La classe Heuristic calcul l'heuristic pour l'algorithme A*
 * 
 * @author FOSSET Renan, OGOUWOLE Derrick, TANDA Mu'Izz, d'ALMEIDA Bernold
 *
 */
public class Heuristic {
	/**
	 * Represente la liste des lieux de stockage 
	 */
	private ArrayList<LieuStock> goals;
	
	public Heuristic(ArrayList<LieuStock> goals) {
		this.goals = goals;
		}
	
	/**
	 * Methode privée qui calcule la distance de Manhattan entre deux Element
	 * @param c1 Element de depart
	 * @param c2 Element d'arrivée
	 * @return Entier qui represente la distance entre les deux Element
	 */
	private int manhattan(Element c1, Element c2) {
		return Math.abs(c1.getCol()-c2.getCol()) + Math.abs(c1.getLigne()-c2.getLigne());
	}
	/**
	 * Methode qui calcule l'heuristic pour l'algorithme A* avec la distance de Manhattan
	 * @param s L'etat du jeu ou on calcule l'heuristic pour gagner
	 * @return L'heuristic
	 */
	public double calculate(Etat s) {
		ArrayList<Caisse> boxes = s.boites;
		double sum = 0;
		Joueur player = s.joueur;
		double playerMin = getDistB((Element)player, boxes);
		sum+= playerMin;
		for (Caisse b : boxes) {
			double boxMin = getDistG((Element)b, this.goals);
			sum += boxMin;
		}
		return sum;
	}
	
	/**
	 * Methode qui retourne la distance minimum entre le joueur et une des caisses
	 * @param obj Le joueur
	 * @param sets La liste des caisses
	 * @return La distance minimum
	 */
	private double getDistB(Element obj, ArrayList<Caisse> sets) {
		double minDist = 1000000;
		for (Element c : sets) {
			double dist;
			dist = manhattan(obj, c);			
			if (dist < minDist)
				minDist = dist;
		}
		return minDist;
	}
	/**
	 * Methode qui retourne la distance minimum entre une caisse et un leu de stockage
	 * @param obj La caisse
	 * @param sets La liste des lieux de stockage
	 * @return La distance minimum
	 */
	private double getDistG(Element obj, ArrayList<LieuStock> sets) {
		double minDist = 1000000;
		for (Element c : sets) {
			double dist;
			dist = manhattan(obj, c);
			if (dist < minDist)
				minDist = dist;
		}
		
		return minDist;
	}
	/**
	 * Methode getter pour recuperer et lancer l'heuristic
	 * @param etat Etat du jeu a calculer
	 * @return l'heuristic
	 */
	public double getHeuristic(Etat etat) {
		return calculate(etat);
		
	}

}
