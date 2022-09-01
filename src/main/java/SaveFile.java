import javafx.util.Pair;

import java.io.*;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class SaveFile {
  private static final String SAVE_FILE_NAME = ".data.txt";
  private static final String TEMPORARY_FILE_NAME = ".data_temp.txt";
  private static final File saveFile = new File(SAVE_FILE_NAME);
  private static final File temporaryFile = new File(TEMPORARY_FILE_NAME);
  private static ArrayList<SaveLine> fileData;

  /**
   * Guarantees the existence of the save file. This function should only be
   * called once in the beginning.
   *
   * @throws IOException if the file failed to be created.
   */
  private static void guaranteeSaveFile() throws IOException {
    saveFile.createNewFile();
  }

  /**
   * Loads the contents of the save file into fileData.
   *
   * @throws IOException if the file failed to be created or read.
   */
  public static void loadSaveFile() throws IOException {
    guaranteeSaveFile();
    FileReader fileReader = new FileReader(saveFile);
    BufferedReader bufferedReader = new BufferedReader(fileReader);
    fileData = (ArrayList<SaveLine>) bufferedReader.lines().map(SaveLine::of).collect(Collectors.toList());
  }

  public static void addData(String infoType, String ... nameData) throws IOException {
    fileData.add(new SaveLine(infoType, nameData));
    saveFile();
  }

  public static void addData(SaveLine saveLine) throws IOException {
    fileData.add(saveLine);
    saveFile();
  }

  public static void removeData(String infoType, String ... nameData) throws IOException {
    fileData.remove(new SaveLine(infoType, nameData));
    saveFile();
  }

  public static void saveFile() throws IOException {
    guaranteeSaveFile();
    temporaryFile.delete();
    temporaryFile.createNewFile();
    FileWriter temporaryFileWriter = new FileWriter(temporaryFile);
    for (SaveLine i : fileData) {
      temporaryFileWriter.append(i.toString());
      temporaryFileWriter.append('\n');
    }
    temporaryFileWriter.close();
    saveFile.delete();
    temporaryFile.renameTo(saveFile);
  }

  public static ArrayList<SaveLine> getFileData() {
    return fileData;
  }
}
