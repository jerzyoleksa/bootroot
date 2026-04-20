package org.example.module3.util;

import org.example.module3.domain.Person;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Test {

    // Najczęściej spotykane
    public static void main(String[] args) {
        Person jurek = new Person("Jurek","Oleksa");
        Person norbert = new Person("Norbert","Katabas");
        List<Person> users = List.of(jurek, norbert);
        List<String> aktywneMaile = users.stream()           // Stream ← z Listy
                //.filter(u -> u.getLastName().startsWith("J"))
                .map(Person::getFirstName)
                .toList();                                        // z powrotem do Listy

        System.out.println(users);

        // Array → Stream → List
        int[] oceny = {3, 5, 2, 6, 4};
        double srednia = Arrays.stream(oceny)                 // Stream ← z array
                .average()
                .orElse(0.0);

        // List → Stream → array (rzadziej)
        List<String> imiona = List.of("baba", "jaga");

        Stream.of(jurek, norbert)
                .map(Person::getFirstName)
                .forEach(System.out::println);

        users.stream().map(Person::checkAge); // nie wykona sie bo nie ma funkcji terminalnej

        List<Person> list = users.stream().filter(p -> p.getLastName().startsWith("O")).toList();

        System.out.println(list);

        String[] tablica = imiona.stream()
                .filter(s -> s.length() > 3)
                .toArray(String[]::new);

        System.out.println(srednia);
    }

}
