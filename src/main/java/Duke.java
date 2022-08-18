import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Duke {
    public static void main(String[] args) throws Exception{
        // Reader and Writers
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // Variables
        String spacing = "-----------------------------------------";

        // Main code
        System.out.println(spacing);
        System.out.println("Hello! I am Greg!");
        System.out.println("What do you need help with?");
        System.out.println(spacing + "\n");
        while (true) {
            String word = br.readLine();
            if (word.equals("bye")) {
                System.out.println(spacing);
                System.out.println("Goobye, see you again!\n");
                System.out.println(spacing + "\n");
                break;
            } else {
                System.out.println(spacing);
                System.out.println(word + "\n");
                System.out.println(spacing + "\n");
            }
        }
    }
}
