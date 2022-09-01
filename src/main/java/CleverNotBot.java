import java.util.Scanner;

public class CleverNotBot {
    public static void main(String[] args) throws CleverNotBotException {

        UITextBox textBox = new UITextBox();
        Storage storage = new Storage();
        TaskList tasks = new TaskList(storage.getTasksFromFile());
        Scanner sc = new Scanner(System.in);
        Handler hl = new Handler();

        hl.parseText("greet").run(tasks, textBox, storage);
        while (sc.hasNext()) {
            String ip = sc.nextLine();
            Function nxtStep = hl.parseText(ip);
            nxtStep.run(tasks, textBox, storage);
            if (nxtStep.isExitingProgram()) {
                break;
            }
        }

    }

}
