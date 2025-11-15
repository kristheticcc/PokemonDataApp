package AppPokemonData;

public class PokemonCharacter implements Comparable<PokemonCharacter> {
    private String name;
    private String japaneseName;
    private int hp;
    private int speed;
    private int attack;
    private int defense;
    private String type1;
    private String type2;

    private static String sortMode = "name"; // Default sort mode

    public PokemonCharacter(String name, String japaneseName, int hp, int speed, int attack, int defense, String type1, String type2) {
        this.name = name;
        this.japaneseName = japaneseName;
        this.hp = hp;
        this.speed = speed;
        this.attack = attack;
        this.defense = defense;
        this.type1 = type1;
        this.type2 = type2;
    }

    public String getName() {
        return name;
    }

    public String getJapaneseName() {
        return japaneseName;
    }

    public int getHp() {
        return hp;
    }

    public int getSpeed() {
        return speed;
    }
    public int getAttack() {
        return attack;
    }
    public int getDefense() {
        return defense;
    }
    public String getType1() {
        return type1;
    }
    public String getType2() {
        return type2;
    }

    public static void setSortMode(String mode) {
        sortMode = mode;
    }

    public static String getSortMode() {
        return sortMode;
    }

    @Override
    public int compareTo(PokemonCharacter other) {
        if(sortMode.equals("hp")) {
            if(this.hp!=other.hp) {
                return Integer.compare(this.hp, other.hp);
            } else {
                return this.name.compareTo(other.name);
            }
        }
        else if(sortMode.equals("speed")) {
            if(this.speed!=other.speed) {
                return Integer.compare(this.speed, other.speed);
            } else {
                return this.name.compareTo(other.name);
            }
        }
        else {
            return this.name.compareTo(other.name);
        }
    }

    @Override
    public String toString() {
        // Personal note: Look at documentation to understand String.format method
        return String.format("Name: %-15s | Japanese: %-20s | HP: %-3d | Speed: %-3d | Type: %s%s",
                name, japaneseName, hp, speed, type1, (type2!=null && !type2.isEmpty() ? "/" + type2: ""));
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        PokemonCharacter other = (PokemonCharacter) obj;
        return name.equals(other.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
