//unable to update duke.txt after marking, unmarking or deleting in this version
import jdk.jshell.Snippet;
import java.io.*;
import java.time.chrono.MinguoDate;
import java.util.Scanner;

public class Duke {

    private static int IND_COUNT;
    private static Task[] taskList;

    private static void appendToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAdd);
        fw.close();
    }

   //adds previous tasks back into taskList when restarting Duke
   private static void readFile(File dukeFile) {
        try {
            Scanner sc = new Scanner(dukeFile);
            while (sc.hasNextLine()) {
                String data = sc.nextLine();
                String[] split = data.split(" : ");
                addTaskList(split);
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error reading file: file not found");
        }
   }

   private static boolean addTaskList(String[] strArray) {
        if (strArray[0].equals("T")) {
            Task newTask = new ToDos(strArray[2]);
            taskList[IND_COUNT] = newTask;
            IND_COUNT++;
            return true;
        }
        if (strArray[0].equals("D") || strArray.equals("E")) {
            Task newTask = new Deadlines(strArray[2], strArray[3]);
            taskList[IND_COUNT] = newTask;
            IND_COUNT++;
            return true;
        }
        return false;
   }

   //rewrites entire duke.txt file when delete, mark or unmark called
    //shld replace with code that modifies the txt file rather than rewrites it entirely
    //is broken :'(
  /** private static boolean modifyFile(File dukeFile) throws IOException {
        try {
            PrintWriter writer = new PrintWriter(dukeFile);
            writer.print("");
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
        try {
            for (int i = 0; i < IND_COUNT; i++) {
                appendToFile("duke.txt", taskList[IND_COUNT].textDesc());
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        return true;
   }
   */

    public static void main(String[] args) throws DukeException, IOException {
        File dukeFile = new File("duke.txt"); //creates new text file
        dukeFile.createNewFile();
        Scanner sc = new Scanner(System.in);
        IND_COUNT = 0;
        taskList = new Task[100];
        readFile(dukeFile);
        String buffLine = "    _____________________________________";
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("    Hello from\n" + logo);
        System.out.println("    What can I do for you?\n" + buffLine);
        String userReply = sc.nextLine();

        while (!userReply.equals("bye")) {
            try {
                if (userReply.toLowerCase().startsWith("mark")) {
                    int pos = Character.getNumericValue(userReply.charAt(5));
                    String prevDesc = taskList[pos - 1].textDesc();
                    taskList[pos - 1].markAsDone();
                    System.out.println(IND_COUNT);
                    //modifyFile(dukeFile);
                    System.out.println(buffLine + "\n" + "    Nice! I've marked this task as done: ");
                    taskList[pos - 1].fullDesc();
                    System.out.println(buffLine);
                    userReply = sc.nextLine();
                } else if (userReply.toLowerCase().startsWith("unmark")) {
                    int pos = Character.getNumericValue(userReply.charAt(7));
                    taskList[pos - 1].markAsUndone();
                    //modifyFile(dukeFile);
                    System.out.println(buffLine + "\n" + "    Ok, I've marked this task as not done yet: ");
                    taskList[pos - 1].fullDesc();
                    System.out.println(buffLine);
                    userReply = sc.nextLine();
                } else if (userReply.toLowerCase().startsWith("todo")) {
                    try {
                        taskList[IND_COUNT] = new ToDos(userReply.substring(5, userReply.length()));
                        System.out.println(buffLine + "\n" + "    Got it. I've added this task: ");
                        taskList[IND_COUNT].fullDesc();
                        System.out.println("    Now you have " + String.valueOf(IND_COUNT + 1)
                                + " tasks in this list." + "\n" + buffLine);
                        appendToFile("duke.txt", taskList[IND_COUNT].textDesc());
                        IND_COUNT++;
                        userReply = sc.nextLine();
                    } catch (StringIndexOutOfBoundsException t) {
                        System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                        userReply = sc.nextLine();
                    }
                } else if (userReply.toLowerCase().startsWith("deadline")) {
                    try {
                        int splitPoint = userReply.indexOf("/");
                        taskList[IND_COUNT] = new Deadlines(userReply.substring(9, splitPoint - 1),
                                userReply.substring(splitPoint + 1, userReply.length()));
                        System.out.println("    Got it. I've added this task: ");
                        taskList[IND_COUNT].fullDesc();
                        System.out.println("    Now you have " + String.valueOf(IND_COUNT + 1)
                                + " tasks in this list." + "\n" + buffLine);
                        appendToFile("duke.txt", taskList[IND_COUNT].textDesc());
                        IND_COUNT++;
                        userReply = sc.nextLine();
                    } catch (StringIndexOutOfBoundsException t) {
                        System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                        userReply = sc.nextLine();
                    }
                } else if (userReply.toLowerCase().startsWith("event")) {
                    int splitPoint = userReply.indexOf("/");
                    taskList[IND_COUNT] = new Events(userReply.substring(6, splitPoint - 1),
                        userReply.substring(splitPoint + 1, userReply.length()));
                    System.out.println("    Got it. I've added this task: ");
                    taskList[IND_COUNT].fullDesc();
                    System.out.println("    Now you have " + String.valueOf(IND_COUNT + 1)
                            + " tasks in this list." + "\n" + buffLine);
                    appendToFile("duke.txt", taskList[IND_COUNT].textDesc());
                    IND_COUNT++;
                    userReply = sc.nextLine();
                } else if (userReply.toLowerCase().startsWith("list")){
                    System.out.println(buffLine);
                    for (int i = 0; i <  IND_COUNT; i++) {
                        System.out.println("    " + (i + 1) + ". " + taskList[i].stringDesc());
                    }
                    System.out.println(buffLine);
                    userReply = sc.nextLine();
                } else if (userReply.toLowerCase().startsWith("delete")) {
                    System.out.println(buffLine + "\n    Noted. I've removed this task: ");
                    taskList[Character.getNumericValue(userReply.charAt(7)) - 1].fullDesc();
                    for (int i = userReply.charAt(7) - 1; i < IND_COUNT; i++) {
                        taskList[i] = taskList[i + 1];
                    }
                    IND_COUNT--;
                   // modifyFile(dukeFile);
                    System.out.println("    Now you have " + String.valueOf(IND_COUNT) +
                            " tasks in this list." + "\n" + buffLine);
                    userReply = sc.nextLine();
                }
                else {
                    throw new DukeException("");
                }
            } catch (DukeException e) {
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                userReply = sc.nextLine();
            }
        }

        System.out.println(buffLine + "\n" + "    Bye. Hope to see you again soon!"
                + "\n" + buffLine);
    }
}
