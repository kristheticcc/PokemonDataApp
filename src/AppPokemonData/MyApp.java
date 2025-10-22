package AppPokemonData;
import java.util.Scanner;
public class MyApp {
    public int showMenu() {
        int option=0;
        Scanner sc=new Scanner(System.in);

        System.out.println("-----------MENU-----------");
        System.out.println("1. Exit");
        System.out.println("2. Open data file");
        System.out.println("--------------------------");

        System.out.print("Select an option: ");
        option=sc.nextInt();
        return option;
    }
}
