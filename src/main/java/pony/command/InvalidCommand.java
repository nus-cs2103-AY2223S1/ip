package pony.command;

import pony.Storage;
import pony.task.TaskList;
import pony.Ui;

public class InvalidCommand extends Command {

    public void execute(TaskList tasks, Storage storage, Ui ui) {
        System.out.println(":( OOPS!!! I'm sorry, but I don't know what that means...");
    }
}
