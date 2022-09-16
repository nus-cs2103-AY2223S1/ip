package duke;

/**
 * Class responsible for handling all events in Duke
 */
public class EventHandler {

    private TaskList taskList;
    private GuiUserInterface guiUserInterface;
    private Storage storage;

    /**
     * Constructor for EventHandler.
     *
     * @param taskList TaskList that Event Handler uses.
     * @param guiUserInterface GuiUserInterface that Event Handler uses.
     * @param storage Storage that Event Handler uses.
     */
    public EventHandler(TaskList taskList, GuiUserInterface guiUserInterface, Storage storage) {
        this.taskList = taskList;
        this.guiUserInterface = guiUserInterface;
        this.storage = storage;
    }

    /**
     * Adds a ToDo into taskList.
     *
     * @param input User input
     * @return The response from the Duke bot
     * @throws DukeNoDescriptionException Thrown when description is empty.
     */
    public String addTodo(String input) throws DukeNoDescriptionException {
        if (input.length() == 4) {
            throw new DukeNoDescriptionException();
        }
        String description = input.substring(5);
        ToDo tempTask = new ToDo(description);
        this.taskList.add(tempTask);
        storage.save();
        assert description.length() > 0 : "description should not be empty";
        return guiUserInterface.addTaskMessage(tempTask);
    }

    /**
     * Adds a Deadline into taskList.
     *
     * @param input User input
     * @return The response from the Duke bot
     * @throws DukeNoDescriptionException Thrown when description is empty.
     */
    public String addDeadline(String input) throws DukeNoDescriptionException {
        if (input.length() == 8) {
            throw new DukeNoDescriptionException();
        }

        int endIndex = input.indexOf("/");
        String description = input.substring(9, endIndex);
        String by = input.substring(endIndex + 4);

        Deadline tempTask = new Deadline(description, by);

        this.taskList.add(tempTask);
        storage.save();
        assert description.length() > 0 : "description should not be empty";
        return guiUserInterface.addTaskMessage(tempTask);
    }

    /**
     * Adds an Event into taskList.
     *
     * @param input User input.
     * @return The response from the Duke bot
     * @throws DukeNoDescriptionException Thrown when description is empty.
     */
    public String addEvent(String input) throws DukeNoDescriptionException {
        if (input.length() == 5) {
            throw new DukeNoDescriptionException();
        }

        int endIndex = input.indexOf("/");
        String description = input.substring(6, endIndex);
        String at = input.substring(endIndex + 4);

        Event tempTask = new Event(description, at);

        this.taskList.add(tempTask);
        storage.save();
        assert description.length() > 0 : "description should not be empty";
        return guiUserInterface.addTaskMessage(tempTask);
    }

    /**
     * Marks an item in taskList as done and prints the required message.
     *
     * @param input User input.
     * @return The response from the Duke bot
     */
    public String markTask(String input) {
        int taskIndex = Integer.parseInt(input.substring(5)) - 1;
        taskList.get(taskIndex).markAsDone();
        storage.save();
        assert taskIndex >= 0 : "taskIndex should be >= 0";
        return guiUserInterface.markTaskMessage(taskIndex);
    }

    /**
     * Unmarks an item in taskList as undone and prints the required message.
     *
     * @param input User input.
     * @return The response from the Duke bot
     */
    public String unmarkTask(String input) {
        int taskIndex = Integer.parseInt(input.substring(7)) - 1;
        taskList.get(taskIndex).markAsUndone();
        storage.save();
        assert taskIndex >= 0 : "taskIndex should be >= 0";
        return guiUserInterface.unmarkTaskMessage(taskIndex);

    }

    /**
     * Deletes an item from taskList and prints the required message.
     *
     * @param input User input.
     * @return The response from the Duke bot
     */
    public String deleteTask(String input) {
        int taskIndex = Integer.parseInt(input.substring(7)) - 1;
        String temp = guiUserInterface.taskDeletedMessage(taskIndex);
        this.taskList.remove(taskIndex);
        storage.save();
        assert taskIndex >= 0 : "taskIndex should be >= 0";
        return temp;
    }


    /**
     * Finds Tasks in taskList that match the description and prints them out using userInterface.
     *
     * @param input User input.
     * @return The response from the Duke bot
     */
    public String find(String input) {
        String query = input.substring(5);
        TaskList matches = taskList.filterByKeyword(query);
//        userInterface.printMatches(matches);
        assert query.length() >= 0 : "keywords should not be empty";
        return guiUserInterface.printMatches(matches);
    }

    /**
     * Handles output of user requesting for help.
     *
     * @return The response from Duke.
     */
    public String help() {
        return guiUserInterface.printHelp();
    }

    /**
     * Handles output of user saying hi.
     *
     * @return The response from Duke.
     */
    public String hi() {
        return guiUserInterface.welcomeMessage();
    }
}
