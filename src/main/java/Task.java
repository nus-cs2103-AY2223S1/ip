import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public abstract class Task {
    private String description;
    private boolean isDone;

    /**
     * Constructor to create new Task
     * 
     * @param description Task Description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Checks if the input string contains a description
     * 
     * @param splitInput String split by " " and limited to 2
     * @throws DukeException if no description is present
     */
    public static void checkDescription(String[] splitInput) throws DukeException {
        if (splitInput.length == 1) {
            throw new DukeException("Please enter the description when creating new task!");
        }
    }

    /**
     * Factory method to create new Task
     * 
     * @param input String containing task type, description and deadline/timing if
     *              needed
     * @return new Task
     * @throws DukeException if invalid task type or wrong parameters
     */
    public static Task createTask(String input) throws DukeException {
        String[] splitInput = input.split(" ", 2);

        switch (Command.valueOf(splitInput[0])) {
        case todo:
            checkDescription(splitInput);
            return Todo.createTodo(splitInput[1]);
        case deadline:
            checkDescription(splitInput);
            return Deadline.createDeadline(splitInput[1]);
        case event:
            checkDescription(splitInput);
            return Event.createEvent(splitInput[1]);
        default:
            throw new DukeException("I'm sorry but I don't know what that means.");
        }
    }

    public String getStatusIcon() {
        return this.isDone ? "X" : " ";
    }

    public void markAsDone() {
        this.isDone = true;
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("\t" + this);
    }

    public void markAsNotDone() {
        this.isDone = false;
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("\t" + this);
    }

    public static Date parseDate(String input) throws DukeException {
        SimpleDateFormat formatter;
        if (input.indexOf("/") != 1) {
            formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        } else if (input.indexOf("-") != 1) {
            formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        } else {
            throw new DukeException(
                    "Please make sure your date is in `dd/MM/yyyy HH:mm` or `dd-MM-yyyy HH:mm` format.");
        }
        try {
            return formatter.parse(input);
        } catch (ParseException e) {
            throw new DukeException(
                    "Please make sure your date is in `dd/MM/yyyy HH:mm` or `dd-MM-yyyy HH:mm` format.");
        }
    }

    public static String formatDate(Date date) {
        SimpleDateFormat standardDateFormat = new SimpleDateFormat("EEE, MMM d, hh:mm a");
        return standardDateFormat.format(date);
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

}
