import java.util.List;

class Consumer implements Runnable{
    private List<String> buffer;

    public Consumer(List<String> buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {

        while (true){
            if (buffer.isEmpty()){
                continue;
            }
            if (buffer.get(0).equals(Main.EOF)){
                System.out.println(Thread.currentThread().getName()+" exiting.");
                break;
            } 
            else {
                System.out.println(Thread.currentThread().getName()+ " removed " +buffer.remove(0));
            }
        }
    }
}