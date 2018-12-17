import java.util.InputMismatchException;

public class ParseInput {

    public static String getCommand(String input){
        String[] command = input.split(" ");
        return command[0].trim();
    }

    public static String getValue(String input){
        String[] command = input.split(" ");

        if(command.length == 2){
            return command[1].trim();
        }
        else{
            throw new InputMismatchException("Something wrong with input");
        }
    }
}
