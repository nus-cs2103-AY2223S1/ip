package duke;

import commands.*;

public class Parser {

    private final Ui ui;

    private TaskList tasks;
    private boolean isOpen;
    public Parser(Ui ui, TaskList tasks) {
        this.ui = ui;
        this.tasks = tasks;
        this.isOpen = true;
    }

    public boolean getIsOpen() {
        return isOpen;
    }

    public Command read(String input) throws DukeException {
        if (input.equals("bye")) {
            this.isOpen = false;
            return new ByeCommand(this.ui);
        } else if (input.equals("list")) {
            return new ListCommand(this.tasks, this.ui);
        } else if (input.startsWith("mark")) {
            int target = Integer.valueOf(input.split(" ")[1]) - 1;
            return new MarkCommand(this.ui, this.tasks, target, true);
        } else if (input.startsWith("unmark")) {
            int target = Integer.valueOf(input.split(" ")[1]) - 1;
            return new MarkCommand(this.ui, this.tasks, target, false);
        } else if (input.startsWith("todo")) {
            String[] info = input.split("todo ");
            if (info.length <= 1) {
                throw new DukeException("OOPS!! The description of a todo cannot be empty.");
            }
            return new AddTaskCommand(tasks, new ToDo(info[1]), ui);
        } else if (input.startsWith("deadline")) {
            String[] info = input.split("deadline ");
            if (info.length <= 1) {
                throw new DukeException("OOPS!! The description of a deadline cannot be empty.");
            }
            String[] item = info[1].split(" /by ");
            if (item.length <= 1) {
                throw new DukeException("OOPS!! A deadline has to be set!");
            }
            return new AddTaskCommand(tasks, new Deadline(item[0], item[1]), ui);
        } else if (input.startsWith("event")) {
            String[] info = input.split("event ");
            if (info.length <= 1) {
                throw new DukeException("OOPS!! The description of a event cannot be empty.");
            }
            String[] item = info[1].split(" /at ");
            if (item.length <= 1) {
                throw new DukeException("OOPS!! The timing of the event has to be set!");
            }
            return new AddTaskCommand(tasks, new Event(item[0], item[1]), ui);
        } else if (input.startsWith("delete")) {
            String[] info = input.split("delete ");
            if (info.length <= 1) {
                throw new DukeException("OOPS!! The item to be deleted has to be specified.");
            }
            int target = Integer.valueOf(info[1]) - 1;
            return new DeleteCommand(tasks, target, ui);
        } else if (input.startsWith("find")) {
            String[] info = input.split("find ");
            if (info.length <= 1) {
                throw new DukeException("Please specify what you would like to find.");
            }
            return new FindCommand(tasks, info[1], ui);
        } else {
            return new UnknownCommand(ui);
        }

    }
}
