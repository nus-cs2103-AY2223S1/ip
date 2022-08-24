import java.util.Scanner;

public class Parser {

    private static int cnt = 0;
    private static Scanner sc = new Scanner(System.in);

    public static Command parse(String input) throws DukeException {
        String[] cmd = input.split(" ", 2);
        int num;

        switch (cmd[0]) {
            case "bye":
                return new ExitCommand(cmd[0]);
            case "list":
                return new ListCommand(cmd[0]);
            case "mark":
                num = Integer.parseInt(cmd[1]);
                return new MarkCommand(cmd[0], num - 1);
            case "unmark":
                num = Integer.parseInt(cmd[1]);
                return new UnmarkCommand(cmd[0], num - 1);
            case "delete":
                if (cmd[1].length() < 1) {
                    throw new DukeException("The index of a task cannot be empty.");
                }

                num = Integer.parseInt(cmd[1]);
                return new DeleteCommand(cmd[0], num - 1);
            default:
                cnt++;
                return new AddCommand(input);
        }
    }
}
