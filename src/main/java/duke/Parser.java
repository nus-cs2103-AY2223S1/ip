package duke;

import commands.*;

/**
 * Reads user inputs and returns the relevant Commands.
 */
public class Parser {

    private final Ui ui;

    private TaskList tasks;
    private Storage storage;
    private boolean isOpen;

    /**
     * Returns a new Parser.
     * @param ui User Interface that prints a message to the user.
     * @param tasks The TaskList that Commands will act on.
     * @param storage Storage on which Commands will act on.
     */
    public Parser(Ui ui, TaskList tasks, Storage storage) {
        this.ui = ui;
        this.tasks = tasks;
        this.storage = storage;
        this.isOpen = true;
    }

    public boolean getIsOpen() {
        return isOpen;
    }

    /**
     * Reads user inputs and returns the relevant Commands.
     * @param input Input from users.
     * @return A new Command corresponding to user's input.
     * @throws DukeException In the event where erroneous input is provided.
     */
    public Command read(String input) throws DukeException {
        if (input.equals("bye")) {
            this.isOpen = false;
            return new ByeCommand(this.tasks, this.ui, this.storage);
        } else if (input.startsWith("update")) {
            String[] info = input.split(" ");
            try {
                int i = Integer.parseInt(info[1]);
                if (i > tasks.size()) {
                    throw new DukeException("Task #" + i + " does not exist.");
                } else {
                    Task toUpdate = tasks.get(i - 1);
                    String update = "";
                    for (int j = 2; j < info.length; j++) {
                        if (j == info.length - 1) {
                            update += info[j];
                        } else {
                            update = update + info[j] + " ";
                        }
                    }
                    return new UpdateTaskCommand(toUpdate, this.ui, update);
                }
            } catch (NumberFormatException e) {
                throw new DukeException("Wrong format! Please specify which Task you want to update.\n"
                + "e.g. update 4 [new task info]");
            }
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
            assert info.length > 1;
            return new AddTaskCommand(tasks, new ToDo(info[1]), ui);
        } else if (input.startsWith("deadline")) {
            String[] info = input.split("deadline ");
            if (info.length <= 1) {
                throw new DukeException("OOPS!! The description of a deadline cannot be empty.");
            }
            assert info.length > 1;
            String[] item = info[1].split(" /by ");
            if (item.length <= 1) {
                throw new DukeException("OOPS!! A deadline has to be set!");
            }
            assert item.length > 1;
            return new AddTaskCommand(tasks, new Deadline(item[0], item[1]), ui);
        } else if (input.startsWith("event")) {
            String[] info = input.split("event ");
            if (info.length <= 1) {
                throw new DukeException("OOPS!! The description of a event cannot be empty.");
            }
            assert info.length > 1;
            String[] item = info[1].split(" /at ");
            if (item.length <= 1) {
                throw new DukeException("OOPS!! The timing of the event has to be set!");
            }
            assert item.length > 1;
            return new AddTaskCommand(tasks, new Event(item[0], item[1]), ui);
        } else if (input.startsWith("delete")) {
            String[] info = input.split("delete ");
            if (info.length <= 1) {
                throw new DukeException("OOPS!! The item to be deleted has to be specified.");
            }
            assert info.length > 1;
            int target = Integer.valueOf(info[1]) - 1;
            return new DeleteCommand(tasks, target, ui);
        } else if (input.startsWith("find")) {
            String[] info = input.split("find ");
            if (info.length <= 1) {
                throw new DukeException("Please specify what you would like to find.");
            }
            assert info.length > 1;
            return new FindCommand(tasks, info[1], ui);
        } else {
            return new UnknownCommand(ui);
        }

    }
}
