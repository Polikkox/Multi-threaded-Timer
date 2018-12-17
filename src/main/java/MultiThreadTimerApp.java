public class MultiThreadTimerApp {
    public static void main(String[] args) {
        ConsoleCommandsInt consoleCommands = new ConsoleCommands();
        ThreadTimerApp threadTimer = new ThreadTimerApp();
        threadTimer.start(consoleCommands);
    }
}
