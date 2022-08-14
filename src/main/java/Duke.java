import java.util.Scanner;

public class Duke {
    private static Scanner scanner = new Scanner(System.in);

    private static void speak(String message) {
        System.out.println("============================================================\n");
        System.out.println(message);
        System.out.println("\n============================================================");
    }

    private static void greet() {
        speak(" Greetings! My Name is Alfred ^_^\n How may I be of service today?");
    }

    public static void main(String[] args) {
        greet();

        String cmd = "";
        while (!cmd.equals("bye")) {
            cmd = scanner.nextLine().trim();
            if (!cmd.equals("bye")) {
                speak(" " + cmd);
            } else {
                speak(" Farewell!");
            }
        }
    }
}
