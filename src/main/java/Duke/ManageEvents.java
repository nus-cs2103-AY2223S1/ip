package Duke;

import java.util.ArrayList;

public class ManageEvents {

    public TaskList taskList;
    private Graphics graphics;

    public ManageEvents(TaskList taskList, Graphics graphics) {
        this.taskList = taskList;
        this.graphics = graphics;
    }

    void addTodo(String input) throws BlankDescriptionException {
        if (input.length() == 4) {
            throw new BlankDescriptionException();
        }
        String description = input.substring(5);
        ToDo temp = new ToDo(description);
        this.taskList.add(temp);
        graphics.addMessage(temp);
    }

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

    public void markTask(String input) {
        int taskIndex = Integer.parseInt(input.substring(5)) - 1;
        taskList.get(taskIndex).markAsDone();
        graphics.markMessage(taskIndex);
    }

    public void unmarkTask(String input) {
        int taskIndex = Integer.parseInt(input.substring(7)) - 1;
        taskList.get(taskIndex).markAsUndone();
        graphics.unmarkMessage(taskIndex);
    }

    public void deleteTask(int taskIndex) {
        Graphics.deleteMessage(this.taskList, taskIndex);
        this.taskList.remove(taskIndex);
    }

}
