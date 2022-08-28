package zeus.main;

import java.util.ArrayList;

import zeus.task.Task;

/**
 * Class that deals with interactions with the user
 */
public class Ui {

    private StringBuilder response;

    /**
     * Constructor of Ui class.
     */
    public Ui() {
        this.response = new StringBuilder();
    }

    /**
     * Initialises a new output.
     */
    public void newResponse() {
        this.response.setLength(0);
    }

    /**
     * Returns String of output.
     *
     * @return String of output
     */
    public String getResponse() {
        return this.response.toString();
    }

    private void addToResponse(String s) {
        this.response.append(s);
    }

    /**
     * Prints message to indicate task is added and size of task list.
     *
     * @param task Task to be added
     * @param size Size of list after task is added
     */
    public void printAddTask(Task task, int size) {
        addToResponse("Very well. Your task has been added:\n");
        addToResponse("\t" + task + "\n");
        addToResponse("Now you have " + size + " tasks in the list.\n");
    }

    /**
     * Prints message to indicated task is deleted and size of task list.
     *
     * @param task Task to be deleted
     * @param size Size of list after task is deleted
     */
    public void printDeleteTask(Task task, int size) {
        addToResponse("Very well. I've removed this task:\n");
        addToResponse("  " + task + "\n");
        addToResponse("Now you have " + size + " tasks in the list.\n");
    }

    /**
     * Prints the tasks in the task list.
     *
     * @param taskList ArrayList containing tasks.
     */
    public void printList(ArrayList<Task> taskList) {
        addToResponse("These are the tasks in your list:\n");
        for (int i = 0; i < taskList.size(); i++) {
            Task t = taskList.get(i);
            String currLine = "\t " + (i + 1) + "." + t + "\n";
            addToResponse(currLine);
        }
    }

    /**
     * Print tasks when user uses find command.
     *
     * @param taskList ArrayList of tasks matching user search
     */
    public void printMatchingTasks(ArrayList<Task> taskList) {
        addToResponse("These are the matching tasks in your list:\n");
        for (int i = 0; i < taskList.size(); i++) {
            Task t = taskList.get(i);
            String currLine = "\t " + (i + 1) + "." + t + "\n";
            addToResponse(currLine);
        }
    }


    /**
     * Adds exit message to response.
     */
    public void addExitMessage() {
        addToResponse("It has been a pleasure. I’m sure you’ll be coming back soon enough.");
    }


    /**
     * Adds message to response.
     */
    public void addMessageToResponse(String message) {
        addToResponse(message);
    }

}
