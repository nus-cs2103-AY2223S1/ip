package duke.common;

import duke.command.Command;
import duke.command.UnknownCommand;

/**
 * Represents an AnomaliesManager class.
 * Responsible in storing anomaly status and command that detected anomaly.
 * The attributes are mutable.
 */

public class AnomaliesManager {
    private final Command[] previousCommand;
    private final boolean[] isRaised;

    /**
     * Constructs AnomaliesManager object
     */
    public AnomaliesManager() {
        this.previousCommand = new Command[]{new UnknownCommand("No Question is raised by Anomalies yet!")};
        this.isRaised = new boolean[1];
    }

    /**
     * Modifies the isRaised status and store new command that detected anomalies.
     */
    public void raiseAnomalies(Command anomaliesCommand) {
        this.isRaised[0] = true;
        this.previousCommand[0] = anomaliesCommand;
    }

    /**
     * Modifies the isRaised status to false.
     * Indicates that no anomaly in user input command.
     */
    public void resolveAnomalies() {
        this.isRaised[0] = false;
    }

    public boolean isRaised() {
        return this.isRaised[0];
    }

    public Command getPrevCommand() {
        return this.previousCommand[0];
    }
}
