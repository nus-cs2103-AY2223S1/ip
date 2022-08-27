import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Duke {

    private static final String GREETINGS = "Hello! I'm Ekud \n" + "What can I do for you?";
    //private static final String FOLDER_LOCATION = "C:\\Users\\silas\\Documents\\GitHub\\ip\\data";
    //private static final String FILE_LOCATION = "C:\\Users\\silas\\Documents\\GitHub\\ip\\data\\duke.txt";
    private static final String FOLDER_LOCATION = "C:\\Users\\Yeo\\Downloads\\drive-download-20210330T061318Z-001\\ip\\data";
    private static final String FILE_LOCATION = "C:\\Users\\Yeo\\Downloads\\drive-download-20210330T061318Z-001\\ip\\data\\duke.txt";
    private static final String BANNER = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
    private static ArrayList<Task> tasks = new ArrayList<>();

    private static void print(String msg) {
        System.out.println(BANNER);
        System.out.println(msg);
        System.out.println(BANNER);
    }

    private static Task createTask(char tag, String description, String time) {
        switch (tag) {
            case 'T':
                return new Todo(description);
            case 'E':
                return new Event(description, time);
            case 'D':
                return new Deadline(description, time);
            default:
                return null;
        } 
    }

    private static void save() throws Exception {
        String res = "";
        for (Task task : tasks) {
            res += task;
            res += "\n";
        }

        Files.createDirectories(Paths.get(FOLDER_LOCATION));
        Files.write(Paths.get(FILE_LOCATION), res.getBytes(StandardCharsets.UTF_8));
    }

    private static void load() throws Exception {
        try {

            List<String> res = Files.readAllLines(Paths.get(FILE_LOCATION));

            for (String line: res) {

                char tag = line.charAt(1);
                boolean isDone = (line.charAt(4) == 'X' ? true : false);
                String msg[] = line.split(" ", 3)[2].split(" \\(");
                String name = msg[0];
                String date = "";

                if (msg.length > 1) {
                    date = msg[1].split("\\)")[0];
                }

                Task task = createTask(tag, name, date);

                if (isDone) {
                    task.mark();
                }

                tasks.add(task);

            }
            
        } catch (Exception e) {
            throw(new DukeException("No saved data found!"));
        }
    }

    private static String getTaskName(String[] msg) {
        String input = "";
        for (int i = 1; i < msg.length; i++) {
            input += msg[i];
            if (i < msg.length - 1) input += " ";
        }
        return input;
    }

    private static void printAddTask(String msg) {
        print("Got it. I've added this task:\n" + msg + "\nNow you have " + tasks.size() +  " tasks in the list.");
    }

    public static void processInput(String input) throws Exception {

        if (input.equals("bye")) {

            print("Cya!");

        } else if (input.equals("list")) {

            String list = "";
            for (int i = 0; i < tasks.size(); i++) {
                list += (i + 1) + "." + tasks.get(i);
                if (i != tasks.size() - 1) list += "\n";
            }
            print(list);

        } else if (input.startsWith("mark")) {

            String[] msg = input.split(" ");
            if (msg.length < 2) throw(new DukeException("nothing to mark!"));
            int index = Integer.valueOf(msg[1]) - 1;
            tasks.get(index).mark();
            print("I've marked this task as done: \n" + tasks.get(index));

        } else if (input.startsWith("unmark")) {

            String[] msg = input.split(" ");
            if (msg.length < 2) throw(new DukeException("nothing to unmark!"));

            int index = Integer.valueOf(msg[1]) - 1;
            tasks.get(index).unmark();
            print("I've marked this task as undone: \n" + tasks.get(index));

        } else if (input.startsWith("delete")) {
            String[] msg = input.split(" ");
            if (msg.length < 2) throw(new DukeException("nothing to delete!"));
            int index = Integer.valueOf(msg[1]) - 1;
            print("I've deleted this task: \n" + tasks.get(index));
            tasks.remove(index);

        } else if (input.startsWith("todo")) {

            String[] msg = input.split(" ");
            if (msg.length < 2) throw(new DukeException("nothing to add!"));

            input = getTaskName(msg);
            tasks.add(createTask('T', input, null));
            printAddTask(input);

        } else if (input.startsWith("deadline")) {

            String[] msg = input.split("/");
            if (msg.length < 2) throw(new DukeException("no date specified!"));

            String[] tmp = msg[0].split(" ");
            if (msg.length < 2) throw(new DukeException("nothing to add!"));

            input = getTaskName(tmp);
            tasks.add(createTask('D', input, msg[1]));
            printAddTask(input);

        } else if (input.startsWith("event")) {

            String[] msg = input.split("/");
            if (msg.length < 2) throw(new DukeException("no date specified!"));

            String[] tmp = msg[0].split(" ");
            if (msg.length < 2) throw(new DukeException("nothing to add!"));
            input = getTaskName(tmp);
            tasks.add(createTask('E', input, msg[1]));
            printAddTask(input);

        } else {
            throw(new DukeException("I do not understand!"));
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        print(GREETINGS);

        try {
            load();
        } catch (Exception e) {
            print(e.getMessage());
        }

        while(true) {
            String input = sc.nextLine();
            try {
                processInput(input);
                save();
            } catch (Exception e){
                print(e.getMessage());
            }
        }
    }
}
