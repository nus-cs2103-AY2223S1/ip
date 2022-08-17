import java.util.*;

public class Duke {
    public static void main(String[] args) {
        String lines = "     ____________________________________________________________";
        System.out.println(lines);
        System.out.println("     Hello! I'm Duke");
        System.out.println("     What can I do for you?");
        System.out.println(lines);

        Scanner sc = new Scanner(System.in);
        List<Task> history = new ArrayList<>();

        while(true) {
            String input = sc.nextLine();
            if(input.equals("bye")) {
                System.out.println(lines);
                System.out.println("     Bye! Hope to see you again soon!");
                System.out.println(lines);
                sc.close();
                break;
            } else if(input.equals("list")){
                System.out.println(lines);
                ListIterator<Task> listIterator = history.listIterator();
                while(listIterator.hasNext()) {
                    Task t = listIterator.next();
                    System.out.println("     " + listIterator.nextIndex() +
                            ". " + "[" + t.getStatusIcon() + "] "+
                            t.description);
                }
                System.out.println(lines);
            } else if(input.startsWith("mark")) {
                int index = Integer.parseInt(input.replaceAll("[\\D]", ""));
                Task t = history.get(index-1);
                t.markAsDone();
                System.out.println(lines);
                System.out.println("     Nice! I've marked this task as done");
                System.out.println("        [" + t.getStatusIcon() + "] " + t.description);
                System.out.println(lines);
            } else if (input.startsWith("unmark")){
                int index = Integer.parseInt(input.replaceAll("[\\D]", ""));
                Task t = history.get(index-1);
                t.markAsNotDone();
                System.out.println("     OK, I've marked this task as not done yet");
                System.out.println(lines);
                System.out.println("        [" + t.getStatusIcon() + "] " + t.description);
                System.out.println(lines);
            }
            else {
                Task t = new Task(input);
                history.add(t);
                System.out.println(lines);
                System.out.println("     " + "added: " + input);
                System.out.println(lines);
            }
        }
    }
}
