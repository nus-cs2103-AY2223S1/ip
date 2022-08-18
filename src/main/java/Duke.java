
import java.util.Scanner;
public class Duke {
    private static String WELCOME_MESSAGE =  "Hello! I'm Duke\n" + "What can I do for you?";
    private static String GOODBYE_MESSAGE =  "Bye. Hope to see you again soon!";

    private Task[] tasks = new Task[100];
    private int numInputsStored = 0;

    private void storeTask(String title) {
        this.tasks[numInputsStored++] = new Task(title);
        System.out.println(String.format("added: %s", title));
    }
    private void getTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < this.numInputsStored; i++) {
            System.out.println(String.format("%d. %s", i+1, this.tasks[i]));
        }
    }

    private Task getTaskAtIndex(int index) {
        return this.tasks[index];
    }
    private void markTaskAtIndexAsComplete(int index) {
        Task task = this.getTaskAtIndex(index-1);
        if (task == null) {
            System.out.println("No task found. Are you sure you entered the correct index?");
        } else {
            task.markAsCompleted();
        }
    }
    private void markTaskAtIndexAsIncomplete(int index) {
        Task task = this.getTaskAtIndex(index-1);
        if (task == null) {
            System.out.println("No task found. Are you sure you entered the correct index?");
        } else {
            task.markAsIncomplete();
        }
    }

    private boolean parseAndHandleCommand(String command) {
        String[] components = command.split(" ");
        try {
            String action = components[0];
            int taskIndex = Integer.parseInt(components[1]);
            if (action.equals("mark")) {
                this.markTaskAtIndexAsComplete(taskIndex);
            }

            if (action.equals("unmark")) {
                this.markTaskAtIndexAsIncomplete(taskIndex);
            }

            return true;

        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
    * Note: You are strongly encouraged to customize the chatbot name,
    * command/display formats, and even the personality of the chatbot
    * to make your chatbot unique.
    */
    public static void main(String[] args) {
        System.out.println(WELCOME_MESSAGE);

        Scanner scanner = new Scanner(System.in);
        Duke duke = new Duke();

        while (true) {

            String command = scanner.nextLine();

            if (command.equals("bye")) {
                System.out.println(GOODBYE_MESSAGE);
                break;
            }

            if (command.equals("list")) {
                duke.getTasks();
                continue;
            }

            if (!duke.parseAndHandleCommand(command)) {
                duke.storeTask(command);
            }
        }

        scanner.close();
    }
}
