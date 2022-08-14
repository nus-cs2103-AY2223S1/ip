import java.util.Scanner;
public class Duke {
    private String[] storedInputs;
    private int storedInputCount = 0;

    private final String horizontalLine = "------------------------------------------------------------\n";
    Duke() {
        storedInputs = new String[100];
    }

    private void printStoredInputs() {
        if (storedInputCount > 0) {
            System.out.println(horizontalLine);
            for (int i = 0; i < storedInputCount; i++) {
                System.out.println(i + 1 + ". " + storedInputs[i]);
            }
            System.out.println("\n" + horizontalLine);
        }

    }

    private void addInput(String input) {
        storedInputs[storedInputCount++] = input;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Duke uncleCheong = new Duke();
        System.out.printf("%sEh hello, my name is Uncle Cheong. \n" +
                "What you want?\n%s\n", uncleCheong.horizontalLine, uncleCheong.horizontalLine);
        String input = "";
        while (!input.equals("bye")) {
            input = sc.next();
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                uncleCheong.printStoredInputs();
            } else {
                System.out.printf("%sadded: %s\n%s", uncleCheong.horizontalLine, input, uncleCheong.horizontalLine);
                uncleCheong.addInput(input);
            }
        }
        System.out.printf("%sEh you leaving me so soon?\n%s", uncleCheong.horizontalLine, uncleCheong.horizontalLine);
    }
}
