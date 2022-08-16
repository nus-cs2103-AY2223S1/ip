import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    static final String CHATBOX_NAME = "Ado";
    static final String PARTITION = "<><><><><><><><><><><><><><><><>\n";
    static List<Task> list = new ArrayList<>();

    public static void main(String[] args) {
        String input = "";
        Scanner sc = new Scanner(System.in);

        String startMessage = "Yo! I'm " + CHATBOX_NAME + "!\nWhat can I do for you? :)\n";
        printMessage(startMessage);

        while (!input.equalsIgnoreCase("bye")) {
            input = sc.nextLine();
            String[] segments = input.split(" ");

            if(input.equals("list")) {
                printMessage(listToString());
            }
            else if (input.equals("bye")) {
                printMessage("Gone so soon? :( Bye\n");
            } else if (input.contains("unmark")){
                Task curr = list.get(Integer. parseInt(segments[1]) - 1);
                curr.markAsUndone();
                printMessage("OK, I've marked this task as not done yet:\n" +curr + "\n");
            } else if (input.contains("mark")){
                Task curr = list.get(Integer.parseInt(segments[1]) - 1);
                curr.markAsDone();
                printMessage("Yay! You've completed a task!\n" + curr + "\n");
            } else{
                Task t = new Task(input);
                list.add(t);
                printMessage("added:" + input + "\n");
            }
        }
    }

    static String listToString() {
        if (list.size() == 0) {
            return "List is empty!\n";
        }
        StringBuilder output = new StringBuilder();
        output.append("Here are the tasks in your list: \n");
        for (int i =0; i < list.size(); i++) {
            output.append(i + 1).append(". ").append(list.get(i)).append("\n");
        }
        return output.toString();
    }

     static void printMessage(String message) {
        String print = PARTITION + message + PARTITION;
        System.out.println(print);
    }

}
