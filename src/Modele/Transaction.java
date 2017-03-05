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

  public void avancerTemps(int tempsSimulation) {
    if (tempsSimulation < temps_echeance) {
      if ( operations.get(operationCourante).avancerTemps(tempsSimulation) ) {
        operationCourante++;
        // TODO: SI la transaction est finie ALORS supprimer la transaction de la liste des transaction
        // TODO: SI c'est une transaction de mise à jour, en redeclarer une autre
      }
    }
    else {
      annulerTout();
    }
  }

  //TODO: fonction qui annule une transaction et ses opérations filles.
  public void annulerTout(){

  }
}
