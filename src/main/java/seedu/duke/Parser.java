package seedu.duke;
import java.util.Scanner;

public class Parser {
    private Scanner scan = new Scanner(System.in);
    private TaskList taskList;

    /**
     * A constructor that returns an instance of Parser.
     *
     * @param tasklist The TaskList where tasks are stored during program execution.
     */
    public Parser(TaskList tasklist) {
        this.taskList = tasklist;
    }

    /**
     * Calls the Scanner to wait for the next line of user input.
     */
    public void nextCommand() {
        String command = scan.nextLine();
        this.parse(command);
        nextCommand();
    }

    /**
     * Takes in the user's input and interprets it.
     *
     * @param command The user's input.
     */
    public void parse(String command) {
        if (command.equals("list")) {
            this.taskList.list();
        } else if (command.startsWith("mark") || command.startsWith("unmark")) {
            taskList.toggleDone(command);
        } else if (command.startsWith("delete")) {
            int index = Character.getNumericValue(command.charAt(command.length() - 1));
            taskList.delete(index - 1);
        } else if (command.startsWith("find")) {
            taskList.find(command);
        } else if (command.equals("bye")) {
            Ui.bye();
        } else {
            // Add a task.
            taskList.addTask(command);
        }
    }

}
