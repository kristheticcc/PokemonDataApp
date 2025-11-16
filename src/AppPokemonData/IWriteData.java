package AppPokemonData;
import java.util.HashSet;
/**IWriteData interface defines methods for writing data to a file.
 */
public interface IWriteData {
    /**Writes a set of strings to a specified file.
     * @param someData The set of strings to write.
     * @param fileName The name of the file to write to.
     * @return true if the write operation is successful, false otherwise.
     */
    boolean WriteDataToFile(HashSet<String> someData, String fileName);
}
