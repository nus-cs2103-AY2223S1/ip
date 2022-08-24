package duke;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Parser {
    //here we read the input to give us the type of command that the user has typed
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
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    // we set the first arr int to be 1 if it is a mark command, and 2 if it is a unmark command
    // the 2nd int will then be the task index
    static int[] parseUpdateCommand(String command) {
        String[] parsedCommand = command.split("\\s+");
        if (command.startsWith("mark")) {
            return new int[]{1, Integer.valueOf(parsedCommand[1])};
        }
        return new int[]{2, Integer.valueOf(parsedCommand[1])};
    }


    static int getDeleteNum(String deleteCommand) {
        String[] parsedCommand = deleteCommand.split("\\s+");
        return Integer.valueOf(parsedCommand[1]);
    }

    static LocalDateTime parseDateTime(String command) throws DukeException{
        try {
            String dateTime = command.substring(command.indexOf("/") + 1);
            String[] splitTime = dateTime.split("\\s+");
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
    static void parseFindCommand(String command, TaskList tasks) throws DukeException{
        try {
            String keyword = command.substring(5);
            tasks.findTasks(keyword);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    static void printUpcomingTasks(String fullCommand, TaskList tasks) {
        LocalDateTime dateTime = LocalDateTime.now();
        for (Task task : tasks.getTasks()) {
            if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                if (deadline.getTime().isBefore(dateTime.plusWeeks(1))) {
                    System.out.println(deadline);
                }
            } else if (task instanceof Event) {
                Event event = (Event) task;
                if (event.getTime().isBefore(dateTime.plusWeeks(1))) {
                    System.out.println(event);
                }
            }
        }
    }
}
