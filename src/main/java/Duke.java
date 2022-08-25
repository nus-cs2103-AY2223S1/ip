import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;

public class Duke {
    private static List<Task> taskList = new ArrayList<>(100);

    public static void main(String[] args) {
        try {
            File f = new File("./data/duke.txt");
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                taskList.add(Task.fileLineToTask(s.nextLine()));
            }
            s.close();
        } catch (FileNotFoundException e) {
            try {
                Files.createDirectories(Paths.get("./data"));
                File f = new File("./data/duke.txt");
            } catch (IOException ex) {
                System.out.println("Something went wrong: " + e.getMessage());
            }
        }

        Scanner sc = new Scanner(System.in);
        System.out.println(Duke.start());
        while (sc.hasNext()) {
            String input = sc.nextLine();
            if (input.equals("list")) {
                System.out.println(Duke.list());
            } else if (input.equals("bye")) {
                System.out.println(Duke.bye());
                sc.close();
                break;
            } else if (input.startsWith("mark") && input.length() >= 6) {
                System.out.print(Duke.mark(input));
            } else if (input.startsWith("unmark") && input.length() >= 8) {
                System.out.print(Duke.unmark(input));
            } else if (input.startsWith("delete")) {
                System.out.println(Duke.deleteTask(input));
            } else {
                System.out.println(Duke.addTask(input));
            }
        }
    }

    private static String list() {
        String[] lines = new String[taskList.size() + 1];
        lines[0] = "Here are the tasks in your list: ";
        for (int i = 1; i < taskList.size() + 1; i++) {
            lines[i] = taskList.get(i - 1).toStringWithIndex(i);
        }
        return Duke.replyFormat(lines);
    }

    private static String bye() {
        try {
            FileWriter fw = new FileWriter("./data/duke.txt");
            for (Task task : taskList) {
                fw.write(task.getSaveFormat() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
        return Duke.replyFormat("Bye.", ">:)");
    }

    private static String start() {
        return Duke.replyFormat("Oi! I'm Dook", "What's up?", "Please type dates in the format: yyyy-mm-dd");
    }

    private static String mark(String input) {
        int toMark = Character.getNumericValue(input.charAt(5));
        Task task = taskList.get(toMark - 1);
        task.mark();
        return Duke.replyFormat("Nice! I've marked this task as done:",
                "  " + task.toString());
    }

    private static String unmark(String input) {
        int toMark = Character.getNumericValue(input.charAt(7));
        Task task = taskList.get(toMark - 1);
        task.unmark();
        return Duke.replyFormat("OK, I've marked this task as not done yet:",
                "  " + task.toString());
    }

    private static String addTask(String input) throws DukeException {
        Task task;
        try {
            task = Task.createTask(input);
        } catch (DukeException e) {
            return Duke.replyFormat(e.toString());
        }
        taskList.add(task);
        return Duke.replyFormat("Got it. I've added this task:",
                "   " + task.toString(),
                String.format("Now you have %d tasks in the list.", taskList.size()));
    }

    public static String deleteTask(String input) {
        int toDelete = Character.getNumericValue(input.charAt(7)) - 1;
        Task task = taskList.get(toDelete);
        taskList.remove(toDelete);
        return Duke.replyFormat("Noted. I've removed this task:",
                "  " + task.toString(),
                String.format("Now you have %d tasks in the list.", taskList.size()));
    }

    public static String replyFormat(String... lines) {
        String separator = "     ____________________________________________________________\n";
        String indent = "     ";
        StringBuilder res = new StringBuilder(separator);
        for (String line : lines) {
            res.append(indent).append(line).append("\n");
        }
        res.append(separator);
        return res.toString();
    }

}
