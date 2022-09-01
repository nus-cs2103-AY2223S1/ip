import javafx.util.Pair;

import java.util.ArrayList;

public class SaveLine {
  private String infoType;
  private ArrayList<Pair<String, String>> keyValuePairs;

  public SaveLine(String infoType, String ... nameData) {
    this.infoType = infoType;
    this.keyValuePairs = new ArrayList<>();
    for (int i = 0; i < nameData.length; i += 2) {
      this.keyValuePairs.add(new Pair<>(nameData[i], nameData[i + 1]));
    }
  }

  public SaveLine(String infoType, ArrayList<Pair<String, String>> nameData) {
    this.infoType = infoType;
    this.keyValuePairs = nameData;
  }

  @Override
  public String toString() {
    StringBuilder ret = new StringBuilder(infoType);
    for (Pair<String, String> i : keyValuePairs) {
      ret.append(' ');
      ret.append(i.getKey());
      ret.append(' ');
      ret.append(i.getValue().length());
      ret.append(' ');
      ret.append(i.getValue());
    }
    return ret.toString();
  }

  @Override
  public boolean equals(Object rhs) {
    if (rhs instanceof SaveLine) {
      SaveLine rhsSaveLine = (SaveLine) rhs;
      return rhsSaveLine.infoType.equals(infoType) && rhsSaveLine.keyValuePairs.equals(keyValuePairs);
    }
    return false;
  }

  public static SaveLine of(String line) {
    System.out.println("reading" + line);
    int left = 0, right = 0;
    // read the information type
    while (right < line.length() && line.charAt(right) != ' ') {
      ++right;
    }
    String infoType = line.substring(left, right);
    left = right;
    // read the data
    ArrayList<Pair<String, String>> typeData = new ArrayList<>();
    while (right < line.length()) {
      // space
      ++right;
      left = right;
      // read the type
      while (line.charAt(right) != ' ') {
        ++right;
      }
      String type = line.substring(left, right);
      left = right;
      // space
      ++right;
      left = right;
      // read the amount of data
      while (line.charAt(right) != ' ') {
        ++right;
      }
      int amount = Integer.parseInt(line.substring(left, right));
      left = right;
      // space
      ++right;
      left = right;
      // read data
      right += amount;
      String data = line.substring(left, right);
      left = right;
      // conclude type data pair
      typeData.add(new Pair<>(type, data));
    }
    System.out.println("infoType: " + infoType);
    System.out.println("typeData length: " + typeData.size());
    for (Pair<String, String> i : typeData) {
      System.out.println(i.getKey() + " : " + i.getValue());
    }
    return new SaveLine(infoType, typeData);
  }

  public String getValue(String key) {
    for (Pair<String, String> i : keyValuePairs) {
      if (i.getKey().equals(key)) {
        return i.getValue();
      }
    }
    return "";
  }

  public void setNameData(String key, String value) {
    for (int i = 0; i < keyValuePairs.size(); ++i) {
      if (keyValuePairs.get(i).getKey().equals(key)) {
        keyValuePairs.set(i, new Pair<>(key, value));
        return;
      }
    }
    addNameData(key, value);
  }

  public String getInfoType() {
    return infoType;
  }

  public void setInfoType(String infoType) {
    this.infoType = infoType;
  }

  public void addNameData(String key, String value) {
    keyValuePairs.add(new Pair<>(key, value));
  }
}
