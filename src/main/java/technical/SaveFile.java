package technical;
/**
 * The class which organises the save file.
 * @author Nicholas Patrick
 */

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
   * Guarantees the existence of the save file. Only needs to be called in the
   * beginning but there's no harm in calling it many times.
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

  /**
   * Adds data into fileData.
   *
   * @param infoType The type of information.
   * @param nameData The details of the data.
   * @throws IOException if the file failed to be created or written into.
   */
  public static void addData(String infoType, String ... nameData) throws IOException {
    fileData.add(new SaveLine(infoType, nameData));
    saveFile();
  }

  /**
   * Adds data into fileData.
   *
   * @param saveLine The necessary information packed into a SaveLine.
   * @throws IOException if the file failed to be created or written into.
   */

  public static void addData(SaveLine saveLine) throws IOException {
    fileData.add(saveLine);
    saveFile();
  }

  /**
   * Removes data from the fileData.
   *
   * @param infoType The type of information
   * @param nameData The details of the data.
   * @throws IOException if the file failed to be created or written into.
   */
  public static void removeData(String infoType, String ... nameData) throws IOException {
    fileData.remove(new SaveLine(infoType, nameData));
    saveFile();
  }

  /**
   * Saves the file with the contents of fileData.
   *
   * @throws IOException if the file failed to be created or written into.
   */
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

  // TODO: Prevent fileData from being edited outside this class.
  /**
   * Obtains the fileData.
   *
   * @return fileData itself.
   */
  public static ArrayList<SaveLine> getFileData() {
    return fileData;
  }
}
