package betago;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import betago.tasks.Deadline;
import betago.tasks.Event;
import betago.tasks.Task;
import betago.tasks.Todo;

/**
 * TaskList class that stores Tasks in an ArrayList.
 */
public class TaskList {
    private ArrayList<Task> list;

    /**
     * Constructor for TaskList.
     * Initialises ArrayList variable.
     */
    public TaskList() {
        this.list = new ArrayList<>();
    }

    /**
     * Gets the task in the specific index of the ArrayList.
     *
     * @param index Index of the task to be returned.
     * @return Task in the index of the ArrayList.
     */
    public Task get(int index) {
        return this.list.get(index);
    }

    /**
     * Returns the number of items in the current list.
     *
     * @return Number of items in the ArrayList.
     */
    public int size() {
        return this.list.size();
    }

    /**
     * Returns the list of Tasks in the current TaskList.
     *
     * @return String representation of the items in the list.
     */
    public String listItems() {
        if (list.size() == 0) {
            return "Your list is currently empty.\n";
        }
        String output = "Here are the tasks in your list:\n";
        for (int i = 0; i < this.list.size(); i++) {
            output = output + (i + 1) + ". " + this.list.get(i).toString() + "\n";
        }
        return output;
    }

    /**
     * Marks or unmarks the Task in the specific index of the TaskList.
     *
     * @param str Mark or Unmark command that the user provided.
     * @throws DukeException If user inputs an empty or invalid task number.
     */
    public String markUnmarkItems(String str) throws DukeException {
        String[] inputs = str.split(" ", 2);
        String output;
        if (inputs.length != 2) {
            throw new DukeException("Please indicate the task number that you want to mark/unmark.");
        } else {
            try {
                int marker = Integer.valueOf(inputs[1]);
                if (marker < 1 || marker > this.list.size()) {
                    throw new DukeException("Please indicate a valid task number that you want to mark/unmark.");
                } else if (inputs[0].equalsIgnoreCase("mark")) {
                    this.list.get(marker - 1).markAsDone();
                    output = "Nice! I've marked this task as done:\n"
                            + this.list.get(marker - 1).toString() + "\n";
                    return output;
                } else {
                    this.list.get(marker - 1).markAsNotDone();
                    output = "Nice! I've marked this task as not done yet:\n"
                            + this.list.get(marker - 1).toString() + "\n";
                    return output;
                }
            } catch (NumberFormatException ex) {
                throw new DukeException("Please indicate a valid task number.");
            }
        }
    }

    /**
     * Adds a Todo Task in the ArrayList.
     *
     * @param str Add todo command that the user provided.
     * @throws DukeException If no description is provided.
     */
    public String addTodo(String str) throws DukeException {
        String[] inputs = str.split(" ", 2);
        String output;
        if (inputs.length != 2) {
            throw new DukeException("Please indicate a task description in this format: 'todo (description)' \n");
        } else {
            Todo temp = new Todo(inputs[1]);
            this.list.add(temp);
            output = "Got it. I've added this Todo task:\n" + temp.toString() + "\n"
                    + "Now you have " + this.list.size() + " tasks in the list.\n";
            return output;
        }
    }

    /**
     * Adds a Deadline Task in the ArrayList.
     *
     * @param str Add deadline command that the user provided.
     * @throws DukeException If no description or deadline provided.
     */
    public String addDeadline(String str) throws DukeException {
        String[] inputs = str.split(" ", 2);
        String output;
        if (inputs.length != 2) {
            throw new DukeException("Please indicate a task description for your Deadline task"
                    + " in this format: 'deadline (description) /by (date) (time)'\n");
        } else {
            String[] when = inputs[1].split(" /by ", 2);
            if (when.length != 2) {
                throw new DukeException("Please indicate a valid deadline for your Deadline task "
                        + "in this format: 'deadline (description) /by (date) (time)'\n"
                        + "Please enter the date in one of the following format:"
                        + " yyyy-MM-dd, dd-MMM-yyyy, dd/MM/yyyy\n");
            } else {
                Deadline temp = new Deadline(when[0], when[1]);
                this.list.add(temp);
                output = "Got it. I've added this Deadline task:\n" + temp.toString() + "\n"
                        + "Now you have " + this.list.size() + " tasks in the list.\n";
                return output;
            }
        }
    }

    /**
     * Adds an Event Task in the ArrayList.
     *
     * @param str Add event command that the user provided.
     * @throws DukeException If no description or location provided.
     */
    public String addEvent(String str) throws DukeException {
        String[] inputs = str.split(" ", 2);
        String output;
        if (inputs.length != 2) {
            throw new DukeException("Please indicate a task description for your Event task"
                    + " in this format: 'deadline (description) /at (date) (time)'\n");
        } else {
            String[] when = inputs[1].split(" /at ", 2);
            if (when.length != 2) {
                throw new DukeException("Please indicate a valid date and time for your Event task!\n"
                        + "Do enter the command in this format: 'deadline (description) /at (date) (time)'\n");
            } else {
                Event temp = new Event(when[0], when[1]);
                this.list.add(temp);
                output = "Got it. I've added this Event task:\n" + temp.toString() + "\n"
                        + "Now you have " + this.list.size() + " tasks in the list.\n";
                return output;
            }
        }
    }

