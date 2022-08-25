import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.io.*;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

public class Duke {

    private static List<Task> willDo = new ArrayList<>();
    // The main function to handle all commands here
    public static void main(String[] args) {
        run();
    }

    public static void run() {
        String PATH = "./src/main/files/taskset.txt";
        Scanner sc = new Scanner(System.in);
        String greet = "Hello! I'm Lan\n"
                + "What can I do for you?";
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(greet + "\n" + logo);

        try {
            extractFileTo(PATH);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        String str = sc.nextLine();

        // To get the Command then reply
        while (true) {
            try {
                if (str.startsWith("todo")) {
                    todo(str);
                } else if (str.startsWith("deadline")) {
                    deadline(str);
                } else if (str.startsWith("event")) {
                    event(str);
                } else if (str.startsWith("mark")) {
                    String[] temp = str.split(" ");
                    int key = Integer.decode(temp[1]);
                    mark(key);
                } else if (str.startsWith("unmark")) {
                    String[] temp = str.split(" ");
                    int key = Integer.decode(temp[1]);
                    unmark(key);
                } else if (str.startsWith("list")) {
                    showList();
                } else if (str.startsWith("bye")) {
                    System.out.println("Bye! Hope to see you again soon!");
                    break;
                } else if (str.startsWith("delete")) {
                    delete(str);
                } else {
                    throw new CannotUnderstandException();
                }
            // Handling exceptions
            } catch (WrongMessageException | CannotUnderstandException e) {
                System.out.println(e.getMessage());
            }
            // For programming continue
            str = sc.nextLine();
        }

        try {
            saveFile(PATH);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void extractFileTo(String path) throws WrongMessageException {
        File file = new File(path);
        int index = 1;
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else {
            try {
                Scanner reader = new Scanner(file);
                while (reader.hasNextLine()) {
                    String temp = reader.nextLine();
                    if (temp.equals("")) continue;
                    String[] series = temp.split("\\|");
                    String type = series[0].trim();
                    String info = series[2].trim();
                    String status = series[1].trim();
                    if (type.equals("T")) {
                        String willGive = "todo " + info;
                        todo(willGive);
                        if (status.equals("Y")) {
                            mark(index);
                        }
                        index++;
                    } else if (type.equals("E")) {
                        String time = series[3];
                        String willGive = "event " + info + " /at " + time;
                        event(willGive);
                        if (status.equals("Y")) {
                            mark(index);
                        }
                        index++;
                    } else if (type.equals("D")) {
                        String time = series[3];
                        String willGive = "deadline " + info + " /by " + time;
                        deadline(willGive);
                        if (status.equals("Y")) {
                            mark(index);
                        }
                        index++;
                    } else {
                        throw new WrongMessageException();
                    }
                }
            } catch (WrongMessageException | IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void saveFile(String path) throws IOException {
        //File target = new File(path);
        FileWriter fw = new FileWriter(path);
        for (Task task : willDo) {
            String temp = task.writeToFile();
            fw.write(temp + "\n");
        }
        fw.close();
    }

    public static void todo(String str) throws WrongMessageException{
        String content = str.substring(4).trim();
        if (content.equals("")) {
            throw new WrongMessageException();
        }
        Task todo = new Todo(content);
        willDo.add(todo);
        int size = willDo.size();
        System.out.println("Got it, I've added it to the task list:\n"
                + todo.toString() + "\n"
                + "Now you have " + (size) + " tasks");
    }

    public static void deadline(String str) throws WrongMessageException {
        String ddlstr = str.substring(8).trim();
        if (ddlstr.equals("")) {
            throw new WrongMessageException();
        }
        String[] temp = ddlstr.split("/by");
        String ddlinfo = temp[0];
        String date = temp[1];
        Task deadline = new Deadline(ddlinfo, date);
        willDo.add(deadline);
        int nowsize = willDo.size();
        System.out.println("Got it, I've added it to the task list:\n"
                + deadline.toString() + "\n"
                + "Now you have " + (nowsize) + " tasks");
    }

    public static void event(String str) throws WrongMessageException {
        String eventstr = str.substring(5).trim();
        if (eventstr.equals("")) {
            throw new WrongMessageException();
        }
        String[] temp = eventstr.split("/at");
        String eventinfo = temp[0];
        String takeplace = temp[1];
        Task event = new Event(eventinfo, takeplace);
        willDo.add(event);
        int finalsize = willDo.size();
        System.out.println("Got it, I've added it to the task list:\n"
                + event.toString() + "\n"
                + "Now you have " + (finalsize) + " tasks");
    }

    public static void mark(int target) {
        Task willMark = willDo.get(target - 1);
        willMark.donelah();
        System.out.println("Congratulations! you complete this task:\n"
                + willMark.toString());
    }

    public static void unmark(int target) {
        Task willUnmark = willDo.get(target - 1);
        willUnmark.nodone();
        System.out.println("You undone this task:\n"
                + willUnmark.toString());
    }

    public static void showList() {
        System.out.println("Your list is as following");
        for (int i = 0; i < willDo.size(); i++) {
            Task temp = willDo.get(i);
            System.out.println((i + 1) + "." + temp.toString());
        }
    }

    public static void delete(String str) {
        String[] temp = str.split(" ");
        int key3 = Integer.decode(temp[1]);
        System.out.println("ok I will delete the task" + willDo.get(key3 - 1) + "it right now!");
        willDo.remove(key3 - 1);
        System.out.println("now you have " + willDo.size() + " tasks in the list");
    }

    public static void getOnDate(LocalDate localDate) {
        List<Task> shortList = willDo.stream().filter(task -> task.isOnDate(localDate))
                .collect(Collectors.toList());
        int i = 0;
        System.out.println("Hey, these are what you need to do on this date: "
                + localDate.format(DateTimeFormatter.ofPattern("MMMM d yyyy")));
        for (Task t : shortList) {
            System.out.println((i + 1) + "." + t);
            i++;
        }
    }

    public static void checkArray(Object[] o) {
        for (Object i : o) {
            System.out.println(i);
        }
    }
}