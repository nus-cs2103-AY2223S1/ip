package duke.command;

import java.time.LocalDate;

import duke.DukeException;
import duke.TaskList;
import duke.task.DeadlineTask;
import duke.task.EventTask;
import duke.task.Task;
import duke.task.ToDoTask;

public class AddCommand extends Command {
    private final String commandType;
    private final String description;
    
    public AddCommand(String commandType, String description) {
        this.commandType = commandType;
        this.description = description; 
    }
   
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (o instanceof AddCommand) {
            AddCommand command = (AddCommand) o;
            return command.commandType.equals(this.commandType) && command.description.equals(this.description);
        } else {
            return false;
        }
    }

    /**
     * Executes the command by adding a todo, deadline or event item.
     *
     * @param tasks The user's current list of tasks.
     * 
     * @throws DukeException If the data file cannot be accessed.
     */
    public String execute(TaskList tasks) throws DukeException {
        switch(commandType) {
        case "todo":
            Task tTask = new ToDoTask(description);
            return tasks.addTask(tTask);
        case "deadline":
            String by = description.split(" /by ")[1];
            LocalDate date = LocalDate.parse(by);
            String info = description.split(" /by ")[0];
            Task dTask = new DeadlineTask(info, date);
            return tasks.addTask(dTask);
        case "event":
            String at = description.split(" /at ")[1];
            String about = description.split(" /at ")[0];
            Task eTask = new EventTask(about, at);
            return tasks.addTask(eTask);
        }
        return " ";
    }
}
