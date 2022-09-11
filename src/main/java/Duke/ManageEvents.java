package Duke;


/**
 * Class to manage the events in the program
 */
public class ManageEvents {

    public TaskList taskList;
    private Graphics graphics;

    /**
     * Constructor for the class
     * @param taskList The list of tasks
     * @param graphics The graphics of the program
     */
    public ManageEvents(TaskList taskList, Graphics graphics) {
        this.taskList = taskList;
        this.graphics = graphics;
    }

    /**
     * Method to add the object todo
     * @param input                      The input by the user
     * @throws BlankDescriptionException The exception thrown when
     *                                   user enter a blank field
     */
    void addTodo(String input) throws BlankDescriptionException {
        if (input.length() == 4) {
            throw new BlankDescriptionException();
        }
        String description = input.substring(5);
        ToDo temp = new ToDo(description);
        this.taskList.add(temp);
        graphics.addMessage(temp);
    }

    /**
     * Method to add the object deadline
     * @param input                      The input by the user
     * @throws BlankDescriptionException The exception thrown when
     *                                   user enter a blank field
     */
    void addDeadline(String input) throws BlankDescriptionException {
        if (input.length() == 8) {
            throw new BlankDescriptionException();
        }
        int endAt = input.indexOf("/");
        String description = input.substring(9, endAt);
        String by = input.substring(endAt + 4);

        Deadline tempTask = new Deadline(description, by);
        this.taskList.add(tempTask);
        graphics.addMessage(tempTask);
    }

    /**
     * Method to add the object event
     * @param input                      The input by the user
     * @throws BlankDescriptionException The exception thrown when user
     *                                   enter a blank field
     */
    void addEvent(String input) throws BlankDescriptionException {
        if (input.length() == 5) {
            throw new BlankDescriptionException();
        }
        int endAt = input.indexOf("/");
        String description = input.substring(6, endAt);
        String at = input.substring(endAt + 4);

        Event tempTask = new Event(description, at);
        this.taskList.add(tempTask);
        graphics.addMessage(tempTask);
    }

    /**
     * Method to mark a task in the task list as done
     * @param input The input by the user
     */
    public void markTask(String input) {
        int taskIndex = Integer.parseInt(input.substring(5)) - 1;
        taskList.get(taskIndex).markAsDone();
        graphics.markMessage(taskIndex);
    }

    /**
     * Method to mark a task in the task list as not done
     * @param input The input by the user
     */
    public void unmarkTask(String input) {
        int taskIndex = Integer.parseInt(input.substring(7)) - 1;
        taskList.get(taskIndex).markAsUndone();
        graphics.unmarkMessage(taskIndex);
    }

    /**
     * Method to delete a task in the task list
     * @param taskIndex The position of the task that the
     *                  user want to delete from the task list
     */
    public void deleteTask(int taskIndex) {
        Graphics.deleteMessage(this.taskList, taskIndex);
        this.taskList.remove(taskIndex);
    }

}
