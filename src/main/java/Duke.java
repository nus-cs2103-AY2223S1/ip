
import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    private final static String LINE = "____________________________________________________________";
    private final static String INDENTATION = "   ";
    private TaskList taskList;

    public Duke() {
        this.taskList = new TaskList();
    }

    /**
     * Greeting function of Duke
     */
    public static void greeting() {
        System.out.println(INDENTATION + LINE);
        System.out.println(INDENTATION + "Hello! I'm Duke");
        System.out.println(INDENTATION + "What can I do for you?");
        System.out.println(INDENTATION + LINE);
    }

    /**
     * Exit function of Duke
     */
    public static void exit() {
        System.out.println(INDENTATION + LINE);
        System.out.println(INDENTATION + "Bye. Hope to see you again soon!");
        System.out.println(INDENTATION + LINE);
    }

    /**
     * List task(s) given by user in taskList
     */
    public void list() {
        System.out.println(INDENTATION + LINE);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < this.taskList.getSize(); i++) {
            System.out.println(INDENTATION + String.valueOf(i + 1) + ". " + this.taskList.getTask(i).toString());
        }
        System.out.println(INDENTATION + LINE);
    }

    /**
     * Add task given by user to taskList in Duke
     *
     * @param task task given by user
     */
    public void add(Task task) {
        this.taskList.addToTaskList(task);
        int numTasks = this.taskList.getSize();
        System.out.println(INDENTATION + LINE);
        System.out.println(INDENTATION + "Got it. I've added this task:");
        System.out.println(INDENTATION + INDENTATION + "added: " + task.toString());
        System.out.println(INDENTATION + "Now you have " + String.valueOf(numTasks) + " tasks in the list.");
        System.out.println(INDENTATION + LINE);
    }

    /**
     * Marks task as done
     *
     * @param taskIndex index of task to mark as done
     */
    public void mark(int taskIndex) {
        Task taskToMark = this.taskList.getTask(taskIndex);
        taskToMark.markAsDone();
        System.out.println(INDENTATION + LINE);
        System.out.println(INDENTATION + "Nice! I've marked this task as done:");
        System.out.println(INDENTATION + INDENTATION + taskToMark.toString());
        System.out.println(INDENTATION + LINE);
    }

    public void unmark(int taskIndex) {
        Task taskToMark = this.taskList.getTask(taskIndex);
        taskToMark.markAsUndone();
        System.out.println(INDENTATION + LINE);
        System.out.println(INDENTATION + "OK, I've marked this task as not done yet:");
        System.out.println(INDENTATION + INDENTATION + taskToMark.toString());
        System.out.println(INDENTATION + LINE);
    }

    /**
     * Main function to run Duke
     */
    public void run() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            String[] input = sc.nextLine().split(" ");
            String command = input[0];
            if (command.equals("bye")) {
                sc.close();
                Duke.exit();
                break;
            } else if (command.equals("list")) {
                this.list();
            } else if (command.equals("mark")) {
                int num = Integer.parseInt(input[1]);
                this.mark(num - 1);
            } else if (command.equals("unmark")) {
                int num = Integer.parseInt(input[1]);
                this.unmark(num - 1);
            } else if (command.equals("todo")) {
                String[] taskDescription = Arrays.copyOfRange(input, 1, input.length);
                Task toDo = new ToDo(String.join(" ", taskDescription));
                this.add(toDo);
            } else if (command.equals("deadline")) {
                int byIndex = Arrays.asList(input).indexOf("/by");
                String[] taskDescription = Arrays.copyOfRange(input, 1, byIndex);
                String[] by = Arrays.copyOfRange(input, byIndex + 1, input.length);
                Task deadline = new Deadline(String.join(" ", taskDescription), String.join(" ", by));
                this.add(deadline);
            } else if (command.equals("event")) {
                int atIndex = Arrays.asList(input).indexOf("/at");
                String[] taskDescription = Arrays.copyOfRange(input, 1, atIndex);
                System.out.println(Arrays.toString(taskDescription));
                String[] at = Arrays.copyOfRange(input, atIndex + 1, input.length);
                Task event = new Event(String.join(" ", taskDescription), String.join(" ", at));
                this.add(event);
            } else {
                Task task = new Task(String.join(" ", input));
                this.add(task);
            }
        }

    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Duke dukeBot = new Duke();
        Duke.greeting();
        dukeBot.run();
    }
}
