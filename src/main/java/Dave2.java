import Commands.Command;
import DataStruct.*;
import DaveExceptions.DaveException;
import Parser.Parser;
import Storage.SaveHandler;
import Ui.TextUi;

import java.io.IOException;
import java.util.Scanner;

public class Dave2 {

    private static TaskList tasks = new TaskList();

    private static final SaveHandler saveState = new SaveHandler();

    private static final Scanner scanner = new Scanner(System.in);

    private static boolean isRunning = true;

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
    public static void main(String[] args) {
        try {
            saveState.init();
            tasks = saveState.load();

            String logo = " ____                     _____\n"
                    + "|  _ \\ _____ _   _ __    /___  \\\n"
                    + "| | | |  _  | |/ / _ \\      /  /\n"
                    + "| |_| | |_| |   <  __/     /  /_\n"
                    + "|____/ \\__,_|__/ \\___|    /_____|\n";
            TextUi.print("Hello, I'm\n" + logo + "\nHow can I help ùwú?\n");

            while (isRunning) {
                Pair<String, String> inputData = Parser.splitInputIntoCommand(scanner.nextLine());
                Command command = Parser.dispatch(inputData.getHead(), inputData.getTail(), tasks);
                String result  = command.execute();
                isRunning = command.getIsRunning();
                TextUi.print(result);
            }

            saveState.save(tasks);

        } catch (DaveException e) {
            TextUi.print(e.toString());
        }
    }
}