import java.util.ArrayList;
import java.util.List;

public class ThreadTimerApp {

    private final List<Thread> threadList = new ArrayList<>();
    private final List<Timer> timerList = new ArrayList<>();

    public void start(ConsoleCommandsInt consoleCommands){
        boolean run = true;

        while (run){
            String input = consoleCommands.enterCommand();
        }
    }
}
