package pixel.task;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Date;
import java.util.Locale;

import pixel.util.DateValidator;
import pixel.util.UserInterface;

/**
 * Represents a Task
 * Has three types, Event, Deadline and ToDo
 * Users can set deadlines to tasks
 * Tasks can be marked and unmarked as done
 * Tasks can be deleted
 */
public class Task {

    protected final String due; // can be accessed by subclasses
    protected final String commandWord;
    private final String description;
    private boolean isDone = false;

    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.US)
        .withResolverStyle(ResolverStyle.SMART);

    private final DateValidator validator = new DateValidator(dateFormatter);

    /**
     * Constructor for a new Task object -- will only be called through its subclasses
     *
     * @param description description of the task
     * @param due due day/ date and time of the task
     * @param commandWord "at" or "by"
     */
    public Task(String description, String due, String commandWord) {
        this.description = description;
        this.due = processDateTime(due);
        this.commandWord = commandWord;
    }

    /**
     * converts input date&time from yyyy-MM-dd HHmm to MONTH dd yyyy hh:mm aa format
     *
     * @param due due date and time
     * @return date and time in MONTH dd yyyy hh:mm aa format
     */
    private String processDateTime(String due) {
        String[] tempStringArray = due.strip().split(" ", 2);

        try {
            String dueDate = tempStringArray[0];
            String dueTime = tempStringArray.length < 2 ? "" : tempStringArray[1];

            if (validator.isValid(dueDate)) {
                LocalDate inputDue = LocalDate.parse(dueDate);
                String year = String.valueOf(inputDue.getYear());
                String month = String.valueOf(inputDue.getMonth());
                String date = String.valueOf(inputDue.getDayOfMonth());

                //time pattern of input date in 24 hour format -- HH for 24h, hh for 12h
                DateFormat timeFormat = new SimpleDateFormat("HHmm");

                //Date/time pattern of desired output date
                DateFormat outputFormat = new SimpleDateFormat("hh:mm aa"); // aa for AM/ PM
                Date oldTimeFormat = timeFormat.parse(dueTime);
                String finalTimeFormat = outputFormat.format(oldTimeFormat);

                return month + " " + date + " " + year + " " + finalTimeFormat;

            } else {
                System.out.println("(Note: Due is not in yyyy-MM-dd(SPACE)HHmm format)");
                return due;
            }

        } catch (DateTimeParseException e) {
            return ("Please ensure that your date & time input are in yyyy-MM-dd(SPACE)HHmm(24h) format \n"
                + UserInterface.PROMPT_MESSAGE);

        } catch (IndexOutOfBoundsException e) {
            return ("Please ensure that you have entered both date and time in yyyy-MM-dd(SPACE)HHmm(24h) format \n"
                + UserInterface.PROMPT_MESSAGE);

        } catch (ParseException e) {
            return ("Caught parse exception! \n"
                + UserInterface.AFTER_INVALID_INPUT + "\n"
                + UserInterface.PROMPT_MESSAGE);

        } catch (Exception e) {
            return ("Some other error occurred \n"
                + UserInterface.AFTER_INVALID_INPUT + "\n"
                + UserInterface.PROMPT_MESSAGE);
        }
    }

    /**
     * Marks task as done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks task as not done
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Converts the task to desired format before written to file
     *
     * @return string representation of task to be saved to file
     */
    public String formatTaskBeforeSave() {
        String isTaskDone = this.isDone ? "Done" : "Not Done";
        String tag = "";

        if (this instanceof Event) {
            tag = Event.TAG;
            assert tag == "E" : "event tag should be E";
        } else if (this instanceof Deadline) {
            tag = Deadline.TAG;
            assert tag == "D" : "deadline tag should be D";
        } else if (this instanceof ToDo) {
            tag = ToDo.TAG;
            assert tag == "T" : "todo tag should be T";
        }

        String taskToString = tag + " ;;; " + isTaskDone + " ;;; "
            + this.description + " ;;; " + this.commandWord + " ;;; " + this.due;
        return taskToString;
    }

    /**
     * toString method
     *
     * @return String representation of the Task object
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }

    public String getDescription() {
        return this.description;
    }

    private String getStatusIcon() {
        return (this.isDone ? "X" : " "); // mark done task with X
    }

}

