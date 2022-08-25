package duke;

public class Duke {

    public static void main(String[] args) {
        Ui ui = new Ui();
        Dukebot driver = new Dukebot(ui);
        while (true) {
            String input = ui.getInput();
            driver.handleInput(input);
        }
    }
}
