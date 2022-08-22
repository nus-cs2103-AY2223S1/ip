package sky.command;

import sky.Storage;
import sky.TaskList;
import sky.Ui;
import sky.exception.TextNoMeaningException;
import sky.task.Event;
import sky.task.Task;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.PatternSyntaxException;

/**
 * The EventCommand class deals with adding an event task into taskList.
 */
public class EventCommand extends Command {
    private String fullCommand;

    public EventCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            String taskEvent = this.fullCommand.substring(6);
            String[] arrOfStrings = taskEvent.split(" /at ");
            if (arrOfStrings.length != 2) {
                throw new TextNoMeaningException("  Make sure you specify \"/at\" exactly once.");
            }
            String taskDescription = arrOfStrings[0];
            String taskDurationUserInput = arrOfStrings[1];
            String taskDuration = produceDateAndTimeForEvent(taskDurationUserInput);
            Task task = new Event(taskDescription, taskDuration);
            taskList.addTask(task);
            // Add task into data file
            storage.append(task.toString());
            System.out.println("  Got it. I've added this task: \n" +
                    "    " + task +
                    "\n  Now you have " + taskList.size() +
                    (taskList.size() <= 1 ? " task in the list.": " tasks in the list."));
        } catch (IndexOutOfBoundsException e) {
            System.out.println("  You have either not entered any text after typing event, \n" +
                    "  or you have positioned your slash wrongly.");
        } catch (PatternSyntaxException e) {
            System.out.println("  There is a problem with the regex expression written by the dev.");
        } catch (TextNoMeaningException e) {
            System.out.println(e);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

    private String produceDateAndTimeForEvent(String s) throws TextNoMeaningException {
        try {
            String[] arrOfStrings = s.split(" ");
            if (arrOfStrings.length != 2) {
                throw new TextNoMeaningException("  Provide the date and time after \"/at\".");
            }
            String dateGiven = arrOfStrings[0].replaceAll("/", "-");
            LocalDate d1 = LocalDate.parse(dateGiven);
            String dateString = d1.format(DateTimeFormatter.ofPattern("MMM d yyyy"));

            String timeGiven1 = arrOfStrings[1].substring(0, 2) + ":" + arrOfStrings[1].substring(2, 4);
            String timeGiven2 = arrOfStrings[1].substring(5, 7) + ":" + arrOfStrings[1].substring(7);
            LocalTime t1 = LocalTime.parse(timeGiven1);
            LocalTime t2 = LocalTime.parse(timeGiven2);
            String timeString1 = t1.format(DateTimeFormatter.ofPattern("h:mma"));
            String timeString2 = t2.format(DateTimeFormatter.ofPattern("h:mma"));
            String finalTimeString = timeString1 + "-" + timeString2;

            return dateString + ", " + finalTimeString;
        } catch (PatternSyntaxException e) {
            System.out.println("  There is a problem with the regex expression written by the dev.");
        } catch (TextNoMeaningException e) {
            System.out.println(e);
        } catch (DateTimeParseException e) {
            System.out.println("  Date and/or time given cannot be parsed.");
            System.out.println("  Provide date and time as: \"yyyy/mm/dd XXXX-XXXX\", where XXXX is time in 24-hours.");
        } catch (DateTimeException e) {
            System.out.println("  Unable to format date and/or time.");
        } catch (IllegalArgumentException e) {
            System.out.println("  Provide date and time as: \"yyyy/mm/dd XXXX-XXXX\", where XXXX is time in 24-hours.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("  Provide time as: XXXX-XXXX, in 24-hours standard.");
        }
        throw new TextNoMeaningException("  Are you that bad? Come on.");
    }
}