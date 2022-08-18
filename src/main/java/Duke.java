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
            Task task = new Task(input);
            System.out.println(COLON + input);
            this.taskList.add(task);
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
