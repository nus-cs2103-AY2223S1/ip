package sky.command;

import sky.Storage;
import sky.TaskList;
import sky.exception.TextNoMeaningException;
import sky.task.Deadline;
import sky.task.Task;

import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.PatternSyntaxException;

/**
 * The DeadlineCommand class deals with adding a deadline task into taskList.
 */
public class DeadlineCommand extends Command {
    private String fullCommand;

    public DeadlineCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    @Override
    public String execute(TaskList taskList, Storage storage) throws TextNoMeaningException, IOException {
        try {
            String taskDeadline = this.fullCommand.substring(9);
            String[] arrOfStrings = taskDeadline.split(" /by ");
            if (arrOfStrings.length != 2) {
                throw new TextNoMeaningException("Make sure you specify \"/by\" exactly once.");
            }
            String taskDescription = arrOfStrings[0];
            String taskByUserInput = arrOfStrings[1];
            String taskBy = produceDateAndTimeForDeadline(taskByUserInput);
            Task task = new Deadline(taskDescription, taskBy);
            taskList.addTask(task);
            // Add task into data file.
            storage.append(task.toString());
            String s = "Got it. I've added this task: \n" +
                    "    " + task +
                    "\nNow you have " + taskList.getSize() +
                    (taskList.getSize() <= 1 ? " task in the list.": " tasks in the list.");
            return s;
        } catch (IndexOutOfBoundsException e) {
            throw new TextNoMeaningException("You have either not entered any text after typing deadline, \n" +
                    "  or you have positioned your slash wrongly.");
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
            String[] arrOfStrings = s.split(" ");
            if (arrOfStrings.length != 1 && arrOfStrings.length !=2) {
                throw new TextNoMeaningException("Provide the date and time after \"/by\"" +
                        " as: \"yyyy/mm/dd XXXX\", where XXXX is time in 24-hours.");
            }
            String dateGiven = arrOfStrings[0].replaceAll("/", "-");
            LocalDate d1 = LocalDate.parse(dateGiven);
            String dateString = d1.format(DateTimeFormatter.ofPattern("MMM d yyyy"));

            // If time is provided
            if (arrOfStrings.length == 2) {
                String timeGiven = arrOfStrings[1].substring(0, 2) + ":" + arrOfStrings[1].substring(2);
                LocalTime t1 = LocalTime.parse(timeGiven);
                String timeString = t1.format(DateTimeFormatter.ofPattern("h:mma"));

                return dateString + ", " + timeString;
            }
            return dateString;
        } catch (PatternSyntaxException e) {
            throw new TextNoMeaningException("There is a problem with the regex expression written by the dev.");
        } catch (DateTimeParseException e) {
            throw new TextNoMeaningException("Provide date and time as: \"yyyy/mm/dd XXXX\", where XXXX is time in 24-hours.");
        } catch (DateTimeException e) {
            throw new TextNoMeaningException("Unable to format date and/or time.");
        } catch (IllegalArgumentException e) {
            throw new TextNoMeaningException("Provide date and time as: \"yyyy/mm/dd XXXX\", where XXXX is time in 24-hours.");
        } catch (IndexOutOfBoundsException e) {
            throw new TextNoMeaningException("Provide time as: XXXX, in 24-hours standard.");
        }
    }
}
