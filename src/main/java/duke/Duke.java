package duke;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private ArrayList<Task> list;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
            list = tasks.list();
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
            list = tasks.list();
        } catch (FileNotFoundException e) {
            ui.showFileNotFoundError();
        }
    }

    public void run() {
        ui.sayHello();

        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        String userInput = "0";

        while (true) {

            userInput = myObj.nextLine();  // Read user input
            String command = Parser.parseUserInput(userInput);

            switch (command) {
                case "bye":
                    ui.showByeMessage();
                    storage.writeItems(list);
                    System.exit(0);
                case "list":
                    ui.showList(this.list);
                    break;
                case "mark":
                    String markMessage = this.tasks.markDone(Parser.getTaskName(userInput));
                    ui.showMessage(markMessage);
                    break;
                case "unmark":
                    String unmarkMessage = this.tasks.unmarkDone(Parser.getTaskName(userInput));
                    ui.showMessage(unmarkMessage);
                    break;
                case "delete":
                    try {
                        this.tasks.deleteTask(Parser.getTaskName(userInput));
                    } catch (DukeException e) {
                        ui.showMessage(e.getMessage());
                    }
                case "schedule":
                    String date = Parser.getDate(userInput);
                    ui.showSchedule(date, list);
                    break;
                case "find":
                    String keyword = Parser.getTaskName(userInput);
                    ArrayList<Task> foundTasks = this.tasks.find(keyword);
                    //ui.showMessage("Here are the matching tasks in your list:\n");
                    //ui.showList(foundTasks);
                    ui.showFoundTasks(foundTasks, keyword);
                case "todo":
                case "deadline":
                case "event":
                    try {
                        this.tasks.addTask(userInput);
                    } catch (DukeException e) {
                        ui.showMessage(e.getMessage());
                    }
                    break;
                default:
                    ui.showDoNotKnowMessage();
                    break;

            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}