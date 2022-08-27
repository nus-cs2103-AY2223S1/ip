package duke;

public class Ui {

    public Ui () {}

    // greetings
    public void greeting() {
        String greet = "Hello! I'm Duke\nWhat can I do for you?";
        Parser.echo(greet);
    }

    // to create loading error
    public static void showLoadingError() {
        Parser.echo("No data in duke.txt");
    }
}
