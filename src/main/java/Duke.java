import java.util.Scanner;

public class Duke {

    private static String divider() {
        return "--------------------";
    }

    private static void setup() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
        System.out.println(divider());
    }

    private static void printData(String[] data) {
        int index = 1;
        for (String s : data) {
            if (s == null) {
                return;
            }
            System.out.println(index + ". " + s);
            index++;
        }
        return;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] data = new String[100];
        int pointer = 0;

        setup();

        while (true) {
            String line = scanner.nextLine();
            System.out.println(divider());

            switch (line) {

                case "bye":
                    System.out.println("See you again!");
                    return;

                case "list":
                    printData(data);
                    break;

                default:
                    data[pointer] = line;
                    pointer++;
                    System.out.println("Added: " + line);
                    break;
            }
        }

    }
}
