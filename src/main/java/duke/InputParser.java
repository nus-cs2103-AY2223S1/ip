package duke;

import duke.exceptions.EmptyTextException;
import duke.exceptions.EndProgramException;
import duke.exceptions.IllegalCommandException;

class InputParser {
    void parse(String input, TaskList taskList, Storage storage) throws EmptyTextException, IllegalCommandException, EndProgramException {
        if (input.startsWith("find")) {
            taskList.find(input.substring(6));
        } else if (input.equals("bye")) {
            storage.save(taskList);
            throw new EndProgramException();
        } else if (input.equals("list")) {
            taskList.list();
        } else if (input.startsWith("mark")) {
            taskList.mark(Integer.parseInt(input.substring(5)), true);
        } else if (input.startsWith("unmark")) {
            taskList.mark(Integer.parseInt(input.substring(7)), false);
        } else if (input.startsWith("todo")) {
            if (input.length() > 4) {
                String name = input.substring(input.indexOf(" ") + 1);
                taskList.todo(name);
            } else {
                throw new EmptyTextException();
            }
        } else if (input.startsWith("deadline")) {
            if (input.length() < 9 || input.substring(input.indexOf(" ") + 1).trim().equals("")) {
                throw new EmptyTextException();
            } else {
                String name = input.substring(input.indexOf(" ") + 1, input.indexOf("/"));
                String date = input.substring(input.indexOf("/") + 4);
                taskList.deadline(name, date);
            }
        } else if (input.startsWith("event")) {
            if (input.length() < 6 || input.substring(input.indexOf(" ") + 1).trim().equals("")) {
                throw new EmptyTextException();
            } else {
                String name = input.substring(input.indexOf(" ") + 1, input.indexOf("/"));
                String date = input.substring(input.indexOf("/") + 4);
                taskList.event(name, date);
            }
        } else if (input.startsWith("delete")) {
                int id = Integer.parseInt(input.substring(input.indexOf(" ") + 1));
                taskList.delete(id);
        } else {
            throw new IllegalCommandException();
        }
    }
}
