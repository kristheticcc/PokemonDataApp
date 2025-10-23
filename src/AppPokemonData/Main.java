package AppPokemonData;
import java.util.Scanner;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

/** Main Class containing main function, openDataFile function, and the Test function.
 * It also contains a globally declared ArrayList.
 * @author Krish Makwana
 * @version 1.0
 */
public class Main {
    /** Global ArrayList "pokemonList" used for storing each line from the "pokemon.csv" file.
     */
    private static ArrayList<String> pokemonList = new ArrayList<>();

    /** Test function to print the first and last seven lines from the "pokemon.csv" file using the global ArrayList.
     */
    public static void Test() {
        System.out.println("----------First seven lines:----------");
        for(int i=0; i<7; i++) {
            System.out.println(pokemonList.get(i));
        }
        System.out.println("----------Last seven lines:----------");
        for(int i=pokemonList.size()-1; i>=pokemonList.size()-7; i--) {
            System.out.println(pokemonList.get(i));
        }

    }

    /** Function to open the date file "pokemon.csv" and read each line into the global ArrayList.
     * Has a FileReader and BufferedReader to read the file.
     * Uses try-catch block to handle IOException.
     */
    public static void openDataFile() {
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
                    pokemonList.add(line);
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

    }

    /** Main function to run the application.
     * Creates an instance of MyApp class to show the menu and get user input.
     * if user selects option 1, the application exists with a thank you message.
     * if user selects option 2, it calls the openDataFile function to read the data file.
     * if user selects an invalid option, it prompts the user to try again.
     * @see MyApp
     */
    public static void main(String[] args) {
        MyApp app = new MyApp();
        int option;
        while(true) {
            option=app.showMenu();
            if (option==1) {
                System.out.println("Thanks for using this application.");
                break;
            }
            else if (option==2) {
                openDataFile();
                Test();
                break;
            }
            else {
                System.out.println("Invalid option!!! Try again.");
            }

        }

        }
    }
