package duke;

import duke.exceptions.EmptyTextException;
import duke.exceptions.EndProgramException;
import duke.exceptions.IllegalCommandException;

/**
 * Encapsulates the input parser which parses user input.
 */
class InputParser {

    /**
     * Parses user input according to the specified user command.
     * @param input The user input.
     * @param taskList The list of tasks currently kept in memory.
     * @param storage The Storage class which will save data to memory when user closes the chat.
     * @return Returns response according to the user's command.
     * @throws EmptyTextException Thrown when user only specifies a command without arguments.
     * @throws IllegalCommandException Thrown when user specifies a command which is not recognised.
     * @throws EndProgramException Thrown when user wants to exit the program.
     */
    String parse(String input, TaskList taskList, Storage storage) throws EmptyTextException, IllegalCommandException, EndProgramException {
        String response = "";
        assert(input.length() > 0);
        String command;
        if (input.contains(" ")) {
            command = input.substring(0, input.indexOf(" "));
        } else {
            command = input;
        }
        switch (command) {
        case "find":
            response = taskList.find(input.substring(6));
            break;

        case "bye":
            storage.save(taskList);
            throw new EndProgramException();

        case "list":
            response = taskList.list();
            break;

        case "mark":
            assert(input.length() > 5);
            response = taskList.mark(Integer.parseInt(input.substring(5)), true);
            break;

        case "unmark":
            assert(input.length() > 7);
            response = taskList.mark(Integer.parseInt(input.substring(7)), false);
            break;

        case "delete":
            if (input.trim().length() <= 6) {
                throw new EmptyTextException();
            }
            int id = Integer.parseInt(input.substring(input.indexOf(" ") + 1));
            response = taskList.delete(id);
            break;

        case "todo":
            if (input.trim().length() <= 4) {
                throw new EmptyTextException();
            }
            String name = input.substring(input.indexOf(" ") + 1);
            response = taskList.addTask(name, "T", "");
            break;

        case "deadline":
        case "event":
            int minLength = input.startsWith("deadline") ? 8 : 5;
            if (input.trim().length() <= minLength || input.substring(input.indexOf(" ") + 1).trim().equals("")) {
                throw new EmptyTextException();
            }
            name = input.substring(input.indexOf(" ") + 1, input.indexOf("/"));
            String date = input.substring(input.indexOf("/") + 4);
            response = taskList.addTask(name, input.startsWith("deadline") ? "D" : "E", date);
            break;
        default:
            throw new IllegalCommandException();
        }

        return response;
    }
}
