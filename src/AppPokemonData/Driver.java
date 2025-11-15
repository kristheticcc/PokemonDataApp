package AppPokemonData;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.TreeSet;
import java.util.TreeMap;
import java.util.Map;

/** Driver class to run the Pokemon Data Analysis application.
 * Shows a menu to the user to select options like loading data file, searching Pokemon, and unit testing.
 * @author Krish Makwana
 * @version 1.0
 */
public class Driver {

    private static final int EXIT=1;
    private static final int OPEN_FILE=2;
    private static final int SEARCH_POKEMON=3;
    private static final int FIND_BY_ATTRIBUTES=4;
    private static final int UNIT_TESTING=10;

    private static final int SUB_PRINT_LINES=1;
    private static final int SUB_WRITE_NAMES=2;

    private static final int SEC_HP=1;
    private static final int SEC_SPEED=2;

    private static ReadData readDataObj=new ReadData();
    private static TestData testDataObj=new TestData();
    private static AnalyzePokemonData analyzeDataObj=new AnalyzePokemonData();
    private static WriteData writeDataObj=new WriteData();
    private static boolean fileLoaded=false;
    private static ArrayList<PokemonCharacter> allPokemons=new ArrayList<>();
    private static Scanner sc=new Scanner(System.in);


    /** Function to display a meny with options to the user and get their input.
     * @return int option selected by the user.
     */
    public static int showMenu() {

        System.out.println("-----------MENU-----------");
        System.out.println("1. Exit");
        System.out.println("2. Open and read data file");
        System.out.println("3. Search Pokemon by Pokemon character");
        System.out.println("4. Find characters via attributes");
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

    public static int showSecondaryMenu() {
        System.out.println("-------ATTRIBUTE SEARCH MENU-------");
        System.out.println("1. Get all characters with a specific hit point value");
        System.out.println("2. Get all characters within a specific speed value");
        System.out.println("----------------------------");
        System.out.print("Select an option: ");
        return sc.nextInt();
    }

    public static int showHpTertiaryMenu() {
        System.out.println("-------HIT POINTS SEARCH-------");
        System.out.println("1. Find character with specific hit point value");
        System.out.println("2. Find characters within a specific range of hit values");
        System.out.println("3. Find the character with the lowest hit point value");
        System.out.println("4. Find the character with the highest hit point value");
        System.out.println("5. Go back to the previous menu");
        System.out.println("----------------------------");
        System.out.print("Select an option: ");
        return sc.nextInt();
    }

    public static int showSpeedTertiaryMenu() {
        System.out.println("-------SPEED SEARCH-------");
        System.out.println("1. Find the character with the fastest speed");
        System.out.println("2. Which character has the slowest speed");
        System.out.println("3. Which characters are the top 3 fastest");
        System.out.println("4. Which characters are the top 3 slowest");
        System.out.println("5. Which characters fall within a specific speed range");
        System.out.println("6. What are top 3 speed groups");
        System.out.println("7. Which group represents the largest speed group");
        System.out.println("8. Go back to the previous menu");
        System.out.println("----------------------------");
        System.out.print("Select an option: ");
        return sc.nextInt();
    }

    /** Function to handle opening and reading the data file.
     * Prompts the user to enter the file name and attempts to open and read it.
     * Allows up to two attempts if the file is not found or cannot be read.
     */
    public static void handleOpenFile() {
        int attempts=0;
        boolean success=false;

        while(attempts<2 && !success) {
            System.out.println("Enter the file name: ");
            sc.nextLine();
            String fileName=sc.nextLine();

            if(readDataObj.openDataFile(fileName)) {
                if(readDataObj.readDataFile()) {
                    System.out.println("File loaded successfully with " + readDataObj.getRawDataList().size() + " lines.");
                    fileLoaded=true;
                    allPokemons=analyzeDataObj.parseAllPokemon(readDataObj.getRawDataList());
                    System.out.println("Parsed " + allPokemons.size() + " Pokemon characters.");
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
        if(!success) {
            System.out.println("Failed to load the file after 2 attempts.");
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

    public static void handleFindByAttributes() {
        if(!fileLoaded) {
            System.out.println("Error: Load a data file first (option 2)!");
            return;
        }
        int secOption=showSecondaryMenu();

        if(secOption==SEC_HP) {
            handleHpMenu();
        }
        else if(secOption==SEC_SPEED) {
            handleSpeedMenu();
        }

        else {
                System.out.println("Invalid option! Try again.");
        }

    }

    public static void handleHpMenu(){
        while(true) {
            int option=showHpTertiaryMenu();
            if(option==5) {
                System.out.println("Returning to previous menu.");
                break;
            }
            int minHp= analyzeDataObj.getMinHp(allPokemons);
            int maxHp= analyzeDataObj.getMaxHp(allPokemons);
            if(option==1) {
                System.out.println("Hp Range: " + minHp + " - " + maxHp);
                System.out.print("Enter the specific hit point value: ");
                int hp=sc.nextInt();
                TreeSet<PokemonCharacter> results=analyzeDataObj.findByExactHp(allPokemons, hp);
                if(results.isEmpty()) {
                    System.out.println("No characters found with hit point value " + hp);
                }
                else{
                    System.out.println("Found "+results.size()+" characters with hit point value " + hp+":");
                    for(PokemonCharacter p: results) {
                        System.out.println(p);
                    }
                }
            }
            else if(option==2) {
                System.out.println("Hp Range: " + minHp + " - " + maxHp);
                System.out.print("Enter the minimum hit point value: ");
                int minRange=sc.nextInt();
                System.out.print("Enter the maximum hit point value: ");
                int maxRange=sc.nextInt();
                TreeSet<PokemonCharacter> results=analyzeDataObj.findByHpRange(allPokemons, minRange, maxRange);
                if(results.isEmpty()) {
                    System.out.println("No characters found within hit point range " + minRange + " - " + maxRange);
                }
                else{
                    System.out.println("Found "+results.size()+" characters within hit point range " + minRange + " - " + maxRange +":");
                    for(PokemonCharacter p: results) {
                        System.out.println(p);
                    }
                }
            }
            else if(option==3) {
                PokemonCharacter lowest=analyzeDataObj.findLowestHp(allPokemons);
                System.out.println("Pokemon with the lowest hit point value:");
                System.out.println(lowest);
            }
            else if(option==4) {
                PokemonCharacter highest=analyzeDataObj.findHighestHp(allPokemons);
                System.out.println("Pokemon with the highest hit point value:");
                System.out.println(highest);
            }
            else {
                System.out.println("Invalid option! Try again.");
            }
        }
    }

    public static void handleSpeedMenu() {
        while (true) {
            int option = showSpeedTertiaryMenu();
            if (option == 8) {
                System.out.println("Returning to previous menu.");
                break;
            }
            int minSpeed = analyzeDataObj.getMinSpeed(allPokemons);
            int maxSpeed = analyzeDataObj.getMaxSpeed(allPokemons);
            if (option == 1) {
                PokemonCharacter fastest = analyzeDataObj.findFastestSpeed(allPokemons);
                System.out.println("Pokemon with the fastest speed:");
                System.out.println(fastest);
            } else if (option == 2) {
                PokemonCharacter slowest = analyzeDataObj.findSlowestSpeed(allPokemons);
                System.out.println("Pokemon with the slowest speed:");
                System.out.println(slowest);
            } else if (option == 3) {
                TreeSet<PokemonCharacter> top3 = analyzeDataObj.findTop3Fastest(allPokemons);
                System.out.println("Top 3 fastest Pokemon:");
                for (PokemonCharacter p : top3.descendingSet()) {
                    System.out.println(p);
                }
            } else if (option == 4) {
                TreeSet<PokemonCharacter> bottom3 = analyzeDataObj.findTop3Slowest(allPokemons);
                System.out.println("Top 3 slowest Pokemon:");
                for (PokemonCharacter p : bottom3) {
                    System.out.println(p);
                }
            } else if (option == 5) {
                System.out.println("Speed Range: " + minSpeed + " - " + maxSpeed);
                System.out.print("Enter the minimum speed value: ");
                int minRange = sc.nextInt();
                System.out.print("Enter the maximum speed value: ");
                int maxRange = sc.nextInt();
                TreeSet<PokemonCharacter> results = analyzeDataObj.findBySpeedRange(allPokemons, minRange, maxRange);
                if (results.isEmpty()) {
                    System.out.println("No Pokemon found within speed range " + minRange + " - " + maxRange);
                } else {
                    System.out.println("Found " + results.size() + " characters within speed range " + minRange + " - " + maxRange + ":");
                    for (PokemonCharacter p : results) {
                        System.out.println(p);
                    }
                }
            } else if (option == 6) {
                TreeMap<String, TreeSet<String>> top3Groups = analyzeDataObj.findTop3SpeedGroups(allPokemons);
                System.out.println("Top 3 speed groups (by number of Pokemon):");
                int rank = 1;
                for (Map.Entry<String, TreeSet<String>> entry : top3Groups.entrySet()) {
                    System.out.println(rank + ". " + entry.getKey().toUpperCase() + " (" + entry.getValue().size() + " Pokemon):");
                    System.out.println(" Characters: " + entry.getValue());
                    rank++;
                }
            } else if (option == 7) {
                String result = analyzeDataObj.findLargestSpedGroup(allPokemons);
                System.out.println(result);
            } else {
                System.out.println("Invalid option!");
            }
        }
    }


    /** Main function to run the application.
     */
    public static void main(String[] args) {

        int option;
        System.out.println("Welcome to the Pokemon Data Analysis Application!");
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
            else if (option==FIND_BY_ATTRIBUTES) {
                handleFindByAttributes();
            }
            else if (option==UNIT_TESTING) {
                handleUnitTesting();
            }
            else {
                System.out.println("Invalid option!!! Try again.");
            }

        }
        sc.close();

        }
    }
