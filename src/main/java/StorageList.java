import java.util.ArrayList;
import java.util.List;

/**
 * StorageList is a class that stores a list of strings
 */
public class StorageList {
  private final List<String> list = new ArrayList<>();

  /**
   * Adds a string to the list
   * @param s String to be added to the list
   */
  public void add(String s) {
    list.add(s);
  }

  /**
   * Returns the list of strings
   * @return List of strings
   */
  @Override
  public String toString() {
    String ans = "";
    for (int i = 0; i < list.size(); i++) {
      ans += (i+1) + ". " + list.get(i) + "\n\t ";
    }
    return ans;
  }
}
