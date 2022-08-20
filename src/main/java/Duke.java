import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Scanner sc = new Scanner(System.in);
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");

        boolean hasnext = true;

        while (hasnext) {
            String message = sc.nextLine();
            List list = new List(message);
            switch (message) {
                case "bye" :
                    hasnext = false;
                    System.out.println("Bye. Hope to see you again soon!\n");
                    break;
                case "list" :
                    System.out.println(list.listOut());
                    break;
                default :
                    System.out.println(String.format("added: %s\n", message));
            }
        }
    }
}
