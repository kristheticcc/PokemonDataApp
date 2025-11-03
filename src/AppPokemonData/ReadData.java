package AppPokemonData;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.io.File;
/**
 * ReadData class implements the IReadData interface to read data from a file.
 */

public class ReadData implements IReadData {

    private ArrayList<String> rawDataList = new ArrayList<>();
    private String currentFileName="";

    @Override
    public boolean openDataFile(String fileName) {
        File file=new File(fileName);
        if(file.exists()) {
            currentFileName=fileName;
            return true;
        }
        return false;
    }

    @Override
    public boolean readDataFile() {
        if(currentFileName.isEmpty()) {
            return false;
        }
        try {
            rawDataList.clear();
            FileReader fr=new FileReader(currentFileName);
            BufferedReader br=new BufferedReader(fr);
            String line;
            while((line=br.readLine())!=null) {
                rawDataList.add(line);
            }
            br.close();
            fr.close();
            return true;
        }
        catch(IOException e) {
            System.out.println("File read error!!!");
            return false;
        }

    }
    /**
     * Overloaded method to open and read data file in one call.
     */
    public boolean readDataFile(String fileName) {
        if(openDataFile(fileName)) {
            return readDataFile();
        } else {
            return false;
        }
    }

    @Override
    public ArrayList<String> getRawDataList() {
        return rawDataList;
    }
}

