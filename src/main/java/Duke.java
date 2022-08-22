import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Duke {
    public static void main(String[] args) throws DukeException, IOException {
        System.out.println("Hello! I'm Edric\nWhat can I do for you?");

        String filePath = "data/duke.txt";
        List<Task> db = new ArrayList<>();
        try {
            readFileContents(filePath, db);
        } catch (FileNotFoundException e) {
            System.out.println("Data file not found!");
        }
        Scanner sc = new Scanner(System.in);
        // while loop keeps grabbing input from user, unless user inputs bye to break the loop
        while (true) {
            String input = sc.nextLine();
            // bye keyword: exit
            if (input.equals("bye")) {
                break;
            }
            String[] words = input.split(" ");
            // list keyword: prints list of stored text
            if (input.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i=0; i < db.size(); i++) {
                    Task curr = db.get(i);
                    System.out.format("%d. %s\n", i + 1, curr.toString());
                }
            }
            // mark keyword: checks the box of an item in the list
            else if (words[0].equals("mark")) {
                String numString = words[1];
                int idx = Integer.parseInt(numString) - 1;
                Task curr = db.get(idx);
                curr.mark();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("\t" + curr.toString());
            }
            // unmark keyword: unchecks the box of an item in the list
            else if (words[0].equals("unmark")) {
                String numString = words[1];
                int idx = Integer.parseInt(numString) - 1;
                Task curr = db.get(idx);
                curr.unmark();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("\t" + curr.toString());
            }
            // to do keyword: create new to do item
            else if (words[0].equals("todo")) {
                if (words.length == 1) {
                    System.out.println(new EmptyTodoException());
                } else {
                    String desc = input.split("todo")[1];
                    db.add(new Todo(desc));
                    System.out.println("Got it. I've added this task:");
                    System.out.println("\t " + db.get(db.size() - 1).toString());
                    System.out.println("Now you have " + db.size() + " tasks in the list");
                }
            }
            // deadline keyword: create new deadline item
            else if (words[0].equals("deadline")) {
                String[] dl = input.split("/by");
                String desc = dl[0].split("deadline")[1];
                db.add(new Deadline(desc, dl[1]));
                System.out.println("Got it. I've added this task:");
                System.out.println("\t " + db.get(db.size() - 1).toString());
                System.out.println("Now you have " + db.size() + " tasks in the list");
            }
            // event keyword: create new event item
            else if (words[0].equals("event")) {
                String[] ev = input.split("/at");
                String desc = ev[0].split("event")[1];
                db.add(new Event(desc, ev[1]));
                System.out.println("Got it. I've added this task:");
                System.out.println("\t " + db.get(db.size() - 1).toString());
                System.out.println("Now you have " + db.size() + " tasks in the list");
            }
            // delete keyword: remove task from list
            else if (words[0].equals("delete")) {
                String numString = words[1];
                int idx = Integer.parseInt(numString) - 1;
                System.out.println("Got it. I've removed this task:");
                System.out.println("\t " + db.get(idx).toString());
                db.remove(idx);
                System.out.println("Now you have " + db.size() + " tasks in the list");
            }
            else {
                System.out.println(new UnknownCommandException());
            }
        }

        try {
            updateTasksToFile(filePath, db);
        } catch (IOException e) {
            System.out.println("Error writing to data file!");
        }
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static void readFileContents(String filePath, List<Task> db) throws FileNotFoundException {
        File file = new File(filePath); // create a File for the given file path
        Scanner sc = new Scanner(file); // create a Scanner using the File as the source
        while (sc.hasNext()) {
            String input = sc.nextLine();
            String[] words = input.split(" # ");

            // check description
            String desc = words[2];
            // check task
            String task = words[0];
            if (task.equals("T")) {
                Task todo = new Todo(desc);
                // check marked
                if (words[1].equals("1")) {
                    todo.mark();
                }
                db.add(todo);
            } else if (task.equals("D")) {
                Task deadline = new Deadline(desc, words[3]);
                // check marked
                if (words[1].equals("1")) {
                    deadline.mark();
                }
                db.add(deadline);
            } else if (task.equals("E")) {
                Task event = new Event(desc, words[3]);
                // check marked
                if (words[1].equals("1")) {
                    event.mark();
                }
                db.add(event);
            } else {
                System.out.println("Incorrect File Input!");
            }
        }

    }

    private static void updateTasksToFile(String filePath, List<Task> db) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (int i=0; i < db.size(); i++) {
            Task curr = db.get(i);
            // System.out.println(curr.toDataEntry());
            fw.write(curr.toDataEntry());
        }
        fw.close();
    }

}
