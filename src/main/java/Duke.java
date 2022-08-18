import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private boolean inNeed = true;
    private final ArrayList<Task> list = new ArrayList<>(100);

    private final static String DEADLINE_SPLIT = " /by ";
    private final static String EVENT_SPLIT = " /at ";

    private void greet() {
        generateReply("Hello! I'm Sheep\n" +
                "What can I do for you?");
    }

    private void generateReply(String reply) {
        System.out.println("____________________________________________________________\n" +
                reply +
                "\n____________________________________________________________\n");
    }

    private String[] splitString(String s, String regex) {
        return s.split(regex, 2);
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
            case "todo":
                Task t = new Todo(command[1]);
                this.addTask(t);
                break;
            case "deadline":
                String[] argsDeadline = splitString(command[1], DEADLINE_SPLIT);
                Deadline d = new Deadline(argsDeadline[0], argsDeadline[1]);
                this.addTask(d);
                break;
            case "event":
                String[] argsEvent = splitString(command[1], EVENT_SPLIT);
                Event e = new Event(argsEvent[0], argsEvent[1]);
                this.addTask(e);
                break;
            default:
                this.generateReply("Invalid command");
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

    private void addTask(Task t) {
        this.list.add(t);
        generateReply("Got it. I've added this task:\n" +
                t +
                "\n Now you have " + this.countTask() + " tasks in the list");
    }

    private Integer countTask() {
        return this.list.size();
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
