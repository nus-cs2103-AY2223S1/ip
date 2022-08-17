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
     * List text given by user in taskList
     */
    public void list() {
        System.out.println(INDENTATION + LINE);
        for (int i = 0; i < this.taskList.getSize(); i++) {
            System.out.println(INDENTATION + String.valueOf(i + 1) + ". " + this.taskList.getText(i));
        }
        System.out.println(INDENTATION + LINE);
    }

    /**
     * Add text given by user to taskList in Duke
     * @param text text given by user
     */
    public void add(String text) {
        this.taskList.addToTaskList(text);
        System.out.println(INDENTATION + LINE);
        System.out.println(INDENTATION + "added: " + text);
        System.out.println(INDENTATION + LINE);
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
        Scanner sc = new Scanner(System.in);
        while (true) {
            String command = sc.nextLine();
            if (command.equals("bye")) {
                sc.close();
                Duke.exit();
                break;
            } else if (command.equals("list")) {
                dukeBot.list();
            } else {
                dukeBot.add(command);
            }
        }
    }
}
