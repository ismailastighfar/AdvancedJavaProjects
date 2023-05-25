
import java.util.Random;

public class Sonsor{

    int ID_sonsor;
    final int  sensingInterval = 10;
    double value;
    String name;

    public Sonsor(int ID_sonsor , String name){
        this.ID_sonsor = ID_sonsor;
        this.name = name;
    }

    public double run(){
        Random random = new Random();
        double range = 60 - 20;
        double scaled = random.nextDouble() * range;
        double shifted = scaled + 20;
        return shifted; // == (rand.nextDouble() * (max-min)) + min;
    }
    
    public int getSensingInterval(){
        return this.sensingInterval;
    }
}