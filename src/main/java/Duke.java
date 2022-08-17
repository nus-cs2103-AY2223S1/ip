import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        intro();
        repeatCommand();
        outro();
    }

    private static void intro() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    private static void repeatCommand() {
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();
        while (!command.equals("bye")) {
            System.out.printf("\t%s\n", command);
            command = sc.nextLine();
        }
    }

    private static void outro(){
        System.out.println("Bye. Hope to see you again");
    }
}
