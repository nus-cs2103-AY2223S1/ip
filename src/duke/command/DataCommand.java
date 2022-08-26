package duke.command;

import duke.util.ParsedData;

public abstract class DataCommand implements Command {

    protected ParsedData data;

    @Override
    public boolean isExit() {
        return false;
    }
    
    DataCommand(ParsedData  data) {
        this.data = data;
    }
}
