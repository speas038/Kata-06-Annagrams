package com.speasmachines;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws Exception {

        Long programStart = System.nanoTime();
        Map<String, List<String>>dictionaryMap = buildDictionaryLookupTable();
        Long timeToBuildDictionary = TimeUnit.NANOSECONDS.toMillis(System.nanoTime()-programStart);

        Long sortStart = System.nanoTime();
        Map<String, List<String>> filteredMap = dictionaryMap.entrySet().stream().filter(x -> x.getValue().size() > 1)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        Long timeToFilterList = TimeUnit.NANOSECONDS.toMillis(System.nanoTime()-sortStart);

        Long printStart = System.nanoTime();
        filteredMap.entrySet().stream().forEach(a -> System.out.println(a));
        Long timeToPrint = TimeUnit.NANOSECONDS.toMillis(System.nanoTime()-printStart);

        Long runTime = TimeUnit.NANOSECONDS.toMillis(System.nanoTime()-programStart);

        System.out.println("Number of annagram Lists entries: " + filteredMap.size());
        System.out.println("Time to build Dictornary: " + timeToBuildDictionary);
        System.out.println("Time to build filtered list " + timeToFilterList);
        System.out.println("Time to print " + timeToPrint);
        System.out.println("Total Time: " + runTime);

    }

    private static Map<String, List<String>> buildDictionaryLookupTable() throws IOException {


        File file = new File("./resources/wordlist.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        Map<String, List<String>> dictionaryMap = new HashMap<>();

        String input;

        //Build hash map
        while ((input = br.readLine()) != null)
        {
            insertWord(dictionaryMap, input);
        }

        return dictionaryMap;
    }

    public static void insertWord(Map<String, List<String>> dictionaryMap, String input) {

        String word;
        String key;
        word = input;
        key = getSortedString(word);

        if(!dictionaryMap.containsKey(key))
        {
            List<String> newList = new ArrayList<>();
            newList.add(word);
            dictionaryMap.put(key, newList);
        }

        //If it doesn't contain the word, add it
        if( !dictionaryMap.get(key).contains(word))
        {
            dictionaryMap.get(key).add(word);
        }

        //otherwise it was a duplicate, so carry on, no action
    }


    public static String getSortedString(String st)
    {
        char[] ar = st.toCharArray();
        Arrays.sort(ar);
        return new String(ar);
    }
}
