import java.io.*;
import java.util.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/*class SkeletonDuke {
    private List<Task> list;
    private static int noOfTasks;*/
    //private final static String fileLocation = "./data/duke.txt";

    /*SkeletonDuke() {
        this.list = new ArrayList<Task>();
        this.noOfTasks = 0;
    }*/

    /*void add(String s) {
        String[] strarr = s.split(" ");
        String typeOfTask = strarr[0];
        //String description = s;
        //String date = s;
        Task newTask = new Task(s);
        try {
            if (typeOfTask.equals("todo")) {
                String[] descriptionList1 = this.processDescription(strarr);
                newTask = new ToDo(descriptionList1[0]);
            } else if (typeOfTask.equals("deadline")) {
                String[] descriptionList2 = this.processDescription(strarr);
                newTask = new Deadline(descriptionList2[0],descriptionList2[1]);
            } else if (typeOfTask.equals("event")) {
                String[] descriptionList3 = this.processDescription(strarr);
                newTask = new Event(descriptionList3[0],descriptionList3[1]);
            }
            list.add(newTask);
            this.noOfTasks++;
            String addedTask = newTask.toString();
            /*System.out.println("Got it. I've added this task:");
            System.out.println(newTask.toString());
            System.out.println("Now you have " + this.noOfTasks + " tasks in the list.");*/
        /*} catch(TaskWithNoDescriptionException ex) {
            System.err.print(ex);
        }

    }

    /*void delete(int taskNo) {
        Task currentTask = list.get(taskNo - 1);
        list.remove(taskNo - 1);
        noOfTasks--;
        String deletedTask = currentTask.toString();
        /*System.out.println("Noted. I've removed this task:");
        System.out.println(deletedTask.toString());
        System.out.println("Now you have " + noOfTasks + " tasks in the list.");*/
    }

    /*void getList() {
        System.out.println("Here are the tasks in your list:");
        for(int i = 1; i < list.size() + 1; i++) {
            String currentTask = list.get(i - 1).toString();
            System.out.println(i + ". " + currentTask);
        }
    }

    /*void mark(int taskNo) {
        Task taskToBeModify = list.get(taskNo - 1);
        taskToBeModify.markAsDone();
        String markedTask = taskToBeModify.toString();
        /*System.out.println("Nice! I've marked this task as done:");
        System.out.println(taskToBeModify.toString());*/
    }

    /*void unmark(int taskNo) {
        Task taskToBeModify = list.get(taskNo - 1);
        taskToBeModify.unmarked();
        String unmarkedTask = taskToBeModify.toString();
        /*System.out.println("OK! I've marked this task as not done yet:");
        System.out.println(taskToBeModify.toString());*/
    }

    /*String[] processDescription(String[] strarr) throws TaskWithNoDescriptionException {
        if(strarr.length > 1) {
            String description = strarr[1];
            String date = "";
            String[] strarr1 = new String[2];
            for (int i = 2; i < strarr.length; i++) {
                if (strarr[i].equals("/by") || strarr[i].equals("/at")) {
                    date = strarr[i + 1];
                    break;
                } else {
                    description = description + " " + strarr[i];
                }
            }
            strarr1[0] = description;
            strarr1[1] = date;
            return strarr1;
        } else {
            throw new TaskWithNoDescriptionException(":( OOPS!!! The description of a " + strarr[0] + " cannot be empty.");
        }
    }*/

    /*void saveNewChanges() throws IOException {
        File taskFile = new File(fileLocation);
        PrintWriter pw = new PrintWriter(taskFile);
        for(int i = 0; i < this.list.size(); i++) {
            pw.println( (i+1) + ":" + this.list.get(i).write());
        }
        pw.close();
    }*/

     /*void greet() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

    }*/

    /*static boolean checkFile() {
        File taskFile = new File(fileLocation);
        String base = System.getProperty("user.dir");
        java.nio.file.Path path =  java.nio.file.Paths.get(base, fileLocation);
        boolean fileExists = java.nio.file.Files.exists(path);
        return fileExists;
    }*/

    /*static void createFiles() throws Exception {
        File taskFile = new File(fileLocation);
        taskFile.getParentFile().mkdirs();
        taskFile.createNewFile();
    }*/

    /*void readFiles() throws Exception {
        if (this.checkFile() == true) {
            FileReader file = new FileReader(fileLocation);
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine() ) {
                String task = sc.nextLine();
                String[] strarr = task.split(":");
                String typeOfTask = strarr[1];
                String statusOfTask = strarr[2];
                String taskDescription = strarr[3];
                if (typeOfTask.equals("T")) {
                    Task pastTask = new ToDo(taskDescription);
                    if (statusOfTask.equals("X")) {
                        pastTask.markAsDone();
                    }
                    list.add(pastTask);
                    this.noOfTasks++;
                } else if (typeOfTask.equals("D")) {
                    String dateOfTask = strarr[4];
                    Task pastTask = new Deadline(taskDescription, dateOfTask);
                    if (statusOfTask.equals("X")) {
                        pastTask.markAsDone();
                    }
                    list.add(pastTask);
                    this.noOfTasks++;
                } else if (typeOfTask.equals("E")) {
                    String dateOfTask = strarr[4];
                    Task pastTask = new Event(taskDescription, dateOfTask);
                    if (statusOfTask.equals("X")) {
                        pastTask.markAsDone();
                    }
                    this.list.add(pastTask);
                    this.noOfTasks++;
                }

            }
        } else {
            System.out.println("There is no existing task list. Duke is creating a new one now.");
            this.createFiles();
        }
    }*/


    /*void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }*/

}