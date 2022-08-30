import javafx.util.Pair;

import java.io.*;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class SaveFile {
  private static final String SAVE_FILE_NAME = ".data.txt";
  private static final String TEMPORARY_FILE_NAME = ".data_temp.txt";
  private static final File saveFile = new File(SAVE_FILE_NAME);
  private static final File temporaryFile = new File(TEMPORARY_FILE_NAME);
  private static FileWriter saveFileWriter;
  private static FileWriter temporaryFileWriter;
  private static ArrayList<Pair<String, Pair<String, String>[]>> fileData;

  /**
   * Guarantees the existence of the save file. This function should only be
   * called once in the beginning.
   *
   * @throws IOException if the file failed to be created.
   */
  private static void guaranteeSaveFile() throws IOException {
    saveFile.createNewFile();
    saveFileWriter = new FileWriter(saveFile);
    temporaryFileWriter = new FileWriter(temporaryFile);
  }

  /**
   * Loads the contents of the save file into fileData.
   *
   * @throws IOException if the file failed to be created or read.
   */
  private static void loadSaveFile() throws IOException {
    guaranteeSaveFile();
    FileReader fileReader = new FileReader(saveFile);
    BufferedReader bufferedReader = new BufferedReader(fileReader);
    fileData = (ArrayList<Pair<String, Pair<String, String>[]>>) bufferedReader.lines().map(x -> {
      int left = 0, right = 0;
      // read the information type
      while (right < x.length() && x.charAt(right) != ' ') {
        ++right;
      }
      String infoType = x.substring(left, right);
      left = right;
      // space
      ++right;
      left = right;
      // read the data
      ArrayList<Pair<String, String>> typeData = new ArrayList<>();
      while (right < x.length()) {
        // space
        ++right;
        left = right;
        // read the type
        while (x.charAt(right) != ' ') {
          ++right;
        }
        String type = x.substring(left, right);
        left = right;
        // space
        ++right;
        left = right;
        // read the amount of data
        while (x.charAt(right) != ' ') {
          ++right;
        }
        int amount = Integer.parseInt(x.substring(left, right));
        left = right;
        // space
        ++right;
        left = right;
        // read data
        right += amount;
        String data = x.substring(left, right);
        left = right;
        // conclude type data pair
        typeData.add(new Pair<>(type, data));
      }
      @SuppressWarnings("unchecked")
      Pair<String, Pair<String, String>[]> ret = new Pair<String, Pair<String, String>[]>("", new Pair[0]);
      return new Pair<>(infoType, typeData.toArray(ret.getValue()));
    }).collect(Collectors.toList());
  }

  /**
   * Adds a line of information to the save file.
   *
   * @param infoType The type of information.
   * @param data The content of information, an array of key value pairs.
   * @throws IOException if the file failed to be created or written into.
   */
  private static void addData(String infoType, Pair<String, String>[] data) throws IOException {
    fileData.add(new Pair<>(infoType, data));
    saveFile();
  }

  private static void removeData(String infoType, Pair<String, String>[] data) throws IOException {
    fileData.remove(new Pair<>(infoType, data));
    saveFile();
  }

  private static void saveFile() throws IOException {
    guaranteeSaveFile();
    temporaryFile.delete();
    temporaryFile.createNewFile();
    for (Pair<String, Pair<String, String>[]> i : fileData) {
      temporaryFileWriter.append(i.getKey());
      for (Pair<String, String> j : i.getValue()) {
        temporaryFileWriter.append(' ');
        temporaryFileWriter.append(j.getKey());
        temporaryFileWriter.append(' ');
        temporaryFileWriter.append(Integer.toString(j.getValue().length()));
        temporaryFileWriter.append(' ');
        temporaryFileWriter.append(j.getValue());
      }
      temporaryFileWriter.append('\n');
    }
    saveFile.delete();
    temporaryFile.renameTo(saveFile);
  }
}
