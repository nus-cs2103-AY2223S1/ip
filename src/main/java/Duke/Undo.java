package duke;

import java.util.ArrayList;

/**
 * Undo object class.
 */
public class Undo {

    private ArrayList<String> commandStack;
    private ArrayList<Integer> indexStack;
    private ArrayList<Task> taskStack;

    /**
     * Creates a new Undo object.
     */
    public Undo() {
        this.commandStack = new ArrayList<>();
        this.indexStack = new ArrayList<>();
        this.taskStack = new ArrayList<>();
    }

    /**
     * Adds the last command by user to undo stack.
     *
     * @param command command entered by user.
     */
    public void addLastCommand(String command) {
        this.commandStack.add(command);
    }

    /**
     * Adds the last command by user to undo stack.
     *
     * @param index of task.
     */
    public void addLastIndex(Integer index) {
        this.indexStack.add(index);
    }

    /**
     * Adds the last command by user to undo stack.
     *
     * @param task that was deleted.
     */
    public void addLastTask(Task task) {
        this.taskStack.add(task);
    }

    /**
     * Removes from stack the last user command entered and returns it.
     *
     * @return last valid user command.
     */
    public String popLastCommand() {
        String lastCommand = this.commandStack.get(this.commandStack.size() - 1);
        this.commandStack.remove(this.commandStack.size() - 1);
        return lastCommand;
    }

    /**
     * Removes from stack the last task index entered and returns it.
     *
     * @return last task index.
     */
    public Integer popLastIndex() {
        Integer lastIndex = this.indexStack.get(this.indexStack.size() - 1);
        this.indexStack.remove(this.indexStack.size() - 1);
        return lastIndex;
    }

    /**
     * Removes from stack the last deleted task and returns it.
     *
     * @return last deleted task.
     */
    public Task popLastTask() {
        Task lastTask = this.taskStack.get(this.taskStack.size() - 1);
        this.taskStack.remove(this.taskStack.size() - 1);
        return lastTask;
    }

    /**
     * Checks if command stack is empty.
     *
     * @return true if empty and false if not.
     */
    public boolean isCommandStackEmpty() {
        if (this.commandStack.size() == 0) {
            return true;
        } else {
            return false;
        }
    }
}
