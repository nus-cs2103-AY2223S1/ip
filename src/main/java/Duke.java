import java.lang.reflect.Array;
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
        //lvlOne();
        lvlTwo();
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

    /**public static void lvlTwo() {
        String[] tasks = new String[100];
        int taskNum = 0;

        System.out.println("Hello! I'm Duke\n");
        System.out.println("What tasks do you have to do?");
        Scanner sc = new Scanner(System.in);
        String str2 = sc.nextLine();

        while (!str2.equals("bye")) {
            if (str2.equals("list")) {
                for (int i = 0; i < taskNum; i++) {
                    int num = i + 1;
                    System.out.println(num + ". " + tasks[i]);
                }
                str2 = sc.nextLine();
                continue;
            }

            tasks[taskNum] = str2;
            taskNum++;
            System.out.println("added: " + str2);
            str2 = sc.nextLine();
        }
        System.out.println("Bye! See you soon!");
    }*/

    public static void lvlTwo() {
        String[] tasks = new String[100];
        int taskNum = 0;

        System.out.println("Hello! I'm Duke\n");
        System.out.println("What tasks do you have to do?");
        Scanner sc = new Scanner(System.in);
        String str2 = sc.nextLine();

        while (!str2.equals("bye")) {
            if (str2.equals("list")) {
                for (int i = 0; i < taskNum; i++) {
                    int num = i + 1;
                    System.out.println(num + ". " + tasks[i]);
                }
                str2 = sc.nextLine();
                continue;
            }

            tasks[taskNum] = str2;
            taskNum++;
            System.out.println("added: " + str2);
            str2 = sc.nextLine();
        }
        System.out.println("Bye! See you soon!");
    }
}
