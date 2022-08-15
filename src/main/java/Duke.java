import java.util.Scanner;

public class Duke {
    public static final String LINES = "_________________________\n";

    public static void printMessage(String message) {
        System.out.print(LINES + message + "\n" + LINES);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        printMessage("Hey there! I'm Speedy.\nWhat do you want to do today?\n");
        while(sc.hasNext()) {
            String command = sc.nextLine();
            if(command.equals("bye")) {
                printMessage("Bye! Hope you had fun!");
                break;
            }
            printMessage(command);
        }
    }
}
