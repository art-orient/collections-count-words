package com.efimchick.ifmo.collections.countwords;


import java.util.*;

public class Words {

    public String countWords(List<String> lines) {
        Map<String, Integer> result = new TreeMap<String, Integer>();
        for (String line : lines) {
            String[] nextLine = line.toLowerCase(Locale.ROOT).split("[\\s\\d\\.\\p{Punct}„“«»…]+");
            for (String s : nextLine) {
                if (s.length() > 3) {
                    result.put(s, result.getOrDefault(s, 0) + 1);
                }
            }
        }
        result = sortByValues(result);
        StringBuilder str = new StringBuilder();
        for (Map.Entry<String, Integer> entry : result.entrySet()) {
            if (entry.getValue() < 10) {
                continue;
            }
            str.append(entry.getKey()).append(" - ").append(entry.getValue()).append("\n");
        }
        str.setLength(str.length() - 1);
        return str.toString();
    }

    public static <K, V extends Comparable<V>> Map<K, V> sortByValues(final Map<K, V> map) {
        Comparator<K> valueComparator =  new Comparator<K>() {
            public int compare(K k1, K k2) {
                int compare = map.get(k2).compareTo(map.get(k1));
                if (compare == 0) return 1;
                else return compare;
            }
        };
        Map<K, V> sortedByValues = new TreeMap<K, V>(valueComparator);
        sortedByValues.putAll(map);
        return sortedByValues;
    }
}
