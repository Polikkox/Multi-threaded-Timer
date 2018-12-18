public class Timer implements Runnable{
    private String name;
    private int id;
    private static int idThread = 0;
    private long startTime;
    private long elapsedTime;
    private long pause = 0;
    private volatile boolean isThreadRun = true;

    public Timer(String name) {
        this.name = name;
        this.id = this.idThread++;
    }

    @Override
    public void run() {
        startTime = System.nanoTime();
        while (true){
            if(this.isThreadRun) {
                try {
                    Thread.sleep(1000);
                }
                catch (InterruptedException e) {
                    stopTime();
                    throw new RuntimeException(e);
                }
            }
        }
    }
    public void interruptThread(){
        Thread.currentThread().interrupt();
    }

    public synchronized void resumeThread(){
        long endTime = System.nanoTime();
        this.isThreadRun = true;
        this.startTime = startTime + (endTime - pause);
        this.pause = 0;
    }

    public void stopTime(){
        long endTime = System.nanoTime();

        if(this.pause != 0){
            this.elapsedTime = ((endTime - this.startTime) - (endTime - this.pause)) / 1000000000;
            return;
        }
        this.elapsedTime = (endTime - this.startTime) / 1000000000;
    }

    public void pauseTime(){
        this.isThreadRun = false;
        this.pause = System.nanoTime();
    }

    public String toString(){
        return "Thread : " + this.name + "\nThread id: " + this.id + "\nSeconds: " + this.elapsedTime;
    }

    public void peekThread(){
        stopTime();
    }

    public String getName() {
        return this.name;
    }
}
