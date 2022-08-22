import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TaskList {

    private final ArrayList<Task> myList;

    TaskList() {
        this.myList = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("duke.txt"));
            String line = br.readLine();
            while (line != null) {
                textToObject(line);
                line = br.readLine();
            }
            br.close();
        } catch (FileNotFoundException e){
            System.out.println("Existing text file not found, creating new file! :)");
        } catch (IOException e) {
            System.out.println("IO Exception Error.");
            e.printStackTrace();
        }
    }

    void addTask(Task myTask) {
        try {
            myList.add(myTask);
            System.out.println("Got it. I've added this task:");
            System.out.println(myTask);
            System.out.println("Now you have " + myList.size() + " task(s) in the list.");
        } catch (Exception e) {
            System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
        }
    }

    private LocalDate dateFormatter(String myDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
        LocalDate curDate = LocalDate.parse(myDate, formatter);
        return curDate;
    }

    void textToObject(String line) {
        String taskType = Character.toString(line.charAt(1));
        switch (taskType){
            case "T":
                myList.add(new Todo(line.substring(7)));
                break;
            case "D":
                myList.add(new Deadline(line.substring(7).split(" \\(by")[0], 
                    this.dateFormatter(line.split("\\(by: ")[1].split("\\)")[0]))
                );
                break;
            case "E":
                myList.add(new Event(line.substring(7).split(" \\(at")[0], 
                    this.dateFormatter(line.split("\\(at: ")[1].split("\\)")[0]))
                );
                break;
            default:
                break;
        }
    }

    void listTasks() {
        if (myList.size() == 0) {
            System.out.println("You have no tasks in your list.");
        } else {
            System.out.println("Here are the tasks in your list: ");
            for(int i = 0; i<myList.size(); i++) {
                System.out.println(i+1 + "." + myList.get(i));
            }
        }
    }

    void markTask(Integer itemNumber) {
        myList.get(itemNumber).markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("["
        + myList.get(itemNumber).getStatusIcon() + "] "
        + myList.get(itemNumber).getDescription());
    }

    void removeTask(Integer itemNumber) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(myList.get(itemNumber));
        myList.remove(myList.get(itemNumber));
        System.out.println("Now you have " + myList.size() + " in the list.");
    }

    void saveTasks() {
        try {
            FileWriter myWriter = new FileWriter("duke.txt");
            for (Task myTask : myList) {
                myWriter.write(myTask.toString() + "\n");
            }
            myWriter.close();
          } catch (IOException e) {
            System.out.println("Error saving tasks.");
            e.printStackTrace();
          }
    }

    void unmarkTask (Integer itemNumber) {
        myList.get(itemNumber).markAsUndone();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("["
        + myList.get(itemNumber).getStatusIcon() + "] "
        + myList.get(itemNumber).getDescription());
    }
}