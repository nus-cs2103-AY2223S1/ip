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
            case "list":
                this.printList();
                break;
            default:
                this.list.add(message);
                this.generateReply("added: " + message);
        }
    }

    private void printList() {
        System.out.println("____________________________________________________________");
        int count = 1;
        for (String s: this.list) {
            System.out.println(count++ + ". " + s);
        }
        System.out.println("____________________________________________________________");
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
