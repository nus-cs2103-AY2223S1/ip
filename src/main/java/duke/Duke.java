package duke;

import java.io.IOException;

import duke.command.Command;
import duke.common.AnomaliesManager;
import duke.parser.Parser;
import duke.storage.FileManager;
import duke.storage.TaskList;
import duke.ui.BotUI;

/**
 * Represents the class of the duke bot program.
 * A <code>Duke</code> object consists of BotUI and TaskList.
 */
public class Duke {

    private final BotUI ui;
    private final TaskList taskList;
    private final AnomaliesManager anomaliesManger;

    /**
     * Constructs Duke object.
     * The taskList is assigned to the previous duke chatBot data if the file is found.
     * TaskList will be initialised to an empty taskList if file is not found.
     */
    public Duke() {
        this.ui = new BotUI();
        this.anomaliesManger = new AnomaliesManager();
        this.taskList = FileManager.read();
    }

    //Runs the duke chatBot program.
    private void runBot() {
        System.out.println(ui.sayHello());
        boolean isExitDuke = false;
        while (!isExitDuke) {
            try {
                System.out.print(BotUI.userSpeak());
                Command c = Parser.parse(ui.readCommand(), anomaliesManger);
                String output = c.execute(taskList, ui, anomaliesManger);
                System.out.println(output);
                FileManager.write(this.taskList);
                isExitDuke = c.isExit();
            } catch (DukeException de) {
                System.out.println(de.getMessage());
            } catch (IOException ex) {
                System.out.println("Error while Saving File!");
                System.out.println(ex.getMessage());
            }
        }
    }

    /**
     * Represents the main method of the program.
     * For text-based UI execution.
     */
    public static void main(String[] args) {
        new Duke().runBot();
    }

    /**
     * Returns the response of the bot according to user input in GUI
     *
     * @param input String of user raw input in GUI.
     * @return String of the resulting bot response from the GUI input
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input, anomaliesManger);
            String response = c.execute(this.taskList, ui, anomaliesManger);
            FileManager.write(this.taskList);
            return response;
        } catch (DukeException | IOException de) {
            return de.getMessage();
        }
    }

}
