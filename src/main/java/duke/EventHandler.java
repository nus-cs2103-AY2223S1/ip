package duke;

public class EventHandler {

    public TaskList taskList;
    private UserInterface userInterface;
    private GuiUserInterface guiUserInterface;
    private Storage storage;

    /**
     * Contructor for EventHandler.
     *
     * @param taskList      TaskList for EventHandler to handle.
     * @param userInterface UserInterface that EventHandler uses.
     */
    public EventHandler(TaskList taskList, UserInterface userInterface, GuiUserInterface guiUserInterface, Storage storage) {
        this.taskList = taskList;
        this.userInterface = userInterface;
        this.guiUserInterface = guiUserInterface;
        this.storage = storage;
    }

    /**
     * Adds a ToDo into taskList.
     *
     * @param input User input
     * @throws DukeNoDescriptionException Thrown when description is empty.
     */
    public String addTodo(String input) throws DukeNoDescriptionException {
        if (input.length() == 4) {
            throw new DukeNoDescriptionException();
        }
        String description = input.substring(5);
        ToDo tempTask = new ToDo(description);
        this.taskList.add(tempTask);
//        userInterface.addTaskMessage(tempTask);
        storage.save();
        return guiUserInterface.addTaskMessage(tempTask);
    }

    /**
     * Adds a Deadline into taskList.
     *
     * @param input User input
     * @throws DukeNoDescriptionException Thrown when description is empty.
     */
    public String addDeadline(String input) throws DukeNoDescriptionException {
        if (input.length() == 8) {
            throw new DukeNoDescriptionException();
        }

        int endAt = input.indexOf("/");
        String description = input.substring(9, endAt);
        String by = input.substring(endAt + 4);

        Deadline tempTask = new Deadline(description, by);

        this.taskList.add(tempTask);
//        userInterface.addTaskMessage(tempTask);
        storage.save();
        return guiUserInterface.addTaskMessage(tempTask);
    }

    /**
     * Adds an Event into taskList.
     *
     * @param input User input.
     * @throws DukeNoDescriptionException Thrown when description is empty.
     */
    public String addEvent(String input) throws DukeNoDescriptionException {
        if (input.length() == 5) {
            throw new DukeNoDescriptionException();
        }

        int endAt = input.indexOf("/");
        String description = input.substring(6, endAt);
        String at = input.substring(endAt + 4);

        Event tempTask = new Event(description, at);

        this.taskList.add(tempTask);
//        userInterface.addTaskMessage(tempTask);
        storage.save();
        return guiUserInterface.addTaskMessage(tempTask);
    }

    /**
     * Marks an item in taskList as done and prints the required message.
     *
     * @param input User input.
     */
    public String markTask(String input) {
        int taskIndex = Integer.parseInt(input.substring(5)) - 1;
        taskList.get(taskIndex).markAsDone();
//        userInterface.markTaskMessage(taskIndex);
        storage.save();
        return guiUserInterface.markTaskMessage(taskIndex);
    }

    /**
     * Unmarks an item in taskList as undone and prints the required message.
     *
     * @param input User input.
     */
    public String unmarkTask(String input) {
        int taskIndex = Integer.parseInt(input.substring(7)) - 1;
        taskList.get(taskIndex).markAsUndone();
//        userInterface.unmarkTaskMessage(taskIndex);
        storage.save();
        return guiUserInterface.unmarkTaskMessage(taskIndex);

    }

    /**
     * Deletes an item from taskList and prints the required message.
     *
     * @param input User input.
     */
    public String deleteTask(String input) {
        int taskIndex = Integer.parseInt(input.substring(7)) - 1;
//        UserInterface.taskDeletedMessage(taskIndex);
        String temp = guiUserInterface.taskDeletedMessage(taskIndex);
        this.taskList.remove(taskIndex);
        storage.save();
        return temp;
    }

    /**
     * Finds Tasks in taskList that match the description and prints them out using userInterface.
     *
     * @param input User input.
     */
    public String find(String input) {
        String query = input.substring(5);
        TaskList matches = taskList.filterByKeyword(query);
//        userInterface.printMatches(matches);
        return guiUserInterface.printMatches(matches);
    }

}
