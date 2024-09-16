package commands;

import duke.*;

import java.time.format.DateTimeParseException;

public class DeadlineCommand implements Command {
    /**
     * Deconstructs the string to create an appropriate Deadline and updates the TaskList
     *
     * @param parser
     * @throws DukeException
     */
    @Override
    public void execute(Parser parser) throws DukeException {
        TaskList taskList = parser.getTaskList();
        String line = parser.getLine();

        if (line.length() <= 9) {
            throw new DukeException("Oops! The description of a deadline cannot be empty.");
        }
        int idxOfBy = line.indexOf("/by");
        if (idxOfBy == -1) {
            throw new DukeException("Oops! The description of a deadline must include /by");
        }
        try {
            new Deadline(line.substring(9, idxOfBy), line.substring(idxOfBy + 4));
        } catch (DateTimeParseException e) {
            throw new DukeException("Error! Please enter deadlines in this format:\t\ndeadline TASK /by DD-MM-YYYY");
        }
        Deadline deadline = new Deadline(line.substring(9, idxOfBy), line.substring(idxOfBy + 4));
        taskList.add(deadline);
        Ui.showLine();
        Ui.show("\tGot it boss. I've added this task:");
        Ui.show("\t\t" + deadline);
        Ui.show("\tNow you have " + taskList.size() + " tasks in the list.");
        Ui.showLine();
    }
}
