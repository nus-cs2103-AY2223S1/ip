package command;

import java.util.List;

public class CommandResponse {

    public List<String> responseList;
    public boolean triggerSave;

    public CommandResponse(List<String> responseList, boolean triggerSave) {
        this.responseList = responseList;
        this.triggerSave = triggerSave;
    }
}
