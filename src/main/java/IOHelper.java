import java.util.Scanner;

class IOHelper {
    public static void printWithoutPrompt(String message) {
        System.out.println(message);
    }

    public static void print(String message) {
        System.out.println(">> " + message);
    }

    public static String read(Scanner scanner) {
        System.out.print("<< ");
        return scanner.nextLine();
    }
}
