package com.efimchick.ifmo.collections.countwords;

import java.util.*;

public class Words {
    private final boolean ASCENDING = true;
    private final boolean DESCENDING = false;

    public String countWords(List<String> lines) {

        String punctuation = "[«»,.()\\[\\]!?…;“„':–[0-9]\n]++";

        StringBuilder stringBuilder = new StringBuilder();

        for (String line : lines) {
            line = line + "\n";
            line = line.toLowerCase().replaceAll(punctuation, " ");
            stringBuilder.append(line);
        }

        lines = Arrays.asList(stringBuilder.toString().split("\\s"));

        TreeMap<String, Integer> map = new TreeMap<>();
        for (String word : lines) {
            if (!map.containsKey(word)) {
                map.put(word, 1);
            } else {
                int currentValue = map.get(word);
                map.put(word, currentValue + 1);
            }
        }

        return sortByComparator(map, DESCENDING).toString()
            .replaceAll(", ", "\n")
            .replaceAll("=", " - ")
            .replaceAll("[{}]", "");
    }

    private static Map<String, Integer> sortByComparator(Map<String, Integer> notSortMap, final boolean order) {

        List<Map.Entry<String, Integer>> list = new LinkedList<>(notSortMap.entrySet());

        list.sort(new Comparator<>() {

            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {

                if (order) {
                    return o1.getValue().compareTo(o2.getValue());
                } else {
                    return o2.getValue().compareTo(o1.getValue());
                }
            }
        });

        Map<String, Integer> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entry : list) {
            if (entry.getValue() > 9 && entry.getKey().length() >= 4) {
                sortedMap.put(entry.getKey(), entry.getValue());
            }
        }
        return sortedMap;
    }
}
