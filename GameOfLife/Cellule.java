import java.util.Random;

public class Cellule {
    int etat;
    private static Random random = new Random();

    public Cellule() {
        // Initialise l'état aléatoirement (0 ou 1)
        this.etat = random.nextInt(2);
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public void calculerEtatFuture(Cellule cg, Cellule cd, Regle r) {
        int nbVoisinesVivantes = 0;
        if (cg.etat == 1) nbVoisinesVivantes++;
        if (cd.etat == 1) nbVoisinesVivantes++;

        if (this.etat == 1) {
            if (nbVoisinesVivantes < 2 || nbVoisinesVivantes > 3) {
                // La cellule meurt (isolement ou surpopulation)
                this.etat = 0;
            }
        } else {
            if (nbVoisinesVivantes == 3) {
                // La cellule renaît
                this.etat = 1;
            }
        }
    }

    public boolean equals(Cellule c) {
        return this.etat == c.etat;
    }
}
