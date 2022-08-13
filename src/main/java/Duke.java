import java.util.Scanner;
public class Duke {
    private boolean hasExited = false;
    public static void main(String[] args) {
        Duke zeus = new Duke();
        zeus.chat();
    }

    public void chat() {
        greetUser();
        Scanner sc = new Scanner(System.in);
        while(!hasExited) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                hasExited = true;
            } else {
                echo(input);
            }
        }
        exitMessage();
    }

    public void greetUser() {
        generateLine();
        System.out.println("\t Hello! I'm Zeus");
        System.out.println("\t What can I do for you?");
        generateLine();
    }

    public void exitMessage() {
        generateLine();
        System.out.println("\t Bye. Hope to see you again soon!");
        generateLine();
    }
    public void echo(String input) {
        generateLine();
        System.out.println("\t " + input);
        generateLine();

    }

    public void generateLine() {
        System.out.println("\t____________________________________________________________");

    }
}
