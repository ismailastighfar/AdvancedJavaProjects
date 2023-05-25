import java.util.List;

class Producer implements Runnable{
    private List<String> buffer;

    public Producer(List<String> buffer) {
        this.buffer = buffer;
    }


    @Override
    public void run() {
        String numbers[] = {"1","2","3"};
        for (String number : numbers){
            buffer.add(number);
            System.out.println(Thread.currentThread().getName()+" added "+number);
        }
        System.out.println(Thread.currentThread().getName()+" added "+Main.EOF);
        buffer.add(Main.EOF);
    }
}