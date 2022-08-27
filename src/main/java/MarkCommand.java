public class MarkCommand extends Command {
    int index;
    String taskType;
    String userCommand;

    public MarkCommand(String userCommand) {
        this.userCommand = userCommand;
        String[] taskData = userCommand.split(" ", 2);
        this.taskType = taskData[0];
        this.index = Integer.parseInt(taskData[1].trim()) - 1;
    }

    public void execute (TaskList tasks, Ui ui, Storage storage) throws UwuException {
        if (index >= tasks.size()) {
            throw new NullTaskException("\n\thm...it seems that task " + String.valueOf(index) + " does not exist ><" +
                    "\n\tplease check that you have keyed in the right task number~ <:");
        }

        switch (taskType) {
            case "mark":
                Task markedTask = tasks.get(index);
                markedTask.setIsDone(true);
                ui.markTask(markedTask);
                storage.save(tasks.taskListToStorageString());
                break;
            case "unmark":
                Task unmarkedTask = tasks.get(index);
                unmarkedTask.setIsDone(false);
                ui.unmarkTask(unmarkedTask);
                storage.save(tasks.taskListToStorageString());
                break;
        }
    };

    public boolean isExit() {
        return false;
    };
}
