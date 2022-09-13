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
    public String addTodo(String input) throws BlankDescriptionException {
        if (input.length() == 4) {
            throw new BlankDescriptionException();
        }
        String description = input.substring(5);
        ToDo temp = new ToDo(description);
        this.taskList.add(temp);
        return graphics.addMessage(temp);
    }

    /**
     * Method to add the object deadline
     * @param input                      The input by the user
     * @throws BlankDescriptionException The exception thrown when
     *                                   user enter a blank field
     */
    public String addDeadline(String input) throws BlankDescriptionException {
        if (input.length() == 8) {
            throw new BlankDescriptionException();
        }
        int endAt = input.indexOf("/");
        String description = input.substring(9, endAt);
        String by = input.substring(endAt + 4);

        Deadline tempTask = new Deadline(description, by);
        this.taskList.add(tempTask);
        return graphics.addMessage(tempTask);
    }

    /**
     * Method to add the object event
     * @param input                      The input by the user
     * @throws BlankDescriptionException The exception thrown when user
     *                                   enter a blank field
     */
    public String addEvent(String input) throws BlankDescriptionException {
        if (input.length() == 5) {
            throw new BlankDescriptionException();
        }
        int endAt = input.indexOf("/");
        String description = input.substring(6, endAt);
        String at = input.substring(endAt + 4);

        Event tempTask = new Event(description, at);
        this.taskList.add(tempTask);
        return graphics.addMessage(tempTask);
    }

    /**
     * Method to mark a task in the task list as done
     * @param input The input by the user
     */
    public String markTask(String input) {
        int taskIndex = Integer.parseInt(input.substring(5)) - 1;
        taskList.get(taskIndex).markAsDone();
        assert taskIndex >= 0: "task index should not be negative";
        return graphics.markMessage(taskIndex);
    }

    /**
     * Method to mark a task in the task list as not done
     * @param input The input by the user
     */
    public String unmarkTask(String input) {
        int taskIndex = Integer.parseInt(input.substring(7)) - 1;
        taskList.get(taskIndex).markAsUndone();
        assert taskIndex >= 0: "task index should not be negative";
        return graphics.unmarkMessage(taskIndex);
    }

    /**
     * Method to delete a task in the task list
     * @param taskIndex The position of the task that the
     *                  user want to delete from the task list
     */
    public String deleteTask(int taskIndex) {
        assert taskIndex >= 0: "task index should not be negative";
        String response = Graphics.deleteMessage(this.taskList, taskIndex);
        this.taskList.remove(taskIndex);
        return response;
    }

    public String find(String input) {
        String keyword = input.substring(5);
        TaskList results = taskList.findTasks(keyword);
        return graphics.printSearches(results);

    }



}
