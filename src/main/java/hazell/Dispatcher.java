package hazell;

import hazell.entities.Deadline;
import hazell.entities.Event;
import hazell.entities.TaskList;
import hazell.entities.ToDo;
import hazell.exceptions.HazellException;
import hazell.exceptions.UnknownCommand;

public class Dispatcher {
    public Dispatcher() { }

    public String handle(Command command, TaskList taskList) throws HazellException {
        if (command.startsWith("bye")) {
            return "Bye. Hope to see you again soon!";
//            System.exit(0);
        } else if (command.startsWith("list")) {
            return String.format("Here are the tasks in your list:\n%s", taskList);
        } else if (command.startsWith("mark")) {
            int index = Integer.parseInt(command.getTrailingArgs().get(0)) - 1;
            return taskList.markTaskAsDone(index);
        } else if (command.startsWith("unmark")) {
            int index = Integer.parseInt(command.getTrailingArgs().get(0)) - 1;
            return taskList.markTaskAsUndone(index);
        } else if (command.startsWith("todo")) {
            String description = String.join(" ", command.getTrailingArgs());
            return taskList.addTask(ToDo.create(description));
        } else if (command.startsWith("deadline")) {
            String description = String.join(" ", command.getTrailingArgs());
            String time = command.getKwarg("by");
            return taskList.addTask(Deadline.create(description, time));
        } else if (command.startsWith("event")) {
            String description = String.join(" ", command.getTrailingArgs());
            String time = command.getKwarg("at");
            return taskList.addTask(Event.create(description, time));
        } else if (command.startsWith("delete")) {
            int index = Integer.parseInt(command.getTrailingArgs().get(0)) - 1;
            return taskList.deleteTask(index);
        } else if (command.startsWith("find")) {
            String keyword = command.getTrailingArgs().get(0);
            return taskList.findMatchingTasks(keyword);
        }
        throw new UnknownCommand();
    }
}
