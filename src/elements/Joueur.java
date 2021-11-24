package elements;
import java.awt.Image;
import javax.swing.ImageIcon;
/**
 * La classe Joueur represente le joueur du jeu sokoban. 
 * Elle hérite de la classe element
 * 
 * @author FOSSET Renan, OGOUWOLE Derrick, TANDA Mu'Izz, d'ALMEIDA Bernold
 *
 */
public class Joueur extends Element {

    public Joueur(int c, int l) {
        super(c, l);
        init();
    }

    public void init() {
ImageIcon iicon = new ImageIcon("src/resource/joueur/bas.png");
        Image image = iicon.getImage();
        setImage(image);
       }
    
    public void changeImage(String im) {
    	ImageIcon iicon = new ImageIcon("src/resource/joueur/"+im+".png");
        Image image = iicon.getImage();
        setImage(image);
    }
 
}