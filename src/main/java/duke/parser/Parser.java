package duke.parser;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import duke.exception.DukeException;
import duke.tasklist.TaskList;

public class Parser {

    public static Boolean parseInput(String input, TaskList taskList) throws DukeException {
        String[] commands = input.split(" ", 2);
        String command = commands[0].toUpperCase();
        int numCommandArgs = commands.length;

        if (command.equals("bye")) {
            return true;

        } else if (command.equals("list") && numCommandArgs == 2) {
            taskList.printList();
            return false;

        } else if (command.equals("unmark") && numCommandArgs == 2) {
            int index = -1;
            try {
                index = Integer.parseInt(commands[1]) - 1;
                taskList.unMarkTask(index);
            } catch (NumberFormatException e) {
                throw new DukeException("\tPlease enter a valid task number to unmark.");
            }
            return false;

        } else if (command.equals("mark") && numCommandArgs == 2) {
            int index = -1;
            try {
                index = Integer.parseInt(commands[1]) - 1;
                taskList.markTask(index);
            } catch (NumberFormatException e) {
                throw new DukeException("\tPlease enter a valid task number to mark.");
            }
            return false;

        } else if (command.equals("todo") && numCommandArgs == 2) {
            String content = commands[1];
            if (content.length() == 0) {
                throw new DukeException("\tThe description of a todo cannot be empty.");
            }
            taskList.addToDo(content);
            return false;

        } else if (command.equals("deadline") && numCommandArgs == 2) {
            if (!commands[1].contains(" by ")) {
                throw new DukeException("\tFormatting of deadline is incorrect.");
            }

            String[] contents = input.replace("deadline", "").split(" by ", 2);
            String desc = contents[0];

            if (desc.length() == 0 || contents[1].length() == 0) {
                throw new DukeException("\tThe description of a deadline cannot be empty.");
            }

            String[] dateTimeSplit = contents[1].trim().split(" ", 2);
            LocalDate date = LocalDate.now();
            LocalTime time = LocalTime.now();
            try {
                date = LocalDate.parse(dateTimeSplit[0]);
                time = LocalTime.parse(dateTimeSplit[1]);
            } catch (DateTimeParseException e) {
                throw new DukeException("\tFormatting of date and time is incorrect.");
            }

            taskList.addDeadline(desc, date, time);
            return false;

        } else if (command.equals("event") && numCommandArgs == 2) {
            if (!commands[1].contains(" at ")) {
                throw new DukeException("\tFormatting of event is incorrect.");
            }

            String[] contents = input.replace("event", "").split(" at ", 2);
            String desc = contents[0];

            if (desc.length() == 0 || contents[1].length() == 0) {
                throw new DukeException("\tThe description of an event cannot be empty.");
            }

            String[] dateTimeSplit = contents[1].trim().split(" ", 2);
            LocalDate date = LocalDate.now();
            LocalTime time = LocalTime.now();

            try {
                date = LocalDate.parse(dateTimeSplit[0]);
                time = LocalTime.parse(dateTimeSplit[1]);
            } catch (DateTimeParseException e) {
                throw new DukeException("\tFormatting of date and time is incorrect.");
            }

            taskList.addDeadline(desc, date, time);
            return false;

        } else if (command.equals("delete") && numCommandArgs == 2) {
            int index = -1;
            try {
                index = Integer.parseInt(commands[1]) - 1;
                taskList.deleteTask(index);
            } catch (NumberFormatException e) {
                throw new DukeException("\tPlease enter a valid task number to delete.");
            }
            return false;

        } else if (command.equals("find") && numCommandArgs == 2) {
            String desc = commands[1];
            if (desc.length() == 0) {
                throw new DukeException("\tThe description of a find command cannot be empty.");
            }
            taskList.find(desc);
            return false;
        }

        throw new DukeException("\tI'm sorry, but I don't know what that means.");
    }
}
