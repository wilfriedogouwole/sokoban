package ia;
import sokoban.Etat;
/**
 * La classe Noeud represente les noeud de l'ia dans la recherche de la solution . 
 * 
 * 
 * @author FOSSET Renan, OGOUWOLE Derrick, TANDA Mu'Izz, d'ALMEIDA Bernold
 *
 */
public class Noeud {
	/**
	 * Noeud parent
	 */
	public Noeud parent;
	/**
	 * Etat du jeu a ce noeud
	 */
	public Etat etat;
	/**
	 * Le cout pour arriver a ce noeud
	 */
	public int cost;
	/**
	*Mouvement pour passer du noeud parent au noeud
	*/
	public String move;
	
	public Noeud(Etat et, Noeud parent,String move, int cout ) {
		this.etat = et;
		this.parent = parent;
		this.cost = cout;
		this.move = move;
	}
	@Override
	/**
     * Modification de la Methode equals pour pouvoir comparer des noeuds
     */
	public boolean equals(Object n) {
		return (this.etat == ((Noeud) n).etat);
	}

}
