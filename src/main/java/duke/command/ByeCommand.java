package duke.command;

import java.util.Timer;
import java.util.TimerTask;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * ExitCommand closes the Duke chatbot.
 */
public class ByeCommand extends Command {

    /**
     * Constructor for ExitCommand.
     */
    public ByeCommand() {
        super();
        this.setExit(true);
    }

    /**
     * @inheritDoc
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                System.exit(0);
            }
        };
        timer.schedule(timerTask, 2000);
        return "Bye. Hope to see you again soon!";
    }
}
