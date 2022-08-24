package duke;

public class ListCommand extends Command {

    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println(tasks);
    }

    @Override
    boolean isExit() {
        return false;
    }

    /**
     * @param o Object we are comparing to.
     * @return boolean whether the object o is an instance of ListCommand.
     */
    @Override
    public boolean equals(Object o) {
        return o instanceof ListCommand;
    }
}
