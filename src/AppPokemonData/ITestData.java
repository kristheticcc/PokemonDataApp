package AppPokemonData;
import java.util.ArrayList;
import java.util.HashSet;
public interface ITestData {
    boolean testPrintFirstAndLastSevenLines(ArrayList<String> originalList);
    boolean testWriteSet(HashSet<String> someList, String fileName);
}
