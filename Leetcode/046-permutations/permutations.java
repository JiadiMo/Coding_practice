// Given a collection of distinct integers, return all possible permutations.
//
// Example:
//
//
// Input: [1,2,3]
// Output:
// [
//   [1,2,3],
//   [1,3,2],
//   [2,1,3],
//   [2,3,1],
//   [3,1,2],
//   [3,2,1]
// ]
//
//


class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        helper(0, nums, result);
        return result;
    }
 
    private void helper(int i, int[] nums, List<List<Integer>> result){
        if(i==nums.length-1){
            result.add(convertArrayToList(nums));
            return;
        }
 
        for(int j=i; j<nums.length; j++){
            swap(nums, i, j);
            helper(i+1, nums, result);
            swap(nums, i, j);
        }
    }
 
    private void swap(int[] nums, int i, int j){
        int t = nums[i];
        nums[i]=nums[j];
        nums[j]=t;
    }
 
    private ArrayList<Integer> convertArrayToList(int[] num) {
        ArrayList<Integer> item = new ArrayList<Integer>();
        for (int h = 0; h < num.length; h++) {
            item.add(num[h]);
        }
        return item;
    }
}

