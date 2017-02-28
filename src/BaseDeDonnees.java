import java.util.*;

public class BaseDeDonnees {
  private DonneeTempsReel[] donneesTempsReel;
  private DonneeClassique[] donneesClassique;
  private ArrayList<Transaction> transactions;

  private int tempsLectureClassique;
  private int tempsEcritureClassique;
  private int tempsLectureReel;
  private int tempsEcritureReel;

	public BaseDeDonnees(int nbr_dtr, int min_v, int max_v, int nbr_dc, int min_nbr_op, int max_nbr_op, int duree_s, int lp, int tlc, int tec, int tlr, int ter) {
    tempsLectureClassique = tlc;
    tempsEcritureClassique = tec;
    tempsLectureReel = tlr;
    tempsEcritureReel = ter;

    generate_tempsreel(nbr_dtr, min_v, max_v);
    generate_tempsclassique(nbr_dc);
    generate_transactions_miseajour();
    generate_transactions_utilisateur(min_nbr_op, max_nbr_op, duree_s, lp);
	}

  private void generate_tempsreel(int nbr, int min, int max){
    donneesTempsReel = new DonneeTempsReel[nbr];
    for(int i=0; i<nbr; i++){
      donneesTempsReel[i] = new DonneeTempsReel(0 , (int)(min + Math.random()*(max-min)) );
    }

  }
  private void generate_tempsclassique(int nbr){
    donneesClassique = new DonneeClassique[nbr];
    for(int i=0; i<nbr; i++){
      donneesClassique[i] = new DonneeClassique();
    }
  }

  private void generate_transactions_miseajour(){
    for (int i = 0; i < donneesTempsReel.length; i++ ) {
      Operation op = new Operation(donneesTempsReel[i], true, false, donneesTempsReel[i].getValidite()*2/3);
      ArrayList<Operation> liste_op = new ArrayList<Operation>();
      liste_op.add(op);
      Transaction t = new Transaction(donneesTempsReel[i].getValidite()*2/3, donneesTempsReel[i].getValidite(), liste_op);
    }
  }

  private void generate_transactions_utilisateur(int min, int max, int duree, int lambda){
    // Création d'une transaction
      // Calcul du nombre d'opération
      Random rnd = new Random();
      int nombre_op = min + rnd.nextInt(max - min);
      // Création de la liste d'opération
      ArrayList<Operation> liste_op = new ArrayList<Operation>();
      for (int n = 0; n < nombre_op; n++) {
        // la donnée sera t'elle une donnée en temps réel ou classique?
        boolean surReel = Math.random() < 0.5;

        // Choisir la donnée
        DonneeClassique donnee;
        if (surReel) {
          donnee = donneesTempsReel[rnd.nextInt(donneesTempsReel.length)];
        } else {
          donnee = donneesClassique[rnd.nextInt(donneesClassique.length)];
        }

        // l'opération sera t'elle lecture ou ecriture ?
        boolean lecture;
        if (surReel) {
          // seul les transactions des maj modifie les données en temps réel
          lecture = true;
        } else {
          lecture = Math.random() < 0.5;
        }

        // définir la durée
        int duree_op;
        if (surReel) {
          if (lecture) {
            duree_op = tempsLectureReel;
          }
          else {
            duree_op = tempsEcritureReel;
          }
        }
        else {
          if (lecture) {
            duree_op = tempsLectureClassique;
          }
          else {
            duree_op = tempsEcritureClassique;
          }
        }
        Operation op = new Operation(donnee, true, false, duree_op);
        liste_op.add(op);

      }
      //Transaction t = new Transaction(/*date d'arrivé selon la loi de poisson*/, /*date d'echance*/, liste_op);

  }
  public static void main(String[] args) {

    // TODO: Interface graphique + Formulaire
    int min_nombre_operation = 1;
    int max_nombre_operation = 3;
    int duree_lecture_temps_reel = 1;

    int duree_lecture_classique =  2;
    int duree_ecriture_classique = 2;
    int duree_simulation =100 ;
    double lambda_poisson = 0.5;

    int nombre_donnee_temps_reel = 10;
    int nombre_donnee_classique = 10;
    int min_validite = 10;
    int max_validite = 20;
  }
}
