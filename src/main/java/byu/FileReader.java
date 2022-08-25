package byu;

import java.io.*;
import java.util.Scanner;
import task.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class FileReader {

    File file;
    Scanner sc;
    InputStream is;
    ToDoList list;

    public FileReader(String filePath) throws IOException {

        /*
        is = getClass().getResourceAsStream("/data/Duke.txt");
        this.list = new ToDoList();
        if (is == null) {
            System.out.print("file is not found");
        }
    }
*/
        try {
            this.file = new File("./Duke.txt");
            this.sc = new Scanner(file);
            this.list = new ToDoList();
        } catch (FileNotFoundException e) {
            String TEXT_FILE = "./Duke.txt";
            Path textFilePath = Paths.get(TEXT_FILE);
            Files.createFile(textFilePath);
            this.sc = new Scanner(file);
            this.list = new ToDoList();
        }

    }


    public ToDoList load() {
/*
        try {
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);

            String line;
            while ((line = br.readLine()) != null) {
                String[] details = line.split(" \\| ");
                Task t;
                switch (details[0]) {
                    case "D":
                        t = new Deadlines(details[2], details[3]);
                        break;
                    case "E":
                        t = new Events(details[2], details[3]);
                        break;
                    case "T":
                        t = new ToDos(details[2]);
                        break;
                    default:
                        t = new Task("unknown");
                }
                if (details[1].equals("1")) {
                    t.markDone();
                }
                list.addTask(t);
            }
            is.close();
        } catch (IOException e) {
            System.out.print(e.getMessage());
        }
        return list;
    }
*/


        while (sc.hasNext()) {
            String line = sc.nextLine();
            String[] details = line.split(" \\| ");
            Task t;
            switch (details[0]) {
                case "D":
                    t = new Deadlines(details[2], details[3]);
                    break;
                case "E":
                    t = new Events(details[2], details[3]);
                    break;
                case "T":
                    t = new ToDos(details[2]);
                    break;
                default:
                    t = new Task("unknown");
            }
            if (details[1].equals("1")) {
                t.markDone();
            }
            list.addTask(t);
        }
        sc.close();
        return this.list;
    }



}
