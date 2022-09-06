package duke.parser;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import duke.exception.DukeException;
import duke.tasklist.TaskList;

public class Parser {

    public static String parseInput(String input, TaskList taskList) throws DukeException {
        String[] commands = input.split(" ", 2);
        String command = commands[0];
        int numCommandArgs = commands.length;

        if (command.equals("bye")) {
            return "Bye. Hope to see you again soon!";
            
        } else if (command.equals("list")) {
            return taskList.printList();

        } else if (command.equals("unmark") && numCommandArgs == 2) {
            int index = -1;
            try {
                index = Integer.parseInt(commands[1]) - 1;
            } catch (NumberFormatException e) {
                throw new DukeException("Please enter a valid task number to unmark.");
            }
            return taskList.unMarkTask(index);

        } else if (command.equals("mark") && numCommandArgs == 2) {
            int index = -1;
            try {
                index = Integer.parseInt(commands[1]) - 1;
            } catch (NumberFormatException e) {
                throw new DukeException("Please enter a valid task number to mark.");
            }
            return taskList.markTask(index);

        } else if (command.equals("todo") && numCommandArgs == 2) {
            String content = commands[1];
            if (content.length() == 0) {
                throw new DukeException("The description of a todo cannot be empty.");
            }
            return taskList.addToDo(content);
        
        } else if (command.equals("deadline") && numCommandArgs == 2) {
            if (!commands[1].contains(" by ")) {
                throw new DukeException("Formatting of deadline is incorrect.");
            }

            String[] contents = commands[1].split(" by ", 2);
            String desc = contents[0];

            if (desc.length() == 0 || contents[1].length() == 0) {
                throw new DukeException("The description of a deadline cannot be empty.");
            }

            String[] dateTimeSplit = contents[1].split(" ");
            LocalDate date = LocalDate.now();
            LocalTime time = LocalTime.now();
            try {
                date = LocalDate.parse(dateTimeSplit[0]);
                time = LocalTime.parse(dateTimeSplit[1]);
            } catch (DateTimeParseException e) {
                throw new DukeException("Formatting of date and time is incorrect.");
            }

            return taskList.addDeadline(desc, date, time);

        } else if (command.equals("event") && numCommandArgs == 2) {
            if (!commands[1].contains(" at ")) {
                throw new DukeException("Formatting of event is incorrect.");
            }

            String[] contents = commands[1].split(" by ", 2);
            String desc = contents[0];

            if (desc.length() == 0 || contents[1].length() == 0) {
                throw new DukeException("The description of an event cannot be empty.");
            }

            String[] dateTimeSplit = contents[1].split(" ");
            LocalDate date = LocalDate.now();
            LocalTime time = LocalTime.now();
            try {
                date = LocalDate.parse(dateTimeSplit[0]);
                time = LocalTime.parse(dateTimeSplit[1]);
            } catch (DateTimeParseException e) {
                throw new DukeException("Formatting of date and time is incorrect.");
            }

            return taskList.addEvent(desc, date, time);

        } else if (command.equals("delete") && numCommandArgs == 2) {
            int index = -1;
            try {
                index = Integer.parseInt(commands[1]) - 1;
            } catch (NumberFormatException e) {
                throw new DukeException("Please enter a valid task number to delete.");
            }
            return taskList.deleteTask(index);

        } else if (command.equals("find") && numCommandArgs == 2) {
            String content = commands[1];
            return taskList.find(content);
        }

        throw new DukeException("I'm sorry, but I don't know what that means.");
    }

}
