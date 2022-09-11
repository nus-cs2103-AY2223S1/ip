package duke;

import duke.exceptions.EmptyTextException;
import duke.exceptions.EndProgramException;
import duke.exceptions.IllegalCommandException;

class InputParser {
    String parse(String input, TaskList taskList, Storage storage) throws EmptyTextException, IllegalCommandException, EndProgramException {
        String response = "";
        if (input.startsWith("find")) {
            response = taskList.find(input.substring(6));

        } else if (input.equals("bye")) {
            storage.save(taskList);
            throw new EndProgramException();

        } else if (input.equals("list")) {
            response = taskList.list();

        } else if (input.startsWith("mark")) {
            response = taskList.mark(Integer.parseInt(input.substring(5)), true);

        } else if (input.startsWith("unmark")) {
            response = taskList.mark(Integer.parseInt(input.substring(7)), false);

        } else if (input.startsWith("delete")) {
            if (input.trim().length() <= 6) {
                throw new EmptyTextException();
            }
            int id = Integer.parseInt(input.substring(input.indexOf(" ") + 1));
            response = taskList.delete(id);

        } else if (input.startsWith("todo")) {
            if (input.trim().length() <= 4) {
                throw new EmptyTextException();
            }
            String name = input.substring(input.indexOf(" ") + 1);
            response = taskList.addTask(name, "T", "");

        } else if (input.startsWith("deadline") || input.startsWith("event")) {
            int minLength = input.startsWith("deadline") ? 8 : 5;
            if (input.trim().length() <= minLength || input.substring(input.indexOf(" ") + 1).trim().equals("")) {
                throw new EmptyTextException();
            }
            String name = input.substring(input.indexOf(" ") + 1, input.indexOf("/"));
            String date = input.substring(input.indexOf("/") + 4);
            response = taskList.addTask(name, input.startsWith("deadline") ? "D" : "E", date);

        }else {
            throw new IllegalCommandException();
        }

        return response;
    }
}
