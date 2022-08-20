import java.util.ArrayList;
import java.util.Scanner;

public class Skylark {

    private static final String TEXT_HELLO = "Hello, I am Skylark, how can I help you today?";

    private final Scanner scan;
    private final ArrayList<Task> taskList;

    public Skylark() {
        this.scan = new Scanner(System.in);
        this.taskList = new ArrayList<>();

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

    private boolean response(Scanner scan, ArrayList<Task> taskList) throws SkylarkException {
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
