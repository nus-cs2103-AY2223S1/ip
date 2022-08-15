import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Carbon {
    // chat-bot specific displays
    // ascii art generated from patorjk.com
    // logo for Carbon
    private static String logo = "                 _ _ _ ____ _    ____ ____ _  _ ____ \n" + 
            "                 | | | |___ |    |    |  | |\\/| |___ \n" + 
            "                 |_|_| |___ |___ |___ |__| |  | |___ \n" + 
            "  ▄████████    ▄████████    ▄████████ ▀█████████▄   ▄██████▄  ███▄▄▄▄   \n" + 
            " ███    ███   ███    ███   ███    ███   ███    ███ ███    ███ ███▀▀▀██▄ \n" + 
            " ███    █▀    ███    ███   ███    ███   ███    ███ ███    ███ ███   ███ \n" + 
            " ███          ███    ███  ▄███▄▄▄▄██▀  ▄███▄▄▄██▀  ███    ███ ███   ███ \n" + 
            " ███        ▀███████████ ▀▀███▀▀▀▀▀   ▀▀███▀▀▀██▄  ███    ███ ███   ███ \n" +
            " ███    █▄    ███    ███ ▀███████████   ███    ██▄ ███    ███ ███   ███ \n" + 
            " ███    ███   ███    ███   ███    ███   ███    ███ ███    ███ ███   ███ \n" + 
            " ████████▀    ███    █▀    ███    ███ ▄█████████▀   ▀██████▀   ▀█   █▀  \n" + 
            "                           ███    ███                                   \n";

    // actual introduction
    private static String intro = "Hey, Carbon here. ";
    private static String[] initPrompts = {
            "What's up?",
            "How's things going?",
            "Nice weather today, huh?",
            "How can I help you?",
            "Please don't talk to me.",
            "To get an A+ for CS2103T, you have t---[REDACTED]"
        };

    // text for exits
    private static String[] goodbyes = {
            "Bye-bye, see you again soon!",
            "Farewell. Stay safe.",
            "Nice meeting you. Let's catch up again sometime.",
            "Bye. Good riddance.",
            "...zzzzzz...",
            "What? Yeah, sorry I gotta run now."
        };

    // scanner for inputs
    private static Scanner sc = new Scanner(System.in);

    // own fields
    private Random rand;
    private boolean isRunning;
    private List<String> items;

    // io display standardisation methods
    private static void printOut(String text) {
        String divider = "\n···---······---······---······---······---······---······---···\n";
        System.out.println(divider);
        System.out.println("==> " + text);
    }

    private static String printIn() {
        String receiver = "\n··-··--···--\n";
        System.out.println(receiver);
        System.out.print("<-- ");
        String input = Carbon.sc.next();
        return input;
    }

    // actual constructor and init method
    private Carbon() {
        // init fields
        this.rand = new Random();
        this.items = new ArrayList<String>();

        String randomPrompt = Carbon.initPrompts[
            this.rand.nextInt(Carbon.initPrompts.length)
        ];

        System.out.println(Carbon.logo);

        // extra space
        System.out.println("\n\n");
        Carbon.printOut(Carbon.intro + randomPrompt);
    }

    // main shell method
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

    private void process(String input) {
        String lowerCaseInput = input.toLowerCase();
        switch (lowerCaseInput) {
            case "list":
                this.listItems();
                break;
            case "bye":
                this.isRunning = false;
                break;
            default:
                this.addItem(input);
        }
    }

    private void addItem(String input) {
        this.items.add(input);
        String log = String.format("I have added: %s", input);
        Carbon.printOut(log);
    }

    private void listItems() {
        String log = "Here are the items so far. \n";
        int size = this.items.size();
        for (int i = 0; i < size; i++) {
            String itemLog = String.format("\n    %d: %s", i + 1, this.items.get(i));
            log += itemLog;
        }
        Carbon.printOut(log);
    }

    private void exit() {
        String randomGoodbye = Carbon.goodbyes[
            this.rand.nextInt(Carbon.goodbyes.length)
        ];
        Carbon.printOut(randomGoodbye);
    }
    
    public static void main(String[] args) {
        Carbon shell = new Carbon();
        shell.runShell();
    }
}
