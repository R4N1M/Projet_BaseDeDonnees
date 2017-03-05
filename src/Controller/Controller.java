package Controller;

import Vue.FormFrame;

public class Controller {

	private FormFrame principalFrame;
	
	private Controller(){
		principalFrame = new FormFrame();
	}
	
	
	public static void main(String[] args){
		Controller c = new Controller();
	}
	
}
