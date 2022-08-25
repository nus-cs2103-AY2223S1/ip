import AlanExceptions.AlanException;
import AlanExceptions.FileCorruptException;

import java.util.ArrayList;
import java.util.List;

public class FileParser {
    public List<Task> parseFile(String data) throws AlanException {
        List<Task> result = new ArrayList<>();
        String[] lines = data.split("\n");
        for (String line : lines) {
            String[] d = line.split("\\|", 5);
            if (line.length() > 0) {
                switch (d[0]) {
                    case "D":
                        result.add(new Deadline(
                                new ParsedData(d[1], d[2], d[3], d[4])));
                        break;
                    case "E":
                        result.add(new Event(
                                new ParsedData(d[1], d[2], d[3], d[4])));
                        break;
                    case "T":
                        result.add(new Todo(
                                new ParsedData(d[1], d[2], d[3], d[4])));
                        break;
                    default:
                        throw new FileCorruptException();
                }
            }
        }
        return result;
    }

}
