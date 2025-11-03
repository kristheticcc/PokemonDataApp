package AppPokemonData;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;
public class ReadData implements IReadData {

    private ArrayList<String> rawDataList = new ArrayList<>();

    @Override
    public boolean openDataFile(String fileName) {
        File file=new File(fileName);
        return file.exists();
    }

    @Override
    public boolean readDataFile() {
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter the file name: ");
        String fileName=sc.nextLine();
        while(true) {
            try{
                int lineCount=0;
                FileReader fr=new FileReader(fileName);
                BufferedReader br=new BufferedReader(fr);
                String line;
                while((line=br.readLine())!=null){
                    rawDataList.add(line);
                    lineCount++;

                }
                br.close();
                fr.close();
                System.out.println("File loaded successfully with "+lineCount+ " lines");
                break;

            }
            catch(IOException e) {
                System.out.println("File not found!!! Please try again.");
            }
        }
        return true;

    }

    @Override
    public ArrayList<String> getRawDataList() {
        return rawDataList;
    }
}

