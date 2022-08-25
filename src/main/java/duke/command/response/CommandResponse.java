package duke.command.response;

public class CommandResponse {

    private String responseStr;
    private boolean triggerSave;
    private boolean triggerTerminate;

    public CommandResponse(String responseStr, boolean triggerSave, boolean triggerTerminate) {
        this.responseStr = responseStr;
        this.triggerSave = triggerSave;
        this.triggerTerminate = triggerTerminate;
    }

    public String getResponseStr() {
        return responseStr;
    }

    public boolean isTriggerSave() {
        return triggerSave;
    }

    public boolean isTriggerTerminate() {
        return triggerTerminate;
    }
}
