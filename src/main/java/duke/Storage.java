package duke;

import exceptions.EmptyNameException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private final String path;
    private final TaskList taskList;

    public Storage(String path, TaskList taskList) {
        this.path = path;
        this.taskList = taskList;
    }

    // creates file and folder if it doesn't exist
    public void loadFile() throws IOException {
        File file = new File(this.path);
        File parentFile = file.getParentFile();

        if (parentFile != null && !parentFile.exists() && !parentFile.mkdirs()) {
            throw new IllegalStateException("Error creating directory" + parentFile);
        }
        file.createNewFile();

        Scanner scanner = new Scanner(file);
        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            taskList.readTaskFromLoader(line);
        }

        scanner.close();

    }

    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath,true);
        fw.write(textToAdd + "\n");
        fw.close();
    }

    public void addLineToFile(String command) {
        try {
            String[] combiStr;
            String[] split = command.split(" ", 2);
            if(split.length < 2) {
                throw new EmptyNameException();
            }
            String type = split[0];
            if(type.equals(TaskTypeEnum.todo.toString())){
                String name = split[1];
                combiStr = new String[]{type, "0", name};
            } else {
                String[] split1 = split[1].split("/", 2);
                String name = split1[0];
                String info = split1[1];
                if(type.equals(TaskTypeEnum.deadline.toString()) || type.equals("D")){
                    combiStr = new String[]{type, "0", name, info};
                } else {
                    combiStr = new String[]{type, "0", name, info};
                }
            }
            StringBuilder ret = new StringBuilder();
            for(int i = 0 ; i <combiStr.length; i++){
                if(i != 0){
                    ret.append("|").append(combiStr[i].trim());
                }  else {
                    ret.append(combiStr[i].trim());
                }
            }
            writeToFile(this.path, ret.toString());

        } catch (EmptyNameException | IOException e) {
            System.out.println(e.getMessage());
        }

    }


    public void toggleDone(int index, boolean toDone) {
        File file = new File(this.path);
        try{
            Scanner scanner = new Scanner(file);
            StringBuilder builder = new StringBuilder();
            int currentIndex = 0;
            while(scanner.hasNextLine()){
                if(currentIndex == index){
                    String oldString = scanner.nextLine();

                    String newString = toDone
                            ? oldString.replaceFirst("0", "1")
                            : oldString.replaceFirst("1", "0") ;
                    builder.append(newString).append(System.lineSeparator());
                } else {
                    builder.append(scanner.nextLine()).append(System.lineSeparator());
                }
                currentIndex++;
            }
            String content = builder.toString();

            FileWriter writer = new FileWriter(this.path);
            writer.append(content);
            writer.flush();


        }catch(FileNotFoundException e){
            System.out.println(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void deleteLine(int index){
        File file = new File(this.path);
        try{
            Scanner scanner = new Scanner(file);
            StringBuilder builder = new StringBuilder();
            int currentIndex = 0;
            while(scanner.hasNextLine()){
                if(currentIndex != index){
                    builder.append(scanner.nextLine()).append(System.lineSeparator());
                } else {
                    scanner.nextLine();
                }
                currentIndex++;
            }
            String content = builder.toString();

            FileWriter writer = new FileWriter(this.path);
            writer.append(content);
            writer.flush();


        }catch(FileNotFoundException e){
            System.out.println(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }





}
