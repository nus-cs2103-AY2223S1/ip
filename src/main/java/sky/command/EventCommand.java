package sky.command;

import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.PatternSyntaxException;

import sky.TaskList;
import sky.exception.TextNoMeaningException;
import sky.storage.History;
import sky.storage.Storage;
import sky.task.Event;
import sky.task.Task;

/**
 * The EventCommand class deals with adding an event task into taskList.
 */
public class EventCommand extends Command {
    private String fullCommand;

    public EventCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    @Override
    public String execute(TaskList taskList, Storage storage, History history)
            throws TextNoMeaningException, IOException {
        try {
            String[] arrOfStrings = generateTaskDescriptionAndUserInputDuration();
            assert arrOfStrings.length > 0 : "arrOfStrings should not be empty.";
            String taskDescription = arrOfStrings[0];
            String taskDurationUserInput = arrOfStrings[1];
            String taskDuration = produceDateAndTimeForEvent(taskDurationUserInput);
            Task task = new Event(taskDescription, taskDuration);
            taskList.addTask(task);
            // Add task into data file
            storage.append(task.toString());
            history.addHistoryInTime(taskList);
            return generateResponse(task, taskList);
        } catch (IndexOutOfBoundsException e) {
            throw new TextNoMeaningException("You have either not entered any text after typing event, "
                    + "or you have positioned your slash wrongly.");
        } catch (PatternSyntaxException e) {
            throw new TextNoMeaningException("There is a problem with the regex expression written by the dev.");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

    private String produceDateAndTimeForEvent(String s) throws TextNoMeaningException {
        try {
            String[] arrOfStrings = getDateAndTime(s);
            String dateGiven = arrOfStrings[0];
            String dateString = handleDate(dateGiven);

            String timeGiven1 = arrOfStrings[1].substring(0, 2) + ":" + arrOfStrings[1].substring(2, 4);
            String timeGiven2 = arrOfStrings[1].substring(5, 7) + ":" + arrOfStrings[1].substring(7);
            String finalTimeString = handleTime(timeGiven1, timeGiven2);

            return dateString + ", " + finalTimeString;
        } catch (PatternSyntaxException e) {
            throw new TextNoMeaningException("There is a problem with the regex expression written by the dev.");
        } catch (DateTimeParseException e) {
            throw new TextNoMeaningException("Provide date and time as: \"yyyy/mm/dd XXXX-XXXX\","
                    + " where XXXX is time in 24-hours.");
        } catch (DateTimeException e) {
            throw new TextNoMeaningException("Unable to format date and/or time.");
        } catch (IllegalArgumentException e) {
            throw new TextNoMeaningException("Provide date and time as: \"yyyy/mm/dd XXXX-XXXX\","
                    + " where XXXX is time in 24-hours.");
        } catch (IndexOutOfBoundsException e) {
            throw new TextNoMeaningException("Provide time as: XXXX-XXXX, in 24-hours standard.");
        }
    }

    private String[] generateTaskDescriptionAndUserInputDuration() throws TextNoMeaningException {
        String taskEvent = this.fullCommand.substring(6);
        String[] arrOfStrings = taskEvent.split(" /at ");
        if (arrOfStrings.length != 2) {
            throw new TextNoMeaningException("Make sure you specify \"/at\" exactly once,"
                    + " and follow it up with a date and time as: \"yyyy/mm/dd XXXX-XXXX\","
                    + " where XXXX is time in 24-hours. The time is compulsory.");
        }
        return arrOfStrings;
    }

    private String generateResponse(Task task, TaskList taskList) {
        return "Got it. I've added this task: \n"
                + "    " + task
                + "\nNow you have " + taskList.getSize()
                + (taskList.getSize() <= 1 ? " task in the list." : " tasks in the list.");
    }

    private String[] getDateAndTime(String s) throws TextNoMeaningException {
        String[] arrOfStrings = s.split(" ");
        if (arrOfStrings.length != 2) {
            throw new TextNoMeaningException("Provide the date and time after \"/at\""
                    + " as: \"yyyy/mm/dd XXXX-XXXX\", where XXXX is time in 24-hours.");
        }
        return arrOfStrings;
    }

    private String handleDate(String dateGiven) {
        String modifiedDateGiven = dateGiven.replaceAll("/", "-");
        LocalDate d1 = LocalDate.parse(modifiedDateGiven);
        return d1.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    private String handleTime(String timeGiven1, String timeGiven2) {
        LocalTime t1 = LocalTime.parse(timeGiven1);
        LocalTime t2 = LocalTime.parse(timeGiven2);
        String timeString1 = t1.format(DateTimeFormatter.ofPattern("h:mma"));
        String timeString2 = t2.format(DateTimeFormatter.ofPattern("h:mma"));
        return timeString1 + "-" + timeString2;
    }
}
