import java.util.*;

public class Interval {

    //228. Summary Ranges
    public static List<String> summaryRanges(int[] nums) {
        if(nums == null || nums.length == 0) return Collections.emptyList();
        List<String> res = new ArrayList<>();
        int temp = nums[0];
        for(int i = 1; i < nums.length; i++) {
            if(nums[i - 1] != nums[i] - 1) {
                if(temp == nums[i - 1]){
                    res.add(String.valueOf(nums[i - 1]));
                }
                else {
                    res.add(temp + "->" + String.valueOf(nums[i - 1]));
                }
                temp = nums[i];
            }
        }
        if(temp == nums[nums.length - 1]) {
            res.add(String.valueOf(nums[nums.length - 1]));
        }
        else {
            res.add(temp + "->" + String.valueOf(nums[nums.length - 1]));
        }
        return res;
    }

    //56. Merge Intervals
    public static int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        List<int[]> resList = new ArrayList<>();
        resList.add(new int[]{intervals[0][0], intervals[0][1]});
        int index = 0;
        for(int i = 1; i < intervals.length; i++) {
            if(resList.get(index)[1] >= intervals[i][0]) {
                if(intervals[i][1] > resList.get(index)[1])
                    resList.get(index)[1] = intervals[i][1];
            }
            else {
                resList.add(new int[]{intervals[i][0], intervals[i][1]});
                index++;
            }
        }
        return resList.toArray(new int[resList.size()][]);
    }

    //57. Insert Interval
    public static int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> ans = new ArrayList<>();
        int n = intervals.length;
        int i =0;
        while(i<n && intervals[i][1]<newInterval[0]){
            ans.add(new int[]{intervals[i][0],intervals[i][1]});
            i++;
        }
        while(i<n && intervals[i][0]<=newInterval[1]){
            newInterval[0] = Math.min(intervals[i][0],newInterval[0]);
            newInterval[1] = Math.max(intervals[i][1],newInterval[1]);
            i++;
        }
        ans.add(new int[]{newInterval[0],newInterval[1]});
        while(i<n){
            ans.add(new int[]{intervals[i][0],intervals[i][1]});
            i++;
        }
        int res[][] = new int[ans.size()][2];
        for(int j =0;j<ans.size();j++){
            res[j][0] = ans.get(j)[0];
            res[j][1] = ans.get(j)[1];
        }
        return res;
    }
}
