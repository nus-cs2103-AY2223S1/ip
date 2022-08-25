import java.time.LocalDate;

public abstract class Task {
    protected final String description;
    protected boolean isDone;

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    public String toStringWithIndex(int index) {
        return index + "." + this.toString();
    }

    public static Task createTodo(String input) throws DukeException {
        if (input.length() < 6) {
            if (input.equals("todo") || input.equals("todo ")) {
                throw new DukeException("The description of a todo cannot be empty.");
            } else {
                throw new DukeException();
            }
        }
        return new ToDo(input.substring(5), false);
    }

    public static Task createEvent(String input) throws DukeException {
        int slash = input.indexOf('/');
        if (input.length() < 7) {
            if (input.equals("event") || input.equals("event ")) {
                throw new DukeException("The description of an event cannot be empty.");
            } else {
                throw new DukeException();
            }
        } else if (slash == -1) {
            throw new DukeException("Please type the at in this format: /at dateTime");
        }
        return new Event(input.substring(6, slash - 1),
                false,
                LocalDate.parse(input.substring(slash + 4)));
    }

    public static Task createDeadline(String input) throws DukeException {
        int slash = input.indexOf('/');
        if (input.length() < 10) {
            if (input.equals("deadline") || input.equals("deadline ")) {
                throw new DukeException("The description of a deadline cannot be empty.");
            } else {
                throw new DukeException();
            }
        } else if (slash == -1) {
            throw new DukeException("Please type the at in this format: /by dateTime");
        }
        return new Deadline(input.substring(9, slash - 1),
                false,
                LocalDate.parse(input.substring(slash + 4)));
    }

    public static Task createTask(String input) throws DukeException {
        Task task;
        if (input.startsWith("todo")) {
            task = Task.createTodo(input);
        } else {
            if (input.startsWith("event")) {
                task = Task.createEvent(input);
            } else if (input.startsWith("deadline")) {
                task = Task.createDeadline(input);
            } else {
                throw new DukeException();
            }
        }
        return task;
    }

    public abstract String getSaveFormat();

    public static Task fileLineToTask(String fileLine) {
        String delimiter = " \\| ";
        String[] strings = fileLine.split(delimiter, 3);
        boolean isDone = strings[1].equals("1");
        if (strings[0].equals("T")) {
            return new ToDo(strings[2], isDone);
        } else if (strings[0].equals("D")) {
            return new Deadline(strings[2], isDone, LocalDate.parse(strings[3]));
        } else {
            return new Event(strings[2], isDone, LocalDate.parse(strings[3]));
        }
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + description;
    }
}