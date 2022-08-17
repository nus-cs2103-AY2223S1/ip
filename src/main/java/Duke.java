import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    // Class Fields
    private static final String logo = " ___  ___  __ __ \n"
                                     + "| . \\| __>|  \\  \\\n"
                                     + "|   /| _> |     |\n"
                                     + "|_\\_\\|___>|_|_|_|";
    private static final String SPACER = "----------------------------------------------------";
    private static final String WELCOME = "こんにちは (Konnichiwa)! Rem だよ! (I'm Rem!) :>\n"
            + "今日は何ができますか? (What can I do for you today?)";

    protected CheckList checklist;

    // Constructor
    public Duke() {
        checklist = new CheckList();
    }

    // Class Methods
    private void start() {
        System.out.println(logo + "\n");
        System.out.println(WELCOME);
        System.out.println(SPACER);
    }

    private void run() {
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
                    System.out.println(SPACER + "\n"
                            + "Here's your list ^3^:\n"
                            + checklist.printList() + "\n"
                            + SPACER);
                    break;
                case "bye":
                    System.out.println("またね! (See you soon!) <3");
                    System.exit(0);
                    break;
                case "mark":
                    int taskNum = Integer.parseInt(words.get(0));
                    checklist.tasks.get(taskNum - 1).markDone();
                    System.out.println(SPACER + "\n"
                        + "Great Job on completing this task! ^.^ :\n"
                        + checklist.printTaskStatus(taskNum - 1) + "\n"
                        + SPACER);
                    break;
                case "unmark":
                    taskNum = Integer.parseInt(words.get(0));
                    checklist.tasks.get(taskNum - 1).markUndone();
                    System.out.println(SPACER + "\n"
                        + "Grrr, remember to finish your task! =3=:\n"
                        + checklist.printTaskStatus(taskNum - 1) + "\n"
                        + SPACER);
                    break;
                case "todo":
                    String remainingTodoWords = String.join(" ", words);
                    Todo todo = new Todo(remainingTodoWords);
                    checklist.addTask(todo);
                    System.out.println(SPACER + "\n"
                        + "I've added this task for you! :>\n"
                        + todo + "\n"
                        + "You have " + checklist.tasks.size()
                        + (checklist.tasks.size() == 1 ? " task! :D\n" : " tasks! :D\n")
                        + SPACER);
                    break;
                case "deadline":
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
                    Task task = new Task(input);
                    checklist.addTask(task);
                    System.out.println(SPACER + "\n"
                        + "I've added this task for you! :>\n"
                        + task + "\n"
                        + SPACER);
                    break;
            }
        }
    }

    // Main Method
    public static void main(String[] args) {
        Duke dk = new Duke();
        dk.start();
        dk.run();
    }
}
