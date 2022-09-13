package scruffles;

/**
 * A class that handles parsing of inputs into Scruffles
 *
 * @author Shamus Tan
 */
public class Parser {

    private TaskList tasks;

    public Parser() {
        tasks = Storage.load();
    }

    /**
     * Parses the input received by the program
     *
     * @param input the string input of the program
     * @return the output string of Scruffles
     */
    public String parse(String input) {
        try {
            if (input.equals("list")) {
                return tasks.list();
            } else if (input.startsWith("mark")) {
                return tasks.mark(input);
            } else if (input.startsWith("todo")) {
                return tasks.add(input, Scruffles.Type.TODO);
            } else if (input.startsWith("deadline")) {
                return tasks.add(input, Scruffles.Type.DEADLINE);
            } else if (input.startsWith("event")) {
                return tasks.add(input, Scruffles.Type.EVENT);
            } else if (input.startsWith("delete")) {
                return tasks.delete(input);
            } else if (input.startsWith("find")) {
                return tasks.find(input);
            } else if (input.equals("bye")) {
                return tasks.bye();
            } else {
                throw new UnknownArgumentException(input);
            }
        } catch (UnknownArgumentException e) {
            return e.getMessage();
        }
    }
}