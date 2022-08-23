import java.io.*;
import java.nio.file.Paths;
import java.sql.SQLOutput;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;


public class Duke {
    private static ArrayList<Task> listOfTask = new ArrayList<>();
    private static String DIRECTORY = "./DATA";
    private static String FILENAME = "duke.txt";
    private static String PATHNAME = String.valueOf(Paths.get(DIRECTORY, FILENAME));

     static void showList(){
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= listOfTask.size(); i++) {
            System.out.println(i + "." + listOfTask.get(i - 1).toString());
        }
    }

    static void addList(Task task) {
         try {
             writeToFile(task.getName(), task.getStatusIcon(), task.toString());
             System.out.println("Got it. I've added this task:");
             listOfTask.add(task);
             System.out.println(task.toString());
             if (listOfTask.size() == 1) {
                 System.out.println("Now you have 1 task in the list.");
             } else {
                 System.out.println("Now you have " + listOfTask.size() + " tasks in the list.");
             }
         } catch (IOException e) {
             System.out.println("Something went wrong: " + e.getMessage());
         }
    }

    static void markHelper(String s) {
        int i = Integer.parseInt(s.substring(5, 6)) - 1;
        Task task =  listOfTask.get(i);
        task.mark();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task.toString());
    }

    static void unmarkHelper(String s) {
        int i = Integer.parseInt(s.substring(7, 8)) - 1;
        Task task = listOfTask.get(i);
        task.unmark();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task.toString());
    }

    static void delete(String s) {
         int i = Integer.parseInt(s.substring(7,8)) - 1;
         Task task = listOfTask.remove(i);
        System.out.println("Noted. I've removed this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + listOfTask.size() + " tasks in the list.");
    }

    static void getData() {
         try {
             File f = new File(PATHNAME);
             Scanner s = new Scanner(f);
             while (s.hasNext()) {
                  String string = s.nextLine();
                 int index = string.indexOf("(");
                 //listOfTask.add(new Task(string));
                if (string.substring(1,2).equals("D")){
                     listOfTask.add(new Deadlines(string.substring(9, index - 1), string.substring(index + 1)));
                 } else if (string.substring(1,2).equals("E")) {
                     listOfTask.add(new Events(string.substring(6, index - 1), string.substring(index + 1)));
                 } else {
                    listOfTask.add(new ToDos(string.substring(5)));
                 }
             }
         } catch (FileNotFoundException e ){
             createFile();
         }
    }

    static void createFile() {
        try {
            File f = new File(PATHNAME);
            File dir = new File(Duke.DIRECTORY);
            dir.mkdir();
            f.createNewFile();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    private static void writeToFile(String name, String type, String status) throws IOException {
        FileWriter fw = new FileWriter(PATHNAME, true);
        fw.write(status + "\n");
        fw.close();
    }


    public static void main(String[] args) throws DukeException {
       System.out.println("Hello I'm Duke\nWhat can I do for you?");
        Scanner input = new Scanner(System.in);
        getData();
            while (input.hasNext()) {
                try {
                    String s = input.nextLine();
                    int index = s.indexOf("/");
                    if (s.equals("bye")) {
                        break;
                    } else if (s.equals("list")) {
                        showList();
                    } else if (s.length() > 5 && s.substring(0, 4).equals("mark")) {
                        markHelper(s);
                    } else if (s.length() > 7 && s.substring(0, 6).equals("unmark")) {
                        unmarkHelper(s);
                    } else if (s.length() >= 8 && s.substring(0, 8).equals("deadline")) {
                        if(s.length() == 8){
                            throw new DukeDeadlineEmptyException();
                        }
                        String subS = s.substring(9, index - 1);
                        try {
                            LocalDate date = LocalDate.parse(s.substring(index + 4));

                            Task t = new Deadlines(subS, date);
                            addList(t);
                        } catch (DateTimeException e) {
                            Task t = new Deadlines(subS, s.substring(index + 4));
                            addList(t);
                        }
                    } else if (s.length() >= 5 && s.substring(0, 5).equals("event")) {
                        if(s.length() == 5){
                            throw new DukeEventEmptyException();
                        }
                        Task t = new Events(s.substring(6, index - 1), s.substring(index + 1));
                        addList(t);
                    } else if (s.length() >= 4 && s.substring(0, 4).equals("todo")) {
                        if(s.length() == 4){
                            throw new DukeTodoEmptyException();
                        }
                        Task t = new ToDos(s.substring(5));
                        addList(t);
                    } else if (s.length() > 7 && s.substring(0, 6).equals("delete")) {
                        delete(s);
                    } else {
                        throw new DukeUnknownWordException();
                    }
                } catch (DukeException e) {
                    System.out.println(e.toString());
                }
            }
        System.out.println("Bye. Hope to see you again soon!");
    }
}


