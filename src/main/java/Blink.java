import java.util.Scanner;

public class Blink {

    public static final String SPACING = "---------------------------";

    public void welcome() {
        System.out.println("Hello! Blink here\n" +"What can I do for you today?");
    }

    public void goodbye() {
        System.out.println("Bye bye~ Glad to be of service :D");
    }

    public void start(Scanner sc) {
        System.out.println(Blink.SPACING);
        this.welcome();
        System.out.println(Blink.SPACING);

        while(sc.hasNext()) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                this.goodbye();
                System.out.println(Blink.SPACING);
                break;
            }
            System.out.println(input);
            System.out.println(Blink.SPACING);
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Blink blink = new Blink();
        blink.start(scanner);
    }
}

