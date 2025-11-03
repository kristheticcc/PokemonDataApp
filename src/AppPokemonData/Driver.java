package AppPokemonData;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashSet;

/** Driver class to run the Pokemon Data Analysis application.
 * Shows a menu to the user to select options like loading data file, searching Pokemon, and unit testing.
 * @author Krish Makwana
 * @version 1.0
 */
public class Driver {

    private static final int EXIT=1;
    private static final int OPEN_FILE=2;
    private static final int SEARCH_POKEMON=3;
    private static final int UNIT_TESTING=10;

    private static final int SUB_PRINT_LINES=1;
    private static final int SUB_WRITE_NAMES=2;

    private static ReadData readDataObj=new ReadData();
    private static TestData testDataObj=new TestData();
    private static AnalyzePokemonData analyzeDataObj=new AnalyzePokemonData();
    private static WriteData writeDataObj=new WriteData();
    private static boolean fileLoaded=false;


    /** Function to display a meny with options to the user and get their input.
     * @return int option selected by the user.
     */
    public static int showMenu() {

        Scanner sc=new Scanner(System.in);

        System.out.println("-----------MENU-----------");
        System.out.println("1. Exit");
        System.out.println("2. Open and read data file");
        System.out.println("3. Search Pokemon by Pokemon character");
        System.out.println("10. Unit Testing");
        System.out.println("--------------------------");

        System.out.print("Select an option: ");
        int option=sc.nextInt();
        return option;
    }

    /** Function to display a sub-menu for unit testing options and get user input.
     * @return int option selected by the user.
     */

    public static int showSubMenu() {
        Scanner sc=new Scanner(System.in);

        System.out.println("-------UNIT TEST MENU-------");
        System.out.println("1. Print first and last seven lines of the data file");
        System.out.println("2. Write all character names to a file");
        System.out.println("----------------------------");

        System.out.print("Select an option: ");
        int option=sc.nextInt();
        return option;
    }

    /** Function to handle opening and reading the data file.
     * Prompts the user to enter the file name and attempts to open and read it.
     * Allows up to two attempts if the file is not found or cannot be read.
     */
    public static void handleOpenFile() {
        Scanner sc=new Scanner(System.in);
        int attempts=0;
        boolean success=false;

        while(attempts<2 && !success) {
            System.out.println("Enter the file name: ");
            String fileName=sc.nextLine();

            if(readDataObj.openDataFile(fileName)) {
                if(readDataObj.readDataFile()) {
                    System.out.println("File loaded successfully with " + readDataObj.getRawDataList().size() + " lines.");
                    fileLoaded=true;
                    success=true;
                } else {
                    System.out.println("Error reading the file.");
                    attempts++;
                }
            } else {
                System.out.println("File not found. Try again.");
                attempts++;
            }
        }
    }
    /** Function to handle unit testing options.
     * Checks if the data file is loaded and then shows a sub-menu for unit testing options.
     * Depending on the user's choice, it either prints the first and last seven lines of the data
     * or writes all unique character names to a file.
     */
    public static void handleUnitTesting() {
        if(!fileLoaded) {
            System.out.println("Error: Please load the data file first (option 2)!");
            return;
        }
        int subOption=showSubMenu();
        if(subOption==SUB_PRINT_LINES) {
            ArrayList<String> rawData=readDataObj.getRawDataList();
            testDataObj.testPrintFirstAndLastSevenLines(rawData);
        }
        else if(subOption==SUB_WRITE_NAMES) {
            ArrayList<String> rawData=readDataObj.getRawDataList();
            HashSet<String> characterNames=analyzeDataObj.getAllCharacterNames(rawData);
            System.out.println("Found " + characterNames.size() + " unique Pokemon names.");

            String outputFileName="Pokemon_names.txt";
            if(testDataObj.testWriteSet(characterNames, outputFileName)) {
                System.out.println("Character names written to " + outputFileName + " successfully.");
            } else {
                System.out.println("Error writing character names to file.");
            }
        }
        else {
            System.out.println("Invalid sub-menu option!");
        }
    }
    /** Function to handle searching for a Pokemon by name.
     * Prompts the user to enter a Pokemon name and searches the loaded data for it.
     * If found, it displays the full data line for that Pokemon; otherwise, it notifies the user that the Pokemon was not found.
     */
    public static void handleSearchPokemon() {
        if(!fileLoaded) {
            System.out.println("Error: Load a data file first (option 2)!");
            return;
        }
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter the Pokemon name to search: ");
        String searchName=sc.nextLine().trim();

        ArrayList<String> rawData=readDataObj.getRawDataList();
        boolean found=false;

        for(int i=1; i<rawData.size(); i++) {
            String line=rawData.get(i);
            String[] parts=line.split(",");
            if(parts.length>31 && parts[31].equalsIgnoreCase(searchName)) {
                System.out.println("Pokemon found!");
                System.out.println("Full data: ");
                System.out.println(line);
                found=true;
                break;
            }
        }
        if(!found) {
            System.out.println("Pokemon " + searchName + " not found in the data.");
        }
    }

    /** Main function to run the application.
     */
    public static void main(String[] args) {

        int option;
        while(true) {
            option=showMenu();
            if (option==EXIT) {
                System.out.println("Thanks for using this application.");
                break;
            }
            else if (option==OPEN_FILE) {
                handleOpenFile();
            }
            else if (option==SEARCH_POKEMON) {
                handleSearchPokemon();
            }
            else if (option==UNIT_TESTING) {
                handleUnitTesting();
            }
            else {
                System.out.println("Invalid option!!! Try again.");
            }

        }

        }
    }
