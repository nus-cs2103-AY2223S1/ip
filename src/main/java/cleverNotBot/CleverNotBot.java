package cleverNotBot;

import java.util.Scanner;

public class CleverNotBot {
    public static void main(String[] args) throws CleverNotBotException {

        UI textBox = new UI();
        Storage storage = new Storage();
        TaskList tasks = new TaskList(storage.getTasksFromFile());
        Scanner sc = new Scanner(System.in);
        Parser hl = new Parser();

        hl.parseText("greet").run(tasks, textBox, storage);
        while (sc.hasNext()) {
            String ip = sc.nextLine();
            Command nxtStep = hl.parseText(ip);
            nxtStep.run(tasks, textBox, storage);
            if (nxtStep.isExitingProgram()) {
                break;
            }
        }

    }

}
