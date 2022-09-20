package seedu.duke;

public class Parser {
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
     * Takes in the user's input and interprets it.
     *
     * @param command The user's input.
     * @return A response to be displayed to the user.
     */
    public String parse(String command) {
        assert command != null : "No command provided.";

        if (command.equals("list")) {
            return this.taskList.list();
        } else if (command.startsWith("mark") || command.startsWith("unmark")) {
            return taskList.markOrUnmarkAsDone(command);
        } else if (command.startsWith("delete")) {
            int index = Character.getNumericValue(command.charAt(command.length() - 1));
            return taskList.delete(index - 1);
        } else if (command.startsWith("find")) {
            return taskList.find(command);
        } else if (command.equals("bye")) {
            return Ui.bye();
        } else {
            // Add a task.
            return taskList.addTask(command);
        }
    }

}
