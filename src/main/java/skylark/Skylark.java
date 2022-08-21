package skylark;

import java.io.File;
import java.util.Scanner;

public class Skylark {

    private static final String TEXT_HELLO = "Hello, I am Skylark, how can I help you today?";

    private final Scanner scan;
    private final TaskList taskList;

    public Skylark() {
        this.scan = new Scanner(System.in);
        String filePath = System.getProperty("user.dir")
                + File.separator + "data" + File.separator + "text.txt";
        this.taskList = new TaskList(filePath);

        Printer.printText(Skylark.TEXT_HELLO);
    }

    public void startRunning() {
        while (true) {
            try {
                boolean isEnd = response(scan, taskList);
                if (isEnd) {
                    break;
                }
            } catch (SkylarkException exception) {
                Printer.printText(exception.toString());
            }
        }
    }

    private boolean response(Scanner scan, TaskList taskList) throws SkylarkException {
        String input = scan.nextLine();
        Command command = Command.createCommand(input);
        command.run(taskList);

        return command.getInput().equals(CommandList.COMMAND_BYE.toString());
    }

    public static void main(String[] args) {
        Skylark skylark = new Skylark();
        skylark.startRunning();
    }
}
