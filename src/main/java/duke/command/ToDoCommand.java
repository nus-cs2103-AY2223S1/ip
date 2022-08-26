package duke.command;

import duke.logic.TaskList;
import duke.exception.IllegalDescriptionException;
import duke.task.ToDo;

public class ToDoCommand extends Command {
    private TaskList taskList;
    private String description;

    public ToDoCommand(TaskList taskList, String description) throws IllegalDescriptionException {
        this.taskList = taskList;
        //double check
        if (description.length() > 0) {
            this.description = description;
        } else {
            throw new IllegalDescriptionException("No description specified.");
        }
    }

    @Override
    public void run() {
        taskList.add(new ToDo(description));
        System.out.println("Got it. I've added this task:");
        System.out.println(taskList.get(taskList.size() - 1).toString());
        System.out.println(String.format("Now you have %d tasks in the list.", taskList.size()));
    }
}
