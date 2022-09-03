package chacha;
import java.util.Scanner;

public class Ui {

public String readInput() {
    Scanner input = new Scanner(System.in);
    String s = input.nextLine();
    if (s == "bye") {
        input.close();
    }
    return s;
}

    public String printWelcome() {
        return "Welcome! I'm Chacha.\n" + "How may I assist you?";
    }
}
