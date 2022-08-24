package duke;

public class Parser {
    static final String LIST_WORD = "list";
    static final String END_WORD = "bye";
    static final String ERROR_MESSAGE = "OOPS!!! I'm sorry, but I don't know what that means :-(";

    private static void endProgram(Storage storage,  TaskList taskList) {
        Ui.bye();
        int exitCode = storage.writeResult(taskList);
        if (exitCode == -1) {
            Ui.output(ERROR_MESSAGE);
        }
    }

    static int parseCommand(String args, TaskList taskList, Storage storage) {
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
                // Todo: move output logic into Ui and modify it accordingly
                if (de.msg.equals("Unable to parse query")) {
                    Ui.FormatPrint(ERROR_MESSAGE);
                } else if (de.msg.equals("Unable to process query")) {
                    Ui.FormatPrint(ERROR_MESSAGE);
                } else {
                    Ui.FormatPrint(ERROR_MESSAGE);
                }
            }
        }
        return 1;
    }


}
