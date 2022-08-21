import java.util.Scanner;

public class UserInput {
    Scanner scanner = new Scanner(System.in);

    private String cmd;
    private String rest;
    private int ind = -1;
    private String desc = "";
    private String date = "";

    private String readCommand() {
        String input = scanner.nextLine();
        cmd = Parser.getCmd(input);
        rest = Parser.getRest(input);
        updator();

        return cmd;
    }

    private void updator() {
        switch(cmd) {
        case "mark":
        case "unmark":
        case "delete":
            ind = Integer.parseInt(rest);
        case "todo":
            desc = rest;
        case "event":
        case "deadline":
            desc = Parser.getDesc(rest);
            date = Parser.getDate(rest);
        }
    }
    public int getInd() {
        return ind;
    }
    public String getDesc() {
        return desc;
    }
    public String getDate() {
        return date;
    }
    public String getCmd() {
        return cmd;
    }
}
