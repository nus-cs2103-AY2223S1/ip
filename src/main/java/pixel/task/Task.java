package pixel.task;

import pixel.util.DateValidator;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Date;
import java.util.Locale;

public class Task {

    private final String description;
    private boolean isDone = false;
    protected final String due; // can be accessed by subclasses
    protected final String commandWord;

    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.US)
        .withResolverStyle(ResolverStyle.SMART);

    DateValidator validator = new DateValidator(dateFormatter);

    public Task (String description, String due, String commandWord) {
        this.description = description;
        this.due = dateTimeProcessing(due);
        this.commandWord = commandWord;
    }

    private String dateTimeProcessing(String due) {
        String temp = due;
        String[] tempStringArray = temp.strip().split(" ", 2);

        try {
            String dueDate = tempStringArray[0];
            String dueTime = tempStringArray.length < 2 ? "" : tempStringArray[1];

            if (validator.isValid(dueDate)) {
                LocalDate inputDue = LocalDate.parse(dueDate);
                String year = String.valueOf(inputDue.getYear());
                String month = String.valueOf(inputDue.getMonth());
                String date = String.valueOf(inputDue.getDayOfMonth());
                // System.out.println("date is working");

                //time pattern of input date in 24 hour format -- HH for 24h, hh for 12h
                DateFormat timeFormat = new SimpleDateFormat("HHmm");
                // System.out.println("time input is working");

                //Date/time pattern of desired output date
                DateFormat outputFormat = new SimpleDateFormat("hh:mm aa"); // aa for AM/ PM
                // System.out.println("time output is working");
                Date oldTimeFormat = timeFormat.parse(dueTime);
                String finalTimeFormat = outputFormat.format(oldTimeFormat);

                return month + " " + date + " " + year + " " + finalTimeFormat;

            } else {
                System.out.println("(Note: Due is not in yyyy-MM-dd(SPACE)HHmm format)");
                return due;
            }

        } catch (DateTimeParseException e) {
            System.out.println(e);
            System.out.println("Please ensure that your date & time input are in yyyy-MM-dd(SPACE)HHmm(24h) format");
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e);
            System.out.println("Please ensure that you have entered both date and time in yyyy-MM-dd(SPACE)HHmm(24h) format");
        } catch (ParseException e) {
            System.out.println(e);
            System.out.println("Caught parse exception!");
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Some other error occurred");
        }

        return due; // Shouldn't reach here
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    public String formatTaskBeforeSave() {
        String isTaskDone = this.isDone ? "Done" : "Not Done";
        String tag = "";

        if (this instanceof Event) {
            tag = Event.TAG;
        } else if (this instanceof Deadline) {
            tag = Deadline.TAG;
        } else if (this instanceof ToDo) {
            tag = ToDo.TAG;
        }

        String taskToString = tag + " | " + isTaskDone + " | " + this.description + "| " + this.due;
        return taskToString;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }

    private String getStatusIcon() {
        return (this.isDone ? "X" : " "); // mark done task with X
    }

}

