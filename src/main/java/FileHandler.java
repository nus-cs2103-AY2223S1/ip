import java.io.*;

public class FileHandler {

    private static final String FILE_NAME = "Duke.txt";
    private static final String RELATIVE_PATH = "data/Duke.txt";

    public static String readFromFile() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(RELATIVE_PATH));
        StringBuilder outputMessage = new StringBuilder();
        String line = reader.readLine();
        while(line != null) {
            outputMessage.append(line);
            outputMessage.append("\n");
            line = reader.readLine();
        }
        reader.close();
        return outputMessage.toString();

    }

    public static void writeToFile(String content) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(RELATIVE_PATH));
        writer.write(content);
        writer.close();
    }

    public static boolean editLine(String content, int lineNum) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(RELATIVE_PATH));
        StringBuilder outputMessage = new StringBuilder();
        String line = reader.readLine();
        int counter = 0;
        while(line != null) {
            counter++;
            if (counter == lineNum) {
                outputMessage.append(content);
            } else {
                outputMessage.append(line);
            }
            outputMessage.append("\n");
            line = reader.readLine();
        }
        reader.close();
        writeToFile(outputMessage.toString());
        return lineNum <= counter;
    }

    public static void addLine(String content) throws IOException {
        String fileContent = readFromFile();
        fileContent += content + "\n";
        writeToFile(fileContent);
    }

}
