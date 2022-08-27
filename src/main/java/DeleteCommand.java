public class DeleteCommand extends Command {
    protected String commandLine;

    public DeleteCommand(String commandLine) {
        this.commandLine = commandLine;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        int index = Integer.parseInt(commandLine.substring(7)) - 1;
        try {
            if (index > taskList.size() - 1) {
                throw new DukeException("You have no such tasks.");
            } else {
                Task task = taskList.get(index);
                taskList.remove(index);
                storage.updateData(taskList);
                System.out.println("Noted. I've removed this task:\n" +
                        " " + task.toString() + "\n" + "Now you have " +
                        taskList.size() + " tasks in the list.\n");
            }
        } catch (DukeException e) {
            System.out.println(e.toString());
        }
    }
}
