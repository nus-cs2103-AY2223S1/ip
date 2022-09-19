package meower;

import exception.MeowerException;
import exception.TaskListOutOfBoundsException;
import task.Task;

public class Ui {

    private TaskList tasks;
    private final String MESSAGE_GREETING = "Hello! I'm Duke  \n" + "What can I do for you?";
    private final String MESSAGE_FIND = "Tasks found:\n";
    private final String MESSAGE_LOG_ERROR = "File pathing for log file is facing issues, tasks not saved\n";
    private final String MESSAGE_MARK = "Task has been marked done:";
    private final String MESSAGE_UNMARK = "Task has been marked not done:";
    private final String MESSAGE_ADD = "Task added: \n";
    private final String MESSAGE_DELETE = "Task deleted: \n";
    private final String MESSAGE_ERROR_END = "Error in saving Tasks, progress from current session is not saved :(";
    
    public Ui(TaskList tasks) {
        this.tasks = tasks;
    }

    
    /** 
     * returns the greeting message
     * @return String
     */
    public String greeting() {
        return this.chat(this.MESSAGE_GREETING);
    }

    
    /** 
     * returns a log file error message
     * @return String
     */
    public String logFileError() {
        return this.chat(MESSAGE_LOG_ERROR);
    }
    
    /** 
     * Shows a list of the tasks the chatbot holds to the user in tasklist index order, 
     * indicates the task type, done or not done, task description and task date
     * @param tasks tasklist of tasks
     * @param isFind specify if there is a keyword to filter the tasks by
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
     * @param newTask new task inputted by user to be added to tasklist
     */
    public String add(Task newTask) {
        return this.chat(String.format("%s%s", MESSAGE_ADD, newTask));
    }

    
    /** 
     * Shows the task that was deleted to the user, indicates the task type, task description and task date
     * @param pos index in tasklist where task to be deleted
     * @throws MeowerException Main Meower chatbot Exception
     */
    public String delete(int pos) throws MeowerException{
        try {
            return this.chat(String.format("%s%s", MESSAGE_DELETE, this.tasks.get(pos)));
        } catch (TaskListOutOfBoundsException e) {
            throw new MeowerException(e.getLocalizedMessage());
        }
    }

    
    /** 
     * Shows the task that was marked done to the user, indicates the task type, done or not done, task description and task date
     * @param pos index in tasklist where task to be marked done
     * @throws MeowerException Main Meower chatbot Exception
     */
    public String mark(int pos) throws MeowerException {
        try {
            return this.chat(MESSAGE_MARK + this.tasks.get(pos));
        } catch (TaskListOutOfBoundsException e) {
            throw new MeowerException(e.getLocalizedMessage());
        }
    }

    
    /** 
     * Shows the task that was marked not done to the user, indicates the task type, done or not done, task description and task date
     * @param pos index in tasklist where task to be marked not done
     * @throws MeowerException Main Meower chatbot Exception
     */
    public String unmark(int pos) throws MeowerException {
        try {
            return this.chat(MESSAGE_UNMARK + tasks.get(pos));
        } catch (TaskListOutOfBoundsException e) {
            throw new MeowerException(e.getLocalizedMessage());
        }
    }

    
    /** 
     * returns the error messages from exceptions
     * @param e exception from which error message to be shown
     */
    public String errorMessage(MeowerException e) {
        return this.chat(e.getLocalizedMessage());
    }

    
    /** 
     * returns the error message when there was an error in ending the program
     * @return String
     */
    public String errorEnd() {
        return this.chat(MESSAGE_ERROR_END);
    }

    
    /** 
     * returns the message upon successful loading of task, specifies number of tasks loaded
     * @param numOfTasks number of tasks loaded
     * @return String
     */
    public String load(int numOfTasks) {
        return this.chat(String.format("Loaded %d tasks. \nHave a productive day!", numOfTasks));
    }

    
    /** 
     * returns the message upon successful saving of task, specifies number of tasks saved
     * @param numOfTasks number of tasks saved
     * @return String
     */
    public String bye(int numOfTasks) {
        return this.chat(String.format("Saved %d tasks. \nBye bye! :D", numOfTasks));
    }

    
    /** 
     * returns the message
     * @param message any message from Meower chatbot
     */
    public String chat(String message) {
        return(message);
    }
}
