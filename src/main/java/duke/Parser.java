package duke;

import java.time.format.DateTimeParseException;

public class Parser {

    public static void parseInput(String input, Ui ui, Storage storage, TaskList taskList) {
        String[] breakitdown = input.split(" ");
        String command = breakitdown[0];
        Task newTask;
        try {
            if (command.equals("bye")) {
                ui.bye();
                storage.write(taskList);
                System.exit(0);
            } else if (command.equals("list")) {
                ui.printTaskList(taskList);
            } else if (command.equals("unmark")) {
                int idx = Integer.parseInt(breakitdown[1]);
                Task toUnmark = taskList.unmark(idx);
                ui.printOnUnmark(toUnmark);
            } else if (command.equals("mark")) {
                int idx = Integer.parseInt(breakitdown[1]);
                Task toMark = taskList.mark(idx);
                ui.printOnMark(toMark);
            } else if (command.equals("todo")) {
                if (breakitdown.length == 1) {
                    throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
                }
                String taskName = input.substring(5);
                newTask = new ToDos(taskName);
                taskList.add(newTask);
                ui.printOnAdd(newTask, taskList);
            } else if (command.equals("deadline")) {
                if (breakitdown.length == 1) {
                    throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
                }
                String desc = input.substring(9);
                if (!desc.contains("/by")) {
                    throw new DukeException("OOPS!!! /by keyword not found!");
                }
                String[] taskNameBy = desc.split("/by ");
                if (taskNameBy.length == 1) {
                    throw new DukeException("OOPS!!! Date of deadline cannot be empty.");
                }
                String taskName = taskNameBy[0];
                String by = taskNameBy[1];
                try {
                    newTask = new Deadlines(taskName, by);
                    taskList.add(newTask);
                    ui.printOnAdd(newTask, taskList);
                } catch (DateTimeParseException e) {
                    System.out.println("OOPS!!! Please enter date in YYYY-MM-DD format");
                }
            } else if (command.equals("event")) {
                if (breakitdown.length == 1) {
                    throw new DukeException("OOPS!!! The description of an event cannot be empty.");
                }
                String desc = input.substring(6);
                if (!desc.contains("/at")) {
                    throw new DukeException("OOPS!!! /at keyword not found!");
                }
                String[] taskNameLocation = desc.split("/at ");
                if (taskNameLocation.length == 1) {
                    throw new DukeException("OOPS!!! Location of event cannot be empty.");
                }
                String taskName = taskNameLocation[0];
                String location = taskNameLocation[1];
                newTask = new Events(taskName, location);
                taskList.add(newTask);
                ui.printOnAdd(newTask, taskList);
            } else if (command.equals("delete")) {
                int idx = Integer.parseInt(breakitdown[1]);
                Task toDelete = taskList.delete(idx);
                ui.printOnDelete(toDelete, taskList);
            } else {
                throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } catch (DukeException e) {
            System.out.println(e.toString());
        }
    }

}
