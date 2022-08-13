import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String LOGO = "\t ____        _        \n"
            + "\t|  _ \\ _   _| | _____ \n"
            + "\t| | | | | | | |/ / _ \\\n"
            + "\t| |_| | |_| |   <  __/\n"
            + "\t|____/ \\__,_|_|\\_\\___|\n";
    private static final String LINE = "\t____________________________________________________________";

    private static void run() {
        ArrayList<String> storage = new ArrayList<>();
        boolean isExited = false;
        String command = "";
        Scanner sc = new Scanner(System.in);

        System.out.println(LOGO
                + LINE
                + "\n"
                + "\tHello! I'm Duke\n"
                + "\tWhat can I do for you?\n"
                + LINE);

        while (!isExited) {
            command = sc.nextLine();
            switch (command) {
                case "bye":
                    isExited = true;
                    System.out.println(LINE
                            + "\n\tBye. Hope to see you again soon!\n"
                            + LINE);
                    break;
                case "list":
                    System.out.println(LINE);
                    for (int i = 0; i < storage.size(); i++) {
                        System.out.printf("\t%d%s\n", i, ". " + storage.get(i));
                    }
                    System.out.println(LINE);
                    break;
                default:
                    storage.add(command);
                    System.out.println(LINE
                            + "\n\t"
                            + "added: "
                            + command
                            + "\n"
                            + LINE);
            }
        }
    }

    public static void main(String[] args) {
        Duke.run();
    }
}
