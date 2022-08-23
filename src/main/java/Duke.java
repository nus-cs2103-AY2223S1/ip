import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Duke {
    protected static Storage storage = new Storage("data/tasks.txt");
    protected static TaskList tasks = new TaskList(storage.load(), storage);
    protected static DateTimeFormatter parserFormats = new DateTimeFormatterBuilder()
            .appendOptional(DateTimeFormatter.ISO_LOCAL_DATE)
            .appendOptional(DateTimeFormatter.ofPattern("d MMM uuuu"))
            .appendOptional(DateTimeFormatter.ofPattern("yyyyMMdd"))
            .toFormatter();

    public static void processTask(String input, String type) {
        String[] arr = input.split(" ", 2);
        String desc = "";
        try {
            desc = arr[1];
        } catch (ArrayIndexOutOfBoundsException e) {
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
                String by = LocalDate.parse(details[1], parserFormats).
                        format(DateTimeFormatter.ofPattern("MMM d yyyy"));
                temp = new Deadline(details[0], by);
                tasks.add(temp);
            } catch (ArrayIndexOutOfBoundsException e) {
                printOut("Oops! Your deadline should have a due date after /by.");
                return;
            } catch (DateTimeParseException e) {
                printOut("Please enter a valid date!");
                return;
            }
        }
        if (type.equals("event")) {
            try {
                String[] details = desc.split(" /at ");
                String at = LocalDate.parse(details[1], parserFormats).
                        format(DateTimeFormatter.ofPattern("MMM d yyyy"));
                temp = new Event(details[0], at);
                tasks.add(temp);
            } catch (ArrayIndexOutOfBoundsException e) {
                printOut("Oops! Your event should have a date after /at.");
                return;
            } catch (DateTimeParseException e) {
                printOut("Please enter a valid date!");
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
                    tasks.markTaskAsDone(index);
                    printOut("I've marked this as done:\n" + tasks.taskToString(index));
                    break;
                } catch(IndexOutOfBoundsException e) {
                    printOut("This task number is invalid!");
                    break;
                }
            case "unmark":
                try {
                    int index = Integer.parseInt(nextWords[1]) - 1;
                    tasks.markTaskAsUndone(index);
                    printOut("I've marked this as undone:\n" + tasks.taskToString(index));
                    break;
                } catch(IndexOutOfBoundsException e) {
                    printOut("This task number is invalid!");
                    break;
                }
            case "delete":
                try {
                    int index = Integer.parseInt(nextWords[1]) - 1;
                    printOut("I'll remove this task:\n" + tasks.taskToString(index) +
                            "\nYou now have " + (tasks.size() - 1) + " tasks.");
                    tasks.remove(index);
                    break;
                } catch(IndexOutOfBoundsException e) {
                    printOut("This task number is invalid!");
                    break;
                }
            case "list":
                printOut("Here are your tasks:" + tasks.toString());
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