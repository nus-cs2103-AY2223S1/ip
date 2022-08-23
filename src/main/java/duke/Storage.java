package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.time.LocalDate;

public class Storage {
    private String filepath;

    public Storage(String filePath) {
        this.filepath = filePath;
    }

    public ArrayList<Task> loadfromFile() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(" d/MM/uuuu");
        ArrayList<Task> memo = new ArrayList<>();

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(this.filepath));
            String row = reader.readLine();

            while (row != null) {
                //Example of line: [T] [X] read book
                //[D] [ ] Assignment 1 /by 11 09 2022
                String[] parse = row.split(" ", 3);
                Task task = null;

                if (parse[0].equals("[T]")) {
                    task = new Todo(parse[2]);
                } else if (parse[0].equals("[E]")) {
                    String[] parse2 = parse[2].split("/at", 2);
                    LocalDate localDate1 = LocalDate.parse(parse2[1], formatter);
                    task = new Event(parse[2], localDate1);
                } else {
                    String[] parse3 = parse[2].split("/by", 2);
                    LocalDate localDate2 = LocalDate.parse(parse3[1], formatter);
                    task = new Deadline(parse3[2], localDate2);
                }

                if (parse[1].equals("[X]")) {
                    task.mark();
                }
                memo.add(task);
                row = reader.readLine();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        return memo;
    }

    public void savetoFile(List<String> input) {
        try {
            new File(this.filepath).getParentFile().mkdirs();
            FileWriter writer = new FileWriter(this.filepath);

            for (String str : input) {
                writer.write(String.format("%s\n", str));
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
