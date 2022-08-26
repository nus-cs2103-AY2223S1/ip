package duke;

public class Parser {
    static final String LIST_WORD = "list";
    static final String END_WORD = "bye";

    private static void endProgram(Storage storage, TaskList taskList) {
        Ui.bye();
        int exitCode = storage.writeResult(taskList);
        if (exitCode == -1) {
            Ui.processExceptionOutput("Unable to write the record");
        }
    }

    static int parseCommand(String args, TaskList taskList, Storage storage) {
        String command = args.replace("\n", "").replace("/r", "");
        switch (command) {
        case LIST_WORD:
            Ui.listPrint(taskList);
            break;
        case END_WORD:
            endProgram(storage, taskList);
            return 0;
        default:
            try {
                taskList.parseInstructions(command);
            } catch (DukeException de) {
                Ui.processExceptionOutput(de.getMsg());
            }
        }
        return 1;
    }


}
