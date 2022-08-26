package duke.command;

import duke.util.ParsedData;

public abstract class DataCommand implements Command {

    protected ParsedData data;
    
    DataCommand(ParsedData  data) {
        this.data = data;
    }
}
