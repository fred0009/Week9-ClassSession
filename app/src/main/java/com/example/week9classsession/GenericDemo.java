package com.example.week9classsession;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collections;

public class GenericDemo {
    public static void main(String[] args) {
        Pair<String, Integer> pair1 = new Pair<>("Pokemon", 999);
        Pair<String, Integer> pair2 = new Pair<>("AAA", 999);
        Pair<String, Integer> pair3 = new Pair<>("Pokemon", 1);

        Pair<String, Coffee> pair4 = new Pair<>("Pokemon", new Coffee());

        ArrayList<Pair> arrP = new ArrayList<>();
        arrP.add(pair1);
        arrP.add(pair2);
        arrP.add(pair3);
        Collections.sort(arrP);
        System.out.println(arrP);

    }
}

class Coffee implements Comparable<Coffee> {
    @Override
    public int compareTo(Coffee coffee) {
        return 0; // adjust accordingly
    }
}

class Pair<T extends Comparable<T>, S extends Comparable<S>> implements Comparable<Pair<T,S>>{
    public T first;
    public S second;
    public Pair(T first, S second) {
        this.first = first;
        this.second = second;
    }
    @Override
    public int compareTo(Pair<T, S> tsPair) {
        int output = first.compareTo(tsPair.first);
        if (output==0) output = second.compareTo(tsPair.second);
        return output;
    }

    @NonNull
    @Override
    public String toString() {
        String output = "" + String.valueOf(first) + "-" + String.valueOf(second);
        return output;

    }
}
