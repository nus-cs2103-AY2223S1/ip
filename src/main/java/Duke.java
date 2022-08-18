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

    private static String[] splitString(String s, String regex) {
        return s.split(regex, 2);
    }

    private boolean checkCommandArgsLength(String[] command, int length) {
        if (command.length != length) {
            return false;
        } else {
            return true;
        }
    }

    private void parseMessage(String message) {

        String[] command = splitString(message, " ");
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
                this.handleAddTask(command, "todo");
                break;
            case "deadline":
                this.handleAddTask(command, "deadline");
                break;
            case "event":
                this.handleAddTask(command, "event");
                break;
            default:
                this.generateReply("OOPS!!! I'm sorry, but I don't know what that means :-(");
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
                "\nNow you have " + this.countTask() + " tasks in the list");
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

    public void handleAddTask(String[] commands, String type) {
        Task task;
        if (type.equals("todo")) {
            if (checkCommandArgsLength(commands, 2)) {
                if (commands[1].trim().length() != 0) {
                    task = new Todo(commands[1]);
                    this.addTask(task);
                } else {
                    generateReply("OOPS!!! The description of a todo cannot be empty.");
                }
            }
            else {
                generateReply("OOPS!!! The description of a todo cannot be empty.");
            }
        }
        else if (type.equals("deadline")) {
            if (checkCommandArgsLength(commands, 2)) {
                String[] argsDeadline = splitString(commands[1], DEADLINE_SPLIT);
                if (checkCommandArgsLength(argsDeadline, 2)) {
                    task = new Deadline(argsDeadline[0], argsDeadline[1]);
                    this.addTask(task);
                }
                else {
                    generateReply("OOPS!!! Invalid deadline command.");
                }
            } else {
                generateReply("OOPS!!! The description of a deadline cannot be empty.");
            }
        }
        else {
            if (checkCommandArgsLength(commands, 2)) {
                String[] argsDeadline = splitString(commands[1], EVENT_SPLIT);
                if (checkCommandArgsLength(argsDeadline, 2)) {
                    task = new Event(argsDeadline[0], argsDeadline[1]);
                    this.addTask(task);
                }
                else {
                    generateReply("OOPS!!! Invalid event command.");
                }
            } else {
                generateReply("OOPS!!! The description of a event cannot be empty.");
            }
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
