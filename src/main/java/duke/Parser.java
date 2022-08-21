package duke;

import duke.exception.DukeEmptyDeadlineException;
import duke.exception.DukeEmptyEventException;
import duke.exception.DukeEmptyToDoException;
import duke.exception.DukeException;
import duke.exception.DukeInvalidCommandException;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.TaskList;
import duke.task.ToDo;

public class Parser {
    private Ui ui;

    public Parser(Ui ui) {
        this.ui = ui;
    }

    public void execute(String command, String description, TaskList tasks) {
        try {
            if (command.equalsIgnoreCase("list")) {
                ui.listTasks(tasks);
            } else if (command.equalsIgnoreCase("mark")) {
                int index = Integer.parseInt(description.substring(1)) - 1;
                tasks.mark(index);
            } else if (command.equalsIgnoreCase("unmark")) {
                int index = Integer.parseInt(description.substring(1)) - 1;
                tasks.unmark(index);
            } else if (command.equalsIgnoreCase("delete")) {
                int index = Integer.parseInt(description.substring(1)) - 1;
                tasks.delete(index);
            } else if (command.equalsIgnoreCase("todo")) {
                if (description.isEmpty()) {
                    throw new DukeEmptyToDoException();
                }
                tasks.add(new ToDo(description));
            } else if (command.equalsIgnoreCase("deadline")) {
                if(description.isEmpty()) {
                    throw new DukeEmptyDeadlineException();
                }
                String[] input = description.split(" /by ");
                tasks.add(new Deadline(input[0], input[1]));
            } else if (command.equalsIgnoreCase("event")) {
                if(description.isEmpty()) {
                    throw new DukeEmptyEventException();
                }
                String[] input = description.split(" /at ");
                tasks.add(new Event(input[0], input[1]));
            } else if (command.equalsIgnoreCase("help")) {
                ui.helpMessage();
            } else {
                throw new DukeInvalidCommandException();
            }
        } catch (DukeException e) {
            ui.printErr(e);
        } catch (Exception e) {
            ui.printErr("Input is invalid. Type help for list of available commands");
        }
    }


}
