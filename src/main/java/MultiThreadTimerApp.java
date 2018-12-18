import javax.naming.NameAlreadyBoundException;

public class MultiThreadTimerApp {
    public static void main(String[] args) throws NameAlreadyBoundException {
        ThreadTimerApp threadTimer = new ThreadTimerApp();
        threadTimer.start();
    }
}
