package vue;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
/**
 * La classe VueSokoban represente les boites du jeu sokobanpour l'interface graphique au niveau de la jframe. 
 * Elle hérite de la classe JFrame
 * 
 * @author FOSSET Renan, OGOUWOLE Derrick, TANDA Mu'Izz, d'ALMEIDA Bernold
 *
 */
@SuppressWarnings("serial")
public class VueSokoban extends JFrame {
	public String modeJeu;
	public String nivJeu;
	public String[] mode = {"solo", "contre l'ordi"};
	public String[] niv = {"niv1", "niv2","niv3","niv4","niv5","niv6","niv7","niv8"};
	public Vue partie;
	
	public VueSokoban(){
		initSokoban();
	}
	/**
	 * Methode voide qui demande au joueur si il veut jouet totu seul ou contre l'ordinateur et sur quel niveau	
	 */
	public void initSokoban() {
		this.partie=null;
		new JOptionPane();
		this.modeJeu= (String)JOptionPane.showInputDialog(null,"Veuillez indiquer le mode de jeu","Sokoban mode",JOptionPane.QUESTION_MESSAGE,
				null, this.mode, this.mode[0]);
		this.nivJeu= (String)JOptionPane.showInputDialog(null,"Veuillez indiquer le niveau du jeu","Sokoban niveau",JOptionPane.QUESTION_MESSAGE,
				null, this.niv, this.niv[0]);
		if(modeJeu=="solo") {
			this.partie = new VueSolo(nivJeu,this);
			this.setSize((this.partie.getLargeur() + this.partie.bordure),
					this.partie.getHauteur() + 2 * this.partie.bordure);
		}
		else {
			partie = new VueContreOrdi(nivJeu,this);
			this.setSize((this.partie.getLargeur() + this.partie.bordure),
					this.partie.getHauteur() + 2 * this.partie.bordure);
		}
		this.add(this.partie);
		this.setTitle("Sokoban");
		this.setResizable(false);
		JMenuBar menu = new JMenuBar();
		JMenuItem aide = new JMenuItem("Aide");
		menu.add(aide);
		setJMenuBar(menu);
    
		addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent evt) {
				exitForm(evt);
			}
		}); 
		aide.addActionListener(new ActionListener(){    
			public void actionPerformed(ActionEvent e){
					try {
						Desktop desktop = Desktop.getDesktop();    
						desktop.open(new File("aide/help.pdf") );  
					} catch (IOException e1) {
						e1.printStackTrace();
					}
			}
	    });
		setLocationRelativeTo(null);
		this.setVisible(true);
	

	}
	/**
	 * Methode voide de fin de partie qui dit au joueur si il a gagner et si il veut rejouer
	 * @param resultat Resultat de la partie jouet
	 */
	public void finSokoban(String resultat) {
		this.setVisible(false);
		this.remove(this.partie);
		this.partie.boites=null;
		int option = JOptionPane.showConfirmDialog(null, resultat+"  Voulez-vous rejouer ?", "Sokoban", 
		JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if(option == JOptionPane.OK_OPTION) {
			initSokoban();
			
		}
		else {
			System.exit(0);
		}
		
		
		
	}
	
	private void exitForm(java.awt.event.WindowEvent evt) {
		System.exit(0);
	}

}
