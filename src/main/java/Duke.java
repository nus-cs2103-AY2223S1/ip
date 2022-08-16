import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {

        List<Task> willDo = new ArrayList<>();

        // To get user's requirements
        Scanner sc = new Scanner(System.in);
        String greet = "Hello! I'm Lan\n"
                + "What can I do for you?";

        /*String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);*/

        System.out.println(greet);
        String str = sc.next();
        while (true) {
            // terminate point
            if (str.equals("bye")) {
                System.out.println("Bye! Hope to see you again soon!");
                break;
            }
            // other cases
            switch (str) {
                case "mark":
                    int key = sc.nextInt();
                    Task willMark = willDo.get(key - 1);
                    willMark.donelah();
                    System.out.println("Congratulations! you complete this task:\n"
                                    + willMark.toString());
                    break;

                case "unmark":
                    int key2 = sc.nextInt();
                    Task willUnmark = willDo.get(key2 - 1);
                    willUnmark.nodone();
                    System.out.println("You undone this task:\n"
                            + willUnmark.toString());
                    break;

                //When user want to access to task list
                case "list":
                    System.out.println("Your list is as following");
                    for (int i = 0; i < willDo.size(); i++) {
                        Task temp = willDo.get(i);
                        System.out.println((i + 1) + "." + temp.toString());
                    }
                    break;

                //When user want to add tasks
                default:
                    str += sc.nextLine();
                    System.out.println("added: " + str);
                    Task newT = new Task(str);
                    willDo.add(newT);
                    break;
            }
            str = sc.next();
        }
    }
}