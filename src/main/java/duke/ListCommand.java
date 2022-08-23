package duke;

import java.util.List;

public class ListCommand extends Command {

    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println(tasks);
    }

    @Override
    boolean isExit() {
        return false;
    }

    @Override
    public boolean equals(Object o){
        return o instanceof ListCommand;
    }
}
