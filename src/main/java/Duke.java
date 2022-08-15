import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String HORIZONTAL_LINE = "    ____________________________________________________________\n";
    private final Scanner sc;
    private ArrayList<String> list;

    public Duke() {
        this.sc = new Scanner(System.in);
        this.list = new ArrayList<>();
        String logo = "      ____        _        \n"
                + "     |  _ \\ _   _| | _____ \n"
                + "     | | | | | | | |/ / _ \\\n"
                + "     | |_| | |_| |   <  __/\n"
                + "     |____/ \\__,_|_|\\_\\___|\n";
        reply(logo + "\n     Hello! I'm Duke"
                + "\n     What can I do for you?");
    }

    public void reply(String msg) {
        String response = HORIZONTAL_LINE
                + msg + "\n"
                + HORIZONTAL_LINE;
        System.out.println(response);
    }

    public void add(String description) {
        this.list.add(description);
        reply("     added: " + description);
    }

    public void commandHandler() {
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            String[] args = input.split(" ");
            if (args[0].compareTo("bye") == 0) {
                reply("     Bye. Hope to see you again soon!");
                sc.close();
                break;
            } else if (args[0].compareTo("list") == 0) {
                String msg = "";
                for (int i = 0; i < this.list.size(); i++) {
                    msg += String.format("     %d. %s\n", i, this.list.get(i));
                }
                reply(msg);
            } else {
                add(input);
            }
        }
    }

    public static void main(String[] args) {
        Duke dk = new Duke();
        dk.commandHandler();
    }
}
