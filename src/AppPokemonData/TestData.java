package AppPokemonData;
import java.util.ArrayList;
import java.util.HashSet;

/**TestData implements methods of Interface ITestData for testing data operations.
 */
public class TestData implements ITestData {
    @Override
    public boolean testPrintFirstAndLastSevenLines(ArrayList<String> originalList) {
        if(originalList.isEmpty()) {
            System.out.println("Error: List is empty!");
            return false;
        }
        System.out.println("----------First seven lines:----------");
        for(int i=0; i<7 && i<originalList.size(); i++) {
            System.out.println(originalList.get(i));
        }
        System.out.println("----------Last seven lines:----------");
        int startIndex = Math.max(0, originalList.size() - 7);
        for(int i=startIndex; i<originalList.size(); i++) {
            System.out.println(originalList.get(i));
        }
        return true;
    }

    @Override
    public boolean testWriteSet(HashSet<String> someList, String fileName) {
        WriteData writer = new WriteData();
        return writer.WriteDataToFile(someList, fileName);
    }
}
