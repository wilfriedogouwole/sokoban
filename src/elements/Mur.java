package elements;
import java.awt.Image;
import javax.swing.ImageIcon;
/**
 * La classe Mur represente les murs dans le jeu sokoban. 
 * Elle hérite de la classe element
 * 
 * @author FOSSET Renan, OGOUWOLE Derrick, TANDA Mu'Izz, d'ALMEIDA Bernold
 *
 */
public class Mur extends Element{
	public Image image;

    public Mur(int c, int l) {
        super(c, l);
        init();
    }
    
    public void init() {
        ImageIcon iicon = new ImageIcon("src/resource/mur.png");
        image = iicon.getImage();
        setImage(image);
    }
   
}
