import java.util.Scanner;
import java.util.ArrayList;
public class Pony {

    String greet = "Hello! I'm Pony" + "\n" + "What can I do for you? ";
    String exit = "Bye. Hope to see you again soon!";
    Scanner sc = new Scanner(System.in);
    ArrayList<String> list;

    public Pony() {
        this.list = new ArrayList();
    };

    public void initialise() {
        System.out.println(this.greet);
    }
    public void run() {
        String command = takeCommand();
        if (command.equals("list")) {
            if (this.list.size() == 0) {
                System.out.println("Nothing on the list!");
                run();
            } else {
                for (int i = 0; i < this.list.size(); i++) {
                    int sn = i + 1;
                    System.out.println(sn + ". " + this.list.get(i));
                }
                run();
            }
        } else if (command.equals("bye")) {
            System.out.println(this.exit);
        } else {
            this.list.add(command);
            System.out.println("added: " + command);
            run();
        }
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
