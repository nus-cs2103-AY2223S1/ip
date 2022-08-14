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
        return "\t> " + message;
    }

    /**
     * To exit the programme after users type "bye".
     * @return A goodbye message.
     */
    public String exit() {
        return "\t> Bye. Hope to see you again soon :D";
    }

    /**
     *
     * @param description The task that should be added to the list.
     * @return A string that tells the user if the tasks has been added.
     */
    public String add(String description) {
        Task task = new Task(description);
        tasks.add(task);
        return "\t> added: " + task;
    }

    /**
     * To list down all the tasks that are added to the list.
     * @return A list of all the tasks added.
     */
    public String list() {
        int len = tasks.size();
        StringBuilder stringBuilder = new StringBuilder("\t> Here are the tasks in your list :D");
        for (int i = 0; i < len; i++) {
            int index = i + 1;
            String task = "\n\t  " + index + ". " + tasks.get(i);
            stringBuilder.append(task);
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
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
            if (message.equalsIgnoreCase("bye")) {
                System.out.println(duke.exit());
                scanner.close();
                run = false;
            } else if (message.equalsIgnoreCase("list")) {
                System.out.println(duke.list());
            } else if (message.startsWith("mark")) {
                Pattern pattern = Pattern.compile("[^0-9]");
                int taskNumber = Integer.parseInt(pattern.matcher(message).replaceAll(""));
                System.out.println(duke.tasks.get(taskNumber - 1).markAsDone());
            } else if (message.startsWith("unmark")) {
                Pattern pattern = Pattern.compile("[^0-9]");
                int taskNumber = Integer.parseInt(pattern.matcher(message).replaceAll(""));
                System.out.println(duke.tasks.get(taskNumber - 1).markAsNotDone());
            } else {
                System.out.println(duke.add(message));
            }
        }
    }
}
