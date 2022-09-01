package cleverNotBot;

import java.io.*;
import java.util.ArrayList;

public class Storage {
    private String fileLocation = "/data/cleverNotBot.txt";
    private File file;

    public Storage(){
        this.file = new File(this.fileLocation);
    }

    public ArrayList<Task> getTasksFromFile() {
        ArrayList<Task> tasks = new ArrayList<>();
        String line;
        try{
            BufferedReader reader = new BufferedReader(new FileReader(fileLocation));
            while((line = reader.readLine()) != null){
                Task task = convertLineToTask(line);
                tasks.add(task);
            }
        } catch (FileNotFoundException e){
            System.out.println("Error! File does not exist! Creating a new file at location!");
            try{
                file.getParentFile().mkdirs();
                file.createNewFile();
            } catch (IOException errorCreate){
                System.out.println("An error has occurred when trying to create new file");
            }
        } catch (IOException errorObtain){
            System.out.println("An error has occurred when trying to obtain data from the save file.");
        }
        return tasks;
    }

    public void writeToFile(ArrayList<Task> tasks) {
        try {
            // if the file above doesn't exist, create it.
            if(!file.getParentFile().exists()){
                file.getParentFile().mkdir();
            }
            FileWriter fw = new FileWriter(file);
            StringBuilder op = new StringBuilder();
            int counter = 0;
            for (Task task: tasks){
                String taskToStorage = "";
                if (task.getTaskType().equals("T")){
                    taskToStorage = String.format("%s | %d | %s",
                            task.getTaskType(),task.isCompleted()? 1:0,task.getName());
                } else if(task.getTaskType().equals("D") || task.getTaskType().equals("E")){
                    taskToStorage = String.format("%s | %d | %s | %s",
                            task.getTaskType(),task.isCompleted()? 1:0,task.getName(),task.getTime());
                } else {
                    System.out.println("Warning! Illegal entries has been detected!");
                }

                op.append(taskToStorage);
                counter++;
                if(counter < tasks.size()){
                    op.append("\n");
                }
            }
            String result = op.toString();
            fw.write(result);
            fw.close();
        } catch(IOException e){
            System.out.println("An error has occurred during saving of file!");
        }
    }

    private Task convertLineToTask(String line) {
        String[] content = line.split(" \\| ");
        /* debug line
        System.out.println(file.getAbsolutePath());
        System.out.println(String.join(",",content));
         */
        //"1".equals(content[1]) is to convert it to boolean
        switch (content[0]) {
        case "T":
            return new ToDo(content[2], "1".equals(content[1]));
        case "D":
            return new Deadline(content[2], "1".equals(content[1]), content[3]);
        default:
            // new event
            return new Event(content[2], "1".equals(content[1]), content[3]);
        }
    }

}
