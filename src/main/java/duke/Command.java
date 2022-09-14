package duke;

/**
 * Represents a given command by a user in Duke. A command is consists of 2 parts,
 * keyword and content, both of which are String type.
 */
public class Command {
    private final String keyword;
    private final String content;

    public Command(String keyword, String content) {
        this.keyword = keyword.toLowerCase();
        this.content = content;
    }

    /**
     * Executes a given Duke Command based on its type.
     * @param tasks Current TaskList of tasks
     * @param ui Ui of Duke
     * @param storage Handles interaction with the local directory where Duke is located
     * @return String result of executed Duke Command
     * @throws DukeException if invalid Duke Command executed
     */
    protected String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (Parser.isTaskKeyword(keyword)) {
            Parser.validateTaskCommand(keyword, content);
            String confirmationMessage = TaskCommandProcessor.processTaskCommand(keyword, content, tasks, ui);
            storage.saveExistingTasks(tasks);
            return confirmationMessage;
        } else if (Parser.isModifyKeyword(keyword)) {
            String confirmationMessage = ModifyCommandProcessor.processModifyCommand(keyword, content, tasks, ui);
            storage.saveExistingTasks(tasks);
            return confirmationMessage;
        } else if (Parser.isAccessKeyword(keyword)) {
            return AccessCommandProcessor.processAccessCommand(keyword, content, tasks);
        } else if (Parser.isByeKeyword(keyword)){
            return ui.getFarewellMessage();
        } else {
            return ui.getHelpMessage();
        }
    }
}
