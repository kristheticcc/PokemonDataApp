package AppPokemonData;
import java.util.ArrayList;
/**IReadData interface defines methods for reading data from a file.
 */
public interface IReadData {
    /**Opens a data file for reading.
     * @param fileName The name of the file to open.
     * @return true if the file is successfully opened, false otherwise.
     */
    boolean openDataFile(String fileName);
    /**Reads data from the opened file.
     * @return true if the data is successfully read, false otherwise.
     */
    boolean readDataFile();
    /**Gets the raw data list read from the file.
     * @return An ArrayList containing the raw data lines.
     */
    ArrayList<String> getRawDataList();
}
