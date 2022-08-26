package carbon;

import carbon.error.CarbonException;

import java.util.Random;
import java.util.Scanner;

public class Ui {
    // chat-bot specific displays
    // ascii art generated from patorjk.com
    // logo for Carbon
    private static final String LOGO = "                 _ _ _ ____ _    ____ ____ _  _ ____ \n" + 
        "                 | | | |___ |    |    |  | |\\/| |___ \n" + 
        "                 |_|_| |___ |___ |___ |__| |  | |___ \n\n" + 
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
    private static final String INTRO = "Hey, Carbon here. ";
    private static final String[] INIT_PROMPTS = {
        "What's up?",
        "How's things going?",
        "Nice weather today, huh?",
        "How can I help you?",
        "Please don't talk to me.",
        "To get an A+ for CS2103T, you have t---[REDACTED]"
    };

    // text for exits
    private static final String[] GOODBYES = {
        "Bye-bye, see you again soon!",
        "Farewell. Stay safe.",
        "Nice meeting you. Let's catch up again sometime.",
        "Bye. Good riddance.",
        "...zzzzzz...",
        "What? Yeah, sorry I gotta run now."
    };

    private Random rand;
    // scanner for inputs
    private Scanner sysScanner;


    public Ui() {
        this.rand = new Random();
        this.sysScanner = new Scanner(System.in);
        
        // String randomPrompt = Carbon.initPrompts[
        //     this.rand.nextInt(Carbon.initPrompts.length)
        // ];

        String randomPrompt = Ui.INIT_PROMPTS[0];

        System.out.println(Ui.LOGO);

        // extra space
        System.out.println("\n\n");
        this.printOut(Ui.INTRO + randomPrompt);
    }

    // io display standardisation methods
    public void printOut(String text) {
        String divider = "\n···---······---······---······---······---······---······---···\n";
        System.out.println(divider);
        System.out.println("==> " + text);
    }

    public String printIn() {
        String receiver = "\n··-··--···--\n";
        System.out.println(receiver);
        System.out.print("<-- ");
        String input = this.sysScanner.nextLine();
        return input;
    }

    public void exit() {
        // String randomGoodbye = Carbon.goodbyes[
        //     this.rand.nextInt(Carbon.goodbyes.length)
        // ];
        
        String randomGoodbye = Ui.GOODBYES[0] + "\n";
        this.printOut(randomGoodbye);
    }

    public void printError(CarbonException error) {
        this.printOut(error.toString());
    }
}
