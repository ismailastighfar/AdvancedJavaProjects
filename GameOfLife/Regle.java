import java.util.Arrays;

public class Regle {
  // la valeur de la règle en décimal
  private int valeurRegle;

  public Regle(int valeurRegle) {
    this.valeurRegle = valeurRegle;
  }

  // méthode qui convertit la valeur de la règle en binaire et renvoie le résultat sous forme de chaîne de caractères
  public String decimal2binaire() {
    // utilise la méthode Integer.toBinaryString pour convertir la valeur de la règle en binaire
    String binaire = Integer.toBinaryString(valeurRegle);

    // complète la chaîne avec des zéros pour avoir un nombre sur 8 bits
    char[] zeros = new char[8 - binaire.length()];
    Arrays.fill(zeros, '0');
    binaire = new String(zeros) + binaire;

    return binaire;
  }

  public int getValeur() {
    return valeurRegle;
  }

}


  

