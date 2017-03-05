package Modele;
public class DonneeTempsReel extends DonneeClassique {
  private int estampille ;
  private int duree_de_validation ;

  public DonneeTempsReel(int e, int d){
    super();
    estampille = e;
    duree_de_validation = d;
  }

  public void mettre_a_jour(int date){
    estampille = date ;
    System.out.println("Donnée temps réel " + super.id + "mise à jour");
  }

  public int getValidite() {
    return duree_de_validation;
  }



}
