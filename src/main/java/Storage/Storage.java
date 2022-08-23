package Storage;

import Exceptions.StoredFileException;

import Tasks.*;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Storage {

    private Path filePath;

    public Storage(String fileName){
        String dirPath = System.getProperty("user.dir");
        this.filePath = Paths.get(dirPath, "data" ,fileName + ".txt");
    }

    public Storage(String dir, String fileName){
        String dirPath = System.getProperty("user.dir");
        this.filePath = Paths.get(dirPath, dir, fileName + ".txt");
    }

    public TaskList obtain() throws StoredFileException{

        Path filePath = this.filePath;

        try {

            Scanner fileReader = new Scanner(filePath);
            TaskList tasks = new TaskList();
            String contents;
            Task newTask;

            while(fileReader.hasNext()) {
                contents = fileReader.nextLine();

                System.out.println(contents);
                newTask = readTask(contents);
                tasks.addTask(newTask);
            }

            fileReader.close();
            return tasks;
        } catch (IOException e) {
            System.out.println("IO Exception e");
            return null;
        }





    }


    private Task readTask(String content) throws StoredFileException {
        try {
            String[] components = content.split(" \\| "); // Here it is very important
            String type = components[0].strip();
            LocalDate localdate;

            switch (type) {
                case "T":
                    return new ToDo(
                            components[2].strip(),
                            components[1].strip().equals("true"));
                case "D":
                    localdate = LocalDate.parse(components[3].strip());

                    return new Deadline(
                            components[2].strip(),
                            localdate,
                            components[1].strip().equals("true"));
                case "E":

                    return new Event(
                            components[2].strip(),
                            components[3].strip(),
                            components[1].strip().equals("true"));

                default:
                    throw new Exception();
            }

        } catch (Exception e)
        {
            System.out.println("StoredFiledException!");
            throw new StoredFileException();
        }




    }



}
