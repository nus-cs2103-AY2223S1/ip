import java.util.Scanner;

/**
 * Apollo is a chatbot that keeps tracks of various items, encapsulated
 * as a Duke instance.
 *
 * @author Kartikeya
 */
public class Duke {
    private final Scanner s;
    private static final String intro =
        "Welcome to Apollo!\n" +
            "How can I help you today?";
    public static final String divider =
        "\n-----------------------------------------------";

    // Stores all the items given to the chatbot
    private final DukeList itemList;

    public Duke() {
        s = new Scanner(System.in);
        itemList = new DukeList();
    }

    /**
     * Initialises the chatbot.
     */
    void start() {
        System.out.println(intro + divider);
        run();
    }

    /**
     * Runs Apollo. Waits for input lines and
     * processes them accordingly.
     */
    void run() {
        try {
            checkInput(s.nextLine());
        } catch (DukeException e) {
            System.out.println(e.getMessage() + divider);
            run();
        }
    }

    /**
     * Processes chat inputs using a switch statement, throwing a DukeException
     * on incorrect inputs.
     * @param inputString String given to Apollo
     * @throws DukeException Indicates incorrect inputs
     */
    void checkInput(String inputString) throws DukeException {
        String[] input = inputString.split(" ");
        String output = "";
        try {
            switch (input[0]) {
                case "bye": {
                    System.out.println("Goodbye, see you soon!" + divider);
                    return;
                }
                case "list": {
                    output = itemList.toString();
                    break;
                }
                case "mark": {
                    output = itemList.mark(Integer.parseInt(input[1]));
                    break;
                }
                case "unmark": {
                    output = itemList.unmark(Integer.parseInt(input[1]));
                    break;
                }
                case "delete": {
                    output = itemList.deleteItem(Integer.parseInt(input[1]));
                    break;
                }
                default: {
                    output = itemList.addItem(input);
                }
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Missing command description.");
        } catch (NumberFormatException e) {
            throw new DukeException("Please recheck your number input, " +
                "including trailing spaces.");
        }
        System.out.println(output + divider);
        run();
    }

    public static void main(String[] args) {
        Duke instance = new Duke();
        instance.start();
    }
}
