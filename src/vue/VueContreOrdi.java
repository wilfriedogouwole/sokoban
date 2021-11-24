package vue;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import elements.*;
import ia.*;
import sokoban.*;
/**
 * La classe vueContreOrdi pour l'interface graphique quand le joueur humain joue contre l'ordinateur. 
 * Elle hérite de la classe vue
 * 
 * @author FOSSET Renan, OGOUWOLE Derrick, TANDA Mu'Izz, d'ALMEIDA Bernold
 *
 */
@SuppressWarnings("serial")
public class VueContreOrdi extends Vue {
	/**
	 * Etat de la partie pour l'ordinateur
	 */
	Etat etatIa;
	/**
	 * Classe mouvement pour les tests pour l'ordinateur
	 */
	Mouvement deplacementTestIa;
	
	public VueContreOrdi(String niv,VueSokoban vs) {
		super(niv,vs);
    	initMap();
    	addKeyListener(new Touche());
        setFocusable(true);
    }
	 public void initMap() {
    	 Traduction trad =new Traduction(this.levelNum);
    	 trad.initMap();
    	 this.murs = trad.murs;
    	 this.emplacement = trad.emplacement;
    	 this.boites = trad.boites;
    	 this.jHumain=trad.jHumain;
    	 this.etatEnCour=new Etat(boites, jHumain, emplacement);
    	 this.etatIa=etatEnCour.getCopy();
    	 this.deplacementTest =new Mouvement(murs,etatEnCour , emplacement);
    	 this.deplacementTestIa =new Mouvement(murs,etatIa , emplacement);
         this.larg=this.bordure+(this.espace*trad.larg)*2;
         this.haut = this.bordure+(this.espace*trad.haut);
         this.recherche=new Recherche(new Heuristic(emplacement),deplacementTestIa,"a");
         this.recherche.start();
         
     }
	 
	 public void mapGraph(Graphics g) {
	        g.setColor(new Color(91, 91, 91));
	        g.fillRect(0, 0, (this.getLargeur()+this.bordure)*2, this.getHauteur()+2*this.bordure);
	        ArrayList<Element> elem = new ArrayList<>();
	        elem.addAll(this.murs);
	        elem.addAll(this.emplacement);
	        elem.addAll(this.etatEnCour.boites);
	        elem.add(this.etatEnCour.joueur);
	        for (int i = 0; i < elem.size(); i++) {
	            Element el = elem.get(i);
	            int x=this.bordure+(this.espace*el.getCol());
	        	int y=this.bordure+(this.espace*el.getLigne());
	            if (el instanceof Joueur || el instanceof Caisse) {  
	            	g.drawImage(el.getImage(), x + 2, y + 2,this);
	            	} 
	            else {                
	                g.drawImage(el.getImage(), x, y,this);
	            	} 
	        	}
	        elem = new ArrayList<>();
	        elem.addAll(this.murs);
	        elem.addAll(this.emplacement);
	        elem.addAll(this.etatIa.boites);
	        elem.add(this.etatIa.joueur);
	        for (int i = 0; i < elem.size(); i++) {
	            Element el = elem.get(i);
	            int x=this.larg/2+this.bordure+(this.espace*el.getCol());
	        	int y=this.bordure+(this.espace*el.getLigne());
	            if (el instanceof Joueur || el instanceof Caisse) {  
	            	g.drawImage(el.getImage(), x + 2, y + 2,this);
	            	} 
	            else {                
	                g.drawImage(el.getImage(), x, y,this);
	            	} 
	        	}
	        
	        
	      
	      }
	
	 
	 @Override
	 /**
	     * Methode void qui dessine sur le jpanel
	     */
	    public void paintComponent(Graphics g) {
	        super.paintComponent(g);
	        if(this.deplacementTestIa!=null && this.deplacementTestIa.gagnerTest(this.etatIa)) {
	        	this.deplacementTestIa=null;
	        	this.vueJFrame.finSokoban("Vous avez perdu, l'Ia a gagner.");
	        	}
	        
	        else if(this.deplacementTest!=null && this.deplacementTest.gagnerTest(this.etatEnCour)) {
	        	this.deplacementTest=null;
	        	this.vueJFrame.finSokoban("Vous avez gagner.");
	        }
	        else {
	        	mapGraph(g);
	        }

	        
	    }
	 
	@SuppressWarnings("deprecation")
	/**
	 * Methode void pour faire jouer l'IA avec l'algorithme A*
	 */
	public void iaPlay() {
		 String choice;
		 if(this.recherche.isAlive()) {
			 this.recherche.stop();
			 choice = this.recherche.solution();
			 iaMoov(choice.charAt(0));
			 this.recherche=new Recherche(new Heuristic(this.emplacement),this.deplacementTestIa,"a");
			 this.recherche.start();
		 }
		 else {
			 choice = this.recherche.solution();
			 if(choice=="") {
				this.deplacementTest=null;
		        this.vueJFrame.finSokoban("L'Ia c'est perdu, vous avez gagner."); 
			 }
			 else {
				 iaMoov(choice.charAt(0));
			 }
	 	}
		 
	 }
	/**
	 * Methode void qui fait jouet l'ia en fonction du mouvement choisi par l'IA
	 * @param choice direction choisi par l'ordinateur
	 */
	 public void iaMoov(char choice) {
		 switch(choice) {
			case 'h':
				this.etatIa.play("haut", 0, -1);
				break;
			case 'b':
				this.etatIa.play("bas", 0, 1);
				break;
			case 'g':
				this.etatIa.play("gauche", -1, 0);
				break;
			case 'r':
				this.etatIa.play("droite", 1,0);
				break;
		}
	 }
	
	   
	    
	    
	    public class Touche implements KeyListener {
	    	

	    	@Override
	    	public void keyPressed(KeyEvent e) {
	    		
	    		int touche= e.getKeyCode();
	    		switch (touche) {
	            
	    		 case KeyEvent.VK_LEFT:
	             	if (deplacementTest.actions(etatEnCour).contains("g")) {
	             		etatEnCour.play("gauche",-1,0);
	             		iaPlay();
	             		
	             	}
	             	
	                 break;
	                 
	             case KeyEvent.VK_RIGHT:
	             	if (deplacementTest.actions(etatEnCour).contains("r")) {
	             		etatEnCour.play("droite",1,0);
	             		iaPlay();
	             		
	             	}
	             	break;
	                 
	             case KeyEvent.VK_UP:
	             	if (deplacementTest.actions(etatEnCour).contains("h")) {
	             		etatEnCour.play("haut",0,-1);
	             		iaPlay();
	             		
	             	}
	                 break;
	                 
	             case KeyEvent.VK_DOWN:
	             	if (deplacementTest.actions(etatEnCour).contains("b")) {
	             		etatEnCour.play("bas",0,1);
	             		iaPlay();
	             		
	             	}
	                 break;
	                 
	            case KeyEvent.VK_ENTER:
	            	reStart();
	            	break;
	            	
	            
	            default:
	                break;
	        }
	    		repaint();   		


	    	}
	    	 @Override
	     	public void keyReleased(KeyEvent arg0) {
	     		// TODO Auto-generated method stub

	     	}

	     	@Override
	     	public void keyTyped(KeyEvent arg0) {
	     		// TODO Auto-generated method stub

	     	}
	     	

}
	   
	    }