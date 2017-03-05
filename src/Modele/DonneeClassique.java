package Modele;
public class DonneeClassique{

  private static int next_id = 0 ;

  protected int id ;
  protected int valeur ;
  protected boolean est_verrouille ;
  protected int nombre_lecteur;

  public DonneeClassique(){
    id = next_id++ ;
    valeur = 0;
    est_verrouille = false;
    nombre_lecteur = 0;
  }

  public void lire(){
    System.out.println("Lecture de la donnée "+id+".");
  }

  public void ecrire(){
    System.out.println("Ecriture de la donnée "+id+".");
  }

  public boolean estVerrouille() {
    return est_verrouille;
  }

  /*Fonction qui verrouille la donnée
    renvoie true si elle ne l'est pas deja
    sinon false */
  public boolean verrouiller(){
    if(nombre_lecteur == 0 && est_verrouille == false){
      est_verrouille = true ;
      return true;
    } else{
      return false ;
    }
  }

  public boolean deverrouiller(){
    if(est_verrouille == true){
      est_verrouille = false ;
      return true;
    } else{
      return false ;
    }
  }

  public void addLecteur() {
    nombre_lecteur++;
  }

  public void removeLecteur() {
    nombre_lecteur--;
  }


}
