/**
 * Represents a recorder that echoes input to output.
 *
 * @author WR3nd3
 */
public class Recorder {
    private String input;
    private String addOn = "Nya!";

    /**
     * Prints the received String input to the command line interface.
     *
     * @param input String that the user enters via the command line interface.
     */
    public void echo(String input) {
        System.out.println(input + " " + addOn);
    }
}
