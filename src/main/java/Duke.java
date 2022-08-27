import java.util.*;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.text.SimpleDateFormat;
public class Duke {
    private static final String FILE_PATH = "C:/Unu_Stuff/Y3S1/CS2103-CS2103T/Lab/Lab 2/src/data/duke.txt";
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        //file creation
        try {
            createFiles();
        } catch (Exception e) {
            System.out.println("Throwing error in file");
        }

        List<Task> arr = new ArrayList<Task>(); //should be list as compile time type
        String input = "";
        int curr = 0;
        Task task = new Task(input, "");

        //file reading
        try {
            File myObj = new File(FILE_PATH);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] fromText = data.split(" \\| "); //special chara
                boolean done = fromText[1].equals("1") ? true : false;
                switch (fromText[0]) {
                    case "T":
                        task = new ToDo(fromText[2], done, "");
                        arr.add(curr++, task);
                        break;
                    case "E":
                        task = new Event(fromText[2], done, dateReader(fromText[3]));
                        arr.add(curr++, task);
                        break;
                    case "D":
                        task = new Deadline(fromText[2], done, dateReader(fromText[3]));
                        arr.add(curr++, task);
                        break;
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        try {
            PrintWriter writer = new PrintWriter(new File(FILE_PATH));
            writer.print("");
            writer.close();
        } catch (IOException e) {
            System.out.println("Nth to read form duke.txt");
        }

        //main body
        arr.add(curr, task);

        String command = sc.nextLine();
        while (!command.split(" ")[0].equals("bye")) {
            switch (command.split(" ")[0]) {
                case "list":
                    for (int i = 0; i < curr; i++) {
                        System.out.println(arr.get(i));
                    }
                    break;

                case "mark":
                    String[] atMark = command.split(" ");
                    try {
                        int index = Integer.parseInt(atMark[1]);
                        arr.set(index - 1, arr.get(index - 1).markDone());
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println(String.format("%s", arr.get(index - 1)));
                    } catch (Exception e) {
                        System.out.println("☹ OOPS!!! The description of a mark cannot be empty.");
                    }
                    break;

                case "unmark":
                    String[] atUnmark = command.split(" ");
                    try {
                        int index = Integer.parseInt(atUnmark[1]);
                        arr.set(index - 1, arr.get(index - 1).markUndone());
                        System.out.println("OK, I've marked this task as not done yet:");
                        System.out.println(String.format("%s", arr.get(index - 1)));
                    } catch (Exception e) {
                        System.out.println("☹ OOPS!!! The description of a unmark cannot be empty.");
                    }
                    break;

                case "delete":
                    String[] atDel = command.split(" ");
                    try {
                        int index = Integer.parseInt(atDel[1]);
                        index--;
                        Task del = arr.get(index);
                        for (int i = index; i < curr; i++) {
                            arr.set(i, arr.get(i + 1));
                        }
                        curr--;
                        System.out.println("Noted. I've removed this task:");
                        System.out.println(del);
                        System.out.println(String.format("Now you have %s tasks in the list.", curr));

                    } catch (Exception e) {
                        System.out.println("☹ OOPS!!! The description of a delete cannot be empty.");
                    }
                    break;

                case "todo":
                    try {
                        String todo = command.substring(5);
                        task = new ToDo(todo);
                        arr.add(curr++, task);
                        System.out.println("Got it. I've added this task:");
                        System.out.println(task);
                        System.out.println(String.format("Now you have %s tasks in the list.", curr));
                    } catch (Exception e) {
                        System.out.println("☹ OOPS!!! The description of a undo cannot be empty.");
                    }
                    break;

                case "deadline":
                    System.out.println(command);
                    try {
                        String deadline = command.substring(9);
                        String[] atDead = deadline.split(" /by ");
                        System.out.println(Arrays.toString(atDead));
                        String[] timeDead = atDead[1].split("/");
                        timeDead[0] = String.format("%02d", Integer.parseInt(timeDead[0]));
                        timeDead[1] = String.format("%02d", Integer.parseInt(timeDead[1]));
                        String dateDead = String.join("/", timeDead);
                        task = new Deadline(atDead[0], dateDead);
                        arr.add(curr++, task);
                        System.out.println("Got it. I've added this task:");
                        System.out.println(task);
                        System.out.println(String.format("Now you have %s tasks in the list.", curr));
                    } catch (Exception e) {
                        System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
                    }
                    break;

                case "event":
                    try {
                        String event = command.substring(6);
                        String[] atEvent = event.split(" /by ");
                        String[] timeEvent = atEvent[1].split("/");
                        timeEvent[0] = String.format("%02d", Integer.parseInt(timeEvent[0]));
                        timeEvent[1] = String.format("%02d", Integer.parseInt(timeEvent[1]));
                        String dateEvent = String.join("/", timeEvent);
                        task = new Event(atEvent[0], dateEvent);
                        arr.add(curr++, task);
                        System.out.println("Got it. I've added this task:");
                        System.out.println(task);
                        System.out.println(String.format("Now you have %s tasks in the list.", curr));
                    } catch (Exception e) {
                        System.out.println("☹ OOPS!!! The description of a event cannot be empty.");
                    }
                    break;

                case "things":
                    String[] at = command.split(" /on ");
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
                    String[] time = at[1].split("/");
                    time[0] = String.format("%02d", Integer.parseInt(time[0]));
                    time[1] = String.format("%02d", Integer.parseInt(time[1]));
                    String date = String.join("/", time);
                    LocalDateTime dateTime = LocalDateTime.parse(date + " 0000", formatter);
                    task = new Task("", "");//to not go into bye block, change to switch
                    for (int i = 0; i < curr; i++) {
                        if (arr.get(i).sameDay(dateTime)) {
                            System.out.println(arr.get(i));
                        }
                    }
                    break;

                default:
                    System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    break;
            }
            command = sc.nextLine();
        }

        //bye block
        try {
            PrintWriter writer = new PrintWriter(new File(FILE_PATH));
            for (int i = 0; i < curr; i++) {
                writer.println(arr.get(i).toText());
            }
            writer.close();
        } catch (Exception e) {
            System.out.println("File not found error");
        }
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void createFiles() throws IOException {
        String[] arr = FILE_PATH.split("/");
        java.nio.file.Path path = java.nio.file.Paths.get("");
        for (int i = 0; i < arr.length - 1; i++) {
            path = java.nio.file.Paths.get(String.valueOf(path),arr[i]);
            boolean directoryExists = java.nio.file.Files.exists(path);
            if(!directoryExists) {
                new File(String.valueOf(path)).mkdirs();
                System.out.println("hi, made new directory");
            }
        }
        java.nio.file.Path filePath = java.nio.file.Paths.get(FILE_PATH);
        boolean directoryExists = java.nio.file.Files.exists(filePath);
        if(!directoryExists) {
            filePath.toFile().createNewFile();
        }
    }

    public static String dateReader(String str) {
        Map<String, Integer> myMap = new HashMap<String, Integer>();
        myMap.put("JANUARY", 1);
        myMap.put("FEBRUARY", 2);
        myMap.put("MARCH", 3);
        myMap.put("APRIL", 4);
        myMap.put("MAY", 5);
        myMap.put("JUNE", 6);
        myMap.put("JULY", 7);
        myMap.put("AUGUST", 8);
        myMap.put("SEPTEMBER", 9);
        myMap.put("OCTOBER", 10);
        myMap.put("NOVEMBER", 11);
        myMap.put("DECEMBER", 12);

        String[] arr = str.split(" ");
        arr[0] = String.format("%02d", Integer.parseInt(arr[0]));
        arr[1] = String.format("%02d", myMap.get(arr[1]));
        arr[3] = arr[3].substring(0, 2) + arr[3].substring(3);
        return String.format("%s/%s/%s %s", arr[0], arr[1], arr[2], arr[3]);
    }
}
