import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Scanner in = new Scanner(System.in);
        System.out.println("Oi, What u want?");
        while (in.hasNext()) {
            String next = in.nextLine();
            if (next.equals("bye")) {
                System.out.println("Bye!");
                return;
            }
            else {
                System.out.println(next);
            }
        }


    }
}
