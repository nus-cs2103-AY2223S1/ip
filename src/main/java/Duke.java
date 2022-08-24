import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Tasklist tl = new Tasklist();
//        ArrayList<Boolean> marked = new ArrayList<>();
//        ArrayList<String> type = new ArrayList<>();

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String line_divider = "____________________________________________________________";
//        System.out.println("Wassup from\n" + logo);
        System.out.println(line_divider);
        System.out.println("Wassup la I'm Duke\nWhat you want?\n" + line_divider);

        while (true) {
            String curr = sc.nextLine();
            String[] msgWords = curr.split("\\b");
            for (int i = 0; i < msgWords.length; i++) {
                if (msgWords[i].equalsIgnoreCase("bye")) {
                    System.out.println(line_divider + "\nBye. Zai Jian!\n" + line_divider);
                    return;
                } else if (msgWords[i].equalsIgnoreCase("list")) {
                    System.out.println(line_divider);
                    System.out.println("Here are your tasks la:");
                    for (int j = 0; j < tl.size(); j++) {
                        System.out.println(j + 1 + ":" + tl.get(j).toString());
                    }
                    System.out.println(line_divider);
                    break;
                } else if (msgWords[i].equalsIgnoreCase("mark")) {
                    int index = Integer.parseInt(msgWords[2]) - 1;
                    tl.get(index).mark();
                    System.out.println(line_divider);
                    System.out.println("Ok ticked this already");
                    System.out.println(tl.get(index).toString());
                    System.out.println(line_divider);
                    break;
                } else if (msgWords[i].equalsIgnoreCase("unmark")) {
                    int index = Integer.parseInt(msgWords[2]) - 1;
                    tl.get(index).unMark();
                    System.out.println(line_divider);
                    System.out.println("Ok not done yet ah");
                    System.out.println(tl.get(index).toString());
                    System.out.println(line_divider);
                    break;
                } else if (msgWords[i].equalsIgnoreCase("delete")) {
                    int index = Integer.parseInt(msgWords[2]) - 1;
                    Task removed = tl.get(index);
                    tl.remove(index);
                    System.out.println(line_divider);
                    System.out.println("I remove this ah:");
                    System.out.println(removed.toString());
                    System.out.println("Now " + tl.size() + " tasks only");
                    System.out.println(line_divider);
                    break;
                } else if (msgWords[i].equalsIgnoreCase("todo")) {
                    if (curr.length() < 6) {
                        System.out.println("☹ OOPS!!! Why empty");
                        break;
                    }
                    String item = curr.substring(5);
                    Todo newTodo = new Todo(item);
                    tl.add(newTodo);
                    System.out.println(line_divider);
                    System.out.println("Ok I add your task already:");
                    System.out.println(newTodo + "\nNow " + tl.size() + " tasks already");
                    System.out.println(line_divider);
                    break;
                } else if (msgWords[i].equalsIgnoreCase("deadline")) {
                    if (curr.length() < 10 || !curr.contains("/by")) {
                        System.out.println("Why not complete deadline?");
                        break;
                    }
                    int slashPos = curr.indexOf("/by");
                    String taskName = curr.substring(9, slashPos - 1);
                    String deadline = curr.substring(slashPos + 3);
                    Deadline newDL = new Deadline(taskName, deadline);
                    tl.add(newDL);
                    System.out.println(line_divider);
                    System.out.println("Ok I add your task already:");
                    System.out.println(newDL + "\nNow " + tl.size() + " tasks already!");
                    System.out.println(line_divider);
                    break;
                } else if (msgWords[i].equalsIgnoreCase("event")) {
                    if (curr.length() < 7 || !curr.contains("/at")) {
                        System.out.println("Why not complete event?");
                        break;
                    }
                    int slashPos = curr.indexOf("/at");
                    String taskName = curr.substring(6, slashPos - 1);
                    String deadline = curr.substring(slashPos + 3);
                    Event newEvent = new Event(taskName, deadline);
                    tl.add(newEvent);
                    System.out.println(line_divider);
                    System.out.println("Ok I add your task already:");
                    System.out.println(newEvent + "\nNow " + tl.size() + " tasks already!");
                    System.out.println(line_divider);
                    break;
                } else {
                    System.out.println("☹ Walao what do you mean");
                    break;
                }
            }
        }
    }
}