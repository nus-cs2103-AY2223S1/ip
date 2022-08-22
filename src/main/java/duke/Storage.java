package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Storage {

    public void readFile(String input, TaskList returnList) {
        try {
            File myObj = new File(input);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                stringToTask(data,returnList);
            }
            myReader.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            createFile(input);
        } catch (DukeException e) {
            e.printStackTrace();
        }
    }
    private static void stringToTask(String data,TaskList taskList) throws DukeException {
        if(data.length() == 0){}
        char type = (data.charAt(0));
        boolean done = (data.charAt(1)) == ('Y');
        if(type == 'T'){
            String description = data.substring(2);
            taskList.addTask(new Todo(description));
        }
        else if (type == 'D'){
            int indexOfDate = data.indexOf("/");
            String description = data.substring(2,indexOfDate);
            String date = data.substring(indexOfDate +1);
            taskList.addTask(new Deadline(description,date));
        }
        else if (type == 'E'){
            int indexOfDate = data.indexOf("/");
            String description = data.substring(2,indexOfDate);
            String date = data.substring(indexOfDate +1);
            taskList.addTask(new Deadline(description,date));
        }

    }
    private static void createFile(String input) {
        try {
            File myObj = new File(input);
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public void writeFile(List<Task> input){
        try {
            FileWriter myWriter = new FileWriter("duke.txt");
            for(Task cur: input) {
                myWriter.write(cur.parseTask() + '\n');
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
