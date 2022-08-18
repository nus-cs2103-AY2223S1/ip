import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ChatBot christina = new ChatBot("Christina");
        christina.initialize();
        while (christina.isRunning()) {
            christina.processCommand(scanner.nextLine());
        }
        scanner.close();
        christina.terminate();
    }
}
