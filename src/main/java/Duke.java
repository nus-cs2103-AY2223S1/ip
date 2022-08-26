import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;
import java.util.stream.Stream;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Checklist tasks = loadChecklist();
        Scanner scanner = new Scanner(System.in);
        InputParser parser = new InputParser();
        String input = "";
        while (scanner.hasNextLine()) {
            input = scanner.nextLine();
            try {
                parser.parse(input, tasks);
            } catch (Exception e) {
                System.out.println(e);
                if (e.equals(new EndProgramException()))
                    break;

            }
        }
    }

    static Checklist loadChecklist() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("./tasks.txt"));
            Stream<String> lines = reader.lines();

            Checklist checklist = new Checklist(lines);
            reader.close();
            return checklist;
        } catch (Exception e) {
            System.out.println(e);
            return new Checklist();
        }

    }
}
