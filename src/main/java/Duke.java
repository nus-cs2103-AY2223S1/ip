import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        greeting();
        echo();

    }

    public static void greeting() {
        System.out.println("--------------------------------------\n");
        System.out.println("\tHello I'm Duke, what can I do for you?");
        System.out.println("--------------------------------------\n");
    }

    public static void echo() {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        String uncap = str.toLowerCase();
        while (!uncap.equals("bye")) {
            System.out.println("------------------------------\n");
            System.out.printf("\t  %s  \n", str);
            System.out.println("------------------------------\n");
            str = sc.nextLine();
            uncap = str.toLowerCase();
        }
        bye();
        sc.close();
    }

    public static void bye() {
        System.out.println("------------------------------\n");
        System.out.println("Bye. Hope to see you soon again!");
        System.out.println("------------------------------\n");
    }
}
