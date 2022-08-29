package duke.command;

import duke.*;

import java.time.LocalDate;


/**
 * Encapsulates a command to add a Task.
 */
public class AddCommand extends Command {
    String description;
    LocalDate date = null;
    String type;

    /**
     * A constructor to create a AddCommand class
     *
     * @param type type of class to be added
     * @param name description of class
     * @param date Date of the class if it has and event
     */
    public AddCommand(String type, String name , LocalDate date) {
        this.type = type;
        this.description = name;
        this.date = date;
    }

    /**
     * A constructor to create a AddCommand class, specifically a todo
     *
     * @param type type of class to be added
     * @param name description of class
     */
    public AddCommand(String type, String name ) {
        this.type = type;
        this.description = name;
    }

    /**
     * A function that executes the effect of adding a task
     *  @param taskList stores the tasks of the program
     * @param storage reads and writes from the text file which stores the tasks in memory
     * @param ui interfaces with the user using the commandline
     * @return
     */
    @Override
    public String execute(TaskList taskList, Storage storage, Ui ui) throws DukeException {
        String returnString;
        Task task = null;
        if(description.equals("")) {
            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
        }

        switch(type) {
            case "todo" :
                task = new Todo(description);
                break;
            case "event":
                task = new Event(description,date);
                break;
            case "deadline" :
                task = new Deadline(description,date);
                break;
        }

        taskList.addTask(task);
        returnString = ui.showAddCommand(task, taskList.size());
        storage.writeFile(taskList.getTaskList(),"duke.txt");
        return returnString;
    }
}
