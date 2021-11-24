package vue;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;
import elements.*;
import ia.*;
import sokoban.*;
/**
 * La classe abstraite vue ou se trouve des methode pour l'interface graphique. 
 * Elle hérite de la classe JPanel
 * 
 * @author FOSSET Renan, OGOUWOLE Derrick, TANDA Mu'Izz, d'ALMEIDA Bernold
 *
 */
@SuppressWarnings("serial")
public abstract class Vue extends JPanel{
	/**
	 * Represente la taille de la bordure
	 */
	public int bordure = 50;
	/**
	 * represente la taille des espaces de la map
	 */
    public int espace = 30;
    /**
     * Numero du niveau choisi
     */
    public String levelNum;
    /**
     * Largeur de la map
     */
    public int larg = 0;
    /**
     * hauteur de la map
     */
    public int haut = 0;
    /**
     * liste des murs
     */
    public ArrayList<Mur> murs;
    /**
     * liste des lieux de rangement
     */
    public ArrayList<LieuStock> emplacement;
    /**
     * liste des boites
     */
    public ArrayList<Caisse> boites;
    /**
     * joueur 
     */
    public Joueur jHumain;
    /**
     * La classe recherche pour l'ia
     */
    public Recherche recherche;
    /**
     * Etat de la partie 
     */
    public Etat etatEnCour;
    /**
     * La classe mouvement pour les tests
     */
    public Mouvement deplacementTest;
    /**
     * La jframe 
     */
    public VueSokoban vueJFrame;
    
    
    public Vue(String niv,VueSokoban vs) {
    	this.levelNum=niv;
    	this.vueJFrame=vs;
    }
    
    /**
     * Methode getter pour la largeur
     * @return la largeur
     */
    public int getLargeur() {
    	return this.larg;
    } 
    /**
     * Methode getter pour la hauteur
     * @return la hauteur
     */
    public int getHauteur() {
    	return this.haut;
    }
    
    /**
     * Methode void pour recommencer la partie
     */
    public void reStart() {
    	initMap();
    }
    /**
     * Methode qui initialise la partie
     */
    abstract void initMap();
    /**
     * Methode qui dessine la map
     * @param g le graphic pour dessiner
     */
    abstract void mapGraph(Graphics g);
    
}
