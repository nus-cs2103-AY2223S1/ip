package duke;

import java.io.IOException;
import java.util.Scanner;

public class Duke {

    private static TaskList tasks;
    private static Storage storage;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);

        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            tasks = new TaskList();
            ui.showLoadingError();
        } catch (DukeException e) {
            ui.reply("File is Corrupted");
        }
    }

    public void run() {
        boolean isTerminated = false;

        ui.welcomeLogo();
        ui.reply("Hello! I'm Duke. What can I do for you?");

        Scanner userInput = new Scanner(System.in);

        while (!isTerminated) {
            Task newTask = null;

            String userText = userInput.nextLine();
            CommandType type = Parser.parse(userText);

            switch (type) {
            case BYE:
                ui.reply("Bye. Hope to see you again soon!");
                isTerminated = true;
                break;
            case LIST:
                ui.reply(tasks.getList());
                break;
            case MARK:
                ui.drawLine();
                try {
                    tasks.mark(Integer.valueOf(userText.substring(5)));
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
                ui.drawLine();
                break;
            case UNMARK:
                ui.drawLine();
                try {
                    tasks.unmark(Integer.valueOf(userText.substring(7)));
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
                ui.drawLine();
                break;
            case TODO:
                newTask = new ToDo();
                break;
            case EVENT:
                newTask = new Event();
                break;
            case DEADLINE:
                newTask = new DeadLine();
                break;
            case DELETE:
                ui.drawLine();
                try {
                    tasks.delete(Integer.valueOf(userText.substring(7)));
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
                ui.drawLine();
                break;
            case UNABLE:
                ui.reply("OOPS!!! I'm sorry, but I don't know what that means :-(");
                break;
            case FIND:
                ui.drawLine();
                try {
                    tasks.find(userText.substring(5));
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
                ui.drawLine();
                break;
            default:
            }

            if (newTask != null) {
                ui.drawLine();
                try {
                    newTask.addName(userText);
                    tasks.add(newTask);

                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
                ui.drawLine();
            }
            try {
                storage.write(tasks.writeTasks());
            } catch (IOException e) {
                ui.reply(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
