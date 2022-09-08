package duke.util.command;

import duke.DukeException;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todos;
import duke.util.SaveTasks;

public class CommandTodo extends Command{

    public CommandTodo(String command) {
        super(command);
    }

    @Override
    public String handleCommand(TaskList taskList, SaveTasks saveTasks) throws DukeException{
        StringBuilder description = new StringBuilder();
        String[] words = command.split("\\s");
        if (words.length <= 1) {
            throw new DukeException("The description of a todo cannot be empty");
        }
        for (int i = 1; i < words.length; i++) {
            description.append(words[i]);
            if (i != words.length - 1) {
                description.append(" ");
            }
        }
        Task todo = new Todos(description.toString());
        taskList.add(todo);
        return "Got it. I've added this task:\n" + todo + "\nNow you have " +
                String.valueOf(taskList.size()) + " tasks in the list.";
    }
}
