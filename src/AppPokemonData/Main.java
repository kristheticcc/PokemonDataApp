package AppPokemonData;


public class Main {
    public static void main(String[] args) {
        MyApp app = new MyApp();
        int option=app.showMenu();
        System.out.println("Selected option:" +option);
    }
}