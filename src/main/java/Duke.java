import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String divider = "____________________________________________________________\n";
        String introMsg = "Baby Yoda I am\nFor you, what can I do?\n";
        String byeMsg = "See you soon, I will\n";

        System.out.println("Hello from\n" + logo);
        System.out.println(divider + introMsg + divider);

        Scanner in = new Scanner(System.in);
        String s = "";

        while (true) {
            s = in.nextLine();
            if (s.equals("bye"))
                break;
            System.out.println(divider + "\t" + s + "\n" + divider);
        }

        System.out.println(divider + "\t" + byeMsg + divider);
    }
}
