package Modele;
import java.util.ArrayList;

public class Transaction{
  private int temps_arrivee;
  private int temps_echeance;
  private ArrayList<Operation> operations;
  private int operationCourante;

  public Transaction(int a, int e, ArrayList<Operation> o){
    temps_arrivee = a;
    temps_echeance = e;
    operations = o;
    operationCourante = 0;
  }

  public int getArrivee() {
    return temps_arrivee;
  }

  public ArrayList<Operation> getOperations() {
    return operations;
  }

  public int getEcheance() {
    return temps_echeance;
  }

  public boolean[] avancerTemps(int tempsSimulation) {
    boolean termine = false;
    boolean deMisAJour = false;
    boolean success = false;

    // Detecter si la c'est une transaction de mise à jour
    if (operations.get(0).estReel() && !operations.get(0).doitLire() ) {
      deMisAJour = true;
    }

    if (tempsSimulation < temps_echeance) {
      if ( operations.get(operationCourante).avancerTemps(tempsSimulation) ) {
        operationCourante++;
        if (operations.size() == operationCourante) {
          // La transaction est terminée
          termine = true;
          success = true;
        }
      }
    }
    else {
      termine = true;
    }

    boolean[] tab = new boolean[3];
    tab[0] = termine;
    tab[1] = success;
    tab[2] = deMisAJour;

    return tab;
  }

	@Override
	public String toString() {
		return "Transaction [temps_arrivee=" + temps_arrivee + ", temps_echeance=" + temps_echeance + ", operationCourante" + operations.get(operationCourante) + "]";
	}

  
}
