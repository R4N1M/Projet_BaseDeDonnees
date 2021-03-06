package Modele;
public class Operation {
  private DonneeClassique donnee;
  private boolean est_reel;
  private boolean doit_lire;
  private int temps_restant;
  private boolean en_execution;

  public Operation(DonneeClassique d ,boolean r, boolean l,int t){
    donnee = d ;
    est_reel = r;
    doit_lire = l;
    temps_restant = t;
    en_execution = false;
  }

  public DonneeClassique getDonnee() {
    return donnee;
  }

  public boolean estReel() {
    return est_reel;
  }
  public boolean doitLire() {
    return doit_lire;
  }

  public boolean avancerTemps(int tempsSimulation) {
    if (!en_execution) {

      if (!donnee.estVerrouille()) {
        if (doit_lire){
          en_execution = true;
          donnee.addLecteur();
        }
        else if (donnee.verrouiller()) {
          en_execution = true;
        }
      }

    }
    else {
      temps_restant--;
      if (temps_restant == 0) {
        executer(tempsSimulation);

        if (doit_lire) {
            donnee.removeLecteur();
        }
        else {
          donnee.deverrouiller();
        }
        return true;
      }
    }
    return false;
  }

  private void executer(int date){
    if(doit_lire == true){
      donnee.lire();
    }else{
      if(est_reel == false){
        donnee.ecrire();
      }else{
        DonneeTempsReel d = (DonneeTempsReel) donnee ;
        d.mettre_a_jour(date);
      }
    }
  }

@Override
public String toString() {
	return "Operation [donnee=" + donnee + ", est_reel=" + est_reel + ", doit_lire=" + doit_lire + ", temps_restant="
			+ temps_restant + ", en_execution=" + en_execution + "]";
}


}
