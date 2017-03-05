package Modele;
import java.util.*;
import java.util.ArrayList;

public class BaseDeDonnees {

  private DonneeTempsReel[] donneesTempsReel;
  private DonneeClassique[] donneesClassique;
  private ArrayList<Transaction> transactions;

  private int tempsLectureClassique;
  private int tempsEcritureClassique;
  private int tempsLectureReel;
  private int tempsEcritureReel;

  private int tempsActuel;

  private int reussi;

  private int echec;

	public BaseDeDonnees(int nbr_dtr, int min_v, int max_v, int nbr_dc, int min_nbr_op, int max_nbr_op, int duree_s, int lp, int tlc, int tec, int tlr, int ter, double tauxAleatoire) {
    tempsLectureClassique = tlc;
    tempsEcritureClassique = tec;
    tempsLectureReel = tlr;
    tempsEcritureReel = ter;

    transactions = new ArrayList<Transaction>();

    generate_tempsreel(nbr_dtr, min_v, max_v);
    generate_tempsclassique(nbr_dc);
    generate_transactions_miseajour();
    generate_transactions_utilisateur(min_nbr_op, max_nbr_op, duree_s, lp, tauxAleatoire);

    trie();

    tempsActuel = 0;
    reussi = 0;
    echec = 0;
	}

  public ArrayList<Transaction> getTransactions() {
    return transactions;
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

  private void generate_transactions_utilisateur(int min, int max, int duree, int lambda, double tauxAleatoire){
    Random rnd = new Random();
    Poisson poisson = new Poisson(lambda, duree);
    ArrayList<Integer> evenement = poisson.generate();

    for (Integer i : evenement) {
      // Création d'une transaction
      // Calcul du nombre d'opération
      int nombre_op = min + rnd.nextInt(max - min);
      // Création de la liste d'opération
      ArrayList<Operation> liste_op = new ArrayList<Operation>();
      // Création des opérations
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
      int dureeMinimal = 0;
      for (Operation op : liste_op) {
        dureeMinimal += getTempsOperation(op);
      }
      int echeance =  i + (int) ( tauxAleatoire*rnd.nextDouble() * dureeMinimal);
      Transaction t = new Transaction(i, echeance, liste_op);
      // si l'échéance ne dépasse pas la date de fin de la simulation :
      if (echeance <= duree) {
        transactions.add(t);
      }
    }

  }

  public void trie() {
    // FIXME : peut être que le tri est à l'envers
    // Trier les transactions dans leur ordre d'arrivé
    transactions.sort( (Transaction t1, Transaction t2) -> {
      return t1.getArrivee() - t2.getArrivee();
    });
  }

  public void avancerTemps() {
    Transaction transaction = null;
    if (transactions.size() != 0) {
      transaction = transactions.get(0);
    }
    if (transaction != null) {
      if (transaction.getArrivee() >= tempsActuel) {
        // TODO détecter si l'opération est fini et si la transaction était une transaction de mise à jour
        boolean[] resu = transaction.avancerTemps(tempsActuel);
        // si la transaction est terminé
        if(resu[0]) {
          // TODO : retiré la transaction de la liste

          // si la transaction c'est terminé avec succés
          if(resu[1]){
            reussi++;
          }
          else {
            echec++;
          }

          // si la transaction était de mis a jour
          if (resu[2]){
            // TODO: créer une nouvelle transaction de mis a jour et l'ajouter

            trie();
          }
        }
      }
      else {
        tempsActuel++;
      }
    }
    else {
      tempsActuel++;
    }
  }

  private int getTempsOperation(Operation o) {
    if(o.estReel()) {
      if (o.doitLire()) {
        return tempsLectureReel;
      } else {
        return tempsEcritureReel;
      }
    } else {
      if (o.doitLire()) {
        return tempsLectureClassique;
      } else {
        return tempsEcritureClassique;
      }
    }
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

    // définit la borne max pour la valeur aléatoire
    double tauxAleatoire = 2.0;
  }
}
