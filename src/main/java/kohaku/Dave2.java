package kohaku;

import kohaku.commands.Command;
import kohaku.datastruct.TaskList;
import kohaku.datastruct.Pair;
import kohaku.daveexceptions.DaveException;
import kohaku.parser.Parser;
import kohaku.storage.SaveHandler;
import kohaku.ui.TextUi;

import javafx.application.Application;
import java.util.Scanner;

public class Dave2 {

    private static TaskList tasks = new TaskList();

    private static final SaveHandler SAVE_STATE = new SaveHandler();

    private static final Scanner SCANNER = new Scanner(System.in);

    /**
     * Gets the Dave 2's current task list.
     * @return Dave 2's current task list.
     */
    public static TaskList getTasks() {
        return tasks;
    }


    @Deprecated
    /**
     * Original point of entry to Dave 2 when using CLI.
     * Deprecated after adding GUI.
     */
    public static void runDaveCLI() throws DaveException {
        String logo = " ____                     _____\n"
                + "|  _ \\ _____ _   _ __    /___  \\\n"
                + "| | | |  _  | |/ / _ \\      /  /\n"
                + "| |_| | |_| |   <  __/     /  /_\n"
                + "|____/ \\__,_|__/ \\___|    /_____|\n";
        TextUi.print("Hello, I'm\n" + logo + "\nHow can I help ùwú?\n");

        while (true) {
            Pair<String, String> inputData = Parser.splitInputIntoCommand(SCANNER.nextLine());
            Command command = Parser.dispatch(inputData.getHead(), inputData.getTail(), tasks);
            String result  = command.execute();
            TextUi.print(result);
        }

    }

    public static void main(String[] args) {
        try {
            SAVE_STATE.init();
            tasks = SAVE_STATE.load();

            Application.launch(Main.class, args);

        } catch (DaveException e) {
            TextUi.print(e.toString());
        }
    }
}