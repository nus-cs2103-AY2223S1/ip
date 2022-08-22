import com.sun.source.tree.TryTree;

import java.io.FileWriter;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;

public class Doke {
    static final String dokeFilePath = "src/main/java/data/doke.txt";

    private static void createTaskList(File file, ArrayList<Task> arrayList) throws  FileNotFoundException{

        Scanner s = new Scanner(file); // create a Scanner using the File as the source
        while (s.hasNext()) {
            String line = s.nextLine();
            String specialString = " [|] ";
            String[] temp = line.split(specialString);
            Task addTask;

            if (temp[0].equals("T")) {
                addTask = new ToDo(temp[2]);
            } else if (temp[0].equals("D")) {
                addTask = new Deadline(temp[2], temp[3]);
            } else {
                addTask = new Events(temp[2], temp[3]);
            }
            arrayList.add(addTask);

            try {
                if (temp[1].equals("1")) {
                    addTask.markDone();
                } else {
                    addTask.markNotDone();
                }
            } catch (DokeException e) {

            }
        }
        listOut(arrayList);
    }

    private static void writeToFile(File file, String string) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
        writer.append(string + "\n");
        writer.close();
    }

    public static void updateFile(ArrayList<Task> arrayList) {
        try {
            FileWriter fw = new FileWriter(dokeFilePath);
            fw.write("");
            File dokeFile = new File(dokeFilePath);
            for(int i = 0; i < arrayList.size(); i++) {
                writeToFile(dokeFile, createWordForFile(arrayList.get(i)));
            }
            fw.close();

        } catch (IOException e) {}
    }

    private static void listOut(ArrayList<Task> arr) {
        if (arr.isEmpty()) {
            System.out.println("_________________________");
            System.out.println("You have no task!");
            System.out.println("_________________________");
            return;
        }

        int len = arr.size();
        int i=0;
        System.out.println("_________________________");
        while (i < len) {
            System.out.println((i + 1) + "." + arr.get(i).toString());
            i++;
        }
        System.out.println("_________________________");
    }

    private static boolean isInt(String str) {
        try {
            @SuppressWarnings("unused")
            int x = Integer.parseInt(str);
            return true; //String is an Integer
        } catch (NumberFormatException e) {
            return false; //String is not an Integer
        }

    }

    private static int toInt(String str) {
        try {
            @SuppressWarnings("unused")
            int x = Integer.parseInt(str);
            return x; //String is an Integer
        } catch (NumberFormatException e) {
            return -1; //String is not an Integer
        }

    }

    private static String createWordForFile(Task task) {
        String time = task instanceof ToDo ? "" : " | " + task.getTime();
        String action = task.getType() + " | " + task.getStatus() + " | " +
                task.getDesc() + time;
        return action;
    }

    private static void addTask(String[] arr, String action, ArrayList<Task> arrayList,
                                File file) {

        if (arr.length == 1) {
            if (action.equals("todo")) {
                try {
                    throw new DokeException("todo");
                } catch (DokeException d) {
                    System.out.println(d.toString());
                }
            } else if (action.equals("deadline")) {
                try {
                    throw new DokeException("deadline");
                } catch (DokeException d) {
                    System.out.println(d.toString());
                }
            }else {
                try {
                    throw new DokeException("event");
                } catch (DokeException d) {
                    System.out.println(d.toString());
                }
            }
            return;
        }

        String word = "";
        String time="";
        String fileWord= action + " | ";
        int i = 1;
        int length = arr.length;

        while (i < length && ((!arr[i].equals("/at") && action.equals("event"))
                || (!arr[i].equals("/by") && action.equals("deadline"))
                || action.equals("todo"))) {
            if (i == 1) {
                word = word + arr[i];
            } else {
                word = word + " " + arr[i];
            }
            i++;
        }

        if (i != length) {
            time = arr[++i];
            i++;
            while (i< length) {
                time += " " + arr[i];
                i++;
            }
        }

        Task addition;
        if (action.equals("todo")) {
            addition =  new ToDo(word);
            arrayList.add(addition);
        } else if (action.equals("deadline")) {
            addition =  new Deadline(word, time);
            arrayList.add(addition);
        } else {
            addition =  new Events(word, time);
            arrayList.add(addition);
        }

        try {
            File dokeFile = new File(dokeFilePath);
            writeToFile(dokeFile, createWordForFile(addition));
        } catch (IOException e) {}

        System.out.println("_________________________");
        System.out.println("added: " + arrayList.get(arrayList.size() - 1).toString());
        System.out.println("Nice, now you have " + arrayList.size() + " tasks!!");
        System.out.println("_________________________" +"\n");
    }

    private static void deleteTask(int num, ArrayList<Task> arrayList) {
        Task current = arrayList.get(num);
        arrayList.remove(num);
        updateFile(arrayList);
        System.out.println("_________________________");
        System.out.println("This task has been removed:");
        System.out.println(current.toString());
        System.out.println("Nice, now you have " + arrayList.size() + " tasks!!");
        System.out.println("_________________________" + "\n");
    }

    public static void main(String[] args) {

        ArrayList<Task> arrayList = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        File dokeFile = new File(dokeFilePath);
        System.out.println("full path: " + dokeFile.getAbsolutePath());

        System.out.println("_________________________");
        System.out.println("    Hi, my name is Doke");
        System.out.println("    What can I do for you?");
        System.out.println("    Enter a String!!");
        System.out.println("_________________________" + "\n");

        try {
            createTaskList(dokeFile, arrayList);
        } catch (FileNotFoundException f) {
            try {
                dokeFile.createNewFile();
                System.out.println("a new Doke.txt file has been created");
                System.out.println("it is in:" + dokeFile.getAbsolutePath());
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
                return ;
            }
        }

        String str= sc.nextLine();

        while (!str.equals("bye")) {

            if (str.equals("list")) {
                listOut(arrayList);
                str = sc.nextLine();
                continue;
            }

            String[] temp = str.split(" ");
            String action = temp[0];

            if (temp.length == 2 && isInt(temp[1])) {
                int num = toInt(temp[1]);
                if(action.equals("mark")) {

                    Task current = arrayList.get(num - 1);
                    try {
                        current.markDone();
                        updateFile(arrayList);
                        System.out.println("_________________________");
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println(current.toString());
                        System.out.println("_________________________" + "\n");
                    } catch (DokeException a) {
                        System.out.println("_________________________");
                        System.out.println("This task is already marked done:");
                        System.out.println(current.toString());
                        System.out.println("_________________________" + "\n");
                    }
                } else if(action.equals("unmark")) {

                    Task current = arrayList.get(num - 1);
                    try {
                        current.markNotDone();
                        updateFile(arrayList);
                        System.out.println("_________________________");
                        System.out.println("OK, I've marked this task as not done yet:");
                        System.out.println(current.toString());
                        System.out.println("_________________________" + "\n");

                    } catch (DokeException a) {
                        System.out.println("_________________________");
                        System.out.println("This task is already marked not done:");
                        System.out.println(current.toString());
                        System.out.println("_________________________" + "\n");
                    }
                } else if(action.equals("delete")) {
                    deleteTask(num - 1, arrayList);
                }
                str = sc.nextLine();
                continue;
            }

            if (action.equals("todo") || action.equals("deadline") || action.equals("event")) {
                addTask(temp, action, arrayList, dokeFile);
            } else {
                try {
                    throw new DokeException();
                } catch(DokeException d){
                    System.out.println(d.toString());
                }
                str = sc.nextLine();
                continue;
            }

            str = sc.nextLine();
        }

        System.out.println("_________________________");
        System.out.println("    Bye bye!");
        System.out.println("_________________________");

    }
}