package duke.utils;

import duke.excecption.InvalidInputException;
import duke.task.TaskList;

/**
 * Class for parsing strings into commands.
 */
public class ActionParser {
    /**
     * Parses a string into a command.
     * @param line - the string to parse
     * @param todolist - the task list to modify
     * @return the response to the command
     */
    public static String parse(String line, TaskList todolist) {
        if (line.matches("mark \\d+")) {
            int index = Integer.parseInt(line.split(" ")[1]);
            if (index > todolist.getLength() || index < 1) {
                throw new InvalidInputException();
            }

            todolist.mark(index - 1);
            return todolist.getData();
        }

        if (line.matches("mark [\\d ]+")) {
            String[] indices = line.substring(5).split(" ");
            for (String index : indices) {
                int i = Integer.parseInt(index);
                if (i > todolist.getLength()) {
                    continue;
                }

                todolist.mark(i - 1);
            }
            return "Marked multiple tasks!";
        }

        if (line.matches("unmark \\d+")) {
            int index = Integer.parseInt(line.split(" ")[1]);
            if (index > todolist.getLength() || index < 1) {
                throw new InvalidInputException();
            }

            todolist.unmark(index - 1);
            return todolist.getData();
        }

        if (line.matches("unmark [\\d ]+")) {
            String[] indices = line.substring(7).split(" ");
            for (String index : indices) {
                int i = Integer.parseInt(index);
                if (i > todolist.getLength() || i < 1) {
                    continue;
                }

                todolist.unmark(i - 1);
            }
            return "Unmarked multiple tasks!";
        }

        if (line.matches("delete \\d+")) {
            int index = Integer.parseInt(line.split(" ")[1]);
            if (index > todolist.getLength() || index < 1) {
                throw new InvalidInputException();
            }
            todolist.delete(index - 1);
            return "Noted. I've removed this task";
        }

        if (line.matches("find .*")) {
            assert line.startsWith("find ");
            String search = line.substring(5);
            TaskList results = todolist.find(search);
            return "These are the matching results I could find:" + results.getData();
        }

        return "";
    }
}
