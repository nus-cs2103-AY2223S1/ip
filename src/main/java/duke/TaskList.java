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
    private ArrayList<Task> tasks;
    private int count;
    private static Ui ui = new Ui();

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
     * Mark task in arraylist to done.
     *
     * @param index index of task to mark, in 1 indexing.
     */
    public void mark(int index) {
        tasks.get(index - 1).mark();
    }

    /**
     * Unmark task in arraylist to not done.
     *
     * @param index index of task to unmark, in 1 indexing.
     */
    public void unmark(int index) {
        tasks.get(index - 1).unmark();
    }

    /**
     * Adds new task into arraylist.
     *
     * @param str command input by user with information.
     * @param type enum of task type.
     * @return String containing message to be displayed
     */
    public String add(String str, Duke.Type type) {
        String message = "";
        // add new todo task
        if (type.equals(Duke.Type.TODO)) {
            try {
                tasks.add(new Todo(str));
                count++;
                ui.printMessage(String.format("Got it. I've added this task:\n" +
                                "%s\n" +
                                "Now you have %d tasks in the list.",
                        tasks.get(count - 1).toString(),
                        count));
                message =  String.format("Got it. I've added this task:\n" +
                                "%s\n" +
                                "Now you have %d tasks in the list.",
                        tasks.get(count - 1).toString(),
                        count);

            // missing name
            } catch (MissingDescriptionException err) {
                ui.printMessage(err.toString());
                message = err.toString();
            }

        // add new deadline task
        } else if (type.equals(Duke.Type.DEADLINE)) {
            try {
                String[] input = str.split("/by ");
                String name = input[0].replace("deadline", "");
                LocalDate date = LocalDate.parse(input[1]);
                tasks.add(new Deadline(name, date));
                count++;
                ui.printMessage(String.format("Got it. I've added this task:\n" +
                                "%s\n" +
                                "Now you have %d tasks in the list.",
                        tasks.get(count - 1).toString(),
                        count));
                message = String.format("Got it. I've added this task:\n" +
                                "%s\n" +
                                "Now you have %d tasks in the list.",
                        tasks.get(count - 1).toString(),
                        count);
            // missing name
            } catch (MissingDescriptionException err) {
                ui.printMessage("OOPS!!! The description of a deadline cannot be empty.");
                message = "OOPS!!! The description of a deadline cannot be empty.";

            // missing end date
            } catch (ArrayIndexOutOfBoundsException e) {
                ui.printMessage("OOPS!!! The end date of a deadline cannot be empty.");
                message = "OOPS!!! The end date of a deadline cannot be empty.";

            // date in wrong format
            } catch (DateTimeParseException e) {
                ui.printMessage("input date in YYYY-MM-DD format!");
                message = "input date in YYYY-MM-DD format!";
            }

        // add new event task
        } else if (type.equals(Duke.Type.EVENT)) {
            try{
                String[] input = str.split("/at ");
                String name = input[0].replace("event", "");
                String[] end = input[1].split(" ");
                String date = end[0];
                LocalDate dateParsed = LocalDate.parse(date);
                String time = end[1];
                LocalTime timeParsed = LocalTime.parse(time);
                LocalDateTime dateTime = LocalDateTime.of(dateParsed, timeParsed);
                tasks.add(new Event(name, dateTime));
                count++;
                ui.printMessage(String.format("Got it. I've added this task:\n" +
                                "%s\n" +
                                "Now you have %d tasks in the list.",
                        tasks.get(count - 1).toString(),
                        count));
                message = String.format("Got it. I've added this task:\n" +
                                "%s\n" +
                                "Now you have %d tasks in the list.",
                        tasks.get(count - 1).toString(),
                        count);

            // missing name
            } catch (MissingDescriptionException err) {
                ui.printMessage("OOPS!!! The description of an event cannot be empty.");
                message = "OOPS!!! The description of an event cannot be empty.";

            // missing date or time
            } catch (ArrayIndexOutOfBoundsException e) {
                ui.printMessage("OOPS!!! The time of an event cannot be empty.");
                message = "OOPS!!! The time of an event cannot be empty.";

            // date or time in wrong format
            } catch (DateTimeParseException e) {
                ui.printMessage("Input date in YYYY-MM-DD and time in HH:MM format");
                message = "Input date in YYYY-MM-DD and time in HH:MM format";
            }
        }
        return message;
    }

    /**
     * Deletes tasks from arraylist.
     *
     * @param command command input by user, with index to delete.
     * @return String containing message to be displayed
     */
    public String delete(String command) {
        String message = "";
        try {
            int index = Integer.valueOf(command.split(" ")[1]);
            Task task = tasks.remove(index - 1);
            count--;
            String str =  String.format("Noted. I've removed this task:\n" +
                            "%s\n" +
                            "Now you have %d tasks in the list.",
                    task.toString(),
                    count);
            ui.printMessage(str);
            message = str;
        // no task found
        } catch (IndexOutOfBoundsException e) {
            ui.printMessage("OOPS!!! No such task exists.");
            message = "OOPS!!! No such task exists.";
        }
        return message;
    }

    /**
     * Return string representation of specific task in arraylist.
     *
     * @param index index of task to get the string representation of, in 1 indexing.
     * @return String representation of specific task.
     */
    public String getString(int index) {
        return tasks.get(index - 1).toString();
    }

    /**
     * Return string representation of tasks to be written into text file.
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
     * @param keyword word to search in tasks.
     * @return String containing message to be displayed
     */
    public String find(String keyword) {
        String message = "";
        ArrayList<Task> matches = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Task task = tasks.get(i);
            if (task.contains(keyword)) {
                matches.add(task);
            }
        }
        TaskList match = new TaskList(matches);
        String print = String.format("Here are the matching tasks in your list:\n%s", match.toString());
        ui.printMessage(print);
        return print;
    }

    /**
     * Returns string representation of all tasks with indexing.
     *
     * @return string representation of all tasks.
     */
    @Override
    public String toString() {
        String text = "";
        for (int i = 0; i < count; i++) {
            String index = String.format("%d.", i+1);
            String item = index + tasks.get(i).toString();
            text += item;
            text += "\n";
        }
        return text;
    }
}
