import java.util.*;

public class Duke {

    private static String[] addedText = new String[100];
    private static int addedTextIndex = 0;

    private static void addText(String text) {
        Duke.addedText[Duke.addedTextIndex] = text;
        Duke.addedTextIndex++;
    }

    private static void listText() {
        for (int i = 0; i < Duke.addedText.length; ++i) {
            if (Duke.addedText[i] == null) {
                return;
            }
            System.out.println(String.valueOf(i + 1) + ". " + Duke.addedText[i]);
        }
    }

    private static void greet() {
        System.out.println("Hi, my name is Duke\nand it's power hour!");
        System.out.println("***********************");
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String command = scanner.nextLine();
            if (command.equals("bye")) {
                System.out.println("Come again soon!");
                System.exit(0);
            } else if (command.equals("list")) {
                Duke.listText();
                continue;
            } else if (command.equals("")) {
                continue;
            }
            Duke.addText(command);
            System.out.println("added: " + command);
        }
    }

    public static void main(String[] args) {
        Duke.greet();
    }
}
