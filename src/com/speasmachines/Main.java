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

        Long start = System.nanoTime();

        Map<String, List<String>>dictionaryMap = buildDictionaryLookupTable();

        System.out.println("Time to Build Dictionary: " + TimeUnit.NANOSECONDS.toMillis(System.nanoTime()-start));

        Map<String, List<String>> filteredMap = dictionaryMap.entrySet().stream().filter(x -> x.getValue().size() > 1)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

//        filteredMap.entrySet().stream().forEach(a -> System.out.println(a));

        System.out.println("Filtered entries: " + filteredMap.size());

        System.out.println("Total Time: " + TimeUnit.NANOSECONDS.toMillis(System.nanoTime()-start));

//        System.out.println("Map:" + filteredMap);

    }

    private static Map<String, List<String>> buildDictionaryLookupTable() throws IOException {



        File file = new File("./resources/wordlist.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        Map<String, List<String>> dictionaryMap = new Hashtable<>();
        String word;
        String st;

        //Create hash map
        while ((st = br.readLine()) != null)
        {
            word = st;
            String key = getSortedString(word);

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
        return dictionaryMap;
    }

    private static String getSortedString(String st)
    {
        char[] ar = st.toCharArray();
        Arrays.sort(ar);
        return new String(ar);
    }
}
