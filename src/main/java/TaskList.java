import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class TaskList {

    private final ArrayList<Task> myList;

    TaskList() {
        this.myList = new ArrayList<>();

        // Read text file if it exists
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

    void textToObject(String line) {
        if (Character.toString(line.charAt(1)).equals("T")){
            myList.add(new Todo(line.substring(7)));
        } else if (Character.toString(line.charAt(1)).equals("D")){
            myList.add(new Deadline(line.substring(7).split(" \\(by")[0], 
                line.split("\\(by:")[1].split("\\)")[0])
            );
        } else if (Character.toString(line.charAt(1)).equals("E")){
            myList.add(new Event(line.substring(7).split(" \\(at")[0], 
                line.split("\\(at:")[1].split("\\)")[0])
            );
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

    void markTask(Integer itemNumber) {
        myList.get(itemNumber).markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("["
        + myList.get(itemNumber).getStatusIcon() + "] "
        + myList.get(itemNumber).getDescription());
    }

    void unmarkTask (Integer itemNumber) {
        myList.get(itemNumber).markAsUndone();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("["
        + myList.get(itemNumber).getStatusIcon() + "] "
        + myList.get(itemNumber).getDescription());
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

    void removeTask(Integer itemNumber) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(myList.get(itemNumber));
        myList.remove(myList.get(itemNumber));
        System.out.println("Now you have " + myList.size() + " in the list.");
    }
 
}