import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Bloop {

    private static final String HI_MESSAGE = "Hey! I'm Bloop\n" + "\tWhat can I do for you?";

    private static final String BYE_MESSAGE = "Goodbye! Hope to see you soon :)";

    private static final String SEPARATOR = "\t-------------------------------------------------------";

    private static ArrayList<Task> list = new ArrayList<>();

    private static final String FILE_NAME = "bloopData.txt";

    private static void chat() {
        print(HI_MESSAGE);
        makeFile();
        Scanner sc = new Scanner(System.in);
        String text = sc.nextLine();

        while(text.compareTo("bye") != 0) {
            try {
                String[] textArr = text.split(" ");
                String command = textArr[0];

                switch (command) {
                case "list":
                    listOut();
                    break;

                case "unmark":
                    Task task1 = list.get(Integer.parseInt(textArr[1]) - 1);
                    task1.unmark();
                    print("This task has been marked as not done -\n\t\t" + task1);
                    rewriteFile();
                    break;

                case "mark":
                    Task task2 = list.get(Integer.parseInt(textArr[1]) -1 );
                    task2.mark();
                    print("This task has been marked as done -\n\t\t" + task2);
                    rewriteFile();
                    break;

                case "todo":
                    addTask(text, 'T');
                    break;

                case "event":
                    addTask(text, 'E');
                    break;
                    
                case "deadline":
                    addTask(text, 'D');
                    break;

                case "delete":
                    Task task3 = list.get(Integer.parseInt(textArr[1]) - 1);
                    list.remove(task3);
                    rewriteFile();
                    print("This task has been removed -\n\t\t" + task3 + "\n\tNow you have " + list.size() + " tasks in the list");
                }
            } catch(BloopException be) {
                print(be.getMessage());
            } catch(IOException e) {
                print(e.getMessage());
            }
            text = sc.nextLine();
        }
        print(BYE_MESSAGE);
    }

    private static void addTask(String input, char type) throws BloopException {
        Task task;
        if(type == 'T') {
            if(input.trim().length() == 4) {
                throw new BloopException("There is no task to do");
            }
            task = new ToDo(input.substring(5));
        } else {
            int index = input.indexOf('/');
            if(type == 'E') {
                if(input.trim().length() == 5) {
                    throw new BloopException("No event specified");
                }
                task = new Event(input.substring(6, index), input.substring(index + 3));
            } else {
                if(input.trim().length() == 8) {
                    throw new BloopException("No deadline specified");
                }
                task = new Deadline(input.substring(9, index), input.substring(index + 3));
            }
        }
        list.add(task);
        try {
            writeToFile(task);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        print("I've added this task -\n\t\t" + task + "\n\tNow you have " + list.size() + " tasks in the list");
    }

    private static void listOut() {
        System.out.println(SEPARATOR);
        System.out.println("\tTasks in your list -");
        for(int i = 0; i < list.size(); i++) {
            System.out.println("\t\t" + (i + 1) + ". " + list.get(i));
        }
        System.out.println(SEPARATOR);
    }

    private static void print(String message) {
        System.out.println(SEPARATOR);
        System.out.println("\t" + message);
        System.out.println(SEPARATOR);
    }

    private static void makeFile() {
       try {
           File file = new File(FILE_NAME);
           boolean isCreated = file.createNewFile();
           if(!isCreated) {
               addPrevTasks();
           }
       }
       catch (IOException e){
           print("Couldn't create file. Sorry.");
           System.exit(0);
        }
    }

    private static void addPrevTasks() throws FileNotFoundException {
        File file = new File(FILE_NAME);
        Scanner sc = new Scanner(file);
        while(sc.hasNext()) {
            String[] taskArr = sc.nextLine().split("-");
            String type = taskArr[0];
            Task task;
            if(type.equals("T")) {
                task = new ToDo(taskArr[2]);
            } else if(type.equals("E")) {
                task = new Event(taskArr[2], taskArr[3]);
            } else {
                task = new Deadline(taskArr[2], taskArr[3]);
            }
            if(Integer.parseInt(taskArr[1]) == 1) {
                task.mark();
            }
            list.add(task);
        }
    }

    private static void writeToFile(Task task) throws IOException {
        FileWriter fw = new FileWriter(FILE_NAME, true);
        fw.write(task.getType() + "-"
                + (task.getStatus() ? "1" : "0") + "-" + task.getTask() + "-" + task.getBy() + "\n");
        fw.close();
    }

    private static void rewriteFile() throws IOException {
        FileWriter fw = new FileWriter(FILE_NAME);
        fw.write("");
        for(Task task : list) {
            writeToFile(task);
        }
        fw.close();
    }

    public static void main(String[] args) {
        chat();
    }
}
