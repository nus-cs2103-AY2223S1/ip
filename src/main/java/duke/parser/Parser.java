package duke.parser;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import duke.tasklist.TaskList;

import duke.exception.DukeException;

public class Parser {

    public static Boolean FeedDuke(String input, TaskList taskList) throws DukeException {
        if (input.contains("bye")) {
            return true;
            
        } else if (input.contains("list")) {
            taskList.printList();
            return false;

        } else if (input.contains("unmark")) {
            int index = Integer.parseInt(input.replace("unmark", "").trim()) - 1;
            taskList.unmarkTask(index);
            return false;

        } else if (input.contains("mark")) {
            int index = Integer.parseInt(input.replace("mark", "").trim()) - 1;
            taskList.markTask(index);
            return false;

        } else if (input.contains("todo")) {
            String content = input.replace("todo", "").trim();
            if (content.length() == 0) {
                throw new DukeException("\t☹ OOPS!!! The description of a todo cannot be empty.");
            }
            taskList.addToDo(content);
            return false;
        
        } else if (input.contains("deadline")) {
            if (!input.contains(" by ")) {
                throw new DukeException("\t☹ OOPS!!! Formatting of deadline is incorrect.");
            }
            String[] split = input.replace("deadline", "").split(" by ");
            String content = split[0].trim();
            if (content.length() == 0 || split[1].length() == 0) {
                throw new DukeException("\t☹ OOPS!!! The description of a deadline cannot be empty.");
            }
            String[] dateTimeSplit = split[1].trim().split(" ");
            LocalDate date = LocalDate.now();
            LocalTime time = LocalTime.now();
            try {
                date = LocalDate.parse(dateTimeSplit[0]);
                time = LocalTime.parse(dateTimeSplit[1]);
            } catch (DateTimeParseException e) {
                throw new DukeException("\t☹ OOPS!!! Formatting of date and time is incorrect.");
            }
            taskList.addDeadline(content, date, time);
            return false;

        } else if (input.contains("event")) {
            if (!input.contains(" at ")) {
                throw new DukeException("\t☹ OOPS!!! Formatting of event is incorrect.");
            }
            String[] split = input.replace("event", "").split(" at ");
            String content = split[0].trim();
            if (content.length() == 0 || split[1].length() == 0) {
                throw new DukeException("\t☹ OOPS!!! The description of an event cannot be empty.");
            }
            String[] dateTimeSplit = split[1].trim().split(" ");
            LocalDate date = LocalDate.now();
            LocalTime time = LocalTime.now();
            try {
                date = LocalDate.parse(dateTimeSplit[0]);
                time = LocalTime.parse(dateTimeSplit[1]);
            } catch (DateTimeParseException e) {
                throw new DukeException("\t☹ OOPS!!! Formatting of date and time is incorrect.");
            }
            taskList.addEvent(content, date, time);
            return false;

        } else if (input.contains("delete")) {
            int index = Integer.parseInt(input.replace("delete", "").trim()) - 1;
            taskList.deleteTask(index);
            return false;
        }
        throw new DukeException("\t☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
