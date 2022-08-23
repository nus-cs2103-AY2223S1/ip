import java.util.Arrays;
public class Parser {
    String command;
    String[] arguments;

    public Parser(String input) {
        String[] splitInput = input.split(" ");
        command = splitInput[0];
        arguments = Arrays.copyOfRange(splitInput, 1, splitInput.length);
    }
    public Parser() {

    }
    public Task fileLoadParser(String input) {
        String[] splitInput = input.split("\\|");
        String type = splitInput[0];
        String[] arguments = Arrays.copyOfRange(splitInput, 2, splitInput.length);
        Task t;
        if (type.equals("T")) {
            t = new Todo(splitInput[2]);
        } else if (type.equals("D")) {
            t = new Deadline(arguments);
        } else {
            t = new Event(arguments);
        }
        if (splitInput[1].equals("1")) {
            t.complete();
        }
        return t;
    }

    public String getCommand() {
        return this.command;
    }

    public String[] getArguments() {
        return this.arguments;
    }

}
