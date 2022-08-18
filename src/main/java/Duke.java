import java.util.Scanner;
import java.io.*;
import java.util.*;

public class Duke {




    public static void main(String[] args) {

        ArrayList<String> todoList = new ArrayList<String>();

        start();
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();

        while (true) {
            if (command.equals("bye")) {
                bye();
                break;
            }
            else if (command.equals("list")) {
                System.out.println("    ____________________________________________________________");
                for (int i = 1; i <= todoList.size(); i++) {
                    System.out.println("    " + i + ". " + todoList.get(i-1));
                }
                System.out.println("    ____________________________________________________________");
                command = scanner.nextLine();
            }
            else {
                System.out.println("    ____________________________________________________________\n" +
                        "     added: " +
                        command +
                        "\n    ____________________________________________________________");
                todoList.add(command);
                command = scanner.nextLine();
            }
        }



    }

    public static void start() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        //System.out.println("Hello from\n" + logo);
        System.out.println("    ____________________________________________________________\n" +
                "     Hola! I'm Ashy (//●⁰౪⁰●)//\n" +
                "     What can I do for you? my darling~\n" +
                "    ____________________________________________________________");
    }

    public static void bye() {
        System.out.println("    ____________________________________________________________\n" +
                "     Byeeee~ Hope to see you again soon (•͈⌔•͈⑅)\n" +
                "    ____________________________________________________________");
    }

}