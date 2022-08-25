import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        Seaward seaward = new Seaward("tasks.txt");
        System.out.println(seaward.getWelcome());

        try {
            File f = new File("tasks.txt");
            if (f.exists()) {
                Scanner sc = new Scanner(f);
                while (sc.hasNext()) {
                    String curr = sc.nextLine();
                    seaward.readTasks(curr);
                }
                sc.close();
            } else {
                f.createNewFile();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        // Need to find a way to accept input
        Scanner input = new Scanner(System.in);
        while (true) {
            String s = input.nextLine();
            try {
                System.out.println(seaward.readInputString(s));
                if (s.equals("bye")) {
                    break;
                }
            } catch (InvalidDescriptionException | InvalidCommandException | IOException e) {
                System.out.println(e.getMessage());
            }
        }
        input.close();
    }
}
