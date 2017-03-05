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

  public int getEcheance() {
    return temps_echeance;
  }

  public boolean[] avancerTemps(int tempsSimulation) {
    boolean termine = false;
    boolean deMisAJour = false;
    boolean success = false;

    // TODO: Detecter si la c'est une transaction de mise à jour
    /*if ("de mise a jour") {
      deMisAJour = true;
    }*/

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
      annulerTout();
    }

    boolean[] tab = new boolean[3];
    tab[0] = termine;
    tab[1] = success;
    tab[2] = deMisAJour;

    return tab;
  }

  //TODO: fonction qui annule une transaction et ses opérations filles.
  public void annulerTout(){

  }
}
