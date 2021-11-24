package sokoban;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import elements.*;
/**
 * La classe traduction traduit un fichier xsb en partie de sokoban,. 
 * 
 * 
 * @author FOSSET Renan, OGOUWOLE Derrick, TANDA Mu'Izz, d'ALMEIDA Bernold
 *
 */
public class Traduction {
	/**
	 * Numero du niveau choisi
	 */
	public String level;
	/**
	 * Niveau traduit en string
	 */
	public String levelTrad;
	/**
	 * largeur du niveau
	 */
    public int larg = 0;
    /**
     * hauteur du niveau
     */
	public int haut = 0;
	/**
	 * liste des murs
	 */
	public ArrayList<Mur> murs;
	/**
	 * liste des lieux de stockage
	 */
	public ArrayList<LieuStock> emplacement;
	/**
	 * liste des boites
	 */
	public ArrayList<Caisse> boites;
	/**
	 * Joueur 
	 */
	public Joueur jHumain;
	
	
	public Traduction(String l) {
		this.level=l;
		File file = new File("src/resource/niveau/"+this.level+".xsb");
	    FileReader fr;
			
	    try {
	      				
	      //Création de l'objet de lecture
	      fr = new FileReader(file);
	      this.levelTrad = "";
	      int i = 0;
	      //Lecture des données
	      while((i = fr.read()) != -1)
	    	  levelTrad += (char)i;

	      
	    } catch (FileNotFoundException e) {
	      e.printStackTrace();
	    } catch (IOException e) {
	      e.printStackTrace();
	    }
	  }
	/**
	 * Methode void qui initialise le niveau de, string en partie jouable
	 */
	public void initMap() {
		this.murs = new ArrayList<>();
		this.emplacement = new ArrayList<>();
		this.boites = new ArrayList<>();
        int ligne = 0;
        int col = 0;
        Mur mur;
        Caisse boite;
        LieuStock stock;
        for (int i = 0; i < this.levelTrad.length(); i++) {
            char item = this.levelTrad.charAt(i);
            switch (item) {
                case '\n':
                    ligne += 1;
                    if (this.larg < col ){
                        this.larg = col;
                    }
                    col = 0;
                    break;
                case '#':
                    mur = new Mur(col, ligne);
                    this.murs.add(mur);
                    col += 1;
                    break;
                case '$':
                    boite = new Caisse(col, ligne);
                    this.boites.add(boite);
                    col += 1;
                    break;
                case '.':
                    stock = new LieuStock(col,ligne);
                    this.emplacement.add(stock);
                    col += 1;
                    break;
                case '@':
                	this.jHumain = new Joueur(col, ligne);
               	 col += 1;
                    break;
                case '+':
               	 stock = new LieuStock(col, ligne);
               	this.emplacement.add(stock);
               	this.jHumain = new Joueur(col, ligne);
               	 col += 1;
                    break;
                case '*':
                    stock = new LieuStock(col, ligne);
                    this.emplacement.add(stock);
                    boite = new Caisse(col, ligne);
                    this.boites.add(boite);
                    boite.changerBoite(emplacement);
                    col += 1;
                    break;
                case ' ':
               	 col += 1;
                    break;
                default:
                    break;
            }
            this.haut = ligne;
        }
    }
	}
	
	


