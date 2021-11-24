package elements;
import java.awt.Image;
import javax.swing.ImageIcon;
/**
 * La classe LieuStock represente le leir de rangement pour une boite dans le jeu sokoban. 
 * Elle hérite de la classe element
 * 
 * @author FOSSET Renan, OGOUWOLE Derrick, TANDA Mu'Izz, d'ALMEIDA Bernold
 *
 */
public class LieuStock extends Element{
	public Image image;

    public LieuStock(int c, int l) {
        super(c, l);
        init();
    }
    
    public void init() {
        ImageIcon iicon = new ImageIcon("src/resource/objectif.png");
        image = iicon.getImage();
        setImage(image);
    }
}