package duke;

import duke.exceptions.EmptyTextException;
import duke.exceptions.EndProgramException;
import duke.exceptions.IllegalCommandException;

class InputParser {
    void parse(String input, TaskList tasks, Storage storage) throws EmptyTextException, IllegalCommandException, EndProgramException {
        if (input.equals("bye")) {
            storage.save(tasks);
            throw new EndProgramException();
        } else if (input.equals("list")) {
            tasks.list();
        } else if (input.startsWith("mark")) {
            tasks.mark(Integer.parseInt(input.substring(5)), true);
        } else if (input.startsWith("unmark")) {
            tasks.mark(Integer.parseInt(input.substring(7)), false);
        } else if (input.startsWith("todo")) {
            if (input.length() > 4) {
                String name = input.substring(input.indexOf(" ") + 1);
                tasks.todo(name);
            } else {
                throw new EmptyTextException();
            }
        } else if (input.startsWith("deadline")) {
            if (input.length() < 9 || input.substring(input.indexOf(" ") + 1).trim().equals("")) {
                throw new EmptyTextException();
            } else {
                String name = input.substring(input.indexOf(" ") + 1, input.indexOf("/"));
                String date = input.substring(input.indexOf("/") + 4);
                tasks.deadline(name, date);
            }
        } else if (input.startsWith("event")) {
            if (input.length() < 6 || input.substring(input.indexOf(" ") + 1).trim().equals("")) {
                throw new EmptyTextException();
            } else {
                String name = input.substring(input.indexOf(" ") + 1, input.indexOf("/"));
                String date = input.substring(input.indexOf("/") + 4);
                tasks.event(name, date);
            }
        } else if (input.startsWith("delete")) {
                int id = Integer.parseInt(input.substring(input.indexOf(" ") + 1));
                tasks.delete(id);
        } else {
            throw new IllegalCommandException();
        }
    }
}
