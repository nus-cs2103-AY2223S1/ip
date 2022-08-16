import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    private List<Task> listOfTasks;
    private boolean hasEnded = false;

    public Duke() {
        this.listOfTasks = new ArrayList<>();
    }

    private static final String ROW_INDENT = "    ";
    private static final String WORD_INDENT = " ";
    private static final String HORIZONTAL_LINE = "____________________________________________________________";

    private static final String WELCOME_MSG = "Hello! I'm Duke \n" + Duke.ROW_INDENT + Duke.WORD_INDENT + "What can I do for you?";
    private static final String BYE_MSG = "Bye. Hope to see you again soon!";
    private static final String DONE_MSG = "Nice! I've marked this task as done: \n" + Duke.ROW_INDENT + Duke.WORD_INDENT;
    private static final String UNDONE_MSG = "Sure! I've marked this task as not done yet \n" + Duke.ROW_INDENT + Duke.WORD_INDENT;


    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        greet();
        while (!hasEnded) {
            String userInput = getInput(sc);
            String[] inputArray = userInput.split(" ", 2);
            String command = inputArray[0];

            switch (command.toUpperCase()) {
                case "BYE":
                    goodBye();
                    hasEnded = true;
                    break;
                case "LIST":
                    displayList();
                    break;
                case "MARK":
                    markAsDone(Integer.valueOf(inputArray[1]));
                    break;
                case "UNMARK":
                    markAsUndone(Integer.valueOf(inputArray[1]));
                    break;
                default:
                    Task task = new Task(userInput);
                    addToList(task);
            }
        }
    }

    public void markAsDone(int taskNumber) {
        Task taskToMark = listOfTasks.get(taskNumber - 1);
        taskToMark.markAsDone();
        StringBuilder sb = new StringBuilder(DONE_MSG);
        sb.append("[" + taskToMark.getStatusIcon() + "] " + taskToMark);
        printMessage(sb.toString());
    }

    public void markAsUndone(int taskNumber) {
        Task taskToMark = listOfTasks.get(taskNumber - 1);
        taskToMark.markAsUndone();
        StringBuilder sb = new StringBuilder(UNDONE_MSG);
        sb.append("[" + taskToMark.getStatusIcon() + "] " + taskToMark);
        printMessage(sb.toString());
    }

    public void addToList(Task task) {
        this.listOfTasks.add(task);
        printMessage("added: " + task);
    }

    public void displayList() {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks in your list: \n");
        for (int i = 1; i <= this.listOfTasks.size(); i++) {
            sb.append("\n" + ROW_INDENT + WORD_INDENT);
            sb.append(i + ".[" + this.listOfTasks.get(i - 1).getStatusIcon() + "] " + this.listOfTasks.get(i - 1));

        }
        printMessage(sb.toString());
    }

    public String getInput(Scanner sc) {
        System.out.println();
        return sc.nextLine();
    }

    public void greet() {
        printMessage(Duke.WELCOME_MSG);
    }

    public void goodBye() {
        printMessage(Duke.BYE_MSG);
    }

    public void printMessage(String message) {
        System.out.println(Duke.ROW_INDENT + Duke.HORIZONTAL_LINE);
        System.out.println(Duke.ROW_INDENT + Duke.WORD_INDENT + message);
        System.out.println(Duke.ROW_INDENT + Duke.HORIZONTAL_LINE);
    }
}
