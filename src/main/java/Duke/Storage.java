package Duke;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Deals with loading and saving tasks in the file
 */
public class Storage {

    /**
     * Reads lines from a file and stores them in a list.
     * @param filescanner Scanner to read from the file.
     * @return List containing all the tasks from a file.
     */
    public List<Task> readTasks(Scanner filescanner) {
        List<Task> lst = new ArrayList<>(0);
        while (filescanner.hasNextLine()) {
            String task = filescanner.nextLine();
            if(task.charAt(1) == 'D') {
                int startIndex = task.indexOf('(');
                String description = task.substring(7,startIndex);
                int endIndex = task.indexOf(')');
                String str = task.substring(startIndex+5,endIndex);
                int month = convertToInt(str.substring(0,3));
                int day = Integer.parseInt(str.substring(4,6));
                int year = Integer.parseInt(str.substring(7));
                LocalDate d1 = LocalDate.of(year,month,day);
                Task task1 = new Deadline(description,d1);
                char c = task.charAt(4);
                if(c == 'X'){
                    task1.markDone();
                }
                lst.add(task1);
            } else if(task.charAt(1)=='E') {
                int startIndex = task.indexOf('(');
                String description = task.substring(7,startIndex);
                int endIndex = task.indexOf(')');
                String str = task.substring(startIndex+5,endIndex);
                int month = convertToInt(str.substring(0,3));
                int day = Integer.parseInt(str.substring(4,6));
                int year = Integer.parseInt(str.substring(7));
                LocalDate d1 = LocalDate.of(year,month,day);
                Task task1 = new Event(description,d1);
                char c = task.charAt(4);
                if(c == 'X'){
                    task1.markDone();
                }
                lst.add(task1);
            } else {
                Task task1 = new ToDo(task.substring(7));
                char c = task.charAt(4);
                if(c == 'X'){
                    task1.markDone();
                }
                lst.add(task1);
            }
        }
        return lst;
    }

    /**
     * Adds tasks to file.
     * @param pathName Pathname of file
     * @param lst List of tasks to add to file.
     * @throws IOException If there is an error writing to file.
     */
    public void addTasks(String pathName,List<String> lst ) throws IOException {
        for (int i=0; i < lst.size(); i++) {
            writeToFile(pathName, lst.get(i));
        }
    }

    /**
     * Overwrite the current file.
     * @param pathName Pathname of file.
     * @param lst1 First list to write to file.
     * @param lst2 Second list to write to file.
     * @throws IOException If there is an error writing to file.
     */
    public void replaceTasks(String pathName, List<Task> lst1, List<Task> lst2) throws IOException {
        System.out.println("Code has been called to replace tasks");
        overwriteFile(pathName,lst1.get(0).toString());
        for (int i=1; i < lst1.size(); i++) {
            writeToFile(pathName, lst1.get(i).toString());
        }
        for (int i=0; i < lst2.size(); i++) {
            writeToFile(pathName, lst2.get(i).toString());
        }
    }

    /**
     * Writes to file.
     * @param filePath Pathname of file.
     * @param textToAdd Text to write to file.
     * @throws IOException If there is an error writing to file.
     */
    public void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath,true);
        fw.write(textToAdd);
        fw.write(System.getProperty("line.separator"));
        fw.close();
    }

    /**
     * Overwrites existing file.
     * @param filePath Pathname of file.
     * @param textToAdd Text to write to file.
     * @throws IOException If there is an error writing to file.
     */
    public void overwriteFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath,false);
        fw.write(textToAdd);
        fw.write(System.getProperty("line.separator"));
        fw.close();
    }

    public int convertToInt(String str){
        int month = 0;
        switch (str){
            case "Jan":
                month = 1;
                break;
            case "Feb":
                month = 2;
                break;
            case "Mar":
                month = 3;
                break;
            case "Apr":
                month = 4;
                break;
            case "May":
                month = 5;
                break;
            case "June":
                month = 6;
                break;
            case "July":
                month = 7;
                break;
            case "Aug":
                month = 8;
                break;
            case "Sep":
                month = 9;
                break;
            case "Oct":
                month = 10;
                break;
            case "Nov":
                month = 11;
                break;
            case "Dec":
                month = 12;
                break;
        }
        return month;
    }

}
