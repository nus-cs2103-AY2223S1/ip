import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String horizontalLine = "-------------------------";
        System.out.println(logo + "\n" + horizontalLine);
        System.out.println("To all Subjects of Ymir. My name is Eren Yeager.\n" + "How can I help you?" + "\n" + horizontalLine);

        ArrayList<Task> taskList = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        String echo = sc.next();


        while (!echo.equals("bye")) {

            switch (echo) {

                case "list": {
                    System.out.println(horizontalLine + "\n" + "Here are the tasks in your list:");
                    for (int i = 0; i < taskList.size(); i++) {
                        System.out.println((i + 1) + ". " + taskList.get(i));
                    }
                    System.out.println(horizontalLine);
                    break;
                }

                case "mark": {
                    int index = sc.nextInt() - 1;
                    Task completedTask = taskList.get(index);
                    completedTask.markAsDone();
                    System.out.println("Nice! I've marked this task as done:" + "\n" + completedTask);
                    break;
                }

                case "unmark": {
                    int index = sc.nextInt() - 1;
                    Task unfinishedTask = taskList.get(index);
                    unfinishedTask.markAsNotDone();
                    System.out.println("OK, I've marked this task as not done yet:" + "\n" + unfinishedTask);
                    break;
                }

                default:
                    echo += sc.nextLine();
                    System.out.println(horizontalLine + "\n" + "added: " + echo + "\n" + horizontalLine);
                    taskList.add(new Task(echo));
            }
            echo = sc.next();
        }
        System.out.println(horizontalLine + "\n" + "Keep moving forward until you finish all your tasks. Goodbye." + "\n" + horizontalLine);
    }
}
