import java.sql.SQLOutput;
import java.util.Scanner;

public class Duke {

    public void dukeRun() {
        String line = "_______________________________\n";
        System.out.println(line +
                "Hello I'm Duke\n" +
                "What can I do for you?\n" +
                line
        );
        String input = "";
        Task[] arr = new Task[100];
        int index = 0;
        while (!input.equals("bye")) {
            Scanner scan = new Scanner(System.in);
            input = scan.nextLine();
            if (input.equals("bye")) {
                System.out.println(line +
                        "Bye. Hope to see you again soon!\n" +
                        line);
            } else if (input.startsWith("mark") && input.length() > 5){
                int taskNum = Integer.parseInt(input.substring(5));
                arr[taskNum-1].makeDone();
                System.out.println(line +
                        "Nice! I've marked this task as done: \n" +
                        arr[taskNum-1].getStatusIcon() +
                        arr[taskNum-1].description + "\n" + line);
            } else if (input.startsWith("unmark") && input.length() > 7){
                int taskNum = Integer.parseInt(input.substring(7));
                arr[taskNum-1].makeUndone();
                System.out.println(line +
                        "OK, I've marked this task as not done yet: \n" +
                        "[" + arr[taskNum-1].getStatusIcon() + "] " +
                        arr[taskNum-1].description + "\n" + line);
            } else if (input.equals("list")) {
                String list = line + "\n" + "Here are the tasks in your list: \n";
                for (int i = 0; i < index; i++) {
                    list += i + 1;
                    list += ". ";
                    list += arr[i].toString();
                    list += "\n";
                }
                System.out.println(list + line);
            } else if (input.startsWith("todo") && input.length() > 5){
                Todo todo = new Todo(input);
                arr[index] = todo;
                index ++;
                System.out.println(todo.addString(index));
            } else if (input.startsWith("deadline") && input.length() > 9){
                String[] dead = input.split(" /by ");
                Deadlines deadlines = new Deadlines(dead[0], dead[1]);
                arr[index] = deadlines;
                index ++;
                System.out.println(deadlines.addString(index));
            } else if (input.startsWith("event") && input.length() > 6){
                String[] time = input.split(" /at ");
                Event event = new Event(time[0], time[1]);
                arr[index] = event;
                index ++;
                System.out.println(event.addString(index));
            } else {
                Task task = new Task(input);
                arr[index] = task;
                index ++;
                System.out.println(line + "added: " + task.description + "\n" + line);
            }
        }
    }
    public static void main(String[] args) {
       Duke duke = new Duke();
       duke.dukeRun();
    }
}
