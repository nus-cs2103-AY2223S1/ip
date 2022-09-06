import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Ui {
    private static String SPACING = "-----------------------------------------";
    BufferedReader br;

    public Ui() {
        this.br = new BufferedReader(new InputStreamReader(System.in));
    }

    public void greet() {
        System.out.println(SPACING);
        System.out.println("Hello! I am Greg!");
        System.out.println("What do you need help with?");
        System.out.println(SPACING + "\n");
    }

    public void printFrontSpacing() {
        System.out.println("\n  " + SPACING);
    }

    public void printBackSpacing() {
        System.out.println("  " + SPACING + "\n");
    }

    public void sayBye() {
        System.out.println("    Goobye, see you again!\n");
    } 

    public String getInput() throws Exception {
        return br.readLine();
    }
}
