import javax.naming.NameAlreadyBoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ThreadTimerApp {

    private final List<Thread> threadList = new ArrayList<>();
    private final List<Timer> timerList = new ArrayList<>();

    public void start() throws NameAlreadyBoundException {
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



    private void runNewThread(String input) throws NameAlreadyBoundException {
        String threadName = ParseInput.getValue(input);
        checkIsThreadExist(threadName);

        Timer timer = new Timer(threadName);
        Thread thread = new Thread(timer, threadName);
        this.threadList.add(thread);
        this.timerList.add(timer);
        thread.start();
    }

    private void exitThreads() {
        this.threadList.forEach(thread -> thread.interrupt());

        jointThreads();
        this.timerList.forEach(System.out::println);
        this.timerList.clear();
    }

    private void checkALlThreads(){
        peekThread();
        this.timerList.forEach(System.out::println);
    }

    private void jointThreads(){
        this.threadList.forEach(s-> {
            try {
                s.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
    private void peekThread(){
        this.timerList.forEach(s->s.peekThread());
    }

    private void checkIsThreadExist(String threadName) throws NameAlreadyBoundException {
        boolean isThreadExist =  this.timerList.stream()
                .anyMatch(thr -> thr.getName().equals(threadName.trim()));
        if(isThreadExist){
            throw new NameAlreadyBoundException("Thread with given name already exist");
        }
    }
}
