package kohaku.commands;

import kohaku.datastruct.TaskList;
import kohaku.daveexceptions.DaveException;
import kohaku.storage.SaveHandler;

import java.util.Timer;
import java.util.TimerTask;

public class EndCommand extends Command{

    private TaskList tasks;
    private SaveHandler SAVE_STATE = new SaveHandler();

    /**
     * Ends the current session of Dave 2.
     *
     * @param tasks Tasklist to be saved before ending the session.
     * @param args dummy parameter to ensure EndCommand fits the specifications of Command
     */
    public EndCommand(TaskList tasks, String args) {
        this.tasks = tasks;
    }

    /**
     * Executes the command.
     * @return The string representation of the result of the command.
     */
    @Override
    public String execute() throws DaveException {
    
        SAVE_STATE.save(tasks);
        
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                System.exit(0);
            }
        }, 1000L);

        return "See you soon Akiha-sama!";
    }
}
