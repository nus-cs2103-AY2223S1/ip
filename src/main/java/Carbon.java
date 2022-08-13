import java.util.Random;
import java.util.Scanner;

public class Carbon {
    private static Scanner sc = new Scanner(System.in);

    private Random rand;
    private boolean isRunning;

    private static void printOut(String text) {
        String divider = "\n···---······---······---······---······---······---······---···\n";
        System.out.println(divider);
        System.out.println("==> " + text);
    }

    private static String printIn() {
        String receiver = "\n··-··--···--\n";
        System.out.println(receiver);
        System.out.print("<-- ");
        String input = sc.next();
        return input;
    }

    private Carbon() {
        // init fields
        this.rand = new Random();

        // ascii art generated from patorjk.com

        String preface = "                 _ _ _ ____ _    ____ ____ _  _ ____ \n" + 
            "                 | | | |___ |    |    |  | |\\/| |___ \n" + 
            "                 |_|_| |___ |___ |___ |__| |  | |___ \n";

        String logo = "  ▄████████    ▄████████    ▄████████ ▀█████████▄   ▄██████▄  ███▄▄▄▄   \n" + 
            " ███    ███   ███    ███   ███    ███   ███    ███ ███    ███ ███▀▀▀██▄ \n" + 
            " ███    █▀    ███    ███   ███    ███   ███    ███ ███    ███ ███   ███ \n" + 
            " ███          ███    ███  ▄███▄▄▄▄██▀  ▄███▄▄▄██▀  ███    ███ ███   ███ \n" + 
            " ███        ▀███████████ ▀▀███▀▀▀▀▀   ▀▀███▀▀▀██▄  ███    ███ ███   ███ \n" +
            " ███    █▄    ███    ███ ▀███████████   ███    ██▄ ███    ███ ███   ███ \n" + 
            " ███    ███   ███    ███   ███    ███   ███    ███ ███    ███ ███   ███ \n" + 
            " ████████▀    ███    █▀    ███    ███ ▄█████████▀   ▀██████▀   ▀█   █▀  \n" + 
            "                           ███    ███                                   \n";

        // actual introduction
        String intro = "Hey, Carbon here. ";
        String[] initPrompts = {
            "What's up?",
            "How's things going?",
            "Nice weather today, huh?",
            "How can I help you?",
            "Please don't talk to me.",
            "To get an A+ for CS2103T, you have t---[REDACTED]"
        };
        String randomPrompt = initPrompts[this.rand.nextInt(initPrompts.length)];

        System.out.println(preface);
        System.out.println(logo);

        // extra space
        System.out.println("\n\n");
        Carbon.printOut(intro + randomPrompt);
    }

    private void runShell() {
        this.isRunning = true;
        while (this.isRunning) {
            String input = Carbon.printIn();
            this.process(input);
            if (!this.isRunning) {
                this.exit();
            }
        }
    }

    private void exit() {
        String[] goodbyes = {
            "Bye-bye, see you again soon!",
            "Farewell. Stay safe.",
            "Nice meeting you. Let's catch up again sometime.",
            "Bye. Good riddance.",
            "...zzzzzz...",
            "What? Yeah, sorry I gotta run now."
        };
        String randomGoodbye = goodbyes[this.rand.nextInt(goodbyes.length)];
        Carbon.printOut(randomGoodbye);
    }
    
    private void process(String input) {
        String lowerCaseInput = input.toLowerCase();
        switch (lowerCaseInput) {
            case "bye":
                this.isRunning = false;
                break;
            default:
                Carbon.printOut(input);
        }
    }

    public static void main(String[] args) {
        Carbon shell = new Carbon();
        shell.runShell();
    }
}
