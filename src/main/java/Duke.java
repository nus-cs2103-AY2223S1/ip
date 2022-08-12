import java.util.*;

public class Duke {
    private List<String> storage = new ArrayList<String>();

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
        Duke curr = new Duke();
        curr.handleInput();


    }

    private void handleInput() {
        Scanner in = new Scanner(System.in);

        while (in.hasNext()) {
            String next = in.nextLine();
            if (next.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (next.equals("list")) {
                for (int i = 0; i < this.storage.size(); i++) {
                    System.out.printf("%d. %s\n", i+1, storage.get(i));
                }
            } else {
                storage.add(next);
                System.out.printf("added %s\n", next);
            }
        }
    }
}
