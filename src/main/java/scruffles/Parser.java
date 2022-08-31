package scruffles;

/**
 * A class that handles parsing of inputs into Scruffles
 *
 * @author Shamus Tan
 */
public class Parser {
    private static Ui ui = new Ui();

    /**
     * The method of the parser that's used to parse inputs the program receives
     *
     * @param input the string input of the program
     * @param tasks the TaskList of the current program
     * @throws UnknownArgumentException when input is an unknown command
     * @throws DescriptionEmptyException when a Task command has an incomplete input
     */
    public static void parse(String input, TaskList tasks) throws UnknownArgumentException, DescriptionEmptyException {
        if (input.equals("list")) {
            ui.printMessage(tasks.list());
        } else if (input.startsWith("mark")) {
            tasks.mark(input);
        } else if (input.startsWith("todo")) {
            tasks.add(input, Scruffles.Type.TODO);
        } else if (input.startsWith("deadline")) {
            tasks.add(input, Scruffles.Type.DEADLINE);
        } else if (input.startsWith("event")) {
            tasks.add(input, Scruffles.Type.EVENT);
        } else if (input.startsWith("delete")) {
            tasks.delete(input);
        } else {
            throw new UnknownArgumentException(input);
        }
    }
}