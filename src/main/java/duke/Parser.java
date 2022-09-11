package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Class for parsing strings into commands.
 */
public class Parser {
    class EmptyInputException extends RuntimeException {
        public EmptyInputException() {
            super("☹ OOPS!!! The description of a todo cannot be empty.");
        }
    }

    class InvalidInputException extends RuntimeException {
        public InvalidInputException() {
            super("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Parses a string into a command.
     * @param line - the string to parse
     * @param todolist - the task list to modify
     * @return 1 if the user wants to quit, 0 otherwise
     */
    public int parse(String line, TaskList todolist) {
        switch (line) {
        case "":
            throw new EmptyInputException();

        case "bye":
            String response = execute(line, todolist);
            System.out.println(response);
            return 1;

        default:
            response = execute(line, todolist);
            System.out.println(response);
            break;
        }
        return 0;
    }

    /**
     * Executes a command.
     * @param line - the command to execute
     * @param todolist - the task list to modify
     * @return the response to the command
     */
    public String execute(String line, TaskList todolist) {
        switch (line) {
        case "":
            throw new EmptyInputException();

        case "bye":
            return "See you again!";

        case "list":
            return todolist.getData();

        case "save":
            Storage.save(todolist);
            return "Saved!";

        default:
            // We can't use switch statements since we want regex matching
            if (line.matches("mark \\d+")) {
                int index = Integer.parseInt(line.split(" ")[1]);
                if (index > todolist.getLength()) {
                    break;
                }

                todolist.mark(index - 1);
                return todolist.getData();
            }

            if (line.matches("mark [\\d ]+")) {
                String[] indices = line.substring(5).split(" " );
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
                if (index > todolist.getLength()) {
                    break;
                }

                todolist.unmark(index - 1);
                return todolist.getData();
            }

            if (line.matches("unmark [\\d ]+")) {
                String[] indices = line.substring(7).split(" " );
                for (String index : indices) {
                    int i = Integer.parseInt(index);
                    if (i > todolist.getLength()) {
                        continue;
                    }

                    todolist.unmark(i - 1);
                }
                return "Unmarked multiple tasks!";
            }
            if (line.matches("deadline .* by .*")) {
                assert line.startsWith("deadline ");
                String[] res = line.substring(9).split(" by ");
                String desc = res[0];
                String deadline = res[1];

                todolist.addDeadline(desc, deadline);
                return String.format("Added: %s (by: %s)", desc, deadline);
            }

            if (line.matches("deadline .* /by .*")) {
                assert line.startsWith("deadline ");
                String[] res = line.substring(9).split(" /by ");
                String desc = res[0];
                LocalDate date = LocalDate.parse(res[1]);

                todolist.addDeadline(desc, date);
                return String.format(
                        "Added: %s (by: %s)", desc, date.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                );
            }

            if (line.matches("event .* at .*")) {
                assert line.startsWith("event ");
                String[] res = line.substring(6).split(" at ");
                String desc = res[0];
                String time = res[1];

                todolist.addEvent(desc, time);
                return String.format("Added: %s (at: %s)", desc, time);
            }

            if (line.matches("todo .*")) {
                assert line.startsWith("todo ");
                line = line.substring(5);
                todolist.addTask(line);
                return "Added: " + line;
            }

            if (line.matches("delete \\d+")) {
                int index = Integer.parseInt(line.split(" ")[1]);
                if (index > todolist.getLength()) {
                    break;
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
        }
        throw new InvalidInputException();
    }
}
