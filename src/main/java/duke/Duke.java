package duke;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;
import ui.UI;
import task.*;
import storage.Storage;
import parser.Parser;

/**
 * Duke is a Personal ChatBot to help keep track of your Tasks.
 *
 * @author joelwong
 */

public class Duke {

    private UI ui;
    private Storage storage;
    private TaskList tasks;

    private File file = Path.of("data/duke.txt").toFile();

    private ArrayList<Task> listOfActions = new ArrayList<Task>(100);

    /**
     * Creates a new Duke.
     *
     * @param filePath the file path of stored tasks in the text file.
     */
    public Duke(String filePath) {
        ui = new UI();
        storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.load());
            //Ok got shit in the task
            this.tasks.printContent();
            ui.showGotTask();
        } catch (DukeException e) {
            ui.showNoTask();
            //does nothing but instantiate a object
            this.tasks = new TaskList();
        }

    }

    /**
     * Runs Duke.
     */

    public void run() {
        String type;
        int currentAction = this.storage.getSize();
        int end = 0;
        ui.welcomeMessage();
        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine();
        while (end != 1) {
            //If user wants to check the list
            String output = list(tasks.getTaskList(), currentAction);
            String[] input = userInput.split(" ");
            try {
                Parser parse = new Parser(tasks, userInput);
                if (parse.isErreneous()) {
                    type = parse.getType();
                    ui.showInaccurateInput();
                } else  {
                    //change from action to getAction()
                    if (parse.getIsAction()) {
                        currentAction++;
                    }
                    type = parse.getType();
                }
                if (type.equals("bye")) {
                    ui.goodByeMessage();
                    break;
                } else if (type.equals("list")) {
                    ui.showList(tasks.getList());
                } else if (type.equals("delete")) {
                    currentAction--;
                } else if (type.equals("find")) {
                    //do nothing
                } else {
                    //do nothing
                }
            } catch (DukeException e) {
                ui.showInaccurateInput();
            }

            userInput = sc.nextLine();
        }
    }


    public static void main(String[] args) {

        new Duke("data/duke.txt").run();

    }

    public static String list(ArrayList<Task> listOfActions, int currentAction) {
        String o = "";
        for (int i = 0; i < currentAction; i++) {
            o = o + String.format("%d", i + 1) + "." + listOfActions.get(i) + "\n";
        }
        return o;
    }





}


