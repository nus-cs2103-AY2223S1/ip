import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Duke {
    ArrayList<Task> tasks;

    /**
     * Constructor.
     */
    public Duke() {
        this.tasks = new ArrayList<>();
    }

    /**
     * To greet the user.
     * @return A message used to greet the user.
     */
    public String greet() {
        return "Hello! I'm Duke\nWhat can I do for you? ^_^";
    }

    /**
     * To echo user's input.
     * @param message A message to echo.
     * @return The message user's provided.
     */
    public String echo(String message) {
        return "\t " + message;
    }

    /**
     * To exit the programme after users type "bye".
     * @return A goodbye message.
     */
    public String exit() {
        return "\t Bye. Hope to see you again soon :D";
    }

    /**
     *
     * @param task The task that should be added to the list.
     * @return A string that tells the user if the tasks has been added.
     */
    public String add(Task task) {
        tasks.add(task);
        String message = "\t Got it! I have added this task: \n\t\t" + task;
        String numOfTasks = String.format("\n\t Now you have %d %s in the list!", tasks.size(),
                tasks.size() < 2 ? "task" : "tasks");
        return message + numOfTasks;
    }

    /**
     * To list down all the tasks that are added to the list.
     * @return A list of all the tasks added.
     */
    public String list() {
        int len = tasks.size();
        StringBuilder stringBuilder = new StringBuilder("\t Here are the tasks in your list :D");
        for (int i = 0; i < len; i++) {
            int index = i + 1;
            String task = "\n\t " + index + ". " + tasks.get(i);
            stringBuilder.append(task);
        }
        return stringBuilder.toString();
    }

    /**
     * To delete the task specified.
     * @return A string that notifies users that the task has been deleted.
     */
    public String delete(int taskNumber) {
        String message = String.format("\tNoted, I have removed this task: \n\t\t%s", tasks.get(taskNumber - 1));
        tasks.remove(taskNumber - 1);
        return message;
    }

    public static void main(String[] args) throws DukeException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Duke duke = new Duke();
        System.out.println(duke.greet());
        Scanner scanner = new Scanner(System.in);
        boolean run = true;
        while (run) {
            String message = scanner.nextLine();
            try {
                if (message.equals("bye")) {
                    System.out.println(duke.exit());
                    run = false;
                    scanner.close();
                } else if (message.equals("list")) {
                    System.out.println(duke.list());
                } else if (message.startsWith("mark")) {
                    Pattern pattern = Pattern.compile("[^0-9]");
                    int taskNumber = Integer.parseInt(pattern.matcher(message).replaceAll(""));
                    System.out.println(duke.tasks.get(taskNumber - 1).markAsDone());
                } else if (message.startsWith("unmark")) {
                    Pattern pattern = Pattern.compile("[^0-9]");
                    int taskNumber = Integer.parseInt(pattern.matcher(message).replaceAll(""));
                    System.out.println(duke.tasks.get(taskNumber - 1).markAsNotDone());
                } else if (message.startsWith("todo")) {
                    if (message.trim().equals("todo")) {
                        throw new DukeException("Todo description cannot be empty!");
                    }
                    String description = message.substring(5);
                    ToDo todo = new ToDo(description);
                    System.out.println(duke.add((todo)));
                } else if (message.startsWith("deadline")) {
                    int index = message.indexOf(" /by ");
                    if (index == 8 || (index == -1 && message.trim().equals("deadline"))) {
                        throw new DukeException("Deadline description cannot be empty!");
                    } else if (index == -1) {
                        throw new DukeException("I don't know when is the deadline :(, " +
                                "please specify the task in this format: deadline <description> /by <due date>");
                    }
                    String description = message.substring(9, index);
                    String by = message.substring(index + 5);
                    Deadline deadline = new Deadline(description, by);
                    System.out.println(duke.add(deadline));
                } else if (message.startsWith("event")) {
                    int index = message.indexOf(" /at ");
                    if (index == 5 || (index == -1 && message.trim().equals("event"))) {
                        throw new DukeException("Event description cannot be empty!");
                    } else if (index == -1) {
                        throw new DukeException("I don't know when is the event taking place :(, " +
                                "please specify the task in this format: event <description> /at <date>");
                    }
                    String description = message.substring(6, index);
                    String at = message.substring(index + 5);
                    Event event = new Event(description, at);
                    System.out.println(duke.add(event));
                } else if (message.startsWith("delete")) {
                    int taskNumber = Integer.parseInt(message.substring(7));
                    System.out.println(duke.delete(taskNumber));
                } else {
                    throw new DukeException("I'm sorry >< I don't know what this means :(");
                }
            } catch (DukeException e) {
                System.out.println("\t> " + e.getMessage());
            }
        }
    }
}
