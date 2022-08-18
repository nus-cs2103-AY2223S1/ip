
import java.util.Scanner;
public class Duke {
    private static String WELCOME_MESSAGE =  "Hello! I'm Duke\n" + "What can I do for you?";
    private static String GOODBYE_MESSAGE =  "Bye. Hope to see you again soon!";
    private Task[] tasks = new Task[100];
    private int numInputsStored = 0;

    private void storeTask(Task task) {
        this.tasks[numInputsStored++] = task;
        System.out.println(String.format("Got it. I've added this task:\n\t%s\nNow you have %d tasks in the list.", task, numInputsStored));
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
            System.out.printf("No task found. You only have %d tasks but you referenced a task at index %d",
                    this.numInputsStored,
                    index);
        } else {
            task.markAsCompleted();
        }
    }

    private void markTaskAtIndexAsIncomplete(int index) {
        Task task = this.getTaskAtIndex(index-1);
        if (task == null) {
            System.out.printf("No task found. You only have %d tasks but you referenced a task at index %d",
                    this.numInputsStored,
                    index);
        } else {
            task.markAsIncomplete();
        }
    }

    private boolean handleActionAndCommand(String command, Action action) {
        String[] components = command.split(" ");
        String contents = command.substring(action.label.length()).trim();

        try {
            switch (action) {
                case Mark:
                    this.markTaskAtIndexAsComplete(Integer.parseInt(contents));
                    break;
                case Unmark:
                    this.markTaskAtIndexAsIncomplete(Integer.parseInt(contents));
                    break;
                case Todo:
                    this.storeTask(new Todo(contents));
                    break;
                case Deadline:
                    String[] deadlineComponents = contents.split(" /by ");

                        this.storeTask(
                                new Deadline(deadlineComponents[0].trim(),
                                        deadlineComponents[1].trim())
                        );
                    break;

                case Event:
                    String[] eventComponents = contents.split(" /at ");

                        this.storeTask(
                                new Event(eventComponents[0].trim(),
                                        eventComponents[1].trim())
                        );
                    break;
            }

            return true;

        } catch (NumberFormatException e) {
            System.out.println("Could not parse the command");
            return false;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Could not parse the command");
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
            } else if (command.equals("list")) {
                duke.getTasks();
            } else {
                Action action = Action.parseCommand(command);

                if (action != null) {
                    duke.handleActionAndCommand(command, action);
                } else {
                    System.out.println("Invalid command given.\n");
                }
            }
        }

        scanner.close();
    }
}
