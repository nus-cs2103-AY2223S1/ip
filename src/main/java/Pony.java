import java.util.Scanner;

public class Pony {

    String greet = "Hello! I'm Pony" + "\n" + "What can I do for you? ";
    String exit = "Bye. Hope to see you again soon!";
    Scanner sc = new Scanner(System.in);

    public Pony() {
    };

    public void initialise() {
        System.out.println(this.greet);
    }
    public void run() {
        String command = takeCommand();
        while (!command.equals("bye")) {
            System.out.println(command);
            command = takeCommand();
        }
        System.out.println(this.exit);
    }

    public String takeCommand() {
        String command = this.sc.nextLine();
        return command;
    }

    public static void main(String[] args) {
        Pony myPony = new Pony();
        myPony.initialise();
        myPony.run();
    }
}
