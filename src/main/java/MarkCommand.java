public class MarkCommand extends Command {
    private final String details;

    MarkCommand(String command, String details) {
        super(command);
        this.details = details;
    }

    @Override
    public void execute(TaskRecords taskList, BotUI ui) throws DukeException {
        try {
            int taskIdx = Integer.parseInt(details) - 1;
            Task currTask = taskList.get(taskIdx);
            currTask = (super.getCommand().equals("mark")) ? currTask.markDone() : currTask.unmarkDone();
            taskList.addProcess(currTask);
            System.out.print(ui.informMarkStatus(taskList.get(taskIdx)));
        } catch (NumberFormatException ex) {
            throw new DukeException(ui.invalidCheckFormat()) ;
        } catch (IndexOutOfBoundsException ex) {
            throw new DukeException(ui.taskNotExist(taskList));
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
