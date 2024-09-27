import java.util.*;

public class HashMapTasks {

    public static List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> res = new HashMap<>();
        for(String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);
            if(!res.containsKey(key)) {
                res.put(key, new ArrayList<String>());
            }
            res.get(key).add(str);
        }
        return res.values().stream().toList();
    }
}
