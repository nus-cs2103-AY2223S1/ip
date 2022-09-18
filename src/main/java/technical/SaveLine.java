package technical;

import java.util.ArrayList;

import javafx.util.Pair;

/**
 * The class for encapsulating information in a line for the SaveFile.
 * @author Nicholas Patrick
 */
public class SaveLine {
    private String infoType;
    private final ArrayList<Pair<String, String>> keyValuePairs;

    /**
     * Constructs a SaveLine from variadic arguments.
     *
     * @param infoType The name of the information.
     * @param nameData The details of the data.
     */
    public SaveLine(String infoType, String ... nameData) {
        assert(nameData.length % 2 == 0);
        this.infoType = infoType;
        this.keyValuePairs = new ArrayList<>();
        for (int i = 0; i < nameData.length; i += 2) {
            this.keyValuePairs.add(new Pair<>(nameData[i], nameData[i + 1]));
        }
    }

    /**
     * Constructs a SaveLine from an ArrayList.
     *
     * @param infoType The name of the information.
     * @param nameData The details of the data as key-value pairs.
     */
    public SaveLine(String infoType, ArrayList<Pair<String, String>> nameData) {
        this.infoType = infoType;
        this.keyValuePairs = nameData;
    }

    /**
     * Transforms the information into a string which cannot be read in an
     * ambiguous manner.
     *
     * @return The string reflecting the contents of the SaveLine.
     */
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

    /**
     * Checks whether this is equal to another Objbect. If the other object is
     * not a SaveLine, the return value will be false.
     *
     * @param rhs The right hand side of the comparison.
     * @return The boolean stating whether this and the argument are equal.
     */
    @Override
    public boolean equals(Object rhs) {
        if (rhs instanceof SaveLine) {
            SaveLine rhsSaveLine = (SaveLine) rhs;
            return rhsSaveLine.infoType.equals(infoType) && rhsSaveLine.keyValuePairs.equals(keyValuePairs);
        }
        return false;
    }

    /**
     * Parses a line of information from a save file to this class.
     *
     * @param line The String of information from a save file.
     * @return A SaveLine with the information from the given String.
     */
    public static SaveLine of(String line) {
        System.out.println("reading" + line);
        int left = 0;
        int right = 0;
        // read the information type
        while (right < line.length() && line.charAt(right) != ' ') {
            ++right;
        }
        String infoType = line.substring(left, right);
        // left = right;
        // read the data
        ArrayList<Pair<String, String>> typeData = new ArrayList<>();
        while (right < line.length()) {
            // space
            ++right;
            // left = right;
            // read the type
            while (line.charAt(right) != ' ') {
                ++right;
            }
            String type = line.substring(left, right);
            // left = right;
            // space
            ++right;
            left = right;
            // read the amount of data
            while (line.charAt(right) != ' ') {
                ++right;
            }
            int amount = Integer.parseInt(line.substring(left, right));
            // left = right;
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
        return new SaveLine(infoType, typeData);
    }

    /**
     * Gets the value of a given key.
     *
     * @param key The key.
     * @return The value associated with the key. Empty if the key is invalid.
     */
    public String getValue(String key) {
        for (Pair<String, String> i : keyValuePairs) {
            if (i.getKey().equals(key)) {
                return i.getValue();
            }
        }
        return "";
    }

    /**
     * Sets the value of a given key. If the key doesn't exist, it's added.
     *
     * @param key The key.
     * @param value The value.
     */
    public void setKeyValue(String key, String value) {
        for (int i = 0; i < keyValuePairs.size(); ++i) {
            if (keyValuePairs.get(i).getKey().equals(key)) {
                keyValuePairs.set(i, new Pair<>(key, value));
                return;
            }
        }
        addKeyValue(key, value);
    }

    /**
     * Gets the infoType.
     *
     * @return The infoType.
     */
    public String getInfoType() {
        return infoType;
    }

    /**
     * Sets the infoType.
     *
     * @param infoType The infoType.
     */
    public void setInfoType(String infoType) {
        this.infoType = infoType;
    }

    /**
     * Adds a key-value pair of information.
     *
     * @param key The key.
     * @param value The value.
     */
    public void addKeyValue(String key, String value) {
        keyValuePairs.add(new Pair<>(key, value));
    }
}
