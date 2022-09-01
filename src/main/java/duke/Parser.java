package duke;

/**
 * Parser class to parse raw string into commands.
 */
public class Parser {
    static final String LIST_WORD = "list";
    static final String END_WORD = "bye";

    private static String endProgram(Storage storage, TaskList taskList) {

        int exitCode = storage.writeResult(taskList);
        if (exitCode == -1) {
            return Ui.processExceptionOutput("Unable to write the record") + Ui.bye();
        }
        return Ui.bye();
    }

    /**
     * Parses raw string command to different actions.
     *
     * @param args raw string of the input
     * @param taskList current TaskList storing
     * @param storage the storage unit
     * @return String to be displayed to user
     */
    protected static String parseCommand(String args, TaskList taskList, Storage storage) {
        String command = args.replace("\n", "").replace("/r", "");
        switch (command) {
        case LIST_WORD:
            return Ui.listPrint(taskList);
        case END_WORD:
            return endProgram(storage, taskList);
        default:
            try {
                return taskList.parseInstructions(command);
            } catch (DukeException de) {
                return Ui.processExceptionOutput(de.getMsg());
            }
        }
    }
}
