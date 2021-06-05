package me.github.michaelra;
import java.util.List;

public class TableUtil {
    /**
     * Use this to get a table row by row. You can also print the result directly as a table to console.
     * @param rows Rows list of Lists with String type; will be returned row by row
     * @return TableUtil as String containing all rows
     */
    @SuppressWarnings("all") // annoying warning because of row.toArray, no I dont want this to become object :(
    public static String rowsToTable(List<List<String>> rows)
    {
        int[] lenghts = new int[rows.get(0).size()];

        for (List<String> r : rows) {
            for (int i = 0; i < r.size(); i++) {
                lenghts[i] = Math.max(lenghts[i], r.get(i).length());
            }
        }

        StringBuilder sBuilder = new StringBuilder();

        for (int maxLength : lenghts) {
            sBuilder.append("%-").append(maxLength + 2).append("s");
        }

        String format = sBuilder.toString();
        StringBuilder returned = new StringBuilder();

        for (List<String> r : rows) {
            returned.append(String.format(format, r.toArray(new String[0]))).append("\n");
        }

        return returned.toString();
    }
}
