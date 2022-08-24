package duke.command.response;

public class CommandResponse {

    public String responseStr;
    public boolean triggerSave;
    public boolean triggerTerminate;

    public CommandResponse(String responseStr, boolean triggerSave, boolean triggerTerminate) {
        this.responseStr = responseStr;
        this.triggerSave = triggerSave;
        this.triggerTerminate = triggerTerminate;
    }
}
