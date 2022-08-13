import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Blink {

    private static final String SPACING = "---------------------------";
    private String[] store = new String[100];
    private int count = 0;

    public void welcome() {
        System.out.println("Hello! Blink here\n" + "What can I do for you today?");
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
            if (input.isBlank()) {
                continue;
            }
            if (input.equals("bye")) {
                this.goodbye();
                System.out.println(Blink.SPACING);
                break;
            } else if (input.equals("list")) {
                for(int x = 0; x < this.count; x++) {
                    System.out.println(x+1 + ". " + this.store[x]);
                }
                System.out.println(Blink.SPACING);
            } else {
                this.store[count] = input;
                this.count++;
                System.out.println("added: " + input);
                System.out.println(Blink.SPACING);
            }
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Blink blink = new Blink();
        blink.start(scanner);
    }
}

