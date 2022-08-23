public class ListCommand extends Command {

    public static final String COMMAND_NAME = "list";

    ListCommand() {

    }

    @Override
    public void exec(TaskList tasks, Ui ui, Storage storage) {
        StringBuilder reply = new StringBuilder();
        String[] stringifiedTaskList = tasks.toStringList();
        reply.append("Here are your tasks that I have recorded:");
        if (stringifiedTaskList.length == 0) {
            reply.append("\nCongratulations, you don't need to do anything right now!");
        }
        for (int i = 0; i < stringifiedTaskList.length; i++) {
            reply.append(String.format("\n%02d. %s", i + 1, stringifiedTaskList[i]));
        }
        ui.showReply(reply.toString());
    }

    @Override
    public boolean isTerminator() {
        return false;
    }
}
