import java.util.ArrayList;

/**
 * Duke is a personal assistant chatbot.
 * It helps a person to keep track of various things.
 *
 * @author Rexong
 */
public class Duke {
    private DukeCommandHandler dukeCommandHandler;

    public Duke() {
        dukeCommandHandler = new DukeCommandHandler();
    }

    public void start() {
        dukeCommandHandler.run();
    }
}




















