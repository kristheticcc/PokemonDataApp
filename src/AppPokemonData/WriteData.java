package AppPokemonData;
import java.util.HashSet;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * WriteData class implements the IWriteData interface to write a set of strings to a file.
 */
public class WriteData implements IWriteData {
    @Override
    public boolean WriteDataToFile(HashSet<String> someData, String fileName) {
        try {
            FileWriter fw=new FileWriter(fileName);
            BufferedWriter bw=new BufferedWriter(fw);
            for(String line: someData) {
                bw.write(line);
                bw.newLine();
            }
            bw.close();
            fw.close();
        }
        catch(IOException e) {
            System.out.println("File write error!!!");
            return false;
        }
        return true;
    }
}
