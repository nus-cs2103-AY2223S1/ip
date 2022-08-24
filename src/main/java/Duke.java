import java.io.*;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import java.util.Scanner;

public class Duke {
    private static Path fileName;
    private static ArrayList<Task> al;
    private static BufferedReader br;
    private static File file;
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        greeting();
        try {
            fileName = Path.of("D:\\SouceTree Projects\\CS2103T_Ip\\storage.txt");
            file = fileName.toFile();
            System.out.println(file.getPath());
            if (file.exists()) {
                load();
                storeman();
            } else {
                if (file.createNewFile()) {
                    al = new ArrayList<>();
                    storeman();
                } else {
                    throw new IOException("File was not created!");
                }
            }
            save(al);
        } catch (DukeException de) {
            System.out.println("--------------------------------------\n");
            System.out.println(de.getMessage());
            System.out.println("--------------------------------------\n");
            main(args);
        }  catch(IOException io) {
            System.out.println("--------------------------------------\n");
            System.out.println(io.getMessage());
            System.out.println("--------------------------------------\n");
        }
    }

    public static void greeting() {
        System.out.println("--------------------------------------\n");
        System.out.println("\tHello I'm Duke, what can I do for you?");
        System.out.println("--------------------------------------\n");
    }

    public static void echo() throws IOException {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        String uncap = str.toLowerCase();
        while (!uncap.equals("bye")) {
            System.out.println("------------------------------\n");
            System.out.printf("\t  %s  \n", str);
            System.out.println("------------------------------\n");
            str = sc.nextLine();
            uncap = str.toLowerCase();
        }
        bye();
        sc.close();
    }

    public static ArrayList<Task> storeman() throws DukeException, IOException{
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        String uncap = str.toLowerCase();
        while (!uncap.equals("bye")) {
            if (uncap.startsWith("delete")) {
                Integer i = Integer.parseInt(String.valueOf(str.charAt(7)));
                Task del = delete(al, i);
                System.out.println("------------------------------\n");
                System.out.println("\tNoted. I've removed this task:");
                System.out.println("\t\t" + del.toString());
                System.out.println("\tNow you have " + al.size() + " task(s) in the list.");
                System.out.println("------------------------------\n");
            } else {
                if (!uncap.equals("list")) {
                    if (!uncap.startsWith("mark") && !uncap.startsWith("unmark")) {
                        Task t = null;
                        if (uncap.startsWith("deadline")
                                || uncap.startsWith("event")) {
                            if (!uncap.contains("/")) throw new DukeException(
                                    "☹ OOPS!!! Associated time for event or deadline cannot be empty.");
                            if (uncap.startsWith("deadline")) {
                                int idOfSlash = str.indexOf('/');
                                if (idOfSlash - 9 == 0) throw new DukeException("" +
                                        "☹ OOPS!!! The description of a deadline cannot be empty.");
                                if (str.length() < idOfSlash + 4) throw new DukeException(
                                        "☹ OOPS!!! The dude date of a deadline cannot be empty.");
                                t = new Deadline(str.substring(9, idOfSlash), str.substring(idOfSlash + 4));
                            } else if (uncap.startsWith("event")) {
                                int idOfSlash = str.indexOf('/');
                                if (idOfSlash - 6 == 0) throw new DukeException("" +
                                        "☹ OOPS!!! The description of an event cannot be empty.");
                                if (str.length() < idOfSlash + 4) throw new DukeException(
                                        "☹ OOPS!!! The duration of event cannot be empty.");
                                t = new Event(str.substring(6, idOfSlash), str.substring(idOfSlash + 4));
                            }
                        } else if (uncap.startsWith("todo")) {
                            if (str.length() < 6) throw new DukeException("" +
                                    "☹ OOPS!!! The description of a todo cannot be empty.");
                            t = new Todo(str.substring(5));
                        } else {
                            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                        }
                        al.add(t);
                        System.out.println("------------------------------\n");
                        System.out.printf("\tGot it. I've added this task: \n\t\t%s\n", t.toString());
                        System.out.println("\tNow you have " + al.size() + " task(s) in the list.");
                        System.out.println("------------------------------\n");
                    } else {
                        if (uncap.startsWith("unmark")) {
                            int i = Integer.parseInt(String.valueOf(uncap.charAt(7)));
                            Task call = al.get(i - 1);
                            call.markAsUndone();
                            System.out.println("-------------------------------\n");
                            System.out.println("\tOK, I've marked this task as not done yet: ");
                            System.out.println("\t\t" + call.toString());
                            System.out.println("-------------------------------\n");
                        } else if (uncap.startsWith("mark")) {
                            int i = Integer.parseInt(String.valueOf(uncap.charAt(5)));
                            Task call = al.get(i - 1);
                            call.markAsDone();
                            System.out.println("-------------------------------\n");
                            System.out.println("\tNice! I have marked this task as done: ");
                            System.out.println("\t\t" + call.toString());
                            System.out.println("-------------------------------\n");
                        }
                    }
                } else {
                    listOut(al);
                }
            }
            str = sc.nextLine();
            uncap = str.toLowerCase();
        }

        sc.close();
        bye();
        return al;
    }

    private static ArrayList<Task> listOut(ArrayList<Task> al) {
        int count = 1;
        System.out.println("-------------------------------\n");
        System.out.println("\tHere are the tasks in your list: ");
        for (Iterator<Task> it = al.iterator(); it.hasNext(); ) {
            Task curr = it.next();
            System.out.println("\t\t" +count + ". " + curr.toString());
            count++;
        }
        System.out.println("-------------------------------\n");
        count = 1;
        return al;
    }
    public static Task delete(ArrayList<Task> at, Integer id) {
        int actual = id - 1;
        return at.remove(actual);
    }

    public static void load() throws IOException {
        al = new ArrayList<>();
        br = new BufferedReader(new FileReader(fileName.toFile().getPath()));
        String ln = br.readLine();
        while(ln != null) {
            add(ln);
            ln = br.readLine();
        }
    }

    public static void add(String ln) {
        String tag = String.valueOf(ln.charAt(1));
        String desc = null;
        int att = -1;
        boolean done = false;
        Task toAdd = null;
        if (String.valueOf(ln.charAt(4)).equals("X")) {
            done = true;
        }
        if (tag.equals("T")) {
            toAdd = new Todo(ln.substring(7));
        } else if (tag.equals("D")) {
            att = ln.indexOf("(by:");
            toAdd = new Deadline(ln.substring(7,att - 1), ln.substring(att + 5 , ln.length() - 1));
        } else if (tag.equals("E")) {
            att = ln.indexOf("(at:");
            toAdd = new Event(ln.substring(7, att - 1), ln.substring(att + 5, ln.length() -1));
        } else {
            toAdd = new Task(ln.substring(6));
        }
        if (done) {
            toAdd.markAsDone();
        }
        al.add(toAdd);
    }

    public static void save(ArrayList<Task> lst) throws IOException {
        FileWriter fw = new FileWriter(file.getAbsolutePath());
        System.out.println(file.getAbsolutePath());
        for (Iterator<Task> it = al.iterator(); it.hasNext();) {
            Task curr = it.next();
            fw.write(curr.toString() + "\r\n");
        }
        fw.close();
    }
    public static void bye() {
        System.out.println("------------------------------\n");
        System.out.println("\tBye. Hope to see you soon again!");
        System.out.println("------------------------------\n");
    }
}
