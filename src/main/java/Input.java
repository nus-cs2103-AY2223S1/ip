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

    String getCmd() {
        return this.cmd;
    }

    int getTaskNumber() throws DukeException {
        if (task.isEmpty()) {
            throw new DukeException("Error! No task found.");
        } else {
            return Integer.parseInt(task.get()) - 1;
        }
    }

    String getEventTask() throws DukeException {
        if (task.isEmpty()) {
            throw new DukeException("Description of an event not found. Please provide one.");
        } else {
            return task.get().split("/at", 2)[0];
        }
    }

    String getDeadlineTask() throws DukeException {
        if (task.isEmpty()) {
            throw new DukeException("Description of a deadline not found. Please provide one.");
        } else {
            return task.get().split("/by", 2)[0];
        }
    }

    String getToDoTask() throws DukeException {
        if (task.isEmpty()) {
            throw new DukeException("Description of ToDo not found. Please provide one.");
        } else {
            return task.get();
        }
    }

    String getEventTime() throws DukeException {
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

    String getDeadlineTime() throws DukeException {
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

}
