package AppPokemonData;
import java.util.Scanner;

/** Class MyApp containing the showMenu function to display a menu and get user input.
 * @author Krish Makwana
 * @version 1.0
 */
public class MyApp {

    /** Function to display a meny with options to the user and get their input.
     * @return int option selected by the user.
     */
    public int showMenu() {

        Scanner sc=new Scanner(System.in);

        System.out.println("-----------MENU-----------");
        System.out.println("1. Exit");
        System.out.println("2. Open data file");
        System.out.println("--------------------------");

        System.out.print("Select an option: ");
        int option=sc.nextInt();
        return option;
    }


}
