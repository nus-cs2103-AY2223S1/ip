import java.util.Scanner;
public class Duke {
    private final static String greeting = "Hi, I'm Bob! Nice to meet you!\n\tWhats up?";
    private final static String farewell = "Okay then, see ya later :)";

    public Duke() {
        messageFormatter(greeting);
    }

    private void messageFormatter(String input) {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n \t"
                + input + "\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    private void command(String command) {
        while(!command.equals("bye")) {
            messageFormatter(command);
            Scanner scanner = new Scanner(System.in);
            command = scanner.nextLine();
        }
        messageFormatter(farewell);
    }
    
    public static void main(String[] args) {
        Duke newDuke = new Duke();
        newDuke.command(new Scanner(System.in).nextLine());

    }
}
