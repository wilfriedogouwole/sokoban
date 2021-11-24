package elements;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.util.ArrayList;
/**
 * La classe caisse represente les boites du jeu sokoban. 
 * Elle hérite de la classe element
 * 
 * @author FOSSET Renan, OGOUWOLE Derrick, TANDA Mu'Izz, d'ALMEIDA Bernold
 *
 */
public class Caisse extends Element{
	public Caisse(int c, int l) {
        super(c, l);
        init();
    }
    
    public void init() {
        ImageIcon iicon = new ImageIcon("src/resource/caisse.png");
        Image image = iicon.getImage();
        setImage(image);
    }
    /**
     * Methode voide qui change l'image quand la boite est sur un emplacement de rangement
     * @param lieu Liste de tout les lieu de stockage de la partie
     */
    public void changerBoite(ArrayList<LieuStock> lieu) {
    	ImageIcon iicon = new ImageIcon("src/resource/caisse.png");
    	for(int i =0;i<lieu.size();i++) {
    		if (lieu.get(i).getCol()==this.col && lieu.get(i).getLigne()==this.ligne) {
    			iicon = new ImageIcon("src/resource/caisseOk.png");
    		}
    	}
    	Image image = iicon.getImage();
        setImage(image);
    }
}
