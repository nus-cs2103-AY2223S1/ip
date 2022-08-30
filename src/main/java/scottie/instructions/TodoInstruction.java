package scottie.instructions;

import java.util.Map;

import scottie.tasks.TaskList;
import scottie.tasks.Todo;
import scottie.ui.Ui;

/**
 * Encapsulates the todo instruction which is used to
 * add a todo to the task list.
 */
class TodoInstruction extends Instruction {
    /**
     * Constructs a TodoInstruction with the given arguments.
     *
     * @param mainArgument The main argument provided to this instruction.
     * @param flagArgumentsMap The map between flags and values provided to
     *                         this instruction.
     */
    TodoInstruction(String mainArgument, Map<String, String> flagArgumentsMap) {
        super(mainArgument, flagArgumentsMap);
    }

    /**
     * Adds a new Todo to the given TaskList.
     * The main argument of this instruction is used as the Todo's description.
     *
     * @param taskList The TaskList to add the Todo to.
     * @param ui The Ui used to display messages to the user.
     */
    @Override
    public void execute(TaskList taskList, Ui ui) {
        if (!this.hasMainArgument()) {
            ui.showMessages("Sorry, I will need a description for the to-do.");
            return;
        }
        Todo todo = new Todo(this.getMainArgument());
        taskList.addTask(todo);
        ui.showMessages("Got it, I've added this to-do:", todo.toString());
    }
}
