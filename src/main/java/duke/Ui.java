package duke;

import exception.DukeException;
import exception.TaskListOutOfBoundsException;
import task.Task;

public class Ui {

    private TaskList tasks;

    private final String UI_LINE_SPACING = "----------------------------------------";
    private final String MESSAGE_GREETING = "Hello! I'm Duke  \n" + "What can I do for you?";
    private final String MESSAGE_FIND = "Tasks found:\n";
    private final String MESSAGE_LOG_ERROR = "File pathing for log file is facing issues, tasks not saved\n";
    private final String MESSAGE_MARK = "Task has been marked done:";
    private final String MESSAGE_UNMARK = "Task has been marked not done:";
    private final String MESSAGE_ADD = "Task added: \n";
    private final String MESSAGE_DELETE = "Task deleted: \n";
    
    public Ui(TaskList tasks) {
        this.tasks = tasks;
    }

    public String greeting() {
        return this.chat(this.MESSAGE_GREETING);
    }

    public String logFileError() {
        return this.chat(MESSAGE_LOG_ERROR);
    }
    
    /** 
     * Shows a list of the tasks the chatbot holds to the user in tasklist index order, 
     * indicates the task type, done or not done, task description and task date
     * @param tasks
     */
    public String list(TaskList tasks, boolean isFind) {
        if (isFind) {
            return this.chat(MESSAGE_FIND + tasks.toString());
        } else {
            return this.chat(tasks.toString());
        }
    }

    
    /** 
     * Shows the new task that was added to the user, indicates the task type, task description and task date
     * @param newTask
     */
    public String add(Task newTask) {
        return this.chat(String.format("%s%s", MESSAGE_ADD, newTask));
    }

    
    /** 
     * Shows the task that was deleted to the user, indicates the task type, task description and task date
     * @param pos
     * @throws DukeException
     */
    public String delete(int pos) throws DukeException{
        try {
            return this.chat(String.format("%s%s", MESSAGE_DELETE, this.tasks.get(pos)));
        } catch (TaskListOutOfBoundsException e) {
            throw new DukeException(e.getLocalizedMessage());
        }
    }

    
    /** 
     * Shows the task that was marked done to the user, indicates the task type, done or not done, task description and task date
     * @param pos
     * @throws DukeException
     */
    public String mark(int pos) throws DukeException {
        try {
            return this.chat(MESSAGE_MARK + this.tasks.get(pos));
        } catch (TaskListOutOfBoundsException e) {
            throw new DukeException(e.getLocalizedMessage());
        }
    }

    
    /** 
     * Shows the task that was marked not done to the user, indicates the task type, done or not done, task description and task date
     * @param pos
     * @throws DukeException
     */
    public String unmark(int pos) throws DukeException {
        try {
            return this.chat(MESSAGE_UNMARK + tasks.get(pos));
        } catch (TaskListOutOfBoundsException e) {
            throw new DukeException(e.getLocalizedMessage());
        }
    }

    
    /** 
     * Shows the error messages from exceptions
     * @param e
     */
    public String errorMessage(DukeException e) {
        return this.chat(e.getLocalizedMessage());
    }

    public String errorEnd() {
        return this.chat("Error in saving Tasks, progress from current session is not saved :(");
    }

    public String load(int numOfTasks) {
        return this.chat(String.format("Loaded %d tasks. \nHave a productive day!", numOfTasks));
    }

    public String bye(int numOfTasks) {
        return this.chat(String.format("Saved %d tasks. \nBye bye! :D", numOfTasks));
    }

    
    /** 
     * @param message
     */
    public String chat(String message) {
        return(UI_LINE_SPACING + "\n" + message + "\n" + UI_LINE_SPACING);
    }
}
