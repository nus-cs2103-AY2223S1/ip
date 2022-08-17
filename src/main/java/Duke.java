import java.util.Scanner;

public class Duke {

    public static void add_list() {
        Scanner myScan = new Scanner(System.in);
        String s;
        String[] list = new String[100];
        int list_counter = 0;

        while (true) {
            s = myScan.nextLine();
            if (s.equals("bye")) {
                System.out.println("----------------------");
                System.out.println("Bye, hope to see you again!");
                System.out.println("----------------------");
                break;
            } else if (s.equals("list")) {
                for (int i = 0; i < 100; i++) {
                    if (list[i] != null) {
                        String display = String.format("%d. %s", i + 1, list[i]);
                        System.out.println(display);
                    }
                }

            } else {
                list[list_counter] = s;
                list_counter += 1;
                System.out.println("----------------------");
                System.out.println("added: " + s);
                System.out.println("----------------------");
            }
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("----------------------");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can i do for you?");
        System.out.println("----------------------");

        add_list();
    }
}
