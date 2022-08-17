/**
 * Object that accepts a String as an argument and prints this input
 * as output with a "Nya".
 *
 */
public class Recorder {
    private String input;
    private String addon = "Nya!";
    private static final String border = "\n____________________________________________________________\n";

    public void echo(String input) {
        System.out.println(border + input + " " + addon + border);
    }

}
