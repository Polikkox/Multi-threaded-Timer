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
                checkThread(command);
            }
            else if(input.equals("pause")){
                pauseThread(command);
            }
            else if(input.equals("resume")){
                resumeThread(command);
            }
        }
    }

    private void runNewThread(String input) throws NameAlreadyBoundException {
        String threadName = ParseInput.getValue(input);
        if(checkIfThreadExist(threadName)){
            interruptThread(threadName);
        }

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
        System.out.println("threads received interrupt while sleeping");
    }

    private void checkThread(String input){
        String[] anyWhiteSpace = input.split(" ");
        System.out.println(anyWhiteSpace.length);

        if(anyWhiteSpace.length == 2){
            String threadName = ParseInput.getValue(input).trim();
            peekThread(threadName);
            this.timerList.stream()
                    .filter(thr -> thr.getName().equals(threadName))
                    .forEach(System.out::println);
            return;
        }
        peekThreads();
        this.timerList.forEach(System.out::println);
    }


    private void pauseThread(String command) {
        String threadName = ParseInput.getValue(command).trim();
        for(Timer timer : this.timerList){
            if(timer.getName().equals(threadName)){
                timer.pauseTime();
                System.out.println(timer.getName() + " is paused");
            }
        }

    }

    private void resumeThread(String command){
        String threadName = ParseInput.getValue(command).trim();
        for(Timer timer : this.timerList){
            if(timer.getName().equals(threadName)){
                timer.resumeThread();
                System.out.println(timer.getName() + " is unpaused");
            }
        }
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

    private void peekThreads(){
        this.timerList.forEach(s->s.peekThread());
    }

    private void peekThread(String name) {
        for(Timer timer : this.timerList){
            if(timer.getName().equals(name)){
                timer.peekThread();
            }
        }
    }

    private boolean checkIfThreadExist(String threadName){
        return  this.timerList.stream()
                .anyMatch(thr -> thr.getName().equals(threadName.trim()));
    }

    private void interruptThread(String threadName) {
        for(int i = 0; i < this.timerList.size(); i++){
            if(timerList.get(i).getName().equals(threadName)){
                timerList.get(i).interruptThread();
                timerList.remove(i);
            }
        }
    }
}
