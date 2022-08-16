import java.util.Scanner;

public class Duke {
    private static String[] strList = new String[100];
    private static int counter = 0;
    final static String logo =
            "   __ __    ____       ___               __    \n" +
            "  / // /__ / / /__    / _ \\___ ____  ___/ /__ _\n" +
            " / _  / -_) / / _ \\  / ___/ _ `/ _ \\/ _  / _ `/\n" +
            "/_//_/\\__/_/_/\\___/ /_/   \\_,_/_//_/\\_,_/\\_,_/ \n" +
            "                                               \n";

    private static void printCommand(String str) {
        System.out.println("added: " + str);
        System.out.println("#########################");
    }

    private static void storeCommand(String str) {
        if (counter >= 100) return;
        strList[counter] = str;
        counter++;
    }

    private static void displayList() {
        for (int i = 0; i < 100; i++) {
            if (strList[i] == null) break;
            System.out.println(i + 1 + ". " + strList[i]);
        }
    }
    public static void main(String[] args) {
        System.out.println("Hello from\n" + logo);
        Scanner sc = new Scanner(System.in);
        System.out.println("How can I help you today? :)");
        String command = sc.nextLine();
        while (!command.equals("bye")) {
            if (command.equals("list")) {
                displayList();
                command = sc.nextLine();
                continue;
            }
            printCommand(command);
            storeCommand(command);
            command = sc.nextLine();
        }
        sc.close();
        System.out.println("Bye. Hope to see you again soon!");
    }
}
