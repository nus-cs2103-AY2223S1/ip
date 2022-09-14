package commands;

import duke.*;

public class EventCommand implements Command {

    /**
     * Deconstructs the string to create an appropriate Event and updates the TaskList
     *
     * @param parser
     * @throws DukeException
     */
    @Override
    public void execute(Parser parser) throws DukeException {
        TaskList taskList = parser.getTaskList();
        String line = parser.getLine();

        if (line.length() <= 6) {
            throw new DukeException("Oops! The description of a event cannot be empty.");
        }
        int idxOfAt = line.indexOf("/at");
        if (idxOfAt == -1) {
            throw new DukeException("Oops! The description of a event must include /at");
        }
        Event event = new Event(line.substring(6, idxOfAt), line.substring(idxOfAt + 4));
        taskList.add(event);
        Ui.showLine();
        Ui.show("\tGot it boss... I've added this task:");
        Ui.show("\t\t" + event);
        Ui.show("\tNow you have " + taskList.size() + " tasks in the list.");
        Ui.showLine();
    }
}
