import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    List<String> inputList;

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Duke duke = new Duke();
        duke.start();
    }

    public Duke() {
        inputList = new ArrayList<>();
    }

    public void start() {
        Scanner sc = new Scanner(System.in);
        System.out.println(
                "Hello! I'm Duke\n" +
                "What can I do for you?"
        );
        loop(sc);
    }

    public void loop(Scanner sc) {
        String s = sc.nextLine();

        if ("bye".equals(s)) {
            System.out.println("Bye. Hope to see you again soon!");
            return;
        } else if ("list".equals(s)) {
            System.out.print(inputListToString());
        } else {
            inputList.add(s);
            System.out.println("Added: " + s);
        }

        loop(sc);
    }

    private String inputListToString() {
        StringBuilder sb = new StringBuilder();
        int i = 1;
        for (String s : inputList) {
            sb.append(i);
            sb.append(". ");
            sb.append(s);
            sb.append("\n");
            ++i;
        }
        return sb.toString();
    }

}