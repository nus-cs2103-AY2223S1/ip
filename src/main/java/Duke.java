import java.util.Scanner;

public class Duke {
    private static String[] all = new String[100];
    private static int count = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I am Duke");
        System.out.println("What can I do for you?");

        String s = "";
        while (true) {
            s = sc.nextLine();
            if (s.equals("bye")) {
                Bye();
                break;
            } else if (s.equals("list")) {
                DisplayList();
            } else {
                Store(s);
            }
        }
    }

    private static void Bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static  void DisplayList() {
        for (int i = 0; i < count; i++) {
            System.out.printf("%s. %s\n", i + 1, all[i]);
        }
    }

    private static void Store(String s) {
        all[count] = s;
        count += 1;
        System.out.println(s);
    }
}
