package duke;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class Parser {

    public static String feedDuke(String input, TaskList taskList) throws DukeException {
        String[] commandSplit = (input + " ").split(" ", 2);
        String command = commandSplit[0];
        if (command.equals("bye")) {
            return "Bye. Hope to see you again soon!";
            
        } else if (command.equals("list")) {
            return taskList.printList();

        } else if (command.equals("unmark")) {
            int index = -1;
            try {
                index = Integer.parseInt(commandSplit[1].trim()) - 1;
            } catch (NumberFormatException e) {
                throw new DukeException("Please enter a valid numerical description.");
            }
            return taskList.unMarkTask(index);

        } else if (command.equals("mark")) {
            int index = -1;
            try {
                index = Integer.parseInt(commandSplit[1].trim()) - 1;
            } catch (NumberFormatException e) {
                throw new DukeException("Please enter a valid numerical description.");
            }
            return taskList.markTask(index);

        } else if (command.equals("todo")) {
            String content = commandSplit[1].trim();
            if (content.length() == 0) {
                throw new DukeException("The description of a todo cannot be empty.");
            }
            return taskList.addToDo(content);
        
        } else if (command.equals("deadline")) {
            if (!commandSplit[1].contains(" by ")) {
                throw new DukeException("Formatting of deadline is incorrect.");
            }
            String[] split = commandSplit[1].trim().split(" by ");
            String content = split[0].trim();
            if (content.length() == 0 || split[1].length() == 0) {
                throw new DukeException("The description of a deadline cannot be empty.");
            }
            String[] dateTimeSplit = split[1].split(" ");
            LocalDate date = LocalDate.now();
            LocalTime time = LocalTime.now();
            try {
                date = LocalDate.parse(dateTimeSplit[0]);
                time = LocalTime.parse(dateTimeSplit[1]);
            } catch (DateTimeParseException e) {
                throw new DukeException("Formatting of date and time is incorrect.");
            }
            return taskList.addDeadline(content, date, time);

        } else if (command.equals("event")) {
            if (!commandSplit[1].contains(" at ")) {
                throw new DukeException("Formatting of event is incorrect.");
            }
            String[] split = commandSplit[1].trim().split(" at ");
            String content = split[0];
            if (content.length() == 0 || split[1].length() == 0) {
                throw new DukeException("The description of an event cannot be empty.");
            }
            String[] dateTimeSplit = split[1].split(" ");
            LocalDate date = LocalDate.now();
            LocalTime time = LocalTime.now();
            try {
                date = LocalDate.parse(dateTimeSplit[0]);
                time = LocalTime.parse(dateTimeSplit[1]);
            } catch (DateTimeParseException e) {
                throw new DukeException("Formatting of date and time is incorrect.");
            }
            return taskList.addEvent(content, date, time);

        } else if (command.equals("delete")) {
            int index = 0;
            try {
                index = Integer.parseInt(commandSplit[1].trim()) - 1;
            } catch (NumberFormatException e) {
                throw new DukeException("Please enter a valid numerical description.");
            }
            return taskList.deleteTask(index);

        } else if (command.equals("find")) {
            String content = commandSplit[1].trim();
            return taskList.find(content);
        }
        throw new DukeException("I'm sorry, but I don't know what that means.");
    }

}
