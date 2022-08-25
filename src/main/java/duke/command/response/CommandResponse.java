package duke.command.response;

public class CommandResponse {

    public String responseStr;
    public boolean triggerSave;
    public boolean triggerTerminate;

    /**
     * Constructor for CommandResponse class
     *
     * @param responseStr response string
     * @param triggerSave boolean to trigger a save to cache
     * @param triggerTerminate boolean to trigger a termination
     */
    public CommandResponse(String responseStr, boolean triggerSave, boolean triggerTerminate) {
        this.responseStr = responseStr;
        this.triggerSave = triggerSave;
        this.triggerTerminate = triggerTerminate;
    }
}
