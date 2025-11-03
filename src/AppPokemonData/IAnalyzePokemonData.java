package AppPokemonData;
import java.util.ArrayList;
import java.util.HashSet;

/**IAnalyzePokemonData interface defines the method to analyze Pokemon data.
 */
public interface IAnalyzePokemonData {
    /**Extracts all unique character names from the original data.
     * @param originalData The list of original data lines.
     * @return A HashSet containing all unique character names.
     */
    HashSet<String> getAllCharacterNames(ArrayList<String> originalData);
}
