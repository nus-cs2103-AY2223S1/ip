package Duck;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import Models.Deadline;
import Models.Event;
import Models.Todo;
import Quackceptions.UnallowedCharacterException;

public class Storage {
    File duckTxt;

    /**
     * Storage encapsulates the object of the file to be writen/read from
     * @param path path of the data to be stored
     * @param filename filename for data to be written into
     * @throws IOException is thrown when the file is not accessible
     */
    public Storage(String path, String filename) throws IOException {
        java.nio.file.Path filePath = java.nio.file.Paths.get(path);
        File duckTxt = new File(String.valueOf(path), filename);
        if (!java.nio.file.Files.exists(Path.of(path + filename))) {
            duckTxt.getParentFile().mkdirs();
            duckTxt.createNewFile();
        }
        assert duckTxt.exists() : "Path should exist by this point";
        this.duckTxt = duckTxt;
    }

    /**
     * reads the text file
     * @return returns a list containing all the information of the text file, if any
     */
    public List<Todo> readFromFile() {
        ArrayList<Todo> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(duckTxt))) {
            parseIndividualLine(list, reader);
            return list;
        } catch (ParseException e) {
            System.out.println("uh oh something went wrong in reading the file, try deleting it");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnallowedCharacterException u) {
            System.out.println(u);
        }
        return null;
    }
    /**
     * Abstracted function to handle for reading the file line by line
     * @param list ArrayList to be manipulated
     * @param reader BufferedReader to be used to read
     */
    private void parseIndividualLine(ArrayList<Todo> list, BufferedReader reader) throws IOException, ParseException, UnallowedCharacterException {
        String line;
        String[] readerArray;
        while ((line = reader.readLine()) != null) {
            readerArray = line.split(";");
            switch (readerArray[0]) {
            case ("T"):
                list.add(new Todo(readerArray[1], readerArray[2].equals("1")));
                break;
            case ("D"):
                list.add(new Deadline(readerArray[1], readerArray[3].equals("1"), Duck.dateStorageConverter(readerArray[2])));
                break;
            case ("E"):
                list.add(new Event(readerArray[1], readerArray[3].equals("1"), Duck.dateStorageConverter(readerArray[2])));
                break;
            default:
                throw new UnallowedCharacterException("Error Reading File: Field is not recognized");
            }
        }
    }
    /**
     * writeListToFile function writes the given list
     * into the file specified from the duckTxt variable
     * @param list the TaskList to write into duckTxt
     */
    public void writeListToFile(TaskList<Todo> list){
        try {
            FileWriter writer = new FileWriter(this.duckTxt, false);
            for(int i = 0; i < list.size(); i++){
                list.get(i).writeToFile(writer);
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("IOException at WriteListToFile!");
        }

    }
}
