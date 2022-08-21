import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Scanner;
import java.nio.file.Path;

public class Duke {
    enum Command {
        list,
        bye,
        mark,
        unmark,
        delete,
        deadline,
        todo,
        event
    }
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Scanner in = new Scanner(System.in);
        System.out.println("Oi, What u want?");

        File f = new File("./data/duke.txt");
        Path path = f.toPath();
        ArrayList<Task> list = new ArrayList<>();
         if (f.exists()) {
             try {
                 FileReader fr = new FileReader(f);
                 BufferedReader br = new BufferedReader(fr);
                 String line = br.readLine();
                 while (line != null) {
                     String[] task = line.split(",");
                     if (task[0].equals("T")) {
                         list.add(new Todo(task[1].equals("X"), task[2]));
                     }
                     if (task[0].equals("E")) {
                         list.add(new Event(task[1].equals("X"), task[2], task[3]));
                     }
                     if (task[0].equals("D")) {
                         list.add(new Deadline(task[1].equals("X"), task[2], task[3]));
                     }
                     line = br.readLine();
                 }
             } catch (IOException e) {
                 System.out.println(e);
             }
        }


        while (in.hasNext()) {
            String next = in.nextLine();
            String[] command = next.split(" ", 2);
            try {
                Command cmd = Command.valueOf(command[0]);
                int count;
                int index;
                switch (cmd) {
                    case bye:
                        System.out.println("Bye! Don't Come back!");
                        String s = "";
                        for (Task t : list) {
                            s += t.toFile() + "\n";
                        }
                        try {
                            Files.writeString(path, s);
                        } catch (IOException e) {
                            System.out.println(e);
                        }
                        return;
                    case list:
                        for (int i = 0; i < list.size(); i++) {
                            System.out.println(i + 1 + ". " + list.get(i));
                        }
                        break;
                    case mark:
                        index = Integer.parseInt(command[1]) - 1;
                        list.get(index).mark();
                        System.out.println("I have marked this task as done:");
                        System.out.println(list.get(index));
                        break;

                    case event:
                        String[] desc = command[1].split("/at ", 2);
                        try {
                            list.add(new Event(desc[0], desc[1]));
                            count = list.size();
                            System.out.println("Added Task");
                            System.out.println(list.get(count - 1));
                            System.out.println("Now you have " + count + " tasks in the list");
                        } catch (ArrayIndexOutOfBoundsException e) {
                            System.out.println("Invalid Time");
                        }

                        break;
                    case todo:
                        if (command.length < 2) {
                            throw new DukeException("oops the description of a todo cannot be empty!");
                        }
                        list.add(new Todo(command[1]));
                        count = list.size();
                        System.out.println("Added Task");
                        System.out.println(list.get(count - 1));
                        System.out.println("Now you have " + count + " tasks in the list");
                        break;
                    case delete:
                        if (command.length < 2) {
                            throw new DukeException("please specify which item to delete");
                        }
                        index = Integer.parseInt(command[1]) - 1;
                        Task item = list.get(index);
                        list.remove(index);
                        count = list.size();
                        System.out.println("Noted. I have removed this task: \n" + item);
                        System.out.println("You now have " + count + " tasks left in the list");
                        break;
                    case unmark:
                        index = Integer.parseInt(next.split(" ", 2)[1]) - 1;
                        list.get(index).unmark();
                        System.out.println("I have marked this task as not done:");
                        System.out.println(list.get(index));
                        break;
                    case deadline:
                        String[] dl = command[1].split("/by ", 2);
                        try {
                            list.add(new Deadline(dl[0], dl[1]));
                            count = list.size();
                            System.out.println("Added Task");
                            System.out.println(list.get(count - 1));
                            System.out.println("Now you have " + count + " tasks in the list");
                        } catch (ArrayIndexOutOfBoundsException e) {
                            System.out.println("Invalid Time");
                        }
                        break;
                    default:
                        throw new DukeException("oops! I do not know what that means!");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("That is not a valid command");
            }
        }


    }
}



