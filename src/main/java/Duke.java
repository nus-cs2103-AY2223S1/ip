import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static final String line = "____________________________________________________________";
    public static Scanner sc = new Scanner(System.in);
    public static ArrayList<Task> list = updateFile(new File("duke.txt"));
    public static int count = 0;

    public Duke() {};

    public void greet() {
        System.out.println(
                line + "\n" +
                        "Hello! I'm Duke" + "\n" +
                        "What can I do for you?" + "\n" +
                        line + "\n"
        );
    }

    public void loadTask() {
        try {
            File file = new File("duke.txt");
            if (file.createNewFile()) {
                System.out.println("File successfully created: " + file.getName());
                readFile(file);
            } else {
                System.out.println("Loading tasks...");
                readFile(file);
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }


    public static ArrayList<Task> readFile(File file) {

        ArrayList<Task> t = new ArrayList<Task>();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        String st;

        while (true)

            // Print the string
        {
            try {
                if (!((st = br.readLine()) != null)) {

                    break;

                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            t.add(stringToTask(st));
            count++;
            System.out.println(st);
        }
        return t;
    }

    public static ArrayList<Task> updateFile(File file) {

        ArrayList<Task> t = new ArrayList<Task>();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        String st;

        while (true)

        // Print the string
        {
            try {
                if (!((st = br.readLine()) != null)) {

                    break;

                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            t.add(stringToTask(st));
        }
        return t;
    }

    public static void renewFile(File file) {
        try {
            FileWriter fw = new FileWriter(file);
            fw.write("");
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void addTaskToFile(File file, Task t) {
        try {
            FileWriter fw = new FileWriter(file, true);
            fw.write(t.toString());
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void overwriteFile(File f, List<Task> t) {
        try {
            FileWriter fw = new FileWriter(f);
            fw.close();
            fw = new FileWriter(f,true);
            for (Task task : t) {
                fw.write(task.toString());
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Task stringToTask(String s) {
        if (s.length() == 0) {
            return null;
        }
        char task_type = s.charAt(1);
        char done = s.charAt(4);
        Task task = null;
        if (task_type == 'T') {
            task = new Todo(s.substring(6));
        } else if (task_type == 'E') {
            int indexofdate = s.indexOf('(');
            int indexofdates = s.indexOf(')');
            String name = s.substring(6,indexofdate);
            String date = s.substring(indexofdate + 5, indexofdates);
            task = new Event(name,parseFileString(date));
        } else if (task_type == 'D') {
            int indexofdate = s.indexOf('(');
            int indexofdates = s.indexOf(')');
            String name = s.substring(6,indexofdate);
            String date = s.substring(indexofdate + 5, indexofdates);
            task = new Deadline(name,parseFileString(date));
        }
        if (done == 'X') {
            task.setStatus("[X]");
        } return task;
    }


    public static void respond()  {
        try {
            String input = sc.nextLine();

            String[] arr = input.split(" ", 2);
            String command = arr[0];
            File file = new File("duke.txt");
            if (command.equals("mark") || command.equals("unmark")) {
                if (arr.length == 1) {
                    throw new MarkException(command);
                }
                int index = Integer.parseInt(arr[1]);
                Task b = list.get(index-1);
                if (command.equals("mark")) {
                    b.mark(b, index);
                } else if (command.equals("unmark")) {

                    b.unmark(b, index);
                }

                overwriteFile(file, list);
                respond();

            }

            else if (command.equals("delete")) {
                if (arr.length == 1) {
                    throw new MarkException(command);
                }
                Task b = list.get(Integer.parseInt(arr[1]) - 1);
                count--;
                b.delete(b,Integer.parseInt(arr[1]) - 1, list);
                overwriteFile(file,list);
                respond();
            }

            else if (command.equals("todo") || command.equals("deadline") || command.equals("event")) {
                if (arr.length == 1) {
                    throw new EmptyCommandException(command);
                }


                if (command.equals("todo")) {

                    Todo todo = new Todo(arr[1]);
                    list.add(todo);
                    list.get(count++).print();
                    addTaskToFile(file,todo);
                    respond();
                }
                else if (command.equals("event")) {
                    String[] deets = arr[1].split("/at ", 2);
                    Event e = new Event(deets[0], parseString(deets[1]));
                    list.add(e);
                    list.get(count++).print();
                    addTaskToFile(file,e);
                    respond();
                }
                else if (command.equals("deadline")) {
                    String[] deets = arr[1].split("/by ", 2);
                    Deadline d = new Deadline(deets[0], parseString(deets[1]));
                    list.add(d);
                    list.get(count++).print();
                    addTaskToFile(file,d);
                    respond();
                }
            }

            else if (input.equals(("bye"))) {
                bye();
            }

            else if (input.equals("list")) {
                list();
                respond();
            }

            else {
                throw new InvalidCommandException(command);
            }

        }
        catch (EmptyCommandException e){
            System.out.println(e.getMessage());
            respond();
        }

        catch (InvalidCommandException e) {
            System.out.println(e.getMessage());
            respond();
        }

        catch (MarkException e) {
            System.out.println(e.getMessage());
            respond();
        }

    }

    public static void list() {
        System.out.println(
                line + "\n" +
                        "Here are the tasks in your list:");

        for (int i = 0, j = 1; i < count; i++, j++) {

            System.out.print(j + ". ");
            list.get(i).list();
        }
        System.out.println(
                line + "\n"
        );

    }

    private static LocalDateTime parseString(String s) {
        DateTimeFormatter formatter = null;
        LocalDateTime date = null;
        try {
            formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            date = LocalDateTime.parse(s,formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Please use time in dd/MM/yyyy HH:mm format");
            respond();
        }
        return date;
    }

    private static LocalDateTime parseFileString(String s) {
        DateTimeFormatter formatter = null;
        LocalDateTime date = null;
        try {
        formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm a");
        date = LocalDateTime.parse(s,formatter);
        } catch (DateTimeParseException e) {
        System.out.println("Please use time in dd/MM/yyyy HH:mm format");
        respond();
        }
        return date;
    }

    public static void bye() {
        System.out.println(
                line + "\n" +
                        "Bye. Hope to see you again soon!" + "\n" + line
        );

    }

    public static void main (String[] args) {
        Duke duke = new Duke();
        duke.greet();
       duke.loadTask();
       duke.respond();
    }

}
