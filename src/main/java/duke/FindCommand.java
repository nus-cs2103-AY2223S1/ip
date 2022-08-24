package duke;

/**
 *
 */
public class FindCommand extends Command {
    String wordToFind;

    FindCommand(String word) {
        this.wordToFind = word;
    }

    /**
     * @param tasks
     * @param ui
     * @param storage
     */
    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println(tasks.find(this.wordToFind));
    }

    /**
     * @return
     */
    @Override
    boolean isExit() {
        return false;
    }
}
