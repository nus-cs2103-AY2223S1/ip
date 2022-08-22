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
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            String taskDeadline = this.fullCommand.substring(9);
            String[] arrOfStrings = taskDeadline.split(" /by ");
            if (arrOfStrings.length != 2) {
                throw new TextNoMeaningException("  Make sure you specify \"/by\" exactly once.");
            }
            String taskDescription = arrOfStrings[0];
            String taskByUserInput = arrOfStrings[1];
            String taskBy = produceDateAndTimeForDeadline(taskByUserInput);
            Task task = new Deadline(taskDescription, taskBy);
            taskList.addTask(task);
            // Add task into data file.
            storage.append(task.toString());
            System.out.println("  Got it. I've added this task: \n" +
                    "    " + task +
                    "\n  Now you have " + taskList.size() +
                    (taskList.size() <= 1 ? " task in the list.": " tasks in the list."));
        } catch (IndexOutOfBoundsException e) {
            System.out.println("  You have either not entered any text after typing deadline, \n" +
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

    private String produceDateAndTimeForDeadline(String s) throws TextNoMeaningException {
        try {
            String[] arrOfStrings = s.split(" ");
            if (arrOfStrings.length != 1 && arrOfStrings.length !=2) {
                throw new TextNoMeaningException("  Provide the date and time after \"/by\".");
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
            System.out.println("  There is a problem with the regex expression written by the dev.");
        } catch (TextNoMeaningException e) {
            System.out.println(e);
        } catch (DateTimeParseException e) {
            System.out.println("  Date and/or time given cannot be parsed.");
            System.out.println("  Provide date and time as: \"yyyy/mm/dd XXXX\", where XXXX is time in 24-hours.");
        } catch (DateTimeException e) {
            System.out.println("  Unable to format date and/or time.");
        } catch (IllegalArgumentException e) {
            System.out.println("  Provide date and time as: \"yyyy/mm/dd XXXX\", where XXXX is time in 24-hours.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("  Provide time as: XXXX, in 24-hours standard.");
        }
        throw new TextNoMeaningException("  Are you that bad? Come on.");
    }
}
