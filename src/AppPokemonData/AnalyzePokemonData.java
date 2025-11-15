package AppPokemonData;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.TreeSet;
import java.util.TreeMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Collections;
import java.util.Comparator;
/**
 * AnalyzePokemonData class implements IAnalyzePokemonData interface
 * to analyze Pokemon data and extract unique character names.
 */
public class AnalyzePokemonData implements IAnalyzePokemonData {
    @Override
    public HashSet<String> getAllCharacterNames(ArrayList<String> originalData) {
        HashSet<String> characterNames=new HashSet<>();
        int count=0;
        for(String line: originalData) {
            if(count++==0) continue; // skip header line
            String[] parts=line.split(",");
            characterNames.add(parts[31]);
        }
        return characterNames;
    }

    @Override
    public ArrayList<PokemonCharacter> parseAllPokemon(ArrayList<String> originalData) {
        ArrayList<PokemonCharacter> pokemons=new ArrayList<>();
        int count=0;
        for(String line: originalData) {
            if(count++==0) continue; // skip header line

            String[] parts=line.split(",");
            if(parts.length<40) continue;

            try {
                String name=parts[31];
                String japaneseName=parts[30];
                int hp=Integer.parseInt(parts[29]);
                int speed=Integer.parseInt(parts[36]);
                int attack=Integer.parseInt(parts[20]);
                int defense=Integer.parseInt(parts[26]);
                String type1=parts[37];
                String type2=parts[38];
                PokemonCharacter pokemon=new PokemonCharacter(name, japaneseName, hp, speed, attack, defense, type1, type2);
                pokemons.add(pokemon);
            } catch(Exception e) {
                System.out.println("Data parsing error in line: " + line);
            }
        }
        return pokemons;
    }
    @Override
    public TreeSet<PokemonCharacter> findByExactHp(ArrayList<PokemonCharacter> pokemons, int hp) {
        PokemonCharacter.setSortMode("hp");
        TreeSet<PokemonCharacter> result = new TreeSet<>();
        for (PokemonCharacter p : pokemons) {
            if (p.getHp() == hp) {
                result.add(p);
            }
        }
        return result;
    }

    @Override
    public TreeSet<PokemonCharacter> findByHpRange(ArrayList<PokemonCharacter> pokemons, int minHp, int maxHp) {
        PokemonCharacter.setSortMode("hp");
        TreeSet<PokemonCharacter> result = new TreeSet<>();
        for (PokemonCharacter p : pokemons) {
            if (p.getHp() >= minHp && p.getHp() <= maxHp) {
                result.add(p);
            }
        }
        return result;
    }

    @Override
    public PokemonCharacter findLowestHp(ArrayList<PokemonCharacter> pokemons) {
        if (pokemons.isEmpty()) return null;
        PokemonCharacter lowest = pokemons.get(0);
        for (PokemonCharacter p : pokemons) {
            if (p.getHp() < lowest.getHp()) {
                lowest = p;
            }
        }
        return lowest;
    }
    @Override
    public PokemonCharacter findHighestHp(ArrayList<PokemonCharacter> pokemons) {
        if (pokemons.isEmpty()) return null;
        PokemonCharacter highest = pokemons.get(0);
        for (PokemonCharacter p : pokemons) {
            if (p.getHp() > highest.getHp()) {
                highest = p;
            }
        }
        return highest;
    }

    @Override
    public PokemonCharacter findFastestSpeed(ArrayList<PokemonCharacter> pokemons) {
        if (pokemons.isEmpty()) return null;
        PokemonCharacter fastest = pokemons.get(0);
        for (PokemonCharacter p : pokemons) {
            if (p.getSpeed() > fastest.getSpeed()) {
                fastest = p;
            }
        }
        return fastest;
    }

    @Override
    public PokemonCharacter findSlowestSpeed(ArrayList<PokemonCharacter> pokemons) {
        if (pokemons.isEmpty()) return null;
        PokemonCharacter slowest = pokemons.get(0);
        for (PokemonCharacter p : pokemons) {
            if (p.getSpeed() < slowest.getSpeed()) {
                slowest = p;
            }
        }
        return slowest;
    }

    @Override
    public TreeSet<PokemonCharacter> findTop3Fastest(ArrayList<PokemonCharacter> pokemons) {
        PokemonCharacter.setSortMode("speed");
        TreeSet<PokemonCharacter> allSorted = new TreeSet<>();
        allSorted.addAll(pokemons);
        TreeSet<PokemonCharacter> top3 = new TreeSet<>();
        int count = 0;
        for (PokemonCharacter p : allSorted.descendingSet()) {
            top3.add(p);
            count++;
            if (count >= 3) break;
        }
        return top3;
    }

