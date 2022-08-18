import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private Scanner sc;
    private List<Task> taskList;
    private static String NAME = "DoiMoiBot: ";
    private static String COLON = "added task: ";

    public Duke() {
        sc = new Scanner(System.in);
        taskList = new ArrayList<>(100);
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        String input;

        duke.greet();
        duke.storeInList();

        duke.farewell();
    }

    private void greet() {
        System.out.println(NAME + "Hello! I'm doimoibot\n" + "What can I do you for?");
    }

    private void farewell() {
        System.out.println(NAME + "Goodbye! See you soon!");
    }

    private void parrot(String input) {
        while (!input.equals("bye")) {
            System.out.println(NAME + input);
            input = this.getInput();
        }
    }

    private void storeInList() {
        String input;
        while (true) {
            input = this.getInput();

            if (input.equals("bye")) {
                break;
            }

            if (input.equals("list")) {
                this.printList();
                continue;
            }
            //logic to mark tasks with error handling
            if (input.indexOf("mark") == 0) {
                try {
                    String substring = input.substring(5);
                    //Since first task is of index 0 in ArrayList
                    int taskIndex = Integer.parseInt(substring) - 1;
                    Task task = taskList.get(taskIndex);
                    task.markAsDone();
                    System.out.println("Okay! marked as done!\n" + task);
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println("Please indicate which task to mark!");
                } catch (NumberFormatException e) {
                    System.out.println("Please input a valid task index!");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Task with that index does not exist!");
                }
                continue;
            }
            //logic to unmark tasks with error handling
            if (input.indexOf("unmark") == 0) {
                try {
                    String substring = input.substring(7);
                    //Since first task is of index 0 in ArrayList
                    int taskIndex = Integer.parseInt(substring) - 1;
                    Task task = taskList.get(taskIndex);
                    task.markAsNotDone();
                    System.out.println("Okay! marked as not done!\n" + task);
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println("Please indicate which task to mark!");
                } catch (NumberFormatException e) {
                    System.out.println("Please input a valid task index!");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Task with that index does not exist!");
                }
                continue;
            }
            //logic to create ToDos
            if (input.indexOf("todo") == 0) {
                try {
                    ToDos todo = new ToDos(input);
                    System.out.println("Okay! created ToDo!\n" + todo);
                    this.taskList.add(todo);
                } catch (MissingDescriptionException e) {
                    System.out.println("Please indicate what the task is!");
                }
                continue;
            }
            //logic to create Deadlines
            if (input.indexOf("deadline") == 0) {
                try {
                    Deadlines deadline = new Deadlines(input);
                    System.out.println("Okay! created ToDo!\n" + deadline);
                    this.taskList.add(deadline);
                } catch (MissingDescriptionException e) {
                    System.out.println("Please indicate what the task is!");
                } catch (MissingDeadlineException e) {
                    System.out.println("Please specify the deadline!");
                }
                continue;
            }
            //logic to create Events
            if (input.indexOf("event") == 0) {
                try {
                    Events event = new Events(input);
                    System.out.println("Okay! created ToDo!\n" + event);
                    this.taskList.add(event);
                } catch (MissingDescriptionException e) {
                    System.out.println("Please indicate what the task is!");
                } catch (MissingTimingException e) {
                    System.out.println("Please specify the timeline!");
                }
                continue;
            }

        }

    }

    private void printList() {
        for (int i = 1; i < taskList.size() + 1; i++) {
            System.out.println(i + ": " + taskList.get(i - 1));
        }
    }

    private String getInput() {
        return this.sc.nextLine();
    }
}
