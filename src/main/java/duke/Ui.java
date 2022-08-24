package duke;

public class Ui {
    private static final String WELCOME = "Hello! I am Duke\n "
            + "What can i do for you";
    private static final String EXIT = "See you later :)";
    private static final String SEPARATOR = "------------------------------------";
    private static final String ASK_FOR_COMMAND = "What do you want me to do?";

    public static void printSeparator() {
        System.out.println(SEPARATOR);
    }

    public static void printWelcome() {
        printSeparator();
        System.out.println(WELCOME);
        printSeparator();
    }

    public static void printExit() {
        printSeparator();
        System.out.println(EXIT);
        printSeparator();
    }

    public static void printRead() {
        printSeparator();
        TaskList.read();
        printSeparator();
    }

    public static void printSuccessfulLoad() {
        printSeparator();
        System.out.println("Successfully retrieved most recent TaskList");
        printSeparator();
    }

    public static void printFailedLoad() {
        printSeparator();
        System.out.println("Error loading file, an empty TaskList is initialised");
        printSeparator();
    }

    public static void printAskForCommand() {
        printSeparator();
        System.out.println(ASK_FOR_COMMAND);
    }

    public static void printTaskIsDone(int index) {
        Task task = TaskList.getTaskList().get(index);
        printSeparator();
        System.out.println("Task is marked as Done \n" + task.toString());
        printSeparator();
    }

    public static void printTaskIsUndone(int index) {
        Task task = TaskList.getTaskList().get(index);
        printSeparator();
        System.out.println("Task is marked as Undone \n" + task.toString());
        printSeparator();
    }

    public static void printAddTask(Task task) {
        printSeparator();
        System.out.println("added: " + task.toString());
        printSeparator();
    }

    public static void printDeleteTask(int index) {
        Task task = TaskList.getTaskList().get(index);
        printSeparator();
        System.out.println("Removed the task \n" + task.toString());
        printSeparator();
    }




}