    @Override
    public TreeSet<PokemonCharacter> findTop3Slowest(ArrayList<PokemonCharacter> pokemons) {
        PokemonCharacter.setSortMode("speed");
        TreeSet<PokemonCharacter> allSorted = new TreeSet<>();
        allSorted.addAll(pokemons);
        TreeSet<PokemonCharacter> bottom3 = new TreeSet<>();
        int count = 0;
        for (PokemonCharacter p : allSorted) {
            bottom3.add(p);
            count++;
            if (count >= 3) break;
        }
        return bottom3;
    }

    @Override
    public TreeSet<PokemonCharacter> findBySpeedRange(ArrayList<PokemonCharacter> pokemons, int minSpeed, int maxSpeed) {
        PokemonCharacter.setSortMode("speed");
        TreeSet<PokemonCharacter> result = new TreeSet<>();
        for (PokemonCharacter p : pokemons) {
            if (p.getSpeed() >= minSpeed && p.getSpeed() <= maxSpeed) {
                result.add(p);
            }
        }
        return result;
    }

    @Override
    public TreeMap<String, TreeSet<String>> findTop3SpeedGroups(ArrayList<PokemonCharacter> pokemons) {
        HashMap<Integer, TreeSet<String>> speedGroup = new HashMap<>();
        for (PokemonCharacter p : pokemons) {
            int speed = p.getSpeed();
            if(!speedGroup.containsKey(speed)) {
                speedGroup.put(speed, new TreeSet<>());
            }
            speedGroup.get(speed).add(p.getName());
        }

        ArrayList<Map.Entry<Integer, TreeSet<String>>> sortedGroups = new ArrayList<>(speedGroup.entrySet());
        Collections.sort(sortedGroups, new Comparator<Map.Entry<Integer, TreeSet<String>>>() {
            public int compare(Map.Entry<Integer, TreeSet<String>> a, Map.Entry<Integer, TreeSet<String>> b) {
                return Integer.compare(b.getValue().size(), a.getValue().size());
            }
        });
        TreeMap<String, TreeSet<String>> result = new TreeMap<>();
        String[] keys={"first", "second", "third"};
        for(int i=0; i<3 && i<sortedGroups.size(); i++){
            result.put(keys[i], sortedGroups.get(i).getValue());
        }
        return result;
    }

    @Override
    public String findLargestSpedGroup(ArrayList<PokemonCharacter> pokemons) {
        HashMap<Integer, ArrayList<String>> speedGroups = new HashMap<>();
        for (PokemonCharacter p : pokemons) {
            int speed = p.getSpeed();
            if(!speedGroups.containsKey(speed)) {
                speedGroups.put(speed, new ArrayList<>());
            }
            speedGroups.get(speed).add(p.getName());
        }

        int maxSize=0;
        int largestSpeed = 0;
        for (Map.Entry<Integer, ArrayList<String>> entry : speedGroups.entrySet()) {
            if (entry.getValue().size() > maxSize) {
                maxSize = entry.getValue().size();
                largestSpeed = entry.getKey();
            }
        }
        StringBuilder sb=new StringBuilder();
        sb.append("Largest Speed Group: Speed= ").append(largestSpeed).append(" (").append(maxSize).append(" Pokemons)\n");
        sb.append("Pokemon: ");
        ArrayList<String> names=speedGroups.get(largestSpeed);
        Collections.sort(names);
        for(int i=0; i<names.size(); i++) {
            sb.append(names.get(i));
            if(i<names.size()-1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }

    @Override
    public int getMinHp(ArrayList<PokemonCharacter> pokemons) {
        if (pokemons.isEmpty()) return 0;
        int min = pokemons.get(0).getHp();
        for (PokemonCharacter p : pokemons) {
            if (p.getHp() < min) {
                min = p.getHp();
            }
        }
        return min;
    }

    @Override
    public int getMaxHp(ArrayList<PokemonCharacter> pokemons) {
        if (pokemons.isEmpty()) return 0;
        int max = pokemons.get(0).getHp();
        for (PokemonCharacter p : pokemons) {
            if (p.getHp() > max) {
                max = p.getHp();
            }
        }
        return max;
    }

    @Override
    public int getMinSpeed(ArrayList<PokemonCharacter> pokemons) {
        if (pokemons.isEmpty()) return 0;
        int min = pokemons.get(0).getSpeed();
        for (PokemonCharacter p : pokemons) {
            if (p.getSpeed() < min) {
                min = p.getSpeed();
            }
        }
        return min;
    }

    @Override
    public int getMaxSpeed(ArrayList<PokemonCharacter> pokemons) {
        if (pokemons.isEmpty()) return 0;
        int max = pokemons.get(0).getSpeed();
        for (PokemonCharacter p : pokemons) {
            if (p.getSpeed() > max) {
                max = p.getSpeed();
            }
        }
        return max;
    }
}
