package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;
import java.util.Scanner;



public class Container {
    private File file;
    private String filepath;

    public Container(String filepath) throws DukeException {
        this.filepath = filepath;
        File newFile = new File(filepath);
        if (!newFile.exists()) {
            try {
                newFile.getParentFile().mkdir();
                newFile.createNewFile();
            } catch (IOException e) {
                throw new DukeException(e.getMessage());
            }
        }
        this.file = newFile;
    }

    public TreeMap<String,Contact> load() throws DukeException {
        TreeMap<String,Contact> contacts = new TreeMap<>();
        try {
            Scanner sc = new Scanner(this.file);
            while (sc.hasNextLine()) {
                String message = sc.nextLine();
                String[] messageArr = message.split("#");
                String name = messageArr[0];
                Contact contact = new Contact(name,messageArr[1]);
                contacts.put(name,contact);
            }
        } catch (FileNotFoundException e) {
            throw new DukeException("File not found!");
        }
        return contacts;

    }

    public void store(TreeMap<String,Contact> contacts) throws DukeException {
        try {
            FileWriter fw = new FileWriter(filepath);
            String message = "";
            for (Map.Entry<String,Contact> set: contacts.entrySet()) {
                message += set.getValue().parse();
                message += "\n";
            }
            fw.write(message);
            fw.close();
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
    }
}