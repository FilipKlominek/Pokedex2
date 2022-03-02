package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Comparator;

public class Main {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("pokemon.csv"))) {

            ArrayList<Pokemon> pokemon = new ArrayList<>();


            br.readLine();
            String line;
            while (true) {
                line = br.readLine();
                if (line == null) break;

                String[] split = line.split(",");

                pokemon.add(new Pokemon(
                        Integer.parseInt(split[0]),
                        split[1],
                        split[2],
                        split[3],
                        Integer.parseInt(split[4]),
                        Integer.parseInt(split[5]),
                        Integer.parseInt(split[6]),
                        Integer.parseInt(split[7]),
                        Integer.parseInt(split[8]),
                        Integer.parseInt(split[9]),
                        Integer.parseInt(split[10]),
                        Integer.parseInt(split[11]),
                        Boolean.parseBoolean(split[12])
                ));
            }


            System.out.println(pokemon.stream()
                    .filter(pokemon1 -> pokemon1.getGeneration() == 1)
                    .mapToInt(Pokemon::getAttack)
                    .average()
                    .getAsDouble());

            var gen2Legendary = pokemon.stream()
                    .filter(pokemon1 -> pokemon1.getGeneration() == 2)
                    .filter(Pokemon::isLegendary)
                    .map(Pokemon::getName).toArray();
            for (Object o : gen2Legendary) {
                System.out.print(o + " ");
            }
            System.out.println();

            var gen1MaxHp = pokemon.stream()
                    .filter(pokemon1 -> pokemon1.getGeneration() == 1)
                    .max(Comparator.comparingInt(Pokemon::getHp)).get();
            System.out.println(gen1MaxHp.getName() + " " + gen1MaxHp.getHp());

            var gen1SingleType = pokemon.stream()
                    .filter(pokemon1 -> pokemon1.getGeneration() == 1)
                    .filter(pokemon1 -> pokemon1.getType1().equals("") || pokemon1.getType2().equals(""))
                    .map(Pokemon::getName)
                    .toArray();
            for (Object o : gen1SingleType) {
                System.out.print(o + ", ");
            }
            System.out.println();

        } catch (Exception e) {
            System.out.println("Not found");
        }
    }
}
