import java.util.Scanner;

public class ConsoleCommands implements ConsoleCommandsInt {
    private Scanner scanner = new Scanner(System.in);

    public String enterCommand() {
        System.out.println("Enter command: ");
        return scanner.nextLine();
    }
}
