package duke;

/**
 * Parser class to parse raw string into commands.
 */
public class Parser {
    static final String LIST_WORD = "list";
    static final String END_WORD = "bye";
    static final String HELP_WORD = "help";
    static final String GREET_WORD = "greet";

    private static String endProgram(Storage storage, TaskList taskList) {
        String unableToWriteMsg = "Unable to write the record";
        int exitCode = storage.writeResult(taskList);
        if (exitCode == -1) {
            return Ui.processExceptionOutput(unableToWriteMsg) + Ui.bye();
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
    protected static String parseCommand(String args, TaskList taskList, Storage storage, Duke duke) {
        String command = args.replace(System.lineSeparator(), "").replace("/r", "");
        switch (command) {
        case LIST_WORD:
            return Ui.listPrint(taskList);
        case END_WORD:
            duke.setTerminated();
            return endProgram(storage, taskList);
        case GREET_WORD:
            return Ui.greet();
        case HELP_WORD:
            return Ui.help();
        default:
            try {
                return taskList.parseInstructions(command);
            } catch (DukeException de) {
                return Ui.processExceptionOutput(de.getMsg());
            }
        }
    }
}
