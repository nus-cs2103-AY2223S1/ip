import java.util.Scanner;

/**
 * A chatbot named Tako that
 * supports various tasks.
 *
 * @author Alvin Tan Fu Long
 */
public class Tako {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Tako\nWhat do you want?");
        final String EXIT_COMMAND = "bye";
        final String LIST_COMMAND = "list";
        String[] texts = new String[100];
        int textsCount = 0;

        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String input = sc.nextLine();
            if (input.equals(EXIT_COMMAND)) {
                System.out.println("Bye, until next time...");
                break;
            } else if (input.equals(LIST_COMMAND)) {
                for (int i = 0; i < textsCount; i++) {
                    System.out.println((i + 1) + ". " + texts[i]);
                }
            } else {
                texts[textsCount] = input;
                textsCount++;
                System.out.println("added: " + input);
            }
        }
        sc.close();
    }
}