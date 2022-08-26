package duke.command;

import duke.DukeException;
import duke.TaskList;
import duke.task.DeadlineTask;
import duke.task.EventTask;
import duke.task.Task;
import duke.task.ToDoTask;

import java.time.LocalDate;

public class AddCommand extends Command {
    private String type;
    private String description;
    
    public AddCommand(String type, String description) {
        this.type = type;
        this.description = description; 
    }
   
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (o instanceof AddCommand) {
            AddCommand command = (AddCommand) o;
            return command.type.equals(this.type) && command.description.equals(this.description);
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
    public void execute(TaskList tasks) throws DukeException {
        switch(type) {
        case "todo":
            Task tTask = new ToDoTask(description);
            tasks.addTask(tTask);
            break;
        case "deadline":
            String by = description.split(" /by ")[1];
            LocalDate date = LocalDate.parse(by);
            String info = description.split(" /by ")[0];
            Task dTask = new DeadlineTask(info, date);
            tasks.addTask(dTask);
            break;
        case "event":
            String at = description.split(" /at ")[1];
            String about = description.split(" /at ")[0];
            Task eTask = new EventTask(about, at);
            tasks.addTask(eTask);
            break;
        }
    }
}
