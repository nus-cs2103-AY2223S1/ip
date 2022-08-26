package duke;

/**
 * Parser class to parse raw string into commands.
 */
public class Parser {
    static final String LIST_WORD = "list";
    static final String END_WORD = "bye";

    private static void endProgram(Storage storage,  TaskList taskList) {
        Ui.bye();
        int exitCode = storage.writeResult(taskList);
        if (exitCode == -1) {
            Ui.processExceptionOutput("Unable to write the record");
        }
    }

    /**
     * Parses raw string command to different actions.
     *
     * @param args raw string of the input
     * @param taskList current TaskList storing
     * @param storage the storage unit
     * @return
     */
    protected static int parseCommand(String args, TaskList taskList, Storage storage) {
        String command = args.replace("\n", "").replace("/r", "");
        switch (command) {
        case LIST_WORD:
            Ui.ListPrint(taskList);
            break;
        case END_WORD:
            endProgram(storage, taskList);
            return 0;
        default:
            try {
                taskList.parseInstructions(command);
            } catch (DukeException de) {
                Ui.processExceptionOutput(de.msg);
            }
        }
        return 1;
    }


}
