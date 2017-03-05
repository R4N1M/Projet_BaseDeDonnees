package Controller;

import Vue.FormFrame;
import Modele.BaseDeDonnees;

public class Controller {

	private FormFrame principalFrame;
	private BaseDeDonnees bdd;
	
	private final double TAUX_ALEA = 20.0;
	
	private Controller(){
		principalFrame = new FormFrame(this);
	}
	
	public void createDataBase(	int nbr_dtr, int min_v, int max_v, int nbr_dc, int min_nbr_op, 
								int max_nbr_op, int duree_s, int lp, int tlc, int tec, int tlr, 
								int ter
								){
		bdd = new BaseDeDonnees(nbr_dtr, min_v, max_v, nbr_dc, min_nbr_op, max_nbr_op, duree_s, lp, tlc, tec, tlr, ter, TAUX_ALEA);
	}
	
	public static void main(String[] args){
		Controller c = new Controller();
	}
}
