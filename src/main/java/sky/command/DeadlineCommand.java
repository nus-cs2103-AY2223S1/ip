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
import sky.task.Deadline;
import sky.task.Task;

/**
 * The DeadlineCommand class deals with adding a deadline task into taskList.
 */
public class DeadlineCommand extends Command {
    private String fullCommand;

    public DeadlineCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    @Override
    public String execute(TaskList taskList, Storage storage, History history)
            throws TextNoMeaningException, IOException {
        try {
            String[] arrOfStrings = generateTaskDescriptionAndUserInputBy();
            assert arrOfStrings.length > 0 : "arrOfStrings should not be empty.";
            String taskDescription = arrOfStrings[0];
            String taskByUserInput = arrOfStrings[1];
            String taskBy = produceDateAndTimeForDeadline(taskByUserInput);
            Task task = new Deadline(taskDescription, taskBy);
            taskList.addTask(task);
            // Add task into data file.
            storage.append(task.toString());
            history.addHistoryInTime(taskList);
            return generateResponse(task, taskList);
        } catch (IndexOutOfBoundsException e) {
            throw new TextNoMeaningException("You have either not entered any text after typing deadline, "
                    + "or you have positioned your slash wrongly.");
        } catch (PatternSyntaxException e) {
            throw new TextNoMeaningException("There is a problem with the regex expression written by the dev.");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

    private String produceDateAndTimeForDeadline(String s) throws TextNoMeaningException {
        try {
            String[] arrOfStrings = getDateAndPossiblyTime(s);
            String dateGiven = arrOfStrings[0];
            String dateString = handleDate(dateGiven);

            boolean isTimeProvided = getIsTimeProvided(arrOfStrings);
            if (isTimeProvided) {
                String timeGiven = arrOfStrings[1].substring(0, 2) + ":" + arrOfStrings[1].substring(2);
                String timeString = handleTime(timeGiven);

                return dateString + ", " + timeString;
            }
            return dateString;
        } catch (PatternSyntaxException e) {
            throw new TextNoMeaningException("There is a problem with the regex expression written by the dev.");
        } catch (DateTimeParseException e) {
            throw new TextNoMeaningException("Provide date and time as: \"yyyy/mm/dd XXXX\","
                    + " where XXXX is time in 24-hours.");
        } catch (DateTimeException e) {
            throw new TextNoMeaningException("Unable to format date and/or time.");
        } catch (IllegalArgumentException e) {
            throw new TextNoMeaningException("Provide date and time as: \"yyyy/mm/dd XXXX\","
                    + " where XXXX is time in 24-hours.");
        } catch (IndexOutOfBoundsException e) {
            throw new TextNoMeaningException("Provide time as: XXXX, in 24-hours standard.");
        }
    }

    private String[] generateTaskDescriptionAndUserInputBy() throws TextNoMeaningException {
        String taskDeadline = this.fullCommand.substring(9);
        String[] arrOfStrings = taskDeadline.split(" /by ");
        if (arrOfStrings.length != 2) {
            throw new TextNoMeaningException("Make sure you specify \"/by\" exactly once,"
                    + " and follow it up with a date and time as: \"yyyy/mm/dd XXXX\","
                    + " where XXXX is time in 24-hours. The time is optional.");
        }
        return arrOfStrings;
    }

    private String generateResponse(Task task, TaskList taskList) {
        return "Got it. I've added this task: \n"
                + "    " + task
                + "\nNow you have " + taskList.getSize()
                + (taskList.getSize() <= 1 ? " task in the list." : " tasks in the list.");
    }

    private String[] getDateAndPossiblyTime(String s) throws TextNoMeaningException {
        String[] arrOfStrings = s.split(" ");
        if (arrOfStrings.length != 1 && arrOfStrings.length != 2) {
            throw new TextNoMeaningException("Provide the date and time after \"/by\""
                    + " as: \"yyyy/mm/dd XXXX\", where XXXX is time in 24-hours.");
        }
        return arrOfStrings;
    }

    private String handleDate(String dateGiven) {
        String modifiedDateGiven = dateGiven.replaceAll("/", "-");
        LocalDate d1 = LocalDate.parse(modifiedDateGiven);
        return d1.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    private boolean getIsTimeProvided(String[] arrOfStrings) {
        return arrOfStrings.length == 2;
    }

    private String handleTime(String timeGiven) {
        LocalTime t1 = LocalTime.parse(timeGiven);
        return t1.format(DateTimeFormatter.ofPattern("h:mma"));
    }
}
