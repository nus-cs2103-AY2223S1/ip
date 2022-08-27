import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

    private String userInput;

    public Ui() {
        System.out.println("What can I do for you?");
    }

    public void getPrompt(Scanner sc) {
            String line = "";
            while (true) {
                line = sc.nextLine();
                Parser parser = new Parser();
            }

    }
}
