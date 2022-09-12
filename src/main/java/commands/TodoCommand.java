package commands;

import duke.*;

public class TodoCommand implements Command {
    /**
     * Deconstructs the string to create an appropriate Todoclass and updates the TaskList
     *
     * @param parser
     * @throws DukeException
     */
    @Override
    public void execute(Parser parser) throws DukeException {
        TaskList taskList = parser.getTaskList();
        String line = parser.getLine();
        if (line.length() <= 5) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
        }
        Todo todo = new Todo(line.replace("todo ", ""));
        taskList.add(todo);
        Ui.showLine();
        Ui.show("\tGot it. I've added this task:");
        Ui.show("\t\t" + todo);
        Ui.show("\tNow you have " + taskList.size() + " tasks in the list.");
        Ui.showLine();
    }
}
