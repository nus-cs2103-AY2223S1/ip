import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        start();

        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();
        while (true) {
            if (command.equals("bye")) {
                bye();
                break;
            }
            else {
                System.out.println("    ____________________________________________________________\n" +
                        "     " +
                        command +
                        "\n    ____________________________________________________________");
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