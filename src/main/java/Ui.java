import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Ui {
    private static String SPACING = "-----------------------------------------";

    public void greet() {
        System.out.println(SPACING);
        System.out.println("Hello! I am Greg!");
        System.out.println("What do you need help with?");
        System.out.println(SPACING + "\n");
    }

    public void sayBye() {
        System.out.println("\n" + SPACING);
        System.out.println("Goobye, see you again!\n");
        System.out.println(SPACING + "\n");
        return;
    } 

    public void getInput() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Parser parser = new Parser();
        boolean terminate = false;
        while (!terminate) {
            String input = br.readLine();
            terminate = parser.parse(input);
        }
        this.sayBye();
    }
}
