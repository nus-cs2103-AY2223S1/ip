package pikachu.command;

import java.time.LocalDate;

import pikachu.PikachuException;
import pikachu.Storage;
import pikachu.TaskList;
import pikachu.Ui;
import pikachu.task.Deadline;
import pikachu.task.Event;
import pikachu.task.Todo;

/**
 * Represents command that performs add function to the to do list. A <code>AddCommand</code> object corresponds to
 * an instruction to add deadline/event/to do e.g., <code>"event formal dinner /at dinner hall"</code>.
 */
public class AddCommand extends Command {
    private final String input;

    public AddCommand(String fullCommand) {
        input = fullCommand;
    }

    /**
     * Saves the new task if valid, else throw exception.
     * Informs the user about the situation through String output.
     *
     * @param tasks Task List of all tasks currently.
     * @param ui Ui for user to see.
     * @param storage Storage in charge of the current tasks.
     * @return Pikachu's reply.
     * @throws PikachuException If invalid date format for deadline, empty name of the task.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws PikachuException {
        String tempStr = "";
        boolean isValidDdl = input.startsWith("deadline ") && input.contains(" /by ");
        boolean isValidTodo = input.startsWith("todo ");
        boolean isValidEvent = input.startsWith("event ") && input.contains(" /at ");
        if (isValidDdl) {
            String temp1 = input.split(" ", 2)[1];
            String[] temp2 = temp1.split(" /by ", 2);
            try {
                LocalDate date = LocalDate.parse(temp2[1]);
                Deadline newDdl = new Deadline(temp2[0], date);
                tasks.getTaskList().add(newDdl);
                tempStr = "Pikapi(added): " + newDdl + '\n';
                tempStr += "Pikaaaaa: " + tasks.getTaskList().size()
                        + (tasks.getTaskList().size() > 1 ? " tasks" : " task");
            } catch (Exception e) {
                throw new PikachuException("Need valid date format(yyyy-mm-dd) Pikaaaa");
            }
        } else if (isValidTodo) { //add as tasks
            Todo newTodo = new Todo(input.substring(5));
            if (newTodo.getDescription().equals("")) {
                throw new PikachuException("Pi-cannot be empty-pi");
            } else {
                tasks.getTaskList().add(newTodo);
                tempStr = "Pikapi(added): " + newTodo + '\n';
                tempStr += "Pikaaaaa: " + tasks.getTaskList().size()
                        + (tasks.getTaskList().size() > 1 ? " tasks" : " task");
            }
        } else if (isValidEvent) {
            String temp1 = input.split(" ", 2)[1];
            String[] temp2 = temp1.split(" /at ", 2);
            Event newEvent = new Event(temp2[0], temp2[1]);
            tasks.getTaskList().add(newEvent);
            tempStr = "Pikapi(added): " + newEvent + '\n';
            tempStr += "Pikaaaaa: " + tasks.getTaskList().size()
                    + (tasks.getTaskList().size() > 1 ? " tasks" : " task");
        }
        storage.save(tasks.getTaskList());
        return tempStr;
    }

    /**
     * Returns whether this function performs an exit action on the task manager.
     * @return false, do not exit.
     */
    public boolean isExit() {
        return false;
    }
}
