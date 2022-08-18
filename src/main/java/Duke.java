import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Duke {
    private static final String LIST = "list";
    private static final String MARK = "mark";
    private static final String UNMARK = "unmark";
    private static final String TODO = "todo";
    private static final String DEADLINE = "deadline";
    private static final String EVENT = "event";
    private static final String BYE = "bye";
    private static final String DELETE = "delete";

    private static List<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        greetUser();
        askUser();
        sayBye();
    }

    public static void greetUser() {
        System.out.println("\t______________________________________________________");
        System.out.println("\tHey there! I'm Arias!");
        System.out.println("\tHow may I help you? :)");
        System.out.println("\t______________________________________________________\n");
    }

    public static void sayBye() {
        System.out.println("\t______________________________________________________");
        System.out.println("\tNice seeing you! Bye!");
        System.out.println("\t______________________________________________________\n");
    }

    public static void askUser() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            String userInput = sc.nextLine();
            String[] arr = userInput.split(" ", 2);

            if (arr.length == 0) {
                continue;
            }

            String command = arr[0].toLowerCase();
            if (command.equals(LIST)) {
                System.out.println("\t______________________________________________________");
                listTasks();
                System.out.println("\t______________________________________________________\n");
            } else if (command.equals(MARK)) {
                if (arr.length <= 1) {
                    System.out.println("\t______________________________________________________");
                    System.out.println("\tMissing task number to mark!");
                    System.out.println("\t______________________________________________________\n");
                } else {
                    try {
                        int taskNumber = Integer.parseInt(arr[1]);
                        mark(taskNumber);
                    } catch (NumberFormatException e) {
                        System.out.println("\t______________________________________________________");
                        System.out.println("\tInput is not a valid task number " + e.getMessage());
                        System.out.println("\t______________________________________________________\n");
                    }
                }
            } else if (command.equals(UNMARK)) {
                if (arr.length <= 1) {
                    System.out.println("\t______________________________________________________");
                    System.out.println("\tMissing task number to unmark!");
                    System.out.println("\t______________________________________________________\n");
                } else {
                    try {
                        int taskNumber = Integer.parseInt(arr[1]);
                        unmark(taskNumber);
                    } catch (NumberFormatException e) {
                        System.out.println("\t______________________________________________________");
                        System.out.println("\tInput is not a valid task number " + e.getMessage());
                        System.out.println("\t______________________________________________________\n");
                    }
                }
            } else if (command.equals(TODO) || command.equals(DEADLINE) || command.equals(EVENT)) {
                if (arr.length <= 1) {
                    System.out.println("\t______________________________________________________");
                    System.out.println("\tMissing description after given command!");
                    System.out.println("\t______________________________________________________\n");
                } else {
                    userInput = arr[1];
                    Task t = addTasks(command, userInput);
                    System.out.println("\t______________________________________________________");
                    System.out.println("\tGot it! I've added this task!");
                    System.out.println("\t\t" + t);
                    System.out.println("\tYou now have " + tasks.size() + " tasks in the list!");
                    System.out.println("\t______________________________________________________\n");
                }

            } else if (command.equals(DELETE)) {
                if (arr.length <= 1) {
                    System.out.println("\t______________________________________________________");
                    System.out.println("\tMissing task number to delete!");
                    System.out.println("\t______________________________________________________\n");
                } else {
                    try {
                        int taskNumber = Integer.parseInt(arr[1]);
                        delete(taskNumber);
                    } catch (NumberFormatException e) {
                        System.out.println("\t______________________________________________________");
                        System.out.println("\tInput is not a valid task number " + e.getMessage());
                        System.out.println("\t______________________________________________________\n");
                    }
                }
            } else {
                System.out.println("\t______________________________________________________");
                System.out.println("\t'" + userInput + "'" + " not recognised!");
                System.out.println("\t______________________________________________________\n");
            }
        }
    }

    public static Task addTasks(String taskType, String userInput) {
        Task t;
        if (taskType.equals(TODO)) {
            t = new ToDo(userInput);
        } else if (taskType.equals(DEADLINE)) {
            String[] arr = userInput.split(" /by ", 2);
            t = new Deadline(arr[0], arr[1]);
        } else {
            String[] arr = userInput.split(" /at ", 2);
            t = new Event(arr[0], arr[1]);
        }
        tasks.add(t);
        return t;
    }

    public static void listTasks() {
        if (tasks.size() == 0) {
            System.out.println("\tYou do not have any tasks!");
        } else {
            System.out.println("\tHere are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println("\t" + (i + 1) + ". " + tasks.get(i));
            }
        }
    }

    public static void mark(int taskNumber) {
        tasks.get(taskNumber - 1).markAsDone();
        System.out.println("\t______________________________________________________");
        System.out.println("\tAlright! Marked this task as done!");
        System.out.println("\t\t" + tasks.get(taskNumber - 1));
        System.out.println("\t______________________________________________________\n");
    }

    public static void unmark(int taskNumber) {
        tasks.get(taskNumber - 1).markAsNotDone();
        System.out.println("\t______________________________________________________");
        System.out.println("\tOkay! Unmarked this task!");
        System.out.println("\t\t" + tasks.get(taskNumber - 1));
        System.out.println("\t______________________________________________________\n");
    }

    public static void delete(int taskNumber) {
        Task t = tasks.get(taskNumber - 1);
        tasks.remove(taskNumber - 1);
        System.out.println("\t______________________________________________________");
        System.out.println("\tAlright! I've deleted this task!");
        System.out.println("\t\t" + t);
        System.out.println("\tYou now have " + tasks.size() + " tasks in the list!");
        System.out.println("\t______________________________________________________\n");
    }
}
