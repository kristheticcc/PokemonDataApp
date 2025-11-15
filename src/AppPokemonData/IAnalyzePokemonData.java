package AppPokemonData;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.TreeSet;
import java.util.TreeMap;
/**IAnalyzePokemonData interface defines the method to analyze Pokemon data.
 */
public interface IAnalyzePokemonData {
    /**Extracts all unique character names from the original data.
     * @param originalData The list of original data lines.
     * @return A HashSet containing all unique character names.
     */
    HashSet<String> getAllCharacterNames(ArrayList<String> originalData);

    ArrayList<PokemonCharacter> parseAllPokemon(ArrayList<String> originalData);

    TreeSet<PokemonCharacter> findByExactHp(ArrayList<PokemonCharacter> pokemons, int hp);
    TreeSet<PokemonCharacter> findByHpRange(ArrayList<PokemonCharacter> pokemons, int minHp, int maxHp);
    PokemonCharacter findLowestHp(ArrayList<PokemonCharacter> pokemons);
    PokemonCharacter findHighestHp(ArrayList<PokemonCharacter> pokemons);

    PokemonCharacter findFastestSpeed(ArrayList<PokemonCharacter> pokemons);
    PokemonCharacter findSlowestSpeed(ArrayList<PokemonCharacter> pokemons);
    TreeSet<PokemonCharacter> findTop3Fastest(ArrayList<PokemonCharacter> pokemons);
    TreeSet<PokemonCharacter> findTop3Slowest(ArrayList<PokemonCharacter> pokemons);
    TreeSet<PokemonCharacter> findBySpeedRange(ArrayList<PokemonCharacter> pokemons, int minSpeed, int maxSpeed);
    TreeMap<String, TreeSet<String>> findTop3SpeedGroups(ArrayList<PokemonCharacter> pokemons);
    String findLargestSpedGroup(ArrayList<PokemonCharacter> pokemons);

    int getMinHp(ArrayList<PokemonCharacter> pokemons);
    int getMaxHp(ArrayList<PokemonCharacter> pokemons);
    int getMinSpeed(ArrayList<PokemonCharacter> pokemons);
    int getMaxSpeed(ArrayList<PokemonCharacter> pokemons);
}
