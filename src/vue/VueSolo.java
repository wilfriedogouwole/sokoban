package vue;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import elements.*;
import sokoban.*;

/**
 * La classe vueSolo pour l'interface graphique quand le joueur humain jour tout seul. 
 * Elle hérite de la classe vue
 * 
 * @author FOSSET Renan, OGOUWOLE Derrick, TANDA Mu'Izz, d'ALMEIDA Bernold
 *
 */
@SuppressWarnings("serial")
public class VueSolo extends Vue{
	
    public VueSolo(String niv,VueSokoban vs) {
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
    	 this.etatEnCour=new Etat(this.boites, this.jHumain, this.emplacement);
    	 this.deplacementTest =new Mouvement(this.murs,this.etatEnCour , this.emplacement);
         this.larg=this.bordure+(this.espace*trad.larg);
         this.haut = this.bordure+(this.espace*trad.haut);
         
     }
    public void mapGraph(Graphics g) {
        g.setColor(new Color(91, 91, 91));
        g.fillRect(0, 0, this.getLargeur()+this.bordure, this.getHauteur()+2*this.bordure);
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
        
      
      }
    
        
    
    @Override
    /**
     * Methode void qui dessine sur le jpanel
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(this.deplacementTest!=null && this.deplacementTest.gagnerTest(this.etatEnCour)) {
        	this.deplacementTest=null;
        	this.vueJFrame.finSokoban("Vous avez gagner.");
        }
        else {
        	mapGraph(g);
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
            		
            	}
            	
                break;
                
            case KeyEvent.VK_RIGHT:
            	if (deplacementTest.actions(etatEnCour).contains("r")) {
            		etatEnCour.play("droite",1,0);
            		
            	}
            	break;
                
            case KeyEvent.VK_UP:
            	if (deplacementTest.actions(etatEnCour).contains("h")) {
            		etatEnCour.play("haut",0,-1);
            		
            	}
                break;
                
            case KeyEvent.VK_DOWN:
            	if (deplacementTest.actions(etatEnCour).contains("b")) {
            		etatEnCour.play("bas",0,1);
            		
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