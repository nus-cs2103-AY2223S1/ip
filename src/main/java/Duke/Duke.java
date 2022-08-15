import java.util.Scanner;
public class Duke {
    Scanner sc;

    public Duke() {
        this.sc = new Scanner(System.in);
    }

    public void run() {
        System.out.println("Hello! I'm Duke \n what can I do for you?");
        boolean isExit = false;
        while ((!isExit)) {
            String echo = sc.nextLine();
            if (echo.equals("bye")) {
                System.out.println("Bye, see you again!");
                isExit = true;
            } else {
                System.out.println(echo);
            }
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
