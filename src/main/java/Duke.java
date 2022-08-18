import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Duke {
    public static void main(String[] args) throws Exception{
        // Reader and Writers
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // Variables
        String spacing = "-----------------------------------------";
        String[] tasks = new String[100];
        int numTasks = 0;

        // Main code
        System.out.println(spacing);
        System.out.println("Hello! I am Greg!");
        System.out.println("What do you need help with?");
        System.out.println(spacing + "\n");
        while (true) {
            // Say goodbye
            String word = br.readLine();
            if (word.equals("bye")) {
                System.out.println(spacing);
                System.out.println("Goobye, see you again!\n");
                System.out.println(spacing + "\n");
                break;
            } else if (word.equals("list")) {
                System.out.println(spacing);
                for (int i = 0; i < numTasks; i++) {
                    System.out.println(Integer.toString(i+1) + ". " + tasks[i]);
                }
                System.out.println("\n" + spacing + "\n");
            } else { // Read in tasks
                tasks[numTasks] = word;
                numTasks += 1;
                System.out.println(spacing);
                System.out.println("added: " + word + "\n");
                System.out.println(spacing + "\n");
            }
        }
    }
}