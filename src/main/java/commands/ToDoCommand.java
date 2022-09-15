package commands;

import common.ChatResponse;
import dukeexceptions.DukeException;
import dukeexceptions.MissingDescriptionException;
import tasklist.TaskList;
import tasks.ToDo;

public class ToDoCommand extends Command {
    private final String[] args;

    public ToDoCommand(String[] args) {
        this.args = args;
    }

    public static void validateArguments(String[] args) throws DukeException {
        assert args.length > 0 : "No arguments entered into validateArguments";

        if (args.length == 0) {
            throw new MissingDescriptionException("ToDo");
        }
    }

    @Override
    public String execute(TaskList taskList) {
        String todoItem = String.join(" ", this.args);
        assert !todoItem.equals("") : "Parsing error occured in Todo: todo item";

        ToDo newToDo = new ToDo(String.join(" ", this.args));
        taskList.addTask(newToDo);
        return ChatResponse.returnChatAddTask(newToDo, taskList);
    }
}
