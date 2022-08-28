import java.util.Scanner;

public class Ui {

    private static final String GREETINGS = "Hello! I'm Ekud \n" + "What can I do for you?";
    private static final String BANNER = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
    private Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public String readCommand() {
        return sc.nextLine();
    }
    public void print(String msg) {
        System.out.println(BANNER);
        System.out.println(msg);
        System.out.println(BANNER);
    }

    public void printGreetings() {
        print(GREETINGS);
    }
    public void printAddTask(String msg, int size) {
        print("Got it. I've added this task:\n" + msg +
                "\nNow you have " + size +  " tasks in the list.");
    }

    public void printList(TaskList tasks) {
        String list = "";
        for (int i = 0; i < tasks.size(); i++) {
            list += (i + 1) + "." + tasks.get(i);
            if (i != tasks.size() - 1) list += "\n";
        }
        print(list);
    }

    public void close() {
        sc.close();
    }
}
