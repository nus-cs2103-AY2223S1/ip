package ekud.main;
import java.util.Scanner;

import ekud.exception.EkudException;
import ekud.task.TaskList;
import ekud.util.ParseResult;
import ekud.util.Parser;
import ekud.util.Storage;
import ekud.util.Ui;

public class Ekud {

    private final Storage storage;
    private final TaskList taskList;
    private final Ui ui;
    private final Parser parser;
    

    /**
     * Constructor that instantiates new Ekud instance.
     */
    public Ekud() {
        this.storage = new Storage();
        this.ui = new Ui();
        this.parser = new Parser();
        TaskList taskList = null;
        try {
            taskList = new TaskList(this.storage.getTasksFromFile());
        } catch (EkudException exception) {
            ui.showErrorMessage(exception);
            taskList = new TaskList();
        }
        this.taskList = taskList;
    }

    /**
     * Starts the chat bot.
     */
    public void start() {
        ui.greet();
        Scanner sc = new Scanner(System.in);
        boolean active = true;

        while (active) {
            try {
                String command = sc.nextLine();
                ParseResult result = this.parser.parseCommand(command, this.taskList);
                if (result.terminate) {
                    active = false;
                } 
                if (result.saveStorage) {
                    storage.writeTasksToFile(this.taskList.getTaskList());
                }
                this.ui.sendMessage(result.message);
            } catch (EkudException exception) {
                this.ui.showErrorMessage(exception);
            }
        }
        sc.close();
    }

    public static void main(String[] args) {
        Ekud ekud = new Ekud();
        ekud.start();

    }
}
