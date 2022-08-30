package duke;

public class Handler {

    private TaskList taskList;
    private Ui ui;


    /**
     * Constructor for Handler.
     *
     * @param taskList taskList
     * @param ui Ui
     */
    public Handler(TaskList taskList, Ui ui) {
        this.taskList = taskList;
        this.ui = ui;
    }

    /**
     * Marks an item in the list as done and prints the necessary string message.
     *
     * @param echo task and description.
     * @return String string from ui.printDone.
     * @throws DukeEmptyDescriptionException thrown when description is empty.
     */
    public String handleMark(String echo) throws DukeEmptyDescriptionException {
        if (echo.length() == 4) {
            throw new DukeEmptyDescriptionException();
        }
        int index = Integer.parseInt(String.valueOf(echo.charAt(5))) - 1;
        TaskList.taskList.get(index).markAsDone();
        return ui.printMark(index);
    }
    /**
     * Unmarks an item in the list as done and prints the necessary string message.
     *
     * @param echo task and description.
     * @return String string from ui.printDone.
     * @throws DukeEmptyDescriptionException thrown when description is empty.
     */
    public String handleUnmark(String echo) throws DukeEmptyDescriptionException {
        if (echo.length() == 6) {
            throw new DukeEmptyDescriptionException();
        }
        int index = Integer.parseInt(String.valueOf(echo.charAt(7))) - 1;
        TaskList.taskList.get(index).unmarkAsDone();
        return ui.printUnmark(index);
    }

    /**
     * Adds todo into the list and print the relevant todo message.
     *
     * @param echo description of todo.
     * @return String string returned from ui.printTask.
     * @throws DukeEmptyDescriptionException thrown when description is empty.
     */
    public String handleToDo(String echo) throws DukeEmptyDescriptionException {
        if (echo.length() == 4) {
            throw new DukeEmptyDescriptionException();
        }
        String task = echo.substring(5);
        ToDo todo = new ToDo(task);
        TaskList.taskList.add(todo);
        return ui.printTask(todo);
    }
    /**
     * Adds deadline into the list and prints deadline message.
     *
     * @param echo description and due date of deadline.
     * @return String string returned from ui.printTask.
     * @throws DukeEmptyDescriptionException thrown when user does not input the timing required.
     *
     */
    public String handleDeadline(String echo) throws DukeEmptyDescriptionException {
        if (echo.length() == 8) {
            throw new DukeEmptyDescriptionException();
        }
        String time = echo.substring(echo.indexOf("/") + 4);
        String task = echo.substring(9, echo.indexOf("/"));
        Deadline deadline = new Deadline(task, time);
        TaskList.taskList.add(deadline);
        return ui.printTask(deadline);
    }

    /**
     * Adds event into the list and print the relevant event message.
     *
     * @param echo full description of event, including command.
     * @return String string returned from ui.printTask.
     * @throws DukeEmptyDescriptionException thrown when user does not input the timing required.
     */
    public String handleEvent(String echo) throws DukeEmptyDescriptionException {
        if (echo.length() == 5) {
            throw new DukeEmptyDescriptionException();
        }
        String time = echo.substring(echo.indexOf("/") + 4);
        String task = echo.substring(6, echo.indexOf("/"));
        Event event = new Event(task, time);
        TaskList.taskList.add(event);
        return ui.printTask(event);
    }

    /**
     * Deletes an item from the list.
     *
     * @param echo delete command and description of type number.
     * @return String string returned from ui.printDelete.
     * @throws DukeEmptyDescriptionException thrown when description is empty.
     */
    public String handleDelete(String echo) throws DukeEmptyDescriptionException {
        if (echo.length() == 6) {
            throw new DukeEmptyDescriptionException();
        }
        int index = Integer.parseInt(String.valueOf(echo.charAt(7))) - 1;
        Task task = TaskList.taskList.remove(index);
        return ui.printDelete(task, index);
    }

    public String handleFind(String toEcho) {
        String tofind = toEcho.substring(5);
        return ui.printFind(tofind);
    }

    public String addTask(Task task) {
        taskList.add(task);
        return ui.printTask(task);
    }
}
