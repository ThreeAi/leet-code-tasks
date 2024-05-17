import jdk.jshell.spi.SPIResolutionException;

import java.io.Console;
import java.math.BigInteger;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {

        int[] height = new int[]{4,2,3};

        System.out.println(Solution.myAtoi("   -042"));
    }


}

class Solution {
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] temp = nums1.clone();
        int index1 = 0;
        int index2 = 0;
        while (index1 < m || index2 < n) {
            if ((index2 >= n || temp[index1] <= nums2[index2]) && index1 < m) {
                nums1[index1 + index2] = temp[index1++];
            } else {
                nums1[index1 + index2] = nums2[index2++];
            }
        }
    }
    public static int removeElement(int[] nums, int val) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            if (nums[left] == val) {
                while (right > 0 && nums[right] == val) {
                    right--;
                }
                nums[left] = nums[right];
                right--;
                if(left > right) break;
            }
            left++;
        }
        return left;
    }

    public static int removeDuplicates(int[] nums) {
        int index = 0;
        boolean count = true;
        for(int i = 1; i< nums.length; i++) {
            if (nums[index] != nums[i]) {
                nums[++index] = nums[i];
                count = true;
            }
            else if (count) {
                nums[++index] = nums[i];
                count = false;
            }
        }
        return ++index;
    }

    public int majorityElement(int[] nums) {
        int max = 0;
        int res = nums[0];
        Map<Integer, Integer> map = new HashMap<>();
        for(int num : nums) {
            if (map.containsKey(num)) {
                int value = map.get(num);
                map.put(num, ++value);
                if (value > max) {
                    max = value;
                    res = num;
                }
            }
            else {
                map.put(num, 1);
            }
        }
        return res;
    }

    public static void rotate(int[] nums, int k) {
        if (k == 0) return;
        int[] temp = new int[k];
        for(int i = 0; i < nums.length + k; i++) {
            int a = nums[i % nums.length];
            nums[i % nums.length] = temp[i % k];
            temp[i % k] = a;
        }
    }

    public static int maxProfit1(int[] prices) {
        int profit = 0;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] > min) continue;
            int max = 0;
            for (int j = i + 1; j < prices.length; j++) {
                if (prices[j] < max) continue;
                if (prices[j] - prices[i] >= profit) {
                    profit = prices[j] - prices[i];
                    min = prices[i];
                    max = prices[j];
                }
            }
        }
        return profit;
    }

    public static int maxProfit(int[] prices) {
        List<Integer> res = new ArrayList<>();
        List<Integer> pricesList = IntStream.of(prices)
                .boxed()
                .toList();
        recurciveProfit(0, Integer.MAX_VALUE, pricesList, 0, res);
        System.out.println(res);
        return res.stream().max(Integer::compareTo).orElse(0);
    }

    public static void recurciveProfit(int index, int buyPrice, List<Integer> prices, int sum, List<Integer> results) {
        if (index >= prices.size()) {
            results.add(sum);
            return;
        }
        if (prices.get(index) < buyPrice) {
            recurciveProfit(index + 1, prices.get(index), prices, sum, results);
        }
        else {
            recurciveProfit(index + 1, buyPrice, prices, sum, results);
            recurciveProfit(index + 1, Integer.MAX_VALUE, prices, sum + (prices.get(index) - buyPrice), results);
        }
    }

    public static boolean canJump(int[] nums) {
        int reachable = 0;
        for(int i = 0; i < nums.length; i ++) {
            if(i > reachable) return false;
            if (reachable < i + nums[i]) {
                reachable = i + nums[i];
            }
        }
        return true;
    }

    public static int jump(int[] nums) {
        List<Integer> jumps = new ArrayList<>();
        recursiveJumps(nums, 0, 0, jumps);
        return jumps.stream().min(Integer::compareTo).orElse(0);
    }

    private static void recursiveJumps(int[] nums, int currentIndex, int jump, List<Integer> jumps) {
        if (nums.length - 1 == currentIndex) {
            jumps.add(jump);
            return;
        }
        if (currentIndex >= nums.length || nums[currentIndex] == 0) return;
        for(int i = 1; i <= nums[currentIndex]; i++) {
            recursiveJumps(nums, currentIndex + i, jump + 1, jumps);
        }
    }

    public static int[] productExceptSelf(int[] nums) {
        boolean zeroExist = false;
        int indexZero = 0;
        int product = 1;
        for(int i = 0; i < nums.length; i ++) {
            if (nums[i] != 0) {
                product *= nums[i];
            } else if(!zeroExist) {
                zeroExist = true;
                indexZero = i;
            }
            else {
                return new int[nums.length];
            }
        }
        if (zeroExist) {
            int[] res = new int[nums.length];
            res[indexZero] = product;
            return res;
        }
        int[] res = new int[nums.length];
        for(int i = 0; i < nums.length; i++) {
            res[i] = product / nums[i];
        }
        return res;
    }

    public static int canCompleteCircuit(int[] gas, int[] cost) {
            // 4 1 5 2 1 3 2 4 3 5
            // 1 3 2 4 3 5 4 1 5 2
        int n = gas.length;
        int total_surplus = 0;
        int surplus = 0;
        int start = 0;

        for(int i = 0; i < n; i++){
            total_surplus += gas[i] - cost[i];
            surplus += gas[i] - cost[i];
            if(surplus < 0){
                surplus = 0;
                start = i + 1;
            }
        }
        return (total_surplus < 0) ? -1 : start;
    }

    public static int candy(int[] ratings) {
        // +1 -1 +1
        // +1 -1 +1

        // +1 +1 0
        // +1 0 0

        int n = ratings.length;
        int[] candies = new int[n];
        Arrays.fill(candies, 1);

        for (int i = 1; i < n; i++) {
            if (ratings[i] > ratings[i - 1]) {
                candies[i] = candies[i - 1] + 1;
            }
        }

        for (int i = n - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                candies[i] = Math.max(candies[i], candies[i + 1] + 1);
            }
        }

        int totalCandies = 0;
        for (int candy : candies) {
            totalCandies += candy;
        }

        return totalCandies;
    }

    public static int trap(int[] height) {
        int res = 0;
        int indexMaxTop = 0;
        int maxTop = 0;
        for(int i = 0; i < height.length; i++) {
            if(maxTop <= height[i]) {
                maxTop = height[i];
                indexMaxTop = i;
            }
        }
        int localTop = 0;
        for(int i = 0; i < indexMaxTop; i++) {
            if(localTop < height[i]) {
                localTop = height[i];
                continue;
            }
            res += localTop - height[i];
        }
        localTop = 0;
        for(int i = height.length - 1; i > indexMaxTop; i--){
            if(localTop < height[i]) {
                localTop = height[i];
                continue;
            }
            res += localTop - height[i];
        }
        return res;
    }


    public static int romanToInt(String s) {
        int[] digits = new int[s.length()];
        for(int i = 0; i < s.length(); i++){
            digits[i] = convertToInt(s.charAt(i));
        }
        int res = digits[digits.length - 1];
        for(int i = digits.length - 2; i >= 0; i--) {
            if(digits[i] < digits[i+1]) {
                res -= digits[i];
            }
            else {
                res += digits[i];
            }
        }
        return res;
    }

    public static int convertToInt(char c) {
        return switch (c) {
            case 'I' -> 1;
            case 'V' -> 5;
            case 'X' -> 10;
            case 'L' -> 50;
            case 'C' -> 100;
            case 'D' -> 500;
            case 'M' -> 1000;
            default -> 0;
        };
    }

    public static String intToRoman(int num) {
        StringBuilder res = new StringBuilder();
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbols = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
        for(int i = 0; i < values.length; i++){
            while(num - values[i] >= 0) {
                num -= values[i];
                res.append(symbols[i]);
            }
        }
        return res.toString();
    }

    public static int lengthOfLastWord(String s) {
        boolean space = false;
        int max = 0;
        for(int i = s.length() - 1; i >=0; i--) {
            char c = s.charAt(i);
            if(c != ' ') {
                max++;
                space = true;
            }
            else {
                if(space) {
                    return max;
                }
                else {
                    continue;
                }
            }
        }
        return max;
    }

    public static String longestCommonPrefix(String[] strs) {
        String prefix = strs[0];
        for(int i = 1; i < strs.length; i++) {
            int j = 0;
            while(prefix.length() > j && strs[i].length() > j && prefix.charAt(j) == strs[i].charAt(j)){
                j ++;
            }
            if(j == 0) return "";
            prefix = prefix.substring(0, j);
        }
        return prefix;
    }

    public String reverseWords(String s) {
        s = s.trim();
        String[] arr = s.split("\\s+");
        StringBuilder res = new StringBuilder();
        for(int i = arr.length - 1; i >0; i--) {
            if(!arr[i].contains(" ")) {
                res.append(arr[i]).append(" ");
            }
        }
        res.append(arr[0]);
        return res.toString().trim();
    }

    public static String convert(String s, int numRows) {
        if(numRows == 1) {
            return s;
        }
        StringBuilder res = new StringBuilder();
        for(int i = 0; i < numRows; i++) {
            int index = i;
            while(index < s.length()) {
                if(i == 0 || i == numRows - 1) {
                    res.append(s.charAt(index));
                    index = index + (numRows * 2 - 2);
                }
                else {
                    res.append(s.charAt(index));
                    index = index + (numRows * 2 - 2) - i * 2;
                    if (index < s.length()) {
                        res.append(s.charAt(index));
                        index = index + i * 2;
                    }
                }
            }
        }
        return res.toString();
    }

    public static int reverse(int x) {
        String str = String.valueOf(x);
        StringBuilder res = new StringBuilder();
        int end = 0;
        if(str.charAt(0) == '-') {
            res.append('-');
            end = 1;
        }
        for(int i = str.length() - 1; i >= end; i -- ){
            res.append(str.charAt(i));
        }
        try {
            return Integer.parseInt(res.toString());
        }
        catch(Exception e)
        {
            return 0;
        }
    }

    public static int myAtoi(String s) {
        s = s.trim();
        if(s.isEmpty()) return 0;
        StringBuilder res = new StringBuilder();
        int start = 0;
        if(s.charAt(0) == '-' || s.charAt(0) == '+') {
            res.append(s.charAt(0));
            start = 1;
        }
        boolean flag = true;
        for(int i = start; i < s.length() && flag; i++ ) {
            if(Character.isDigit(s.charAt(i))) {
                res.append(s.charAt(i));
            }
            else {
                flag = false;
            }
        }
        try {
            BigInteger result = new BigInteger(res.toString());
            if(result.compareTo(new BigInteger(String.valueOf(Integer.MIN_VALUE))) < 0) return Integer.MIN_VALUE;
            if(result.compareTo(new BigInteger(String.valueOf(Integer.MAX_VALUE))) > 0) return Integer.MAX_VALUE;
            return Integer.valueOf(result.toString());
        }
        catch (Exception e) {
            return 0;
        }
    }
}

class RandomizedSet {

    private Set<Integer> set;
    public RandomizedSet() {
        set = new HashSet<>();
    }

    public boolean insert(int val) {
        return set.add(val);
    }

    public boolean remove(int val) {
        return set.remove(val);
    }

    public int getRandom() {
        Random random = new Random();
        return (int)set.toArray()[random.nextInt(set.size())];
    }
}