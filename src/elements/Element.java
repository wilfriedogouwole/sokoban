package elements;
import java.awt.Image;
/**
 * La classe abstraite element represent tout les elements d'une map de Sokoban.
 * Elle permet aussi de stocker les coordonner de ces elements.
 * @author FOSSET Renan, OGOUWOLE Derrick, TANDA Mu'Izz, d'ALMEIDA Bernold
 *
 */
abstract public class  Element {
	/**
	 * Coordonner de l'element au niveau des colonnes
	 */
	public int col;
	/**
	 * Coordonner de l'element au niveau des lignes
	 */
    public int ligne;
    /**
	 * Image de l'element pour l'interface graphique
	 */
    public Image image;

    public Element(int c, int l) {
        this.col = c;
        this.ligne = l;
    }
    /**
     * Methode getter pour l'image
     * @return retourne l'image
     */
    public Image getImage() {
        return this.image;
    }
    /**
     * Methode setter pour modifier l'image
     * @param Nouvelle image
     */
    public void setImage(Image img) {
        this.image = img;
    }
    /**
     * Methode getter pour la coordonne colonne
     * @return Le numero de la colonne
     */
    public int getCol() {
       return this.col;
    }
    /**
     * Methode getter pour la coordonne ligne
     * @return Le numero de la ligne
     */
    public int getLigne() {
        return this.ligne;
    }
    /**
     * Methode setter pour modifier la coordonne colonne
     * @return Le numero de la nouvelle colonne
     */
    public void setCol(int c) {
        this.col = c;
    }
    /**
     * Methode setter pour modifier la coordonne ligne
     * @return Le numero de la nouvelle ligne
     */
    public void setLigne(int l) {
        this.ligne = l;
    }
    /**
     * Methode abstraite void qui initialise l'image de l'element pour l'interface graphique
     */
    abstract void init();
    @Override
    /**
     * Modification de la Methode hashCode pour pouvoir comparer
     */
	public int hashCode() {
		return this.col*1000 + this.ligne;
	}
    /**
     * Modification de la Methode equals pour pouvoir comparer des elements
     */

    public boolean equals(Object object){
		if (object == null) return false;
	    if (object == this) return true;
	    if (this.getClass() != object.getClass()) return false;
		Element c = (Element) object;
		if(this.hashCode()== c.hashCode()) return true;
		return ((this.col == c.col) && (this.ligne == c.ligne));
	}

  

}
