package Modele;
import java.util.ArrayList;

public class Poisson {

	// paramètre lambda de la loi de poisson
	private double lamda;

	// temps de la simulation
	private int duree;

	public Poisson(double lamda, int d)
	{
		this.lamda=lamda;
		duree = d;
	}

	public double exponentiel(double y)
	{
		if (y==1) y=0.99;
		return (-Math.log(1-y)/lamda*10);
	}

	public ArrayList<Integer> generate()
	{
		ArrayList<Integer> colonne = new ArrayList<Integer>();
		double y = Math.random();
		colonne.add( (int) exponentiel(y));
		int i=0;
		while (colonne.get(i)<1000)
		{
			i++;
			colonne.add( (int) (colonne.get(i-1)+exponentiel(Math.random())) );
		}
		return colonne;
	}


}
