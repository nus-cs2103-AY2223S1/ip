package scruffles;
public class Parser {
    private static Ui ui = new Ui();
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