package AppPokemonData;
import java.util.HashSet;
public interface IWriteData {
    boolean WriteDataToFile(HashSet<String> someData, String fileName);
}
