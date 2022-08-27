package duke;

import commands.ByeCommand;
import commands.Command;
import commands.DeadlineCommand;
import commands.DeleteCommand;
import commands.EventCommand;
import commands.ListCommand;
import commands.MarkCommand;
import commands.TodoCommand;
import commands.UnMarkCommand;
import dukeexceptions.DukeException;
import dukeexceptions.NoDescriptionException;
import dukeexceptions.NoSuchCommandException;
import tasks.TaskList;


public class Duke {
    TaskList taskList;
    String filePath;
    private Storage s;
    private Ui ui;

    public Duke(String filePath) {
        this.ui = new Ui();
        this.filePath = filePath;
        this.s = new Storage(this.filePath);
        this.taskList = s.open();
    }

    public void run() {
        Ui.printEntryStatement();
        boolean toExitProgram = false;
        Ui.printInitStatement();
        while (!toExitProgram) {
            String userIn = ui.readCommand();  // Read user input
            try {
                Command comm = Parser.parse(userIn);
                if (comm instanceof ByeCommand) {
                    toExitProgram = true;
                    this.s.end(this.taskList);
                }
                comm.run(this.taskList);
            } catch (DukeException e) {
                System.err.print(e);
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/task.txt").run();
    }

}






