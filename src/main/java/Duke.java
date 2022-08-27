import java.util.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import  java.nio.file.Paths;
import java.nio.file.Files;

public class Duke {
    private static final String FILE_PATH = "C:/Unu_Stuff/Y3S1/CS2103-CS2103T/Lab/Lab 2/src/data/duke.txt";
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        try {
            createFiles();
        } catch (Exception e) {
            System.out.println("Throwing error in file");
        }

        List<Task> arr = new ArrayList<Task>(); //should be list as compile time type
        String input = "";
        int curr = 0;
        Task task = new Task(input, "");

        try {
            File myObj = new File(FILE_PATH);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] fromText = data.split(" | ");
                System.out.println(Arrays.toString(fromText));
                boolean done = fromText[1].equals("1") ? true : false;
                switch (fromText[0]) {
                    case "T":
                        task = new ToDo(fromText[2], done, "");
                        arr.add(curr++, task);
                        break;
                    case "E":
                        task = new Event(fromText[2], done, fromText[3]);
                        System.out.println(task);
                        arr.add(curr++, task);
                        break;
                    case "D":
                        task = new Deadline(fromText[2], done, fromText[3]);
                        arr.add(curr++, task);
                        break;
                }
            }
            myReader.close();
        }
            catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        try {
            PrintWriter writer = new PrintWriter(new File(FILE_PATH));
            writer.print("");
            writer.close();
        } catch (IOException e) {
            System.out.println("Nth to read form duke.txt");
        }
        /*if(task.getVal().equals("")) {
            arr.add(curr, task);
        }*/

        while(!task.getVal().equals("bye")) {
            task = new Task(sc.nextLine(), "");
            //System.out.println(task.getVal());
            if(task.getVal().equals("list")) {
                for(int i = 0; i < curr; i++) {
                    System.out.println(arr.get(i));
                }
                //System.out.println(input); level 1
            }
            else if(task.getVal().indexOf("mark") == 0) {
                String[] at = task.getVal().split(" ");
                try {
                    int index = Integer.parseInt(at[1]);
                    arr.set(index - 1, arr.get(index - 1).markDone());
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(String.format("%s", arr.get(index - 1)));
                } catch (Exception e) {
                    System.out.println("☹ OOPS!!! The description of a mark cannot be empty.");
                }
            }
            else if(task.getVal().indexOf("unmark") == 0) {
                String[] at = task.getVal().split(" ");
                try {
                    int index = Integer.parseInt(at[1]);

                arr.set(index - 1, arr.get(index - 1).markUndone());
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(String.format("%s", arr.get(index-1)));
                } catch (Exception e) {
                    System.out.println("☹ OOPS!!! The description of a unmark cannot be empty.");
                }
            }
            else if(task.getVal().indexOf("delete") == 0) {
                String[] at = task.getVal().split(" ");
                try {
                    int index = Integer.parseInt(at[1]);
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
            }
            else if(task.getVal().indexOf("todo") == 0) {
                try {
                    String todo = task.getVal().substring(5);
                    task = new ToDo(todo);
                    arr.add(curr++, task);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(task);
                    System.out.println(String.format("Now you have %s tasks in the list.", curr));
                }
                 catch (Exception e) {
                        System.out.println("☹ OOPS!!! The description of a undo cannot be empty.");
                    }
            }
            else if(task.getVal().indexOf("deadline") == 0) {
                try {
                    String deadline = task.getVal().substring(9);
                    String[] at = deadline.split("/");
                    task = new Deadline(at[0], at[1]);
                    arr.add(curr++, task);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(task);
                    System.out.println(String.format("Now you have %s tasks in the list.", curr));
                }
                catch (Exception e) {
                    System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
                }
            }
            else if(task.getVal().indexOf("event") == 0) {
                try {
                    String event = task.getVal().substring(6);
                    String[] at = event.split("/");
                    task = new Event(at[0], at[1]);
                    arr.add(curr++, task);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(task);
                    System.out.println(String.format("Now you have %s tasks in the list.", curr));
                }
                catch (Exception e) {
                        System.out.println("☹ OOPS!!! The description of a event cannot be empty.");
                    }
            }
            else if (!task.getVal().equals("bye")) {
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                //arr[curr++] = task;
            }
        }
        //bye block
        try {
            PrintWriter writer = new PrintWriter(new File(FILE_PATH));
            for(int i = 0; i < curr; i++) {
                writer.println(arr.get(i).toText());
            }
            writer.close();
        } catch (Exception e) {
            System.out.println("File not found error");
        }
        for(int i = 0; i < curr; i++) {

        }
        System.out.println("Bye. Hope to see you again soon!");
    }


    public static void createFiles() throws IOException {
        String[] arrFiles = FILE_PATH.split("/");
        java.nio.file.Path path = java.nio.file.Paths.get("");
        for (int i = 0; i < arrFiles.length - 1; i++) {
            path = java.nio.file.Paths.get(String.valueOf(path),arrFiles[i]);
            boolean directoryExists = java.nio.file.Files.exists(path);
            if(!directoryExists) {
                new File(String.valueOf(path)).mkdirs();
                System.out.println("hi, made new directory");
            }
        }
        File filePath = new File(FILE_PATH);
        filePath.createNewFile();
    }
}
