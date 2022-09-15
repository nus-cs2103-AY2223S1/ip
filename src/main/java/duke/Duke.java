package duke;

import duke.logic.task.Task;
import duke.storage.DukeDecoder;

import java.util.ArrayList;

/**
 * Class for Duke.
 */
public class Duke {
    private DukeDecoder dukeDecoder;
    /**
     * Constructor for Duke
     */
    public Duke() {
        dukeDecoder = new DukeDecoder();
    }

    public ArrayList<Task> initializeList() {
        return dukeDecoder.loadDataFromList();
    }
}
