import java.util.ArrayList;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello! I'm\n" + logo);
        System.out.println("What can I do for you?");

        // Scanner to get input
        Scanner scan = new Scanner(System.in);
        ArrayList<String> log = new ArrayList<>();

        System.out.println("--------------------------------------");
        String s;

        while(true) {
            s = scan.nextLine();
            System.out.println("--------------------------------------");
            if (s.equals("bye")) {
                scan.close();
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (s.equals("list")){
                int count = 1;
                for (String item : log) {
                    System.out.println(count + ". " + item);
                    count++;
                }
            } else {
                log.add(s);
                System.out.println("added: " + s);
            }
            System.out.println("--------------------------------------");
        }

    }
}
