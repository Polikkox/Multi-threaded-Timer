import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ThreadTimerApp {

    private final List<Thread> threadList = new ArrayList<>();
    private final List<Timer> timerList = new ArrayList<>();

    public void start(){
        boolean isRun = true;
        Scanner scanner = new Scanner(System.in);

        while (isRun){
            Message.message("Enter command: ");
            String command = scanner.nextLine();
            String input = ParseInput.getCommand(command);

            if(input.equals("start")){
                runNewThread(command);
            }
            else if(input.equals("exit")){
                exitThreads();
            }
            else if(input.equals("check")){
                checkALlThreads();
            }
        }
    }



    private void runNewThread(String input){
        String threadName = ParseInput.getValue(input);
        Timer timer = new Timer(threadName);
        Thread thread = new Thread(timer, threadName);
        this.threadList.add(thread);
        this.timerList.add(timer);
        thread.start();
    }


    private void exitThreads() {
        threadList.forEach(thread -> thread.interrupt());

        jointThreads();
        timerList.forEach(System.out::println);
        timerList.clear();
    }

    private void checkALlThreads(){
        peekThread();
        timerList.forEach(System.out::println);
    }

    private void jointThreads(){
        threadList.forEach(s-> {
            try {
                s.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
    private void peekThread(){
        timerList.forEach(s->s.peekThread());
    }
}
