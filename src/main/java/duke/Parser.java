package duke;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Parser {

    /**
     * Returns a String that characterises the type of command the user has input
     * If the command is not of a certain format for some inputs, DukeException is thrown
     *
     * @param input
     * @return command type
     * @throws DukeException if todo command has no description or the command is not meaningful
     */
    static String getCommandType(String input) throws DukeException {
        if (input.equals("bye")) {
            return "EXIT";
        } else if (input.equals("list")) {
            return "PRINT";
        } else if (input.startsWith("mark") | input.startsWith("unmark")) {
            return "UPDATE";
        } else if (input.startsWith("delete")) {
            return "DELETE";
        } else if (input.replaceAll(" ", "").equals("todo")) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        } else if (input.startsWith("todo") | input.startsWith("deadline") | input.startsWith("event")) {
            return "ADD";
        } else if (input.equals("upcoming tasks")) {
            return "UPCOMING";
        } else if (input.startsWith("find")) {
            return "FIND";
        } else {
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    /** Return an int array after parsing the mark or unmark command
     * The first integer will determine if its a mark or unmark command
     * The second integer is the task index
     *
     * @param command mark/unmark command
     * @return integer array containing task index
     */

    static int[] parseUpdateCommand(String command) {
        String[] parsedCommand = command.split("\\s+");
        if (command.startsWith("mark")) {
            return new int[]{1, Integer.valueOf(parsedCommand[1])};
        }
        return new int[]{2, Integer.valueOf(parsedCommand[1])};
    }

    /** Returns index of task to delete
     *
     * @param deleteCommand
     * @return task index
     */
    static int getDeleteNum(String deleteCommand) {
        String[] parsedCommand = deleteCommand.split("\\s+");
        return Integer.valueOf(parsedCommand[1]);
    }

    /** Returns date and time of a deadline or event command
     * If the date and time is not of correct format, DukeException is thrown
     *
     * @param command event/deadline command
     * @return LocalDateTime of event/deadline
     * @throws DukeException if date and time is not formatted correctly
     */
    static LocalDateTime parseDateTime(String command) throws DukeException{
        try {
            // Splits the date and time into a String array
            String dateTime = command.substring(command.indexOf("/") + 1);
            String[] splitTime = dateTime.split("\\s+");

            //parses the data and time based on a fixed format
            LocalDate date = LocalDate.parse(splitTime[1], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            LocalTime time = LocalTime.parse(splitTime[2], DateTimeFormatter.ofPattern("HHmm"));

            return LocalDateTime.of(date, time);
        } catch (Exception e) {
            throw new DukeException("Date and Time is not of correct format!");
        }
    }

    /**
     * Prints the task with the keyword in their description
     * Otherwise print an error message that keyword does not exist
     *
     * @param command the user input
     * @param tasks
     * @throws DukeException if keyword cannot be found in our task list
     */
    static String parseFindCommand(String command, TaskList tasks) throws DukeException{
        try {
            String keyword = command.substring(5);
            return tasks.findTasks(keyword);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    static String printUpcomingTasks(TaskList tasks) {
        String response = "";
        LocalDateTime dateTime = LocalDateTime.now();
        for (Task task : tasks.getTasks()) {
            if (task instanceof Deadline) {
                //we specify that the task is of the Deadline class by type casting
                Deadline deadline = (Deadline) task;

                if (deadline.getTime().isBefore(dateTime.plusWeeks(1))) {
                    response += deadline + "\n";
                }
            } else if (task instanceof Event) {
                //we specify that the task is of the Event class by type casting
                Event event = (Event) task;

                if (event.getTime().isBefore(dateTime.plusWeeks(1))) {
                    response += event + "\n";
                }
            }
        }
        return response;
    }
}
