package hazell;

import hazell.entities.Deadline;
import hazell.entities.Event;
import hazell.entities.TaskList;
import hazell.entities.ToDo;
import hazell.exceptions.HazellException;
import hazell.exceptions.UnknownCommand;

/**
 * Class that dispatches user command to corresponding action that bot should take.
 */
public class Dispatcher {
    public Dispatcher() { }

    public String handle(Command command, TaskList taskList) throws HazellException {
        String description, time;
        int index;

        switch (command.getFirstArg()) {
        case "bye":
            return "Bye. Hope to see you again soon!";
//            System.exit(0);
        case "list":
            return String.format("Here are the tasks in your list:\n%s", taskList);
        case "mark":
            index = Integer.parseInt(command.getTrailingArgs().get(0)) - 1;
            return taskList.markTaskAsDone(index);
        case "unmark":
            index = Integer.parseInt(command.getTrailingArgs().get(0)) - 1;
            return taskList.markTaskAsUndone(index);
        case "todo":
            description = String.join(" ", command.getTrailingArgs());
            return taskList.addTask(ToDo.create(description));
        case "deadline":
            description = String.join(" ", command.getTrailingArgs());
            time = command.getKwarg("by");
            return taskList.addTask(Deadline.create(description, time));
        case "event":
            description = String.join(" ", command.getTrailingArgs());
            time = command.getKwarg("at");
            return taskList.addTask(Event.create(description, time));
        case "delete":
            index = Integer.parseInt(command.getTrailingArgs().get(0)) - 1;
            return taskList.deleteTask(index);
        case "find":
            String keyword = command.getTrailingArgs().get(0);
            return taskList.findMatchingTasks(keyword);
        case "postpone":
            index = Integer.parseInt(command.getTrailingArgs().get(0)) - 1;
            time = command.getTrailingArgs().get(1);
            return taskList.postponeTimeSensitiveTask(index, time);
        default:
            throw new UnknownCommand();
        }
    }
}
