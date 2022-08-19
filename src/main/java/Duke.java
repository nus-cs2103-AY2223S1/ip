import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Duke {
    private static String WELCOME_MESSAGE =  "Hello! I'm Duke\n" + "What can I do for you?";
    private static String GOODBYE_MESSAGE =  "Bye. Hope to see you again soon!";
    private List<Task> tasks = Task.loadSavedTasks();

    private int getNumTasks() {
        return this.tasks.size();
    }

    private String getNumTasksAsString() {
        return String.format("Now you have %d tasks in the list.", this.getNumTasks());
    }
    private void storeTask(Task task) {
        this.tasks.add(task);
        System.out.println(
                String.format("Got it. I've added this task:\n\t%s\n",
                    task,
                    this.getNumTasksAsString()));
    }

    private void getTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < this.getNumTasks(); i++) {
            System.out.println(String.format("%d. %s", i+1, this.tasks.get(i)));
        }
    }

    private Task getTaskAtIndex(String index) throws InvalidCommandException {
        try {
            int i = Integer.parseInt(index);
            return this.tasks.get(i-1);
        } catch (NumberFormatException e) {
            throw new InvalidTaskIndexException();
        } catch (IndexOutOfBoundsException e) {
            throw new NoSuchTaskException(this.getNumTasks(), index);
        }
    }

    private void markTaskAtIndexAsComplete(String index) throws InvalidCommandException {
        Task task = this.getTaskAtIndex(index);
        task.markAsCompleted();
    }

    private void markTaskAtIndexAsIncomplete(String index) throws InvalidCommandException {
        Task task = this.getTaskAtIndex(index);
        task.markAsIncomplete();
    }

    private void deleteTaskAtIndex(String index) throws InvalidCommandException {
        Task task = this.getTaskAtIndex(index);
        this.tasks.remove(Integer.parseInt(index) - 1);
        System.out.println(
                String.format("Noted. I've removed this task:\n\t%s\n",
                        task,
                        this.getNumTasksAsString()));
    }

    private void handleActionAndCommand(String command, Action action) throws InvalidCommandException {
        String[] components = command.split(" ");
        String contents = command.substring(action.label.length()).trim();

        switch (action) {
            case Mark:
                this.markTaskAtIndexAsComplete(contents);
                break;
            case Unmark:
                this.markTaskAtIndexAsIncomplete(contents);
                break;
            case Delete:
                this.deleteTaskAtIndex(contents);
                break;
            case Todo:
                if (contents.isBlank())
                    throw new EmptyTitleException();
                else
                    this.storeTask(new Todo(contents));
                break;
            case Deadline:
                String[] deadlineComponents = contents.split(" /by ");

                if (deadlineComponents.length != 2)
                    throw new InvalidDeadlineException();
                else if (deadlineComponents[0].isBlank())
                    throw new EmptyTitleException();
                else
                    this.storeTask(
                            new Deadline(deadlineComponents[0].trim(),
                                    deadlineComponents[1].trim())
                    );
                break;

            case Event:
                String[] eventComponents = contents.split(" /at ");
                if (eventComponents.length != 2)
                    throw new InvalidEventException();
                else if (eventComponents[0].isBlank())
                    throw new EmptyTitleException();
                else
                    this.storeTask(
                            new Event(eventComponents[0].trim(),
                                    eventComponents[1].trim())
                    );
                break;
        }

        Task.saveTaskList(this.tasks);
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
                try {
                    Action action = Action.parseCommand(command);
                    duke.handleActionAndCommand(command, action);
                } catch (InvalidCommandException e) {
                    System.out.println("Invalid command given.\n" + e.getMessage());
                }
            }
        }

        scanner.close();
    }
}
