import java.util.Scanner;

public class Skylark {

    private static final String line = "________________________________________________";

    private static void printText(String text) {
        System.out.println(Skylark.line);
        System.out.println(text);
        System.out.println(Skylark.line);
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String byeCommand = "bye";
        String command;

        Skylark.printText("Hello, I am Skylark, how can I help you today?");

        while (true) {
            command = scan.nextLine();
            if (command.equals(byeCommand)) {
                Skylark.printText("Bye. Hope to see you again soon!");
                break;
            } else {
                System.out.println(command);
            }
        }
    }
}
