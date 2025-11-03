package AppPokemonData;
import java.util.ArrayList;
import java.util.HashSet;

public class AnalyzePokemonData implements IAnalyzePokemonData {
    @Override
    public HashSet<String> getAllCharacterNames(ArrayList<String> originalData) {
        HashSet<String> characterNames=new HashSet<>();
        int count=0;
        for(String line: originalData) {
            if(count++==0) continue; // skip header line
            String[] parts=line.split(",");
            characterNames.add(parts[31]);
        }
        return characterNames;
    }
}
