import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?\n");

        Scanner sc = new Scanner(System.in);
        ArrayList<Task> arrayList = new ArrayList<>();

        loop:
        while(sc.hasNextLine()) {
            String input = sc.nextLine();
            String[] splitInput = input.split(" ", 2);
            String command = splitInput[0];
            String detail = "";
            if(splitInput.length > 1) {
                detail = splitInput[1];
            }
            switch (command) {
                case "bye":
                    System.out.println("Bye. Hope to see you again soon!");
                    break loop;
                case "list":
                    String message = "Here are the tasks in your list:\n";
                    int length = arrayList.size();
                    for(int i = 0; i < length; i++) {
                        message += String.format("%d. " + arrayList.get(i).toString() + "\n", i + 1);
                    }
                    System.out.println(message);
                    break;
                case "mark":
                    int markIndex = Integer.parseInt(detail) - 1;
                    Task markTask = arrayList.get(markIndex);
                    if(markTask.isDone) {
                        System.out.println("This task is already marked as done:\n" + markTask.toString() + "\n");
                    } else {
                        markTask.markDone();
                        System.out.println("Nice! I've marked this task as done:\n" + markTask.toString() + "\n");
                    }
                    break;
                case "unmark":
                    int unMarkIndex = Integer.parseInt(detail) - 1;
                    Task unMarkTask = arrayList.get(unMarkIndex);
                    if(!unMarkTask.isDone) {
                        System.out.println("This task is already marked as undone:\n" + unMarkTask.toString() + "\n");
                    } else {
                        unMarkTask.markUndone();
                        System.out.println("OK, I've marked this task as not done yet:\n" + unMarkTask.toString() + "\n");
                    }
                    break;
                case "todo":
                    Todo todo = new Todo(detail);
                    arrayList.add(todo);
                    String todoMessage = "added: " + todo.toString() + "\n";
                    todoMessage += String.format("Now, you have %d task(s) in the list.", arrayList.size());
                    System.out.println(todoMessage + "\n");
                    break;
                case "deadline":
                    String[] splitDetailDeadline = detail.split(" /by ", 2);
                    String deadlineAction = splitDetailDeadline[0];
                    String deadlineTime = splitDetailDeadline[1];
                    Deadline deadline = new Deadline(deadlineAction, deadlineTime);
                    arrayList.add(deadline);
                    String deadlineMessage = "added: " + deadline.toString() + "\n";
                    deadlineMessage += String.format("Now, you have %d task(s) in the list.", arrayList.size());
                    System.out.println(deadlineMessage + "\n");
                    break;
                case "event":
                    String[] splitDetailEvent = detail.split(" /at ", 2);
                    String eventAction = splitDetailEvent[0];
                    String eventTime = splitDetailEvent[1];
                    Event event = new Event(eventAction, eventTime);
                    arrayList.add(event);
                    String eventMessage = "added: " + event.toString() + "\n";
                    eventMessage += String.format("Now, you have %d task(s) in the list.", arrayList.size());
                    System.out.println(eventMessage + "\n");
                    break;
                default:
                    System.out.println("Invalid input");
            }
        }
        sc.close();
    }
}
