package duke.command;

import duke.Deadline;
import duke.DukeException;
import duke.Event;
import duke.ToDo;

public class AddCommand implements ICommand {
    private final Commands type;
    private final String description;
    private final String dateString;

    public AddCommand(Commands type, String description) {
        this.type = type;
        this.description = description;
        this.dateString = "";
    }
    public AddCommand(Commands type, String description, String dateString) {
        this.type = type;
        this.description = description;
        this.dateString = dateString;
    }
    @Override
    public void execute() {
        try {
            switch (type) {
                case TODO:
                    String input = sc.nextLine().trim();
                    if (input.isBlank()) {
                        throw new DukeException("Something went wrong! Could not read TODO.");
                    }
                    msg = this.taskList.addTask(new ToDo(input));
                    break;
                case DEADLINE:
                    arguments = sc.nextLine().trim().split("\\s/by\\s");
                    if (arguments.length < 2 || arguments[0].isBlank() || arguments[1].isBlank()) {
                        throw new DukeException("Something went wrong! Could not read DEADLINE.");
                    }
                    msg = this.taskList.addTask(new Deadline(arguments[0], arguments[1]));
                    break;
                case EVENT:
                    arguments = sc.nextLine().trim().split("\\s/at\\s");
                    if (arguments.length < 2 || arguments[0].isBlank() || arguments[1].isBlank()) {
                        throw new DukeException("Something went wrong! Could not read EVENT.");
                    }
                    msg = this.taskList.addTask(new Event(arguments[0], arguments[1]));
                    break;
            }
            return msg;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
