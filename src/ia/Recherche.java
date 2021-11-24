package ia;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Queue;
import java.util.PriorityQueue;
import java.util.Set;
import sokoban.*;
/**
 * La classe Recherche represente l'algorithme A*, l'IA. 
 * Elle herite de thread pour crée une autre pile d'execution
 *  
 * @author FOSSET Renan, OGOUWOLE Derrick, TANDA Mu'Izz, d'ALMEIDA Bernold
 *
 */
public class Recherche extends Thread {
	/**
	 * La classe heuristic pour calculer la distance avec Manhattan
	 */
	public static Heuristic h;
	/**
	 * la classe mouvement pour les tests sur l'avancement du jeu
	 */
	public Mouvement mouvementP;
	/**
	 * Noeud appronfondi en ce moment
	 */
	public Noeud noeudEnCour;
	/**
	 * Noueud qui mene a la victoire
	 */
	public Noeud noeudGagnant=null;
	
	
	@SuppressWarnings("static-access")
	public Recherche(Heuristic heu,Mouvement p,String c) {
		this.h=heu;
		this.mouvementP=p;
		}
	/**
	 * Methode qui retourne le movement a faire
	 * @return La direction b, g, h, d
	 */
	public String solution() {
		if(this.noeudGagnant!=null) {
			return getSolution(this.noeudGagnant);
		}
		else if(this.noeudGagnant==null && this.noeudEnCour==null) {
			return "";
		}
		else {
			return getSolution(this.noeudEnCour);
		}
	}
	/**
	 * Methode qui lance la nouveau thread
	 */
	public void run() {
		this.noeudGagnant=algo();
		}
	/**
	 * Methode qui cherche le meilleur chemine pour gagner
	 * @return La solution, le noeud gagnant
	 */
	public Noeud algo() {
		Noeud initial = new Noeud(this.mouvementP.etatinitial, null, "", 0);
		Set<Etat> explored = new HashSet<Etat>();
		Queue<Noeud> queuePrio=new PriorityQueue<Noeud>(11, aetoile);
		queuePrio = new PriorityQueue<Noeud>(11, aetoile);
		queuePrio.add(initial);
		while (!queuePrio.isEmpty()) {
			Noeud n = queuePrio.remove();
			noeudEnCour=n;
			if (this.mouvementP.gagnerTest(n.etat)) {
				return n;
			}
			if (!this.mouvementP.deadlockTest(n.etat)) { 
				explored.add(n.etat);
				ArrayList<String> actions = this.mouvementP.actions(n.etat);
				for (int i=0; i<actions.size(); i++) {
					Noeud child = getChild( n, actions.get(i));
					if((child!=null) && (child.etat!=null)) {
						if ((!explored.contains(child.etat))&&(!queuePrio.contains(child))) {
							queuePrio.add(child);
								
						}
						else {
							for (Noeud next : queuePrio) {
								if (next == child) {
									if(child.cost < next.cost) {
										next = child;
									}
								}
							}
						}
					}
				}
			}
			
		}
		this.noeudEnCour=null;
		return null;
	}
	
	/**
	 * Methode pour avoir le movement a faire
	 * @param n Le meilleur noeud du moment ou le noeud gagnant
	 * @return la direction a prendre
	 */
	public String getSolution( Noeud n) {
		String result ="";
		while (n.parent.parent!=null) {
			n = n.parent;
			}
		result =  n.move  ;
		n.parent=null;
		return result;
	}
	
	/**
	 * Methode qui crée un nouveau noeud avec un mouvement
	 * @param n Le noeud parent
	 * @param action Le mouvement a faire pour obtenier le nouveau noeud
	 * @return le nouveau noeud crée
	 */
	private Noeud getChild( Noeud n, String action) {
		Etat nouvelleEtat=n.etat.getCopy();
		int newCost = n.cost+1;
		char choice = action.charAt(0);
		switch(choice) {
			case 'h':
				nouvelleEtat.play("haut", 0, -1);
				break;
			case 'b':
				nouvelleEtat.play("bas", 0, 1);
				break;
			case 'g':
				nouvelleEtat.play("gauche", -1, 0);
				break;
			case 'r':
				nouvelleEtat.play("droite", 1,0);
				break;
		}
		return new Noeud(nouvelleEtat, n,  Character.toString(choice),newCost);
	}
	
	/**
	 * Methode pour le trie de sortie de la pile avec A* et la distance de heuristic
	 */
	public static Comparator<Noeud> aetoile = new Comparator<Noeud>() {
		@Override
		public int compare(Noeud n1, Noeud n2) {
            return (int) ((n1.cost + h.getHeuristic(n1.etat)) - (n2.cost + h.getHeuristic(n2.etat)));
        }
	};

}
