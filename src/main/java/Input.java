import java.util.Optional;

public class Input {
    private String cmd;
    private Optional<String> task;

    public Input(String str) {
        String[] split = str.split(" ", 2);
        this.cmd = split[0];
        if (split.length > 1 && !split[1].equals("")) {
            this.task = Optional.of(split[1]);
        } else {
            this.task = Optional.empty();
        }
    }

    public String getCmd() {
        return this.cmd;
    }

    public int getTaskNumber() throws DukeException {
        if (task.isEmpty()) {
            throw new DukeException("Error! No task found.");
        } else {
            return Integer.parseInt(task.get()) - 1;
        }
    }

    public String getEventTask() throws DukeException {
        if (task.isEmpty()) {
            throw new DukeException("Description of an event not found. Please provide one.");
        } else {
            return task.get().split("/at", 2)[0];
        }
    }

    public String getDeadlineTask() throws DukeException {
        if (task.isEmpty()) {
            throw new DukeException("Description of a deadline not found. Please provide one.");
        } else {
            return task.get().split("/by", 2)[0];
        }
    }

    public String getToDoTask() throws DukeException {
        if (task.isEmpty()) {
            throw new DukeException("Description of ToDo not found. Please provide one.");
        } else {
            return task.get();
        }
    }

    public String getEventTime() throws DukeException {
        if (task.isEmpty()) {
            throw new DukeException("Description of an event not found. Please provide one.");
        } else {
            String[] splitEvent = task.get().split("/at", 2);
            if (splitEvent.length == 1) {
                throw new DukeException("Time of event not found. Please provide a time.");
            } else {
                return splitEvent[1];
            }
        }
    }

    public String getDeadlineTime() throws DukeException {
        if (task.isEmpty()) {
            throw new DukeException("Description of deadline not found. Please provide one.");
        } else {
            String[] splitEvent = task.get().split("/by", 2);
            if (splitEvent.length == 1) {
                throw new DukeException("Time of deadline not found. Please provide a time.");
            } else {
                return splitEvent[1];
            }
        }
    }

    public Command getCommand() throws DukeException {
        if (cmd.equals("mark")) {
            return new MarkCommand(getTaskNumber());
        } else if (cmd.equals("unmark")) {
            return new UnmarkCommand(getTaskNumber());
        } else if (cmd.equals("bye")) {
            return new ByeCommand();
        } else if (cmd.equals("list")) {
            return new ListCommand();
        } else if (cmd.equals("todo")) {
            return new ToDoCommand(getToDoTask());
        } else if (cmd.equals("event")) {
            return new EventCommand(getEventTask(), getEventTime());
        } else if (cmd.equals("deadline")) {
            return new DeadlineCommand(getDeadlineTask(), getDeadlineTime());
        } else if (cmd.equals("delete")) {
            return new DeleteCommand(getTaskNumber());
        } else {
            throw new DukeException("Unknown command. Please enter a valid command");
        }
    }
}
