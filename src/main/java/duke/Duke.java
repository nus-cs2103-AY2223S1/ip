package duke;

import java.io.IOException;

/**
 * Duke class, your personal assistant.
 */
public class Duke {
    private TaskList taskList = new TaskList();
    private Parser parser = new Parser();
    private Storage storage = new Storage("data.txt");

    /**
     * Start the Duke.
     */
    public void start() {
        storage.fillData(taskList);
        fillParser();
    }

    /**
     * Get response for an input
     * @param input user input
     * @return the appropriate response
     */
    public String getResponse(String input) {
        if (input.equals("bye")) {
            try {
                storage.saveToStorage(taskList);
            } catch (IOException e) {
                return e.getMessage();
            }
            return "Bye. Hope to see you again soon!";
        }
        try {
            return parser.executeCommand(input);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    /**
     * Initialize Parser with built-in commands.
     * We have to do this here and not in Parser because it uses data
     * in the Duke class
     */
    private void fillParser() {
        parser.addCommand("todo", argument -> {
            if (argument.isBlank()) {
                throw DukeException.INVALIDARGUMENT;
            }
            Task item = Task.createToDo(argument);
            taskList.addTask(item);
            return "added: " + item + "\n"
                    + "Now you have " + taskList.getSize() + " tasks in the list.";
        });

        parser.addCommand("deadline", argument -> {
            if (argument.isBlank()) {
                throw DukeException.INVALIDARGUMENT;
            }
            Task item = Task.createDeadline(argument);
            taskList.addTask(item);
            return "added: " + item + "\n"
                    + "Now you have " + taskList.getSize() + " tasks in the list.";
        });

        parser.addCommand("event", argument -> {
            if (argument.isBlank()) {
                throw DukeException.INVALIDARGUMENT;
            }
            Task item = Task.createEvent(argument);
            taskList.addTask(item);
            return "added: " + item + "\n"
                    + "Now you have " + taskList.getSize() + " tasks in the list.";
        });

        parser.addCommand("mark", argument -> {
            try {
                int id = Integer.parseInt(argument) - 1;
                Task item = taskList.getTask(id).changeMark(true);
                return "Nice! I've marked this task as done:\n"
                        + item;
            } catch (NumberFormatException e) {
                throw DukeException.INVALIDARGUMENT;
            }
        });

        parser.addCommand("unmark", argument -> {
            try {
                int id = Integer.parseInt(argument) - 1;
                Task item = taskList.getTask(id).changeMark(false);
                return "OK, I've marked this task as not done yet:\n"
                        + item;
            } catch (NumberFormatException e) {
                throw DukeException.INVALIDARGUMENT;
            }
        });

        parser.addCommand("delete", argument -> {
            try {
                int id = Integer.parseInt(argument) - 1;
                Task item = taskList.deleteTask(id);
                return "Noted. I've removed this task:\n"
                        + item + "\n"
                        + "Now you have " + taskList.getSize() + " tasks in the list.";
            } catch (NumberFormatException e) {
                throw DukeException.INVALIDARGUMENT;
            }
        });

        parser.addCommand("list", argument -> {
            if (!argument.isBlank()) {
                throw DukeException.INVALIDARGUMENT;
            }
            return taskList.toString();
        });

        parser.addCommand("find", argument -> {
            TaskList result = taskList.searchByKeyword(argument);
            return "Here are the matching tasks in your list:" + "\n"
                    + result.toString();
        });
    }
}
