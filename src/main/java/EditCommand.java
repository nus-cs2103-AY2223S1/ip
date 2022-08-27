public class EditCommand extends Command {
    protected String commandLine;

    public EditCommand(String commandLine) {
        this.commandLine = commandLine;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        if (commandLine.startsWith("mark")) {
            int index = Integer.parseInt(commandLine.substring(5)) - 1;
            try {
                if (index > taskList.size() - 1) {
                    throw new DukeException("You have no such tasks.");
                } else {
                    Task task = taskList.get(index);
                    task.isMark(true);
                    storage.updateData(taskList);
                    System.out.println("Nice! I've marked this task as done:\n" +
                            " " + task + "\n");
                }
            } catch (DukeException e) {
                System.out.println(e.toString());
            }
        } else {
            int index = Integer.parseInt(commandLine.substring(7)) - 1;
            try {
                if (index > taskList.size() - 1) {
                    throw new DukeException("You have no such tasks.");
                } else {
                    Task task = taskList.get(index);
                    task.isMark(false);
                    storage.updateData(taskList);
                    System.out.println("OK, I've marked this task as not done yet:\n" +
                            " " + task + "\n");
                }
            } catch (DukeException e) {
                System.out.println(e.toString());
            }
        }
    }
}
