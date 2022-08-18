import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private boolean inNeed = true;
    private ArrayList<String> list = new ArrayList<>(100);

    private void greet() {
        generateReply("Hello! I'm Sheep\n" +
                "What can I do for you?");
    }

    private void generateReply(String reply) {
        System.out.println("____________________________________________________________\n" +
                reply +
                "\n____________________________________________________________\n");
    }

    private void parseMessage(String message) {
        String[] command = message.split(" ", 2);
        String commandKey = command[0];
        switch (commandKey) {
            case "bye":
                this.generateReply("Bye. Hope to see you again soon!");
                this.inNeed = false;
                break;
            default:
                this.list.add(message);
                this.generateReply("added: " + message);
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.greet();
        Scanner scanner = new Scanner(System.in);
        while (duke.inNeed) {
            String message = scanner.nextLine();
            duke.parseMessage(message);
        }
    }
}
