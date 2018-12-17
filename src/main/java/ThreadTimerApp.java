import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ThreadTimerApp {

    private final List<Thread> threadList = new ArrayList<>();
    private final List<Timer> timerList = new ArrayList<>();

    public void start() {
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

        threadList.forEach(s-> {
            try {
                s.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        timerList.forEach(System.out::println);
        timerList.clear();
    }
}
