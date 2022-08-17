/**
 * Object that accepts a String as an argument and prints this input
 * as output with a "Nya".
 *
 */
public class Recorder {
    private String input;
    private String addOn = "Nya!";

    public void echo(String input) {
        System.out.println(input + " " + addOn);
    }

}
