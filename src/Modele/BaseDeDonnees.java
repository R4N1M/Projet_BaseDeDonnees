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

	public BaseDeDonnees(int nbr_dtr, int min_v, int max_v, int nbr_dc, int min_nbr_op, int max_nbr_op, int duree_s, double lp, int tlc, int tec, int tlr, int ter, double tauxAleatoire) {
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

  private void generate_transactions_utilisateur(int min, int max, int duree, double lambda, double tauxAleatoire){
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
        Operation op = new Operation(donnee, surReel, lecture, duree_op);
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
      if (transaction.getArrivee() <= tempsActuel) {
        // Détecter si l'opération est fini et si la transaction était une transaction de mise à jour
        boolean[] resu = transaction.avancerTemps(tempsActuel);
        // si la transaction est terminé
        if(resu[0]) {
          //Retiré la transaction de la liste
          Transaction t = transactions.remove(0);
          // si la transaction c'est terminé avec succés
          if(resu[1]){
            reussi++;
          }
          else {
            echec++;
          }

          // si la transaction était de mis a jour
          if (resu[2]){
            // FIXME: créer une nouvelle transaction de mis a jour et l'ajouter

            int id = t.getOperations().get(0).getDonnee().getId();
            DonneeTempsReel d = donneesTempsReel[id];
            Operation op = new Operation(d, true, false, d.getValidite()*2/3);
            ArrayList<Operation> liste_op = new ArrayList<Operation>();
            liste_op.add(op);
            Transaction t2 = new Transaction(tempsActuel + d.getValidite()*2/3, tempsActuel + d.getValidite(), liste_op);
            trie();
          }
        }
      }
    }

    tempsActuel++;
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

  public int getEchec() {
    return echec;
  }

  public int getReussi() {
    return reussi;
  }

  public String toString() {
    return "nombre de transaction reussis : "+getReussi() +", nombre de transaction raté : "+getEchec();
  }

  public int getTempsActuel() {
  	return tempsActuel;
  }

  public void setTempsActuel(int tempsActuel) {
  	this.tempsActuel = tempsActuel;
  }
}
