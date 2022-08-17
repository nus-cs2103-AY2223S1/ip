import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> list = new ArrayList<>();
        ArrayList<Boolean> marked = new ArrayList<>();
        ArrayList<String> type = new ArrayList<>();

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String line_divider = "____________________________________________________________";
        System.out.println("Wassup from\n" + logo);
        System.out.println(line_divider);
        System.out.println("Wassup la I'm Duke\nWhat you want?\n" + line_divider);

        while (true) {
            String curr = sc.nextLine();
            String[] msgWords = curr.split("\\b");
            for (int i = 0; i < msgWords.length; i++) {
                if (msgWords[i].equalsIgnoreCase("bye")) {
                    System.out.println(line_divider + "\nBye. Zai Jian!\n" + line_divider);
                    break;
                } else if (msgWords[i].equalsIgnoreCase("list")) {
                    System.out.println(line_divider);
                    System.out.println("Here are your tasks la:");
                    for (int j = 0; j < list.size(); j++) {
                        System.out.println(j + 1 + "." + type.get(j) + (marked.get(j) ? "[X] " : "[ ] ") + list.get(j));
                    }
                    System.out.println(line_divider);
                    break;
                } else if (msgWords[i].equalsIgnoreCase("mark")) {
                    int index = Integer.parseInt(msgWords[2]) - 1;
                    marked.remove(index);
                    marked.add(index, true);
                    System.out.println(line_divider);
                    System.out.println("Ok ticked this already");
                    System.out.println((marked.get(index) ? "[X] " : "[ ] ") + list.get(index));
                    System.out.println(line_divider);
                    break;
                } else if (msgWords[i].equalsIgnoreCase("unmark")) {
                    int index = Integer.parseInt(msgWords[2]) - 1;
                    marked.remove(index);
                    marked.add(index, false);
                    System.out.println(line_divider);
                    System.out.println("Ok not done yet ah");
                    System.out.println((marked.get(index) ? "[X] " : "[ ] ") + list.get(index));
                    System.out.println(line_divider);
                    break;
                } else if (msgWords[i].equalsIgnoreCase("todo")) {
                    String item = curr.substring(5);
                    marked.add(false);
                    list.add(item);
                    type.add("[T]");
                    System.out.println(line_divider);
                    System.out.println("Ok I add your task already:");
                    System.out.println("[T]" + "[ ] " + item + "\nNow " + list.size() + " tasks already");
                    System.out.println(line_divider);
                    break;
                } else if (msgWords[i].equalsIgnoreCase("deadline")) {
                    int slashPos = curr.indexOf("/by");
                    String taskName = curr.substring(9, slashPos - 1);
                    String deadline = " (by:" + curr.substring(slashPos + 3) + ")";
                    marked.add(false);
                    list.add(taskName + deadline);
                    type.add("[D]");
                    System.out.println(line_divider);
                    System.out.println("Ok I add your task already:");
                    System.out.println("[D]" + "[ ] " + taskName + deadline + "\nNow " + list.size() + " tasks already!");
                    System.out.println(line_divider);
                } else if (msgWords[i].equalsIgnoreCase("event")) {
                    int slashPos = curr.indexOf("/at");
                    String taskName = curr.substring(6, slashPos - 1);
                    String deadline = " (at:" + curr.substring(slashPos + 3) + ")";
                    marked.add(false);
                    list.add(taskName + deadline);
                    type.add("[E]");
                    System.out.println(line_divider);
                    System.out.println("Ok I add your task already:");
                    System.out.println("[E]" + "[ ] " + taskName + deadline + "\nNow " + list.size() + " tasks already!");
                    System.out.println(line_divider);
                }
//                if (i == msgWords.length - 1) {
//                    marked.add(false);
//                    list.add(curr);
//                    System.out.println(line_divider);
//                    System.out.println("added: " + curr);
//                    System.out.println(line_divider);
//                }
            }
        }
    }
}