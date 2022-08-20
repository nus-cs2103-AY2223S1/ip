public class Ui {
    private static final String indentation = "    ";
    private static final String horizontalLine = "____________________________________________________________";
    private static final String lineSeparator = System.lineSeparator();

    /**
     * Show messages to the user.
     *
     * @param messages List of messages to be shown.
     */
    public void show(String... messages) {
        for (String m : messages) {
            // Formats message.
            System.out.println(indentation + m.replace("\n", lineSeparator + indentation));
        }
    }

    public void showWelcomeMessage() {
        this.show(
                horizontalLine,
                "Hello I'm",
                "____        _        ",
                "|  _ \\ _   _| | _____ ",
                "| | | | | | | |/ / _ \\",
                "| |_| | |_| |   <  __/",
                "|____/ \\__,_|_|\\_\\___|",
                "",
                "What can I do for you?",
                horizontalLine
        );
    }

    public void showGoodbyeMessage() {
        this.show(
                "Bye, hope to see you soon!",
                horizontalLine
        );
    }

    public void showErrorMessage(String message) {
        this.show(
                message,
                horizontalLine
        );
    }

    public void showErrorMessage(Exception exception) {
        this.showErrorMessage(exception.getMessage());
    }

    public void showResult(CommandResult result) {
        this.show(result.getUserMessage(), horizontalLine);
    }
}
