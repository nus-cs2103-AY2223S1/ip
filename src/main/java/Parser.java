import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Parser {
    private Scanner input;
    public Parser() {
        this.input = new Scanner(System.in);
    }

    public LocalDate parseDate(String str) throws DukeException {
        // parse string format date to LocalDate object -> to String format yyyy-MM-dd, exception
        String time = str.trim();
       try {
           if (time.matches("([0-9]{2})/([0-9]{2})/([0-9]{4})")) {
               return LocalDate.parse(time, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
           } else if (time.matches("([0-9]{4})/([0-9]{2})/([0-9]{2})")) {
               return LocalDate.parse(time, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
           } else if (time.matches("([0-9]{2})-([0-9]{2})-([0-9]{4})")) {
               return LocalDate.parse(time, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
           } else if (time.matches("([0-9]{4})-([0-9]{2})-([0-9]{2})")) {
               return LocalDate.parse(time, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
           } else if (time.matches("([0-9]{4}) ([0-9]{2}) ([0-9]{2})")) {
               return LocalDate.parse(time, DateTimeFormatter.ofPattern("yyyy MM dd"));
           } else if (time.matches("([0-9]{2}) ([0-9]{2}) ([0-9]{4})")) {
               return LocalDate.parse(time, DateTimeFormatter.ofPattern("dd MM yyyy"));
           } else {
               throw new DukeException("☹ OOPS!!! This is not a proper time format, please refer to command format information.");
           }
        } catch(Exception e) {
           throw new DukeException(e.getMessage());
        }
    }

    public String parseCommand(TaskList taskList) throws DukeException {
        String line = input.nextLine().trim();
        String [] taskString = line.split(" ", 2);
        String taskType = taskString[0];
        if (taskString.length <= 1 && !taskType.equals("list") && !taskType.equals("bye") && !taskType.equals("help")) {
            throw new DukeException ("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        } else if (taskType.equals("todo")) {
            String description = taskString[1].trim();
            if (description.equals("")) { // task description empty
                throw new DukeException("☹ OOPS!!! The description of a Todo task cannot be empty.");
            }
            taskList.addTask(new ToDo(description));
            return String.format("todo %d", taskList.getSize());
        } else if (taskType.equals("deadline")) {
            if (!taskString[1].contains("/by")) { // command not properly formatted
                throw new DukeException("☹ OOPS!!! The command not properly formatted.\n" +
                        "Please follow the format: Deadline {description} /by {date}.");
            }
            String [] descriptions = taskString[1].split("/by");
            String description = descriptions[0].trim();
            LocalDate end = parseDate(descriptions[1]);
            if (description.equals("")) { // task description empty
                throw new DukeException("☹ OOPS!!! The description of a Deadline task cannot be empty.");
            }
            taskList.addTask(new Deadline(description, end));
            return String.format("deadline %d", taskList.getSize());
        } else if (taskType.equals("event")) {
            if (!taskString[1].contains("/at")) { // command not properly formatted
                throw new DukeException("☹ OOPS!!! The command not properly formatted.\n" +
                        "Please follow the format: Event {description} /at {date} to {date}.");
            } else if (!taskString[1].contains(" to ")) {
                throw new DukeException("☹ OOPS!!! The command not properly formatted.\n" +
                        "Please follow the format: Event {description} /at {date} to {date}.");
            }
            String [] descriptions = taskString[1].split("/at");
            String description = descriptions[0].trim();
            String [] dates = descriptions[1].trim().split("to");
            LocalDate start = parseDate(dates[0]);
            LocalDate end = parseDate(dates[1]);
            if (descriptions[0].trim().equals("")) { // task description empty
                throw new DukeException("☹ OOPS!!! The description of a Event task cannot be empty.");
            } else if (start.isAfter(end)) {
                throw new DukeException("☹ OOPS!!! The start date of a Event task cannot be after end date.");
            }
            taskList.addTask(new Event(description, start, end));
            return String.format("event %d", taskList.getSize());
        } else if (taskType.equals("list")) {
            if (taskString.length == 1 || taskString[1].trim().equals("")) { //list command
                return "list all";
            } else { //list date
                LocalDate date = parseDate(taskString[1].trim());
                return String.format("list %s", date);
            }
        } else if (taskType.equals("mark") || taskType.equals("unmark") || taskType.equals("delete")) {
            String taskIndex = taskString[1].trim();
            if (taskIndex.equals("") || !taskIndex.matches("[0-9]+")) { // task description empty or not numeric
                throw new DukeException(String.format("☹ OOPS!!! The task index of a %s command cannot be empty.", taskType));
            } else if (Integer.parseInt(taskIndex) > taskList.getSize() || Integer.parseInt(taskIndex) < 1) {
                throw new DukeException("☹ OOPS!!! The task index exceeds task list size limit.");
            }
            if (taskType.equals("unmark")) {
                taskList.markAsUndone(Integer.parseInt(taskIndex));
            } else if (taskType.equals("mark")) {
                taskList.markAsDone(Integer.parseInt(taskIndex));
            }
            return String.format("%s %s", taskType, taskIndex);
        } else if (taskType.equals("bye") || taskType.equals("help")) {
            return taskType;
        } else {
            throw new DukeException ("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

}
