package duke;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * to contain list of tasks in Duke.
 */
public class TaskList {
    private static Ui ui = new Ui();
    private ArrayList<Task> tasks;
    private int count;

    /**
     * Constructor for new tasklist instance.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
        this.count = 0;
    }

    /**
     * Constructor for new tasklist instance from arraylist of tasks.
     *
     * @param tasks ArrayList of tasks to copy over.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
        this.count = tasks.size();
    }

    /**
     * Marks task in arraylist to done.
     *
     * @param index index of task to mark, in 1 indexing.
     */
    public String mark(int index) {
        tasks.get(index - 1).mark();
        return "Nice! I've marked this task as done:\n" + getString(index);
    }

    /**
     * Unmarks task in arraylist to not done.
     *
     * @param index index of task to unmark, in 1 indexing.
     */
    public String unmark(int index) {
        tasks.get(index - 1).unmark();
        return "Okay, I've marked this task as undone:\n" + getString(index);
    }

    /**
     * Adds new Todo into arraylist.
     *
     * @param command input by user.
     * @return message to be displayed.
     */
    public String addTodo(String command) {
        try {
            String description = command.replace("todo", "");
            tasks.add(new Todo(description));
            count++;
            return String.format("Got it. I've added this task:\n"
                            + "%s\n"
                            + "Now you have %d tasks in the list.",
                    tasks.get(count - 1).toString(),
                    count);

            // missing name
        } catch (MissingDescriptionException err) {
            return err.toString();
        }
    }

    /**
     * Adds new Deadline into arraylist.
     *
     * @param command input by user.
     * @return message to be displayed.
     */
    public String addDeadline(String command) {
        try {
            String[] input = command.split("/by ");
            String name = input[0].replace("deadline", "");
            LocalDate date = LocalDate.parse(input[1]);
            tasks.add(new Deadline(name, date));
            count++;
            return String.format("Got it. I've added this task:\n"
                            + "%s\n"
                            + "Now you have %d tasks in the list.",
                    tasks.get(count - 1).toString(),
                    count);
            // missing name
        } catch (MissingDescriptionException err) {
            return "OOPS!!! The description of a deadline cannot be empty.";

            // missing end date
        } catch (ArrayIndexOutOfBoundsException e) {
            return "OOPS!!! The end date of a deadline cannot be empty.";

            // date in wrong format
        } catch (DateTimeParseException e) {
            return "input date in YYYY-MM-DD format!";
        }
    }

    /**
     * Adds new Event into arraylist.
     *
     * @param command input by user.
     * @return message to be displayed.
     */
    public String addEvent(String command) {
        try {
            String[] input = command.split("/at ");
            String name = input[0].replace("event", "");
            String[] end = input[1].split(" ");
            String date = end[0];
            LocalDate dateParsed = LocalDate.parse(date);
            String time = end[1];
            LocalTime timeParsed = LocalTime.parse(time);
            LocalDateTime dateTime = LocalDateTime.of(dateParsed, timeParsed);
            tasks.add(new Event(name, dateTime));
            count++;
            return String.format("Got it. I've added this task:\n"
                            + "%s\n"
                            + "Now you have %d tasks in the list.",
                    tasks.get(count - 1).toString(),
                    count);

            // missing name
        } catch (MissingDescriptionException err) {
            return "OOPS!!! The description of an event cannot be empty.";

            // missing date or time
        } catch (ArrayIndexOutOfBoundsException e) {
            return "OOPS!!! The time of an event cannot be empty.";

            // date or time in wrong format
        } catch (DateTimeParseException e) {
            return "Input date in YYYY-MM-DD and time in HH:MM format";
        }
    }

    /**
     * Deletes tasks from arraylist.
     *
     * @param command command input by user, with index to delete.
     * @return String containing message to be displayed.
     */
    public String delete(String command) {
        try {
            int index = Integer.valueOf(command.split(" ")[1]);
            Task task = tasks.remove(index - 1);
            count--;
            return String.format("Noted. I've removed this task:\n"
                            + "%s\n"
                            + "Now you have %d tasks in the list.",
                    task.toString(),
                    count);
        // no task found
        } catch (IndexOutOfBoundsException e) {
            return "OOPS!!! No such task exists.";
        }
    }

    /**
     * Returns string representation of specific task in arraylist.
     *
     * @param index index of task to get the string representation of, in 1 indexing.
     * @return String representation of specific task.
     */
    public String getString(int index) {
        return tasks.get(index - 1).toString();
    }

    /**
     * Returns string representation of tasks to be written into text file.
     *
     * @return string representation of tasks to be written into text file.
     */
    public String toData() {
        String data = "";
        for (Task task : tasks) {
            data += task.toData();
            data += "\n";
        }
        return data;
    }

    /**
     * Searches and prints out matching tasks.
     *
     * @param command input by user.
     * @return String containing message to be displayed.
     */
    public String find(String command) {
        String keyword = command.replace("find", "").strip();

        if (keyword.equals("") || keyword.equals(" ")) {
            return "Please input a keyword!";
        }
        ArrayList<Task> matches = new ArrayList<>();
        int numberOfMatches = 0;
        for (int i = 0; i < count; i++) {
            Task task = tasks.get(i);
            if (task.contains(keyword)) {
                matches.add(task);
                numberOfMatches++;
            }
        }

        if (numberOfMatches == 0) {
            return "No matching tasks found.";
        }

        TaskList match = new TaskList(matches);
        String print = String.format("Here are the matching tasks in your list:\n%s", match);
        assert !print.isEmpty() : "Should not have empty response when searching tasks";
        return print;

    }

    /**
     * Sets the priority of the task in the given index to the input level.
     *
     * @param command input by user.
     * @return message to be displayed to the user.
     */
    public String setPriority(String command) {
        try {
            String info = command.replace("priority ", "");
            String[] instructions = info.split(" ", 2);

            if (info.equals("") || info.equals("priority") || instructions.length != 2) {
                return "Input index of task and priority level! e.g. priority 1 high";
            }

            int index = Integer.parseInt(instructions[0].strip());
            String level = instructions[1].strip();
            tasks.get(index - 1).setPriority(level);
            return "Okay, I've set the priority of this task to:\n" + tasks.get(index - 1);

        } catch (DukeException e) {
            return e.toString();

        } catch (IndexOutOfBoundsException err) {
            return "OOPS!! Task does not exist!";
        }
    }

    /**
     * Returns string representation of all tasks with indexing.
     *
     * @return string representation of all tasks.
     */
    @Override
    public String toString() {
        if (count < 1) {
            return "There are no tasks at the moment!";
        }

        String text = "";
        for (int i = 0; i < count; i++) {
            String index = String.format("%d.", i + 1);
            String item = index + tasks.get(i).toString();
            text += item;
            text += "\n";
        }
        assert !text.isEmpty() : "Should not have empty response when converting list of tasks to String";
        return text;
    }
}
