package AppPokemonData;
import java.util.Scanner;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    private static ArrayList<String> pokemonList = new ArrayList<>();

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
                break;
            }
            else {
                System.out.println("Invalid option!!! Try again.");
            }

        }
        Test();
        }
    }
