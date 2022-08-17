import java.util.Scanner;

public class Duke {
    private Scanner sc;
    private static String NAME = "DoiMoiBot: ";

    public Duke() {
        sc = new Scanner(System.in);
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        String input;

        duke.greet();
        input = duke.getInput();
        duke.parrot(input);

        duke.farewell();
    }

    private void greet() {
        System.out.println(NAME + "Hello! I'm doimoibot\n" + "What can I do you for?");
    }

    private void farewell() {
        System.out.println(NAME + "Goodbye! See you soon!");
    }

    private void parrot(String input) {
        while (!input.equals("bye")) {
            System.out.println(NAME + input);
            input = this.getInput();
        }
    }

    private String getInput() {
        return this.sc.nextLine();
    }
}
