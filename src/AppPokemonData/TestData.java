package AppPokemonData;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.io.IOException;

public class TestData implements ITestData {
    @Override
    public boolean testPrintFirstAndLastSevenLines(ArrayList<String> originalList) {
        System.out.println("----------First seven lines:----------");
        for(int i=0; i<7; i++) {
            System.out.println(originalList.get(i));
        }
        System.out.println("----------Last seven lines:----------");
        for(int i=originalList.size()-1; i>=originalList.size()-7; i--) {
            System.out.println(originalList.get(i));
        }
        return originalList.isEmpty();
    }

    @Override
    public boolean testWriteSet(HashSet<String> someList, String fileName) {
        try {
            FileWriter fw=new FileWriter(fileName);
            BufferedWriter bw=new BufferedWriter(fw);
            for(String line: someList) {
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
