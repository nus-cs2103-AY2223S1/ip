package clevernotbot;

import command.Command;
import exception.CleverNotBotException;
import task.TaskList;

import java.util.Scanner;

public class CleverNotBot {
    public static void main(String[] args) throws CleverNotBotException {

        UI textBox = new UI();
        Storage storage = new Storage();
        TaskList tasks = new TaskList(storage.getTasksFromFile());
        Scanner sc = new Scanner(System.in);
        Parser parser = new Parser();

        parser.parseText("greet").run(tasks, textBox, storage);
        while (sc.hasNext()) {
            String input = sc.nextLine();
            Command commandToRun = parser.parseText(input);
            commandToRun.run(tasks, textBox, storage);
            if (commandToRun.isExitingProgram()) {
                break;
            }
        }

    }

}
