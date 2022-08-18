import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private boolean inNeed = true;
    private ArrayList<Task> list = new ArrayList<>(100);

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
            case "mark":
                int index1 = Integer.parseInt(command[1]);
                this.markDone(index1);
                break;
            case "unmark":
                int index2 = Integer.parseInt(command[1]);
                this.unmarkDone(index2);
                break;
            default:
                Task t = new Task(message);
                this.list.add(t);
                this.generateReply("added: " + t);
        }
    }

    private void printList() {
        System.out.println("____________________________________________________________");
        int count = 1;
        for (Task t: this.list) {
            System.out.println(count++ + ". " + t);
        }
        System.out.println("____________________________________________________________");
    }

    private void markDone(int index) {
        this.list.get(index - 1).markDone();
        generateReply("Nice! I've marked this task as done:\n" +
                this.list.get(index - 1));
    }

    private void unmarkDone(int index) {
        this.list.get(index - 1).unmarkDone();
        generateReply("OK, I've marked this task as not done yet:\n" +
                this.list.get(index - 1));
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
