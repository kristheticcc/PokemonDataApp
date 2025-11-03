package AppPokemonData;
import java.util.ArrayList;
import java.util.HashSet;
/**ITestData interface defines methods for testing data operations.
 */
public interface ITestData {
    /**Tests printing the first and last seven lines of the original list.
     * @param originalList The list of original data lines.
     * @return true if the operation is successful, false otherwise.
     */
    boolean testPrintFirstAndLastSevenLines(ArrayList<String> originalList);

    /**Tests writing a HashSet to a file.
     * @param someList The HashSet to be written to the file.
     * @param fileName The name of the file to write to.
     * @return true if the write operation is successful, false otherwise.
     */
    boolean testWriteSet(HashSet<String> someList, String fileName);
}
