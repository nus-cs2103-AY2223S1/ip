import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> list = new ArrayList<>();
        ArrayList<Boolean> marked = new ArrayList<>();

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
                        System.out.println(j + 1 + "." + (marked.get(j) ? "[X] " : "[ ] ") + list.get(j));
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
                }
                if (i == msgWords.length - 1) {
                    marked.add(false);
                    list.add(curr);
                    System.out.println(line_divider);
                    System.out.println("added: " + curr);
                    System.out.println(line_divider);
                }
            }
        }
    }
}