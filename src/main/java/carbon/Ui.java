package carbon;

import java.util.Random;

/**
 * Provides the user interface for Carbon to interact with the user.
 * Primarily used for the Graphical User Interface.
 */
public class Ui {
    private static final String INTRO = "Hey, Carbon here. ";
    private static final String[] INIT_PROMPTS = {
        "What's up?",
        "How's things going?",
        "Nice weather today, huh?",
        "How can I help you?",
        "Please don't talk to me.",
        "To get an A+ for CS2103T, you have t---[REDACTED]"
    };
    private static final String[] GOODBYES = {
        "Bye-bye, see you again soon!",
        "Farewell. Stay safe.",
        "Nice meeting you. Let's catch up again sometime.",
        "Bye. Good riddance.",
        "...zzzzzz...",
        "What? Yeah, sorry I gotta run now."
    };

    // used for making random choices of text
    private Random rand;

    /**
     * Constructs an instance of the Ui class.
     *
     * @return Ui object.
     */
    public Ui() {
        this.rand = new Random();
    }

    /**
     * Returns a String comprising the greeting for the user.
     * Chooses a prompt from a list randomly.
     *
     * @return Greeting to the user.
     */
    public String greet() {
        int randomIndex = this.rand.nextInt(Ui.INIT_PROMPTS.length);
        String randomPrompt = Ui.INIT_PROMPTS[randomIndex];
        String greeting = Ui.INTRO + randomPrompt;
        return greeting;
    }

    /**
     * Exits the Carbon interactive interface.
     *
     * @return Farewell to the user.
     */
    public String exit() {
        int randomIndex = this.rand.nextInt(Ui.GOODBYES.length);
        String randomGoodbye = Ui.GOODBYES[randomIndex];
        return randomGoodbye;
    }
}
