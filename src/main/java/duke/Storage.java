package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {

    public List<Task> readFile(String input) {
        List<Task> returnList = new ArrayList<>();
        try {
            File myObj = new File(input);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                returnList.add(stringToTask(data));
            }
            myReader.close();
            return returnList;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            createFile(input);
        } catch (DukeException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Task stringToTask(String data) throws DukeException {
        if(data.length() == 0){
            return null;
        }
        char type = (data.charAt(0));
        boolean done = (data.charAt(1)) == ('Y');
        Task task = null;
        if(type == 'T'){
            String description = data.substring(2);
            task = new Todo(description);
        }
        else if (type == 'D'){
            int indexOfDate = data.indexOf("/");
            String description = data.substring(2,indexOfDate);
            String date = data.substring(indexOfDate +1);
            task = new Deadline(description,getDate(date));
        }
        else if (type == 'E'){
            int indexOfDate = data.indexOf("/");
            String description = data.substring(2,indexOfDate);
            String date = data.substring(indexOfDate +1);
            task = new Event(description,getDate(date));
        }
        if(done && task != null){
            task.setDone();
        }
        return task;

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

    private static LocalDate getDate(String input) throws DukeException {
        try {
            return LocalDate.parse(input);
        } catch (DateTimeParseException e){
            throw new DukeException("☹ OOPS!!! Please format your date as yyyy-mm-dd format (e.g., 2019-10-15)");
        }
    }
}
