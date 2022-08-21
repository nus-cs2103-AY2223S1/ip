import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static String start = '\u25B8' + " ";
    public static String sadFace = '\u2639' + " ";
    private static TaskList tasks = new TaskList();

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(start + "hi, i'm Duke!\n  what would you like to do today?");

        Scanner sc = new Scanner(System.in);
        while (true) {

            String input = sc.nextLine();
            String[] arr = input.split(" ", 2);
            if (arr[0].equals("bye")) {
                System.out.println(start + "bye! i hope to see you again soon! :)");
                break;
            }

            switch (arr[0]) {
            case "list":
                tasks.printList();
                break;
            case "mark":
                try {
                    tasks.mark(Integer.parseInt(arr[1]) - 1);
                } catch (NumberFormatException | IndexOutOfBoundsException e) {
                    System.out.println(sadFace + "please enter an integer so i know which task to mark!");
                }
                break;
            case "unmark":
                try {
                    tasks.unmark(Integer.parseInt(arr[1]) - 1);
                } catch (NumberFormatException | IndexOutOfBoundsException e) {
                    System.out.println(sadFace + "please enter an integer so i know which task to unmark!");
                }
                break;
            case "todo":
                try {
                    tasks.add(new ToDo(arr[1]));
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(sadFace + "please tell me the name of the todo task.");
                }
                break;
            case "deadline":
                try {
                    String[] dl = arr[1].split(" /by ", 2);
                    tasks.add(new Deadline(dl[0], dl[1]));
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(
                            sadFace + "for tasks with deadlines, please tell me the name of the task, followed " +
                                    "by '/by',\n  and then the date/time it needs to be completed by."
                    );
                }
                break;
            case "event":
                try {
                    String[] e = arr[1].split(" /", 2);
                    String[] timeInfo = e[1].split(" ", 2);
                    tasks.add(new Event(e[0], timeInfo[0], timeInfo[1]));
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(
                            sadFace + "for events, please tell me the name of the event, when it starts and when it ends."
                    );
                }
                break;
            case "delete":
                try {
                    tasks.delete(Integer.parseInt(arr[1]) - 1);
                } catch (NumberFormatException | IndexOutOfBoundsException e) {
                    System.out.println(sadFace + "please enter an integer so i know which task to delete!");
                }
                break;
            default:
                System.out.println(sadFace + "sorry, i don't know what that means :(");
            }
        }
    }
}
