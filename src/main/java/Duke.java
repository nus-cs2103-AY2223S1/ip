import java.util.Scanner;

public class Duke {
    private static final TaskList taskList = new TaskList();

    public static void main(String[] args) {
        System.out.println(InputParser.initialMessage);
        Scanner scanner = new Scanner(System.in);
        InputParser inputParser = new InputParser(taskList);
        while (scanner.hasNextLine()) {
            System.out.println(inputParser.parseScannerLineInput(scanner, inputParser.breakLoopIndicator));
            if (inputParser.breakLoopIndicator.getIsScannerDone()) break;
        }
    }
}
