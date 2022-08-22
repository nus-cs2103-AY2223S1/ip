import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    /*
     * A method that takes in a string input and performs actions based on the string input
     * */
    public static void DukeTask() throws DukeException, IOException {
        Scanner scanner = new Scanner(System.in);
        String pathName = "data/Duke2.txt";
        File f = new File(pathName);
        if(f.createNewFile()){
            System.out.println("new file created");
        } else {
            System.out.println("File already exists");
        }
        Scanner filescanner = new Scanner(f);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        List<Task> lst = new ArrayList<>();
        int count=0;
        //This part, im loading all the strings in the pre existing file
        String[] loadedTasks = new String[100];
        int taskNum = 0;
        while(filescanner.hasNextLine()){
            String task = filescanner.nextLine();
            loadedTasks[taskNum] = task;
            taskNum++;
        }
        final int fileTask = taskNum; //we do not want to modify the fileTask after

        String str;

        do{
            str = scanner.next(); //This will check for the action word
            if(str.equals("bye")){
                break;
            }else if(str.equals("delete")) {
                System.out.println("Noted. I've removed this task:");
                int index = scanner.nextInt();
                scanner.nextLine();
                Task deleted = lst.get(index-1);
                System.out.println(deleted.toString());
                lst.remove(index-1);
                count--;
                System.out.println("Now you have "+ count + " tasks in the list.");

            }else if(str.equals("deadline")){
                System.out.println("Got it. I've added this task: ");
                String description = "";
                String dateline="";
                while(scanner.hasNext()){
                    String temp = scanner.next();
                    if(temp.equals("/by")){
                        break;
                    }
                    description = description + temp +" ";
                }
                dateline = scanner.nextLine();
                LocalDate d1 = LocalDate.parse(dateline.substring(1));
                Task task = new Deadline(description, d1);
                lst.add(task);
                count++;
                System.out.println(task.toString());
                int total = fileTask+count;
                System.out.println("Now you have "+ total + " tasks in the list.");
            } else if(str.equals("todo")){
                String todoDes = scanner.nextLine();
                if(todoDes.equals("")){
                    throw new DukeException("OOPS!!! The description of a todo cannot be empty ");
                }
                System.out.println("Got it. I've added this task: ");
                Task task = new ToDo(todoDes);
                lst.add(task);
                count++;
                System.out.println(task.toString());
                int total = fileTask+count;
                System.out.println("Now you have "+ total + " tasks in the list.");
            } else if(str.equals("event")){
                System.out.println("Got it. I've added this task: ");
                String description = "";
                String time="";
                while(scanner.hasNext()){
                    String temp = scanner.next();
                    if(temp.equals("/at")){
                        break;
                    }
                    description = description + temp +" ";
                }
                time = scanner.nextLine();
                LocalDate d1 = LocalDate.parse(time.substring(1));
                Task task = new Event(description, d1);
                lst.add(task);
                count++;
                System.out.println(task.toString());
                int total = fileTask+count;
                System.out.println("Now you have "+ total + " tasks in the list.");
            }
            else if(str.equals("list")){ //To print out the list, we read from the file first, then read from the current tasks
                System.out.println("Here are the tasks in your list");
                for(int i=0; i<fileTask;i++){
                    System.out.println(i+1+"."+loadedTasks[i]);
                }
                if(!lst.isEmpty()) {
                    for (int i = 0; i < count; i++) {
                        System.out.println(i + 1+ fileTask + "." + lst.get(i));
                        taskNum++;
                    }
                }
                scanner.nextLine();
            }
            else if(str.equals("unmark")){
                String strnum = scanner.next();
                int num = Integer.valueOf(strnum);
                lst.get(num-1).isDone = false;
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(lst.get(num-1).toString());
                scanner.nextLine();
            }else if(str.equals("mark")){
                String strnum = scanner.next();
                int num = Integer.valueOf(strnum);
                lst.get(num-1).isDone = true;
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(lst.get(num-1).toString());
                scanner.nextLine();
            }
            else{
                for(int i=0;i<count;i++){
                    writeToFile(pathName,(lst.get(i).toString()));
                }
                throw new DukeException(":( OOPS!!! I'm sorry, but I don't know what that means :-(");
            }

        }  while(!str.equals("bye"));

        if (str.equals("bye")) {
            for(int i=0;i<count;i++){
                writeToFile(pathName,lst.get(i).toString());
            }
            System.out.println("Bye. Hope to see you again soon!");
        }

    }

    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath,true);
        fw.write(textToAdd);
        fw.write(System.getProperty("line.separator"));
        fw.close();


    }

    public static void main(String[] args)  {
        try {
            DukeTask();
        }catch(DukeException e) {
            System.out.println(e.getMessage());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
