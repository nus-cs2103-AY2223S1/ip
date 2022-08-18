import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    // Class Fields
    private static final String logo = " ___  ___  __ __ \n"
                                     + "| . \\| __>|  \\  \\\n"
                                     + "|   /| _> |     |\n"
                                     + "|_\\_\\|___>|_|_|_|\n";
    private static final String SPACER = "----------------------------------------------------";
    private static final String WELCOME = "こんにちは (Konnichiwa)! Rem だよ! (I'm Rem!) :>\n"
            + "今日は何ができますか? (What can I do for you today?)\n";

    protected CheckList checklist;

    // Constructor
    public Duke() {
        checklist = new CheckList();
    }

    // Class Methods
    private void run() throws DukeException {
        String input;
        Scanner sc = new Scanner(System.in);
        // Keep input on standby until user enters "bye"
        while (true) {
            System.out.print(">> ");
            input = sc.nextLine();
            ArrayList<String> words = new ArrayList<>(Arrays.asList(input.split(" ")));
            String firstWord = words.remove(0);

            switch (firstWord) {
                case "list":
                    if (words.size() == 0) {
                        System.out.println(SPACER + "\n"
                                + "Here's your list ^3^:\n"
                                + checklist.printList() + "\n"
                                + SPACER);
                    } else {
                        throw new DukeException(SPACER + "\n"
                                + "Please only enter 'list' to view your list. T^T\n"
                                + SPACER);
                    }
                    break;
                case "bye":
                    System.out.println("またね! (See you soon!) <3");
                    System.exit(0);
                    break;
                case "mark":
                    int taskNum = Integer.parseInt(words.get(0));
                    if (taskNum > 0 && taskNum <= checklist.tasks.size()) {
                        checklist.tasks.get(taskNum - 1).markDone();
                        System.out.println(SPACER + "\n"
                                + "Great Job on completing this task! ^.^ :\n"
                                + checklist.printTaskStatus(taskNum - 1) + "\n"
                                + SPACER);
                    } else if (checklist.tasks.size() == 0) {
                        throw new DukeException(SPACER + "\n"
                                + "There's nothing in your list. T^T\n"
                                + SPACER);
                    } else {
                        throw new DukeException(SPACER + "\n"
                                + "Please enter a valid task number. T^T\n"
                                + SPACER);
                    }
                    break;
                case "unmark":
                    taskNum = Integer.parseInt(words.get(0));
                    if (taskNum > 0 && taskNum <= checklist.tasks.size()) {
                        checklist.tasks.get(taskNum - 1).markUndone();
                        System.out.println(SPACER + "\n"
                                + "Grrr, remember to finish your task! =3=:\n"
                                + checklist.printTaskStatus(taskNum - 1) + "\n"
                                + SPACER);
                    } else if (checklist.tasks.size() == 0) {
                        throw new DukeException(SPACER + "\n"
                                + "There's nothing in your list. T^T\n"
                                + SPACER);
                    } else {
                        throw new DukeException(SPACER + "\n"
                                + "Please enter a valid task number. T^T\n"
                                + SPACER);
                    }
                    break;
                case "todo":
                    if (words.size() != 0) {
                        String remainingTodoWords = String.join(" ", words);
                        Todo todo = new Todo(remainingTodoWords);
                        checklist.addTask(todo);
                        System.out.println(SPACER + "\n"
                                + "I've added this task for you! :>\n"
                                + todo + "\n"
                                + "You have " + checklist.tasks.size()
                                + (checklist.tasks.size() == 1 ? " task! :D\n" : " tasks! :D\n")
                                + SPACER);
                    } else {
                        throw new DukeException(SPACER + "\n"
                                + "Please enter a task following 'todo' and I'll add it into your list. T^T\n"
                                + SPACER);
                    }
                    break;
                case "deadline":
                    // After adding new exceptions, throw them here
                    String remainingDdlWords = String.join(" ",words.subList(0, words.indexOf("/by")));
                    String ddl = String.join(" ", words.subList(words.indexOf("/by") + 1, words.size()));
                    Deadline deadline = new Deadline(remainingDdlWords, ddl);
                    checklist.addTask(deadline);
                    System.out.println(SPACER + "\n"
                        + "I've added this task for you! :>\n"
                        + deadline + "\n"
                        + "You have " + checklist.tasks.size()
                        + (checklist.tasks.size() == 1 ? " task! :D\n" : " tasks! :D\n")
                        + SPACER);
                    break;
                case "event":
                    // After adding new exceptions, throw them here
                    String remainingEventWords = String.join(" ",words.subList(0, words.indexOf("/at")));
                    String evt = String.join(" ", words.subList(words.indexOf("/at") + 1, words.size()));
                    Event event = new Event(remainingEventWords, evt);
                    checklist.addTask(event);
                    System.out.println(SPACER + "\n"
                        + "I've added this task for you! :>\n"
                        + event + "\n"
                        + "You have " + checklist.tasks.size()
                        + (checklist.tasks.size() == 1 ? " task! :D\n" : " tasks! :D\n")
                        + SPACER);
                    break;
                default:
                    System.out.println(SPACER + "\n"
                        + "Sorry, I don't understand. T^T\n"
                        + "Please start your command with list, mark, unmark, todo, deadline, event or bye. :')\n"
                        + SPACER);
                    break;
            }
        }
    }

    private void start() throws DukeException {
        try {
            run();
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            start();
        } catch (NumberFormatException e) { // Create a new exception called EmptyTaskExceptions
            System.out.println(SPACER + "\n"
                    + "Please enter a number following 'mark' or 'unmark'. T^T\n"
                    + SPACER);
            start();
        } catch (IllegalArgumentException e) { // Create a new exception called InvalidDukeArgumentException for nonsense inputs
            System.out.println(SPACER + "\n"
                    + "Please use '/by' after 'deadline' and '/at' after 'event' like this:\n"
                    + "'deadline (task) /by (date)' or 'event (task) /at (date)'. T^T\n"
                    + SPACER);
            start();
        }
    }

    // Main Method
    public static void main(String[] args) {
        Duke dk = new Duke();
        System.out.println(logo + "\n" + WELCOME + SPACER);
        try {
            dk.start();
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }
}