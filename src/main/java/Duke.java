import java.util.*;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] dukeList = new String[100];
        int pointer = 0;
        System.out.println("Hello I'm Duke! What can I do for you?");
        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println("  ----\n  Goodbye!\n  ----");
                break;
            } else if (input.equals("list")) {
                String toDisplay = "  ----\n";
                for (int i = 0; i < pointer; i++) {
                    toDisplay += dukeList[i] + "\n";
                }
                toDisplay += "  ----";
                System.out.println(toDisplay);
            } else {
                System.out.println("  ----\n  added: " + input + "\n  ----");
                int temp = pointer + 1;
                dukeList[pointer] = "  " + temp + ": " + input;
                pointer++;
            }
        }
    }
}
