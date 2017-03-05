package Controller;

import Vue.FormFrame;
import Modele.BaseDeDonnees;

public class Controller {

	private FormFrame principalFrame;
	private BaseDeDonnees bdd;
	
	private Controller(){
		principalFrame = new FormFrame();
	}
	
	
	
	public static void main(String[] args){
		Controller c = new Controller();
	}
}
