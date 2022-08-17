import java.util.Scanner;

public class Duke {

    private static TaskList taskList = new TaskList();
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        String welcomeMsg = "Hi there! Baymax at your service.";
        System.out.println(welcomeMsg);
        String input = sc.nextLine();
        while(true) {
            if(input.equalsIgnoreCase("bye")) {
                break;
            }
            if(input.equalsIgnoreCase("list")) {
                taskList.list();
                input = sc.nextLine();
                continue;
            }
            taskList.add(new Task(input));
            input = sc.nextLine();
        }
        System.out.println("Goodbye!");
    }
}
