package duke;

public class TaskHandler {
    private TaskList taskList;
    private Ui ui;

    public TaskHandler(TaskList taskList, Ui ui) {
        this.taskList = taskList;
        this.ui = ui;
    }

    void addTask(String input) {
        taskList.add(input);
    }

    void deleteTask(String input) {
        taskList.delete(input);
    }

    public void markChild(String input) {
        int index = Integer.parseInt(input.substring(5)) - 1;
        taskList.markChild(index);
    }

    public void unmarkChild(String input) {
        int index = Integer.parseInt(input.substring(7)) - 1;
        taskList.unmarkChild(index);
    }

    public void findTask(String input) {
        String formattedInput = input.substring(5);
        taskList.find(formattedInput);
    }
}
