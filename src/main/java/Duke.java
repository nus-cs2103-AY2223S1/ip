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

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        //System.out.println("Hello from\n" + logo);

        System.out.println(greet + "\n" + logo);
        String str = sc.next();
        while (!str.equals("bye")) {
            switch (str) {
                case "todo":
                    String content = sc.nextLine();
                    Task todo = new Todo(content);
                    willDo.add(todo);
                    int size = willDo.size();
                    System.out.println("Got it, I've added it to the task list:\n"
                            + todo.toString() + "\n"
                            + "Now you have " + (size) + " tasks");
                    break;

                case "deadline":
                    String ddlstr = sc.nextLine();
                    String ddlinfo = ddlstr.substring(0, ddlstr.indexOf("/") - 1);
                    String date = ddlstr.substring(ddlstr.indexOf("/") + 4);
                    Task deadline = new Deadline(ddlinfo, date);
                    willDo.add(deadline);
                    int nowsize = willDo.size();
                    System.out.println("Got it, I've added it to the task list:\n"
                            + deadline.toString() + "\n"
                            + "Now you have " + (nowsize) + " tasks");
                    break;

                case "event":
                    String eventstr = sc.nextLine();
                    String eventinfo = eventstr.substring(0, eventstr.indexOf("/") - 1);
                    String takeplace = eventstr.substring(eventstr.indexOf("/") + 4);
                    Task event = new Event(eventinfo, takeplace);
                    willDo.add(event);
                    int finalsize = willDo.size();
                    System.out.println("Got it, I've added it to the task list:\n"
                            + event.toString() + "\n"
                            + "Now you have " + (finalsize) + " tasks");
                    break;

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
                    System.out.println("added: " + str);
                    Task newT = new Task(str);
                    willDo.add(newT);
                    break;
            }
            str = sc.next(); // for continuing
        }
        System.out.println("Bye! Hope to see you again soon!");
    }
}