import java.util.concurrent.Semaphore;

public class Automate extends Thread {
    private static final int TAILLE_AUTOMATE = 100;

    private int generation;
    private Cellule[][] grille;
    private Regle regle;
    private Semaphore semaphore;

    public Automate(Regle regle) {
        this.generation = 0;
        this.regle = regle;
        this.grille = new Cellule[TAILLE_AUTOMATE][TAILLE_AUTOMATE];
        this.semaphore = new Semaphore(1);

        intAutomate();
    }

    private void intAutomate() {
        // Remplit la grille de cellules aléatoirement
        for (int i = 0; i < TAILLE_AUTOMATE; i++) {
            for (int j = 0; j < TAILLE_AUTOMATE; j++) {
                grille[i][j] = new Cellule();
            }
        }
    }

    public void generationSuivante() {
        generation++;

        try {
            semaphore.acquire();

            // Calcule l'état futur de chaque cellule
            for (int i = 0; i < TAILLE_AUTOMATE; i++) {
                for (int j = 0; j < TAILLE_AUTOMATE; j++) {
                    Cellule cg = (i == 0) ? grille[TAILLE_AUTOMATE - 1][j] : grille[i - 1][j];
                    Cellule cd = (i == TAILLE_AUTOMATE - 1) ? grille[0][j] : grille[i + 1][j];
                    grille[i][j].calculerEtatFuture(cg, cd, regle);
                }
            }

            // Applique l'état futur de chaque cellule
            for (int i = 0; i < TAILLE_AUTOMATE; i++) {
                for (int j = 0; j < TAILLE_AUTOMATE; j++) {
                    grille[i][j].setEtat(grille[i][j].etat);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }
    }

    public void afficher() {
        for (int i = 0; i < TAILLE_AUTOMATE; i++) {
            for (int j = 0; j < TAILLE_AUTOMATE; j++) {
                if (grille[i][j].etat == 1) {
                    System.out.print("*");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    @Override
    public void run() {
        while (true) {
            afficher();
            generationSuivante();

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }}}        
        }