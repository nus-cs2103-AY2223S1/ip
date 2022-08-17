
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
        System.out.println(INDENTATION + LINE);
        System.out.println(INDENTATION + "added: " + task.getDescription());
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
