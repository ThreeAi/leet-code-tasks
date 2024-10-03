import java.util.*;

public class HashMapTasks {

    public static List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> res = new HashMap<>();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);
            if (!res.containsKey(key)) {
                res.put(key, new ArrayList<String>());
            }
            res.get(key).add(str);
        }
        return res.values().stream().toList();
    }

    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> numToIndex = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (numToIndex.containsKey(target - nums[i])) {
                return new int[]{numToIndex.get(target - nums[i]), i};
            }
            numToIndex.put(nums[i], i);
        }
        return new int[]{};
    }

    public static boolean isHappy(int n) {
        Set<Integer> set = new HashSet<>();
        int sum = n;
        do {
            if (sum == 1) {
                return true;
            }
            set.add(sum);
            int temp = 0;
            while (sum != 0) {
                temp += (int) Math.pow(sum % 10, 2);
                sum /= 10;
            }
            sum = temp;
        } while (!set.contains(sum));
        return false;
    }

    public static boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length; i++) {
            if(map.containsKey(nums[i]) && i - map.get(nums[i]) <= k) {
                return true;
            }
            map.put(nums[i], i);
        }
        return false;
    }

    public static int longestConsecutive(int[] nums) {
        Set<Integer> numSet = new HashSet<>();
        for (int num : nums) {
            numSet.add(num);
        }

        int longest = 0;

        for (int num : nums) {
            if (!numSet.contains(num - 1)) {
                int length = 1;

                while (numSet.contains(num + length)) {
                    length++;
                }

                longest = Math.max(longest, length);
            }
        }

        return longest;
    }


}
