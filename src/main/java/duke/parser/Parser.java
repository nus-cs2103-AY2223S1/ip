package duke.parser;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import duke.exception.DukeException;
import duke.tasklist.TaskList;

public class Parser {

    public enum TaskType {
        TODO, DEADLINE, EVENT;
    }

    public enum IndexCommands {
        UNMARK, MARK, DELETE, TAG
    }

    public static String parseInput(String input, TaskList taskList) throws DukeException {
        String[] commands = input.split(" ", 2);
        String command = commands[0];
        int numCommandArgs = commands.length;

        if (command.equals("bye")) {
            return "Bye. Hope to see you again soon!";
        }
        if (command.equals("list")) {
            return taskList.printList();
        }

        if (numCommandArgs < 2) {
            throw new DukeException("Invalid number of arguments.");
        }
        switch (command) {
            case "unmark":
                return parseIndexCommand(commands[1], IndexCommands.UNMARK, taskList);

            case "mark":
                return parseIndexCommand(commands[1], IndexCommands.MARK, taskList);

            case "todo":
                return parseTask(commands[1], TaskType.TODO, taskList);

            case "deadline":
                return parseTask(commands[1], TaskType.DEADLINE, taskList);

            case "event":
                return parseTask(commands[1], TaskType.EVENT, taskList);

            case "delete":
                return parseIndexCommand(commands[1], IndexCommands.DELETE, taskList);

            case "find":
                String content = commands[1];
                return taskList.find(content);

            case "tag":
                String tagContent = commands[1];
                return parseTag(tagContent, taskList);
            
            default:
                throw new DukeException("I'm sorry, but I don't know what that means.");
        }
    }

    public static String parseIndexCommand(String content, IndexCommands type, TaskList taskList) 
            throws DukeException {
        int index = -1;
        try {
            index = Integer.parseInt(content) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException("Please enter a valid task number.");
        }

        switch(type) {
            case UNMARK:
                return taskList.unMarkTask(index);

            case MARK:
                return taskList.markTask(index);

            case DELETE:
                return taskList.deleteTask(index);

            default: 
                throw new DukeException("I'm sorry, but I don't know what that means.");
        }
    }

    public static String parseTask(String content, TaskType type, TaskList taskList) throws DukeException {
        if (content.length() == 0) {
            throw new DukeException("The description of a task cannot be empty.");
        }

        String[] contents;
        switch (type) {
            case TODO:
                return taskList.addToDo(content);

            case DEADLINE:
                if (!content.contains(" by ")) {
                    throw new DukeException("Formatting of deadline is incorrect.");
                }
                contents = content.split(" by ", 2);
                break;

            case EVENT:
                if (!content.contains(" at ")) {
                    throw new DukeException("Formatting of event is incorrect.");
                }
                contents = content.split(" at ", 2);
                break;

            default:
                throw new DukeException("Formatting of task is incorrect.");
        }

        String desc = contents[0];
        if (desc.length() == 0 || contents[1].length() == 0) {
            throw new DukeException("The description of a task cannot be empty.");
        }

        String[] dateTimeSplit = contents[1].split(" ");
        if (dateTimeSplit.length < 2) {
            throw new DukeException("Formatting of date and time is incorrect.");
        }
        
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        try {
            date = LocalDate.parse(dateTimeSplit[0]);
            time = LocalTime.parse(dateTimeSplit[1]);
        } catch (DateTimeParseException e) {
            throw new DukeException("Formatting of date and time is incorrect.");
        }

        assert (date.toString() + " " + time.toString()).equals(contents[1]) 
            : "Date and time parsed is incorrect";

        switch(type) {
            case DEADLINE:
                return taskList.addDeadline(desc, date, time);

            case EVENT:
                return taskList.addEvent(desc, date, time);

            default:
                throw new DukeException("Something went wrong here.");
        }        
    }

    public static String parseTag(String content, TaskList taskList) throws DukeException {
        String[] tagSplit = content.split(" ", 2);
        if (tagSplit.length < 2) {
            throw new DukeException("Formatting of tag command is incorrect.");
        }

        String tag = tagSplit[1];
        if (tag.length() == 0) {
            throw new DukeException("Tag description cannot be empty.");
        }

        int index = -1;
        try {
            index = Integer.parseInt(tagSplit[0]) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException("Please enter a valid task number.");
        }

        return taskList.tagTask(index, tag);
    }
}
