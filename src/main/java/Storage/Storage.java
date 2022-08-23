package Storage;

import Exceptions.StoredFileException;

import Tasks.*;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Storage {

    private String path;

    public Storage(String path){
        this.path = path;
    }

    public TaskList obtain() throws StoredFileException{

        // read data
        Scanner fileReader = new Scanner(this.path);
        TaskList tasks = new TaskList();
        String contents;
        Task newTask;

        while(fileReader.hasNext()) {
            contents = fileReader.nextLine();
            newTask = readTask(contents);
            tasks.addTask(newTask);
        }

        fileReader.close();

        return tasks;


    }


    private Task readTask(String content) throws StoredFileException {
        try {
            String[] components = content.split(" | ");
            String type = components[0].strip();
            LocalDate localdate;

            switch (type) {
                case "T":
                    return new ToDo(
                            components[2].strip(),
                            components[1].strip().equals("true"));
                case "D":
                    localdate = LocalDateTime.parse(components[3].strip()).toLocalDate();
                    return new Deadline(
                            components[2].strip(),
                            localdate,
                            components[1].strip().equals("true"));
                case "E":
                    localdate = LocalDateTime.parse(components[3].strip()).toLocalDate();
                    return new Event(
                            components[2].strip(),
                            components[3].strip(),
                            components[1].strip().equals("true"));

                default:
                    throw new Exception();
            }

        } catch (Exception e)
        {
            throw new StoredFileException();
        }




    }


}
