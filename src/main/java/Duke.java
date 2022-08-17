import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Checklist tasks = new Checklist();
        Scanner scanner = new Scanner(System.in);
        InputParser parser = new InputParser();
        String input = "";
        while (scanner.hasNextLine()) {
            input = scanner.nextLine();
            try {
                parser.parse(input, tasks);
            } catch (Exception e) {
                System.out.println(e.toString());
                if (e.equals(new EndProgramException()))
                    break;

            }
        }
    }
}
