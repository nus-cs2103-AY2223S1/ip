import duke.AllTasksList;
import duke.Command;
import duke.Storage;


/**
 * The main driver class for duke
 *
 * @author Cui Shen Yi
 * @version CS2103T AY22/23 Semester 1
 */
public class Duke {
    protected String getResponse(String input) {
        AllTasksList allTasks = Storage.loadTasks();
        String userOutput = Command.chat(input, allTasks);
        return userOutput;
    }
}
