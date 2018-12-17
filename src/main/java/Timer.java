public class Timer implements Runnable{
    private String name;
    private int id;
    private static int idThread = 0;
    private long startTime;
    private long elapsedTime;

    public Timer(String name) {
        this.name = name;
        id = idThread++;
    }



    @Override
    public void run() {
        startTime = System.nanoTime();
        while (true){
            try {
                Thread.sleep(1000);
            }
            catch (InterruptedException e) {
                stopTime();
                throw new RuntimeException(e);
            }
        }


    }
    public void stopTime(){
        Long endTime = System.nanoTime();
        elapsedTime = (endTime - startTime) / 1000000000;
    }

    public String toString(){
        return "Thread : " + this.name + "\nThread id: " + this.id + "\nSeconds: " + this.elapsedTime;
    }

}
