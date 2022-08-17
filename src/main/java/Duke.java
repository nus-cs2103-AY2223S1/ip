import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static final String line = "____________________________________________________________\n";
    public static void greeting() {
        String initMessage = "Hello! I'm Duke\n" + "What can I do for you?\n";
        System.out.println(line + initMessage + line);
    }

    public static void exit() {
        String goodbyeMessage = "Bye. Hope to see you again soon!\n";
        System.out.println(line + goodbyeMessage + line);
    }

    public static void add(String obj, int i) {
        System.out.println(i + ". " + obj);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int count = 0;
        ArrayList<String> list = new ArrayList<>();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        greeting();

        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                exit();
                break;
            } else if (input.equals("list")){
                System.out.println(line);
                for (int i = 0; i < count; i++) {
                    add(list.get(i), i + 1);
                }
                System.out.println(line);
            } else {
                list.add(count, input);
                count++;
                System.out.println(line + "added: " + input + "\n" + line);
            }
        }
    }

}
