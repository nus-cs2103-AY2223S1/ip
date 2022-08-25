import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
public class Duke {

    public static void read() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("list.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] temp = line.split("/");
                if (temp.length == 3) {
                    tasklist.add(new DukeTask(temp[2], temp[1].contains("X"), temp[0].charAt(0)));
                } else {
                    tasklist.add(new DukeTask(temp[2], temp[1].contains("X"), temp[0].charAt(0), temp[3]));
                }

            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }

    public static void save() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("list.txt"));
            for (int i = 0; i < tasklist.size(); i ++) {
                DukeTask t = tasklist.get(i);
                System.out.println(t.task);
                System.out.println(t.time);
                writer.write(t.taskType + "/" + (t.isMarked ? 'X' : 'O') + "/" + t.task + "/" + t.time +"\n");
            }
            writer.close();

        } catch (IOException e) {
            System.out.println("Error: " + e);
        }

    }

    private static final ArrayList<DukeTask> tasklist = new ArrayList<>();
    public static void main(String[] args) {
        read();
        Scanner input = new Scanner(System.in);
        System.out.println("What are your commands sir:");
        boolean pred = true;
        while(pred) {
            if (input.hasNext()) {
                String str = input.nextLine();
                if (str.startsWith("list")) {
                    System.out.println("You requested to view your schedule:");
                    for (int j = 0; j < tasklist.size(); j++) {
                        System.out.println(String.format("List %d: ", j) + tasklist.get(j).toString());
                    }
                } else if (str.startsWith("bye")) {
                    System.out.println("Bye. Hope to see you again");
                    pred = false;

                } else if (str.startsWith("mark")) {
                    try {
                        int j = Integer.valueOf(str.substring(5));
                        tasklist.get(j).isMarked = true;
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println(String.format("List %d: ", j) + tasklist.get(j).toString());
                        save();
                    } catch (Exception e) {
                        System.out.println("Something went wrong, here's the error message cuz im lazy to figure it out for you: " + e);
                    }


                } else if (str.startsWith("unmark")) {
                    int j = Integer.valueOf(str.substring(7));
                    tasklist.get(j).isMarked = false;
                    System.out.println("Got it. I've mark this task as not done:");
                    System.out.println(String.format("List %d: ", j) + tasklist.get(j).toString());
                    save();

                } else if (str.startsWith("todo")) {
                    try {
                        str = str.substring(5);
                        if (str.isBlank()) {
                            System.out.println("Oops, todo can't be empty");
                            continue;
                        }
                        DukeTask t = new DukeTask(str, false, 'T');
                        tasklist.add(t);
                        System.out.println("Got it. I've added this task:");
                        System.out.println(String.format("List %d: ", tasklist.size() - 1) + t.toString());
                        save();
                    } catch (StringIndexOutOfBoundsException e) {
                        System.out.println("Oops, todo can't be empty");
                    } catch (Exception e) {
                        System.out.println("...");
                        System.out.println(e);
                    }


                } else if (str.startsWith("deadline")) {
                    str = str.substring(9);
                    String s1 = str.substring(0, str.indexOf('/') - 1);
                    String s2 = "(" + str.substring(str.indexOf('/') + 1) + ')';
                    DukeTask t = new DukeTask(s1, false, 'D', s2);
                    tasklist.add(t);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(String.format("List %d: ", tasklist.size() - 1) + t.toString());
                    save();

                } else if (str.startsWith("event")) {
                    str = str.substring(6);
                    String s1 = str.substring(0, str.indexOf('/') - 1);
                    String s2 = "(" + str.substring(str.indexOf('/') + 1) + ')';
                    DukeTask t = new DukeTask(s1, false, 'E', s2);
                    tasklist.add(t);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(String.format("List %d: ", tasklist.size() - 1) + t.toString());
                    save();

                } else if (str.startsWith("delete")) {
                    try {
                        int j = Integer.valueOf(str.substring(7));
                        System.out.println("Alight! I've deleted this task for you:");
                        System.out.println(String.format("List %d: ", j) + tasklist.get(j).toString());
                        tasklist.remove(j);
                        save();

                    } catch (Exception e) {
                        System.out.println("Something went wrong, here's the error message cuz im lazy to figure it out for you: " + e);
                    }


                } else {
                    System.out.println("Sorry, I don't know what that means");

                }
            }
        }

    }
}
