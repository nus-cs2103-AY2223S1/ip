import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.nio.file.Files;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;



// look for file. if it does not exist, make it
// from tasks in file, get the var of each line and update the duke list
// 
// at the end of every update, clear the whole file and rewrite with current list of tasks


public class Duke {

    private static ArrayList<Task> list = new ArrayList<>();

    private static void addTask(String taskType, String input) throws DukeException {

        switch (taskType) {
            case "todo":
                String[] removeTaskType = input.split("todo ");
                String description = String.join("", removeTaskType);
                if (description.equals("todo")) {
                    throw new DukeException("");
                }
                Task todo = new ToDo(description);
                list.add(todo);
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + todo);
                break;
            case "deadline":
                String[] removeTaskType2 = input.split("deadline ");
                String desAndBy = String.join("", removeTaskType2);
                String[] sliceByDesAndBy = desAndBy.split(" /by ");
                String description2 = sliceByDesAndBy[0];
                String dueTime = sliceByDesAndBy[1];
                Task deadline = new Deadline(description2, dueTime);
                list.add(deadline);
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + deadline);
                break;
            case "event":
                String[] removeTaskType3 = input.split("event ");
                String desAndBy2 = String.join("", removeTaskType3);
                String[] sliceByDesAndBy2 = desAndBy2.split(" /at ");
                String description3 = sliceByDesAndBy2[0];
                String dueTime2 = sliceByDesAndBy2[1];
                Task event = new Event(description3, dueTime2);
                list.add(event);
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + event);
                break;

        }

        // edge case of 1 task
        String numTask = String.format("Now you have %s tasks in the list.", list.size());
        System.out.println(numTask);

    }

    private static void printList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            String line = String.format("%s. %s", i + 1, list.get(i));
            System.out.println(line);
        }
    }

    // taken from Week 3 topics
    private static void printFileContents(String filePath) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            System.out.println(s.nextLine());
        }
    }



    private static void markTask(int num) {
        Task task = list.get(num - 1);
        task.mark();
    }

    private static void unmarkTask(int num) {
        Task task = list.get(num - 1);
        task.unmark();
    }

    private static void deleteTask(int num) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + list.get(num - 1));
        list.remove(num - 1);
        String numTask = String.format("Now you have %s tasks in the list.", list.size());
        System.out.println(numTask);
    }

    static void validate(String str, String type) throws DukeException {
        if (!type.equals("todo") && !type.equals("deadline") && !type.equals("event")) {
            throw new DukeException("");
        }
    }


    static void addDeadlineOrEvent(String s, String isMarked, String description, String datetime){
//        System.out.println("type:" + s);
        if (s.equals("D")){
//            System.out.println("added D");
            Deadline deadline = new Deadline(description, datetime);
            if (isMarked.equals("1")) {
                deadline.mark();
            }
            list.add(deadline);
        } else {
//            System.out.println("added E");
            Event event = new Event(description, datetime);
            if (isMarked.equals("1")) {
                event.mark();
            }
            list.add(event);
        }
    }

    static void addToDo(String isMarked, String description) {
//        System.out.println("added T");
        ToDo todo = new ToDo(description);
        if (isMarked.equals("1")) {
            todo.mark();
        }
        list.add(todo);
    }

    // settled reading task from text file
    static void getTasks() throws FileNotFoundException{
        File f = new File("data/duke.txt");
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            String[] taskInList = s.nextLine().split(" \\| ");
//            System.out.println(taskInList.length);
            if (taskInList.length == 4) {
                addDeadlineOrEvent(taskInList[0], taskInList[1],taskInList[2],taskInList[3]);
            } else {
                addToDo(taskInList[1],taskInList[2]);
            }
        }
    }

    static String getEventDueDate(Event event) {
        return event.getDueTime();
    }
    static String getDeadlineDueDate(Deadline deadline) {
        return deadline.getDueTime();
    }


    // delete file, add lines to write, put string together and write in file writer
    // NEVER DELETE WRITE THE FILE IN THE SAME METHOD
    private static void rewriteTasks() throws IOException{
        String path = "data/duke.txt";
        FileWriter fw = new FileWriter(path);
        ArrayList<String> taskListArray = new ArrayList<>();
        for (int i=0; i < list.size(); i++){
            if (list.get(i) instanceof ToDo) {
                System.out.println("task is a todo");
                String taskString = String.format("T | %s | %s", list.get(i).getIsDone() ? 1 : 0, list.get(i).getDescription());
                taskListArray.add(taskString);
            } else if (list.get(i) instanceof Deadline) {
                System.out.println("task is a deadline");
                String taskString = String.format("T | %s | %s | %s", list.get(i).getIsDone() ? 1 : 0, list.get(i).getDescription(), getDeadlineDueDate((Deadline) list.get(i)));
                taskListArray.add(taskString);
            } else {
                System.out.println("task is an event");
                String taskString = String.format("T | %s | %s | %s", list.get(i).getIsDone() ? 1 : 0, list.get(i).getDescription(), getEventDueDate((Event) list.get(i)));
                taskListArray.add(taskString);
            }
        }
        String taskListString = "";
        for (int j=0; j < list.size(); j++) {
            taskListString += taskListArray.get(j);
            if (j != list.size() -1) {
                taskListString += "\n";
            }
        }
        fw.write(taskListString);
        fw.close();
    }

    public static void main(String[] args) throws DukeException {

        try {
            File file = new File("data/duke.txt");
            System.out.println("Current Tasks:");
            printFileContents("data/duke.txt");
            getTasks();
            System.out.println(list);
        } catch (FileNotFoundException e) {
            System.out.println("File not found, added file");
        }

        String intro = "Hello! I'm Duke\nWhat can I do for you?";
        System.out.println("____________________________________________________");
        System.out.println(intro);
        System.out.println("____________________________________________________");

        Scanner scanner = new Scanner(System.in);


        try {
            while (scanner.hasNextLine()) {
                String input = scanner.nextLine();
                String[] inputArr = input.split(" ");
                System.out.println("____________________________________________________");
                if (inputArr[0].equals("list")) {
                    printList();
                } else if (inputArr[0].equals("mark")) {
                    int taskNum = Integer.parseInt(inputArr[1]);
                    try {
                        markTask(taskNum);
                        rewriteTasks();
                    } catch (IndexOutOfBoundsException | IOException e) {
                        System.out.println("Task does not exist!");
                    }
                    String output = String.format("Nice! I've marked this task as done:\n%s", list.get(taskNum - 1));
                    System.out.println(output);
                } else if (inputArr[0].equals("unmark")) {
                    int taskNum = Integer.parseInt(inputArr[1]);
                    try {
                        unmarkTask(taskNum);
                        rewriteTasks();
                    } catch (IndexOutOfBoundsException | IOException e) {
                        System.out.println("Task does not exist!");
                    }
                    String output = String.format("OK, I've marked this task as not done yet:\n%s", list.get(taskNum - 1));
                    System.out.println(output);
                } else if (inputArr[0].equals("delete")) {
                    try {
                        int taskNum = Integer.parseInt(inputArr[1]);
                        deleteTask(taskNum);
                        rewriteTasks();
                    } catch (IOException e) {

                    }

                } else if (inputArr[0].equals("todo") || inputArr[0].equals("deadline") || inputArr[0].equals("event")) {
                    try {
                        addTask(inputArr[0], input);
                        rewriteTasks();
                    } catch (IndexOutOfBoundsException | DukeException | IOException e) {
                        System.out.println(e.getMessage());
                        String output = String.format("Oops!! The description of a %s cannot be empty", inputArr[0]);
                        System.out.println(output);
                    }
                } else if (inputArr[0].equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    System.out.println("____________________________________________________");
                    scanner.close();
                } else {
                    try {
                        validate(input, inputArr[0]);
                    } catch (DukeException e) {
                        System.out.println("Oh no!! I'm sorry, but I don't know what that means :(");
                    }
                }
            }
        } catch (IllegalStateException e) {
            // just catching error
        }
    }


}

