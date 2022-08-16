import java.util.Objects;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        lvlOne();
    }

    public static void lvlOne() {
        System.out.println("Hello! I'm Duke\n");
        Scanner sc = new Scanner(System.in);
        System.out.println("Tell me a word!");
        String str = sc.nextLine();
        System.out.println(str);

        while (!str.equals("bye") && !str.equals("goodbye")) {
            sc = new Scanner(System.in);
            str = sc.nextLine();
            System.out.println(str);
        }
        System.out.println("Bye! See you soon!");
    }
}
