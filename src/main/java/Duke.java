import java.util.Scanner;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Parser dc = new Parser();
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?\n");

        String message = sc.nextLine();

        while (!message.equals("bye")) {
            try {
                dc.parse(message);
            } catch (DukeException e){
                System.out.println(e);
            }
            message = sc.nextLine();
        }

        System.out.println("\nBye. Hope to see you again soon!");
        sc.close();
        }
    }
