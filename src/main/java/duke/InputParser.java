package duke;

import duke.exceptions.EmptyTextException;
import duke.exceptions.EndProgramException;
import duke.exceptions.IllegalCommandException;

class InputParser {
    String parse(String input, TaskList taskList, Storage storage) throws EmptyTextException, IllegalCommandException, EndProgramException {
        String response = "";
        String command = input.substring(0, input.indexOf(" ") - 1);
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
            response = taskList.mark(Integer.parseInt(input.substring(5)), true);
            break;

        case "unmark":
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
