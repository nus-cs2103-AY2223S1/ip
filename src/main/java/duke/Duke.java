package duke;
import java.util.ArrayList;

import duke.logic.task.Task;
import duke.storage.DukeDecoder;


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

    /**
     * Initialize internal storage for bot
     * @return ArrayList of Task
     */
    public ArrayList<Task> initializeList() {
        return dukeDecoder.loadDataFromList();
    }
}
