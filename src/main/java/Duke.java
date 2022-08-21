import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    protected static String fullPath;
    protected static ArrayList<Task> arrayList = new ArrayList<>();

    protected static void ReadData(File savedData) throws FileNotFoundException {

        Scanner sc = new Scanner(savedData);

        while (sc.hasNextLine()) { //type#boolean#taskname#time

            String[] array = sc.nextLine().split("#");
            boolean cond1 = (array.length == 3 && array[0].equals("T"));
            boolean cond2 = (array.length == 4 && (array[0].equals("D") || array[0].equals("E")));
            boolean cond3 = (array[1].equals("1") || array[1].equals("0"));

            if ((!cond1 && !cond2) || !cond3) {
                throw new RuntimeException("Cannot read file due to incorrect input");
            }

            char c = array[0].charAt(0);
            String taskname = array[2];
            boolean isDone = array[1].equals("1");

            if (c == 'T') {
                arrayList.add(new Todo(taskname));
            } else {
                String time = array[3];

                if (c == 'E') {
                    arrayList.add(new Event(taskname, time));
                } else {
                    arrayList.add(new Deadline(taskname, time));
                }
            }

            if (isDone) {
                arrayList.get(arrayList.size() - 1).markDone();
            }
        }
    }

    protected static void SaveData(File savedData) throws IOException {

        FileWriter fw = new FileWriter(savedData, false);

        for (Task t : arrayList) {
            fw.write(t.toSavedString() + System.lineSeparator());
        }

        fw.close();
    }

    public static void main(String[] args) throws DukeException, IOException {

        File dataDir = new File(System.getProperty("user.dir") + File.separator + "data");

        if (!dataDir.exists()) {
            dataDir.mkdir();
        }

        fullPath = dataDir.getPath() + File.separator + "Save.txt";
        File savedData = new File(fullPath);
        boolean fileCreated = false;

        try {
            fileCreated = savedData.createNewFile();

            if (!fileCreated) {
                System.out.println("Save file detected, reading it...");
                ReadData(savedData);
            } else {
                System.out.println("Creating save file for first time use");
            }

        } catch (IOException e) {
            System.out.println("An error occurred creating save file");
            e.printStackTrace();
        }

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");

        Scanner sc = new Scanner(System.in);

        String[] strArray = sc.nextLine().split(" ");
        String str = strArray[0];

        while (!str.equals("bye")) {

            if (str.equals("list")) {
                System.out.println("Here are the tasks in your current list:");

                if (arrayList.size() == 0) {
                    System.out.println("  Wow! You have no tasks to do currently!!");
                }

                for (int i = 0; i < arrayList.size(); i++) {
                    int j = i + 1;
                    System.out.println(j + "." + arrayList.get(i));
                }
            } else if (str.equals("mark")) {
                int i = Integer.parseInt(strArray[1]) - 1;
                arrayList.get(i).markDone();
                SaveData(savedData);

                System.out.println("Nice! You actually did something:");
                System.out.println(" " + arrayList.get(i));
            } else if (str.equals("unmark")) {
                int i = Integer.parseInt(strArray[1]) - 1;
                arrayList.get(i).markNotDone();
                SaveData(savedData);

                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(" " + arrayList.get(i));
            } else if (str.equals("delete")) {
                int i = Integer.parseInt(strArray[1]) - 1;
                Task deletedTask = arrayList.remove(i);
                SaveData(savedData);

                System.out.println("Noted. I've removed this task:");
                System.out.println(" " + deletedTask);
                System.out.println("Now you have " + arrayList.size() + " task(s) in the list.");
            } else if (str.equals("todo") || str.equals("deadline") || str.equals("event")) {

                String taskname = "";
                String time = "";
                int i = 1;

                while (i < strArray.length && strArray[i].charAt(0) != '/') {
                    taskname += strArray[i] + " ";
                    i++;
                }

                while (i < strArray.length) { //means now strArray[i] == /by or /at

                    if (strArray[i].charAt(0) != '/') {
                        time += strArray[i] + " ";
                    }

                    i++;
                }

                if (taskname.equals("")) {
                    throw new DukeException("Name of task cannot be empty!");
                }

                if (str.equals("todo")) {
                    Task todo = new Todo(taskname.trim());
                    arrayList.add(todo);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(" " + todo);
                    System.out.println("Now you have " + arrayList.size() + " task(s) in the list.");

                } else if (str.equals("deadline")) {
                    if (time.equals("")) {
                        throw new DukeException("Date/Time cannot be empty!");
                    }

                    Task deadline = new Deadline(taskname.trim(), time.trim());
                    arrayList.add(deadline);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(" " + deadline);
                    System.out.println("Now you have " + arrayList.size() + " task(s) in the list.");

                } else {
                    if (time.equals("")) {
                        throw new DukeException("Date/Time cannot be empty!");
                    }

                    Task event = new Event(taskname.trim(), time.trim());
                    arrayList.add(event);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(" " + event);
                    System.out.println("Now you have " + arrayList.size() + " task(s) in the list.");
                }

                SaveData(savedData);
            } else {
                throw new DukeException("Please enter a valid input");
            }

            strArray = sc.nextLine().split(" ");
            str = strArray[0];
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
