package commands;

import common.ChatResponse;
import dukeexceptions.DukeException;
import dukeexceptions.MissingDescriptionException;
import tasklist.TaskList;
import tasks.ToDo;

/**
 * ToDo command to be executed.
 */
public class ToDoCommand extends Command {
    private final String[] args;

    public ToDoCommand(String[] args) {
        this.args = args;
    }

    /**
     * Validates the passed arguments before executing the command.
     *
     * @param args Arguments to validate.
     * @throws DukeException Exception to be thrown if validation fails.
     */
    public static void validateArguments(String[] args) throws DukeException {
        assert args.length > 0 : "No arguments entered into validateArguments";

        if (args.length == 0) {
            throw new MissingDescriptionException("ToDo");
        }
    }

    /**
     * Executes ToDo Command.
     *
     * @param taskList The taskList relevant to the command.
     * @return String with messages from execution.
     */
    @Override
    public String execute(TaskList taskList) {
        String todoItem = String.join(" ", this.args);
        assert !todoItem.equals("") : "Parsing error occured in Todo: todo item";

        ToDo newToDo = new ToDo(String.join(" ", this.args));
        taskList.addTask(newToDo);
        return ChatResponse.returnChatAddTask(newToDo, taskList);
    }
}