    /**
     * Deletes the Task in the specific index of the ArrayList.
     *
     * @param str Delete command that the user provided.
     * @throws DukeException If no task number is provided or task number is out of range.
     */
    public String deleteItems(String str) throws DukeException {
        String[] inputs = str.split(" ", 2);
        String output;
        if (inputs.length != 2) {
            throw new DukeException("Please indicate the task number that you want to delete.");
        } else {
            try {
                int marker = Integer.valueOf(inputs[1]);
                if (marker < 1 || marker > this.list.size()) {
                    output = "Please indicate a valid task number.\n";
                    return output;
                } else {
                    output = "Noted. I have removed this task:\n" + this.list.get(marker - 1).toString() + "\n";
                    this.list.remove(marker - 1);
                    output += "Now you have " + this.list.size() + " tasks in the list.\n";
                    return output;
                }
            } catch (NumberFormatException ex) {
                throw new DukeException("Please indicate a valid task number.");
            }
        }
    }

    /**
     * Read todo task from the data file and add a todo task accordingly into the TaskList.
     *
     * @param str Line of text from the data file to load task.
     * @throws DukeException If marker is not 1 or 0, or str is of the wrong format.
     */
    public void loadTodo(String str) throws DukeException {
        String[] inputs = str.split(" , ", 3);
        String output;
        if (inputs.length != 3) {
            throw new DukeException("Invalid Input from Data File: Insufficient details");
        } else {
            Todo temp = new Todo(inputs[2]);
            if (inputs[1].equalsIgnoreCase("1")) {
                temp.markAsDone();
            } else if (inputs[1].equalsIgnoreCase("0")) {
                temp.markAsNotDone();
            } else {
                throw new DukeException("Invalid Input from Data File: Incorrect marker");
            }
            this.list.add(temp);
        }
    }

    /**
     * Read deadline task from the data file and add a deadline task accordingly into the TaskList.
     *
     * @param str Line of text from the data file to load task.
     * @throws DukeException If marker is not 1 or 0, or str is of the wrong format.
     */
    public void loadDeadline(String str) throws DukeException {
        String[] inputs = str.split(" , ", 4);
        if (inputs.length != 4) {
            throw new DukeException("Invalid Input from Data File: Insufficient details");
        }
        try {
            String[] dateTime = inputs[3].split(",", 2);
            Deadline temp;
            if (dateTime.length == 2) {
                LocalDate d = LocalDate.parse(dateTime[0], DateTimeFormatter.ofPattern("MMM d yyyy"));
                String deadlineDateTime = d.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                deadlineDateTime = deadlineDateTime + dateTime[1];
                temp = new Deadline(inputs[2], deadlineDateTime);
            } else {
                LocalDate d = LocalDate.parse(dateTime[0], DateTimeFormatter.ofPattern("MMM d yyyy"));
                String deadlineDateTime = d.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                temp = new Deadline(inputs[2], deadlineDateTime);
            }
            if (inputs[1].equalsIgnoreCase("1")) {
                temp.markAsDone();
            } else if (inputs[1].equalsIgnoreCase("0")) {
                temp.markAsNotDone();
            } else {
                throw new DukeException("Invalid Input from Data File: Incorrect marker");
            }
            this.list.add(temp);
        } catch (DukeException e) {
            throw new DukeException(
                    "Invalid Input from Data File: Invalid Deadline Task");
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid Input from Data File: Incorrect Date/Time");
        }
    }

    /**
     * Read event task from the data file and add an event task accordingly into the TaskList.
     *
     * @param str Line of text from the data file to load task.
     * @throws DukeException If marker is not 1 or 0, or str is of the wrong format.
     */
    public void loadEvent(String str) throws DukeException {
        String[] inputs = str.split(" , ", 4);
        if (inputs.length != 4) {
            throw new DukeException("Invalid Input from Data File: Insufficient details");
        }
        try {
            String[] dateTime = inputs[3].split(",", 2);
            Event temp;
            if (dateTime.length == 2) {
                LocalDate d = LocalDate.parse(dateTime[0], DateTimeFormatter.ofPattern("MMM d yyyy"));
                String eventDateTime = d.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                eventDateTime = eventDateTime + dateTime[1];
                temp = new Event(inputs[2], eventDateTime);
            } else {
                LocalDate d = LocalDate.parse(dateTime[0], DateTimeFormatter.ofPattern("MMM d yyyy"));
                String eventDateTime = d.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                temp = new Event(inputs[2], eventDateTime);
            }
            if (inputs[1].equalsIgnoreCase("1")) {
                temp.markAsDone();
            } else if (inputs[1].equalsIgnoreCase("0")) {
                temp.markAsNotDone();
            } else {
                throw new DukeException("Invalid Input from Data File: Incorrect marker");
            }
            this.list.add(temp);
        } catch (DukeException e) {
            throw new DukeException(
                    "Invalid Input from Data File: Invalid Deadline Task");
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid Input from Data File: Incorrect Date/Time");
        }
    }

    /**
     * Find tasks that match the keyword inputted by user.
     *
     * @param str Line of text command from user including keyword to search.
     * @throws DukeException If no keyword is provided.
     */
    public String findTasks(String str) throws DukeException {
        String[] inputs = str.split(" ", 2);
        String output;
        if (inputs.length != 2) {
            throw new DukeException("Please indicate a keyword to search in this format: 'find (keyword)'\n");
        } else {
            ArrayList<Task> matched = new ArrayList<>();
            for (int i = 0; i < this.list.size(); i++) {
                if (this.list.get(i).containKeyword(inputs[1])) {
                    matched.add(this.list.get(i));
                }
            }
            if (matched.size() == 0) {
                output = "There are no matching tasks found.\n";
                return output;
            } else {
                output = "Here are the matching tasks in your list:\n";
                for (int i = 0; i < matched.size(); i++) {
                    output = output + (i + 1) + ". " + matched.get(i).toString() + "\n";
                }
                return output;
            }
        }
    }
}
