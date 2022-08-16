import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    protected static ArrayList<Task> tasks = new ArrayList<>();

    public static void processTask(String input, String type) {
        String[] arr = input.split(" ", 2);
        String desc = "";
        try {
            desc = arr[1];
        }
        catch(ArrayIndexOutOfBoundsException e) {
            printOut("Please enter a description for your task!");
            return;
        }

        Task temp = null;
        if (type.equals("todo")) {
            temp = new Todo(desc);
            tasks.add(temp);
        }
        if (type.equals("deadline")) {
            try {
                String[] details = desc.split(" /by ");
                temp = new Deadline(details[0], details[1]);
                tasks.add(temp);
            }
            catch(ArrayIndexOutOfBoundsException e) {
                printOut("Oops! Your deadline should have a due date after /by.");
                return;
            }
        }
        if (type.equals("event")) {
            try {
                String[] details = desc.split(" /at ");
                temp = new Event(details[0], details[1]);
                tasks.add(temp);
            }
            catch(ArrayIndexOutOfBoundsException e) {
                printOut("Oops! Your event should have a date after /at.");
                return;
            }
        }
        printOut("Okay, I've added this task:\n" + temp.toString() +
                "\nYou now have " + tasks.size() + " tasks.");
    }

    public static void printOut(String str) {
        String line = "____________________________________________________________\n";
        System.out.println(line + str + "\n" + line);
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        printOut("Hello! I'm Duke.\n" +
                "What can I do for you?");
        String next = input.nextLine();

        while (!next.equals("bye")) {
            String[] nextWords = next.split(" ");
            switch(nextWords[0]) {
                case "mark":
                    try {
                        int index = Integer.parseInt(nextWords[1]) - 1;
                        tasks.get(index).markAsDone();
                        printOut("I've marked this as done:\n" + tasks.get(index).toString());
                        break;
                    }
                    catch(IndexOutOfBoundsException e) {
                        printOut("This task number is invalid!");
                        break;
                    }
                case "unmark":
                    try {
                        int index = Integer.parseInt(nextWords[1]) - 1;
                        tasks.get(index).markAsUndone();
                        printOut("I've marked this as undone:\n" + tasks.get(index).toString());
                        break;
                    }
                    catch(IndexOutOfBoundsException e) {
                        printOut("This task number is invalid!");
                        break;
                    }
                case "list":
                    System.out.println("____________________________________________________________\n" +
                            "Here are your tasks:");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println((i + 1) + ". " + tasks.get(i).toString());
                    }
                    System.out.println("____________________________________________________________\n");
                    break;
                case "todo":
                case "deadline":
                case "event":
                    processTask(next, nextWords[0]);
                    break;
                default:
                    printOut("I don't know this command. Try another one!");
            }

            next = input.nextLine();
        }

        printOut("See you later. Bye!");
    }
}