package command;

public class CommandResponse {

    public String responseStr;
    public boolean triggerSave;

    public CommandResponse(String responseStr, boolean triggerSave) {
        this.responseStr = responseStr;
        this.triggerSave = triggerSave;
    }
}
