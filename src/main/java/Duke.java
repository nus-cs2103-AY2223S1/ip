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
        String str = sc.nextLine();

        while (true) {
            if (str.startsWith("todo")) {
                try {
                    String content = str.substring(4).trim();
                    if (content.equals("")) {
                        throw new WrongMessageException();
                    }
                    Task todo = new Todo(content);
                    willDo.add(todo);
                    int size = willDo.size();
                    System.out.println("Got it, I've added it to the task list:\n"
                            + todo.toString() + "\n"
                            + "Now you have " + (size) + " tasks");
                } catch (WrongMessageException e) {
                    System.out.println(e.getMessage());
                }

            } else if (str.startsWith("deadline")) {
                try {
                    String ddlstr = str.substring(8).trim();
                    if (ddlstr.equals("")) {
                        throw new WrongMessageException();
                    }
                    String[] temp = ddlstr.split("/by");
                    String ddlinfo = temp[0];
                    String date = temp[1];
                    Task deadline = new Deadline(ddlinfo, date);
                    willDo.add(deadline);
                    int nowsize = willDo.size();
                    System.out.println("Got it, I've added it to the task list:\n"
                            + deadline.toString() + "\n"
                            + "Now you have " + (nowsize) + " tasks");
                } catch (WrongMessageException e) {
                    System.out.println(e.getMessage());
                }

            } else if (str.startsWith("event")) {
                try {
                    String eventstr = str.substring(5).trim();
                    if (eventstr.equals("")) {
                        throw new WrongMessageException();
                    }
                    String[] temp = eventstr.split("/at");
                    String eventinfo = temp[0];
                    String takeplace = temp[1];
                    Task event = new Event(eventinfo, takeplace);
                    willDo.add(event);
                    int finalsize = willDo.size();
                    System.out.println("Got it, I've added it to the task list:\n"
                            + event.toString() + "\n"
                            + "Now you have " + (finalsize) + " tasks");
                } catch (WrongMessageException e) {
                    System.out.println(e.getMessage());
                }

            } else if (str.startsWith("mark")) {
                String[] temp = str.split(" ");
                int key = Integer.decode(temp[1]);
                Task willMark = willDo.get(key - 1);
                willMark.donelah();
                System.out.println("Congratulations! you complete this task:\n"
                        + willMark.toString());

            } else if (str.startsWith("unmark")) {
                String[] temp = str.split(" ");
                int key2 = Integer.decode(temp[1]);
                Task willUnmark = willDo.get(key2 - 1);
                willUnmark.nodone();
                System.out.println("You undone this task:\n"
                        + willUnmark.toString());

            } else if (str.startsWith("list")) {
                System.out.println("Your list is as following");
                for (int i = 0; i < willDo.size(); i++) {
                    Task temp = willDo.get(i);
                    System.out.println((i + 1) + "." + temp.toString());
                }
            } else if (str.startsWith("bye")) {
                System.out.println("Bye! Hope to see you again soon!");
                break;
            } else if (str.startsWith("delete")) {
                String[] temp = str.split(" ");
                int key3 = Integer.decode(temp[1]);
                System.out.println("ok I will delete the task" + willDo.get(key3 - 1) + "it right now!");
                willDo.remove(key3 - 1);
                System.out.println("now you have " + willDo.size() + " tasks in the list");
            } else {
                try {
                    throw new CannotUnderstandException();
                } catch (CannotUnderstandException e) {
                    System.out.println(e.getMessage());
                }
            }
            str = sc.nextLine(); // for continuing
        }
    }
}