package duke;

public class EventHandler {

    public TaskList taskList;
    private UserInterface userInterface;

    public EventHandler(TaskList taskList, UserInterface userInterface) {
        this.taskList = taskList;
        this.userInterface = userInterface;
    }

    void addTodo(String input) throws DukeNoDescriptionException {
        if (input.length() == 4) {
            throw new DukeNoDescriptionException();
        }
        String description = input.substring(5);
        ToDo tempTask = new ToDo(description);
        this.taskList.add(tempTask);
        // Storage.writeToFile(tempTask);
        userInterface.addTaskMessage(tempTask);
    }

    void addDeadline(String input) throws DukeNoDescriptionException {
        if (input.length() == 8) {
            throw new DukeNoDescriptionException();
        }

        int endAt = input.indexOf("/");
        String description = input.substring(9, endAt);
        String by = input.substring(endAt + 4);

        Deadline tempTask = new Deadline(description, by);

        this.taskList.add(tempTask);
        userInterface.addTaskMessage(tempTask);
    }

    void addEvent(String input) throws DukeNoDescriptionException {
        if (input.length() == 5) {
            throw new DukeNoDescriptionException();
        }

        int endAt = input.indexOf("/");
        String description = input.substring(6, endAt);
        String at = input.substring(endAt + 4);

        Event tempTask = new Event(description, at);

        this.taskList.add(tempTask);
        userInterface.addTaskMessage(tempTask);
    }

    public void markTask(String input) {
        int taskIndex = Integer.parseInt(input.substring(5)) - 1;
        taskList.get(taskIndex).markAsDone();
        userInterface.markTaskMessage(taskIndex);
    }

    public void unmarkTask(String input) {
        int taskIndex = Integer.parseInt(input.substring(7)) - 1;
        taskList.get(taskIndex).markAsUndone();
        userInterface.unmarkTaskMessage(taskIndex);
    }

    public void deleteTask(int taskIndex) {
        UserInterface.taskDeletedMessage(this.taskList, taskIndex);
        this.taskList.remove(taskIndex);
    }

}
