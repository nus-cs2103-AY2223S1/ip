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
        toDo();
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

    public static void toDo() {
        String[] tasks = new String[100];
        String[] check = new String[100];
        for (int i = 0; i < check.length; i++) {
            check[i] = " ";
        }
        int taskNum = 0;

        System.out.println("Hello! I'm Duke\n");
        System.out.println("What tasks do you have to do?");
        Scanner sc = new Scanner(System.in);
        String str2 = sc.nextLine();

        //String check = " ";
        boolean isDone = false;

        while (!str2.equals("bye")) {
            if (str2.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < taskNum; i++) {
                    int num = i + 1;
                    System.out.println(num + ". " + "[" + check[i] + "] " + tasks[i]);
                }
                str2 = sc.nextLine();
                continue;
            }
            if ((str2.substring(0, 4)).equals("mark")) {
                int taskToMark = 0;
                System.out.println("Nice! I've marked this task as done:\n");

                String strTaskToMark = "";
                for (int j = 5; j < str2.length(); j++) {
                    strTaskToMark = strTaskToMark + str2.charAt(j);
                }

                taskToMark = Integer.parseInt(strTaskToMark);
                check[taskToMark - 1] = "X";
                System.out.println("[X] " + tasks[taskToMark - 1]);
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
