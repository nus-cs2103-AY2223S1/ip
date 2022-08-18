package Utilities;

import java.util.stream.IntStream;

public class StringUtilities {
    /**
     * Helper method to split a String array by a delimiter.
     * If two delimiters sit adjacent, an empty array is returned.
     * @param input The string array to split
     * @param delimiter The string to split by
     * @return A nested array containing string arrays that are
     * partitioned by the delimiter, excluding all instances of the delimiter
     */
    public static String[][] splitStringArray(String[] input, String delimiter) {
        if (input.length == 0) {
            return new String[0][];
        }

        int splitStart = 0;
        int splitEnd = input.length - 1;

        while (input[splitStart].equals(delimiter)) {
            splitStart++;
        }
        while(input[splitEnd].equals(delimiter)) {
            splitEnd--;
        }

        int[] splitIndices = IntStream
                .range(splitStart, splitEnd + 1)
                .filter(i -> input[i].equals(delimiter))
                .toArray();

        String[][] splitArrays = new String[
                (int)IntStream
                .range(0, splitIndices.length)
                .filter(i -> i == 0 || splitIndices[i] - 1 != splitIndices[i-1])
                .count() + 1][];

        int startingIndex = splitStart;
        int j = 0;
        for (int splitIndex : splitIndices) {
            if (startingIndex != splitIndex) {
                splitArrays[j] = new String[splitIndex - startingIndex];
                System.arraycopy(input, startingIndex, splitArrays[j], 0, splitArrays[j].length);
                j++;
            }
            startingIndex = splitIndex + 1;
        }

        splitArrays[splitArrays.length - 1] = new String[splitEnd + 1 - startingIndex];
        System.arraycopy(input, startingIndex, splitArrays[splitArrays.length - 1],
                0, splitArrays[splitArrays.length - 1].length);

        return splitArrays;
    }
}
