package AppPokemonData;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.TreeSet;
import java.util.TreeMap;

/**
 * IAnalyzePokemonData interface defines methods for analyzing Pokemon character data.
 */
public interface IAnalyzePokemonData {
    /**Extracts all unique character names from the original data.
     * @param originalData The list of original data lines.
     * @return A HashSet containing all unique character names.
     */
    HashSet<String> getAllCharacterNames(ArrayList<String> originalData);

    /**Parses the original data into a list of PokemonCharacter objects.
     * @param originalData The list of original data lines.
     * @return An ArrayList of PokemonCharacter objects.
     */
    ArrayList<PokemonCharacter> parseAllPokemon(ArrayList<String> originalData);

    /**Finds Pokemon characters with exact HP value.
     * @param pokemons The list of PokemonCharacter objects.
     * @param hp The exact HP value to search for.
     * @return A TreeSet of PokemonCharacter objects with the specified HP.
     */
    TreeSet<PokemonCharacter> findByExactHp(ArrayList<PokemonCharacter> pokemons, int hp);

    /**Finds Pokemon characters within a specified HP range.
     * @param pokemons The list of PokemonCharacter objects.
     * @param minHp The minimum HP value.
     * @param maxHp The maximum HP value.
     * @return A TreeSet of PokemonCharacter objects within the specified HP range.
     */
    TreeSet<PokemonCharacter> findByHpRange(ArrayList<PokemonCharacter> pokemons, int minHp, int maxHp);

    /**Finds the Pokemon character with the lowest HP.
     * @param pokemons The list of PokemonCharacter objects.
     * @return The PokemonCharacter object with the lowest HP.
     */
    PokemonCharacter findLowestHp(ArrayList<PokemonCharacter> pokemons);

    /**Finds the Pokemon character with the highest HP.
     * @param pokemons The list of PokemonCharacter objects.
     * @return The PokemonCharacter object with the highest HP.
     */
    PokemonCharacter findHighestHp(ArrayList<PokemonCharacter> pokemons);

    /**Find the fastest Pokemon character.
     * @param pokemons The list of PokemonCharacter objects.
     * @return The PokemonCharacter object with the highest speed.
     */
    PokemonCharacter findFastestSpeed(ArrayList<PokemonCharacter> pokemons);

    /**Find the slowest Pokemon character.
     * @param pokemons The list of PokemonCharacter objects.
     * @return The PokemonCharacter object with the lowest speed.
     */
    PokemonCharacter findSlowestSpeed(ArrayList<PokemonCharacter> pokemons);

    /**Find the top 3 fastest Pokemon characters.
     * @param pokemons The list of PokemonCharacter objects.
     * @return A TreeSet of the top 3 fastest PokemonCharacter objects.
     */
    TreeSet<PokemonCharacter> findTop3Fastest(ArrayList<PokemonCharacter> pokemons);

    /**Find the top 3 slowest Pokemon characters.
     * @param pokemons The list of PokemonCharacter objects.
     * @return A TreeSet of the top 3 slowest PokemonCharacter objects.
     */
    TreeSet<PokemonCharacter> findTop3Slowest(ArrayList<PokemonCharacter> pokemons);

    /**Find Pokemon characters within a specified speed range.
     * @param pokemons The list of PokemonCharacter objects.
     * @param minSpeed The minimum speed value.
     * @param maxSpeed The maximum speed value.
     * @return A TreeSet of PokemonCharacter objects within the specified speed range.
     */
    TreeSet<PokemonCharacter> findBySpeedRange(ArrayList<PokemonCharacter> pokemons, int minSpeed, int maxSpeed);

    /**Find groups of Pokemon characters by their speed and return the top 3 groups with the most members.
     * @param pokemons The list of PokemonCharacter objects.
     * @return A TreeMap where keys are speed values and values are TreeSets of PokemonCharacter names, limited to the top 3 groups.
     */
    TreeMap<String, TreeSet<String>> findTop3SpeedGroups(ArrayList<PokemonCharacter> pokemons);

    /**Find the speed group with the largest number of Pokemon characters.
     * @param pokemons The list of PokemonCharacter objects.
     * @return The speed value (as a String) of the group with the largest number of members.
     */
    String findLargestSpedGroup(ArrayList<PokemonCharacter> pokemons);

    int getMinHp(ArrayList<PokemonCharacter> pokemons);
    int getMaxHp(ArrayList<PokemonCharacter> pokemons);
    int getMinSpeed(ArrayList<PokemonCharacter> pokemons);
    int getMaxSpeed(ArrayList<PokemonCharacter> pokemons);
}
