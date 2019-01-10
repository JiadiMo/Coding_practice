// Write a function to find the longest common prefix string amongst an array of strings.
//
// If there is no common prefix, return an empty string "".
//
// Example 1:
//
//
// Input: ["flower","flow","flight"]
// Output: "fl"
//
//
// Example 2:
//
//
// Input: ["dog","racecar","car"]
// Output: ""
// Explanation: There is no common prefix among the input strings.
//
//
// Note:
//
// All given inputs are in lowercase letters a-z.
//


class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0)
            return "";
        StringBuilder res = new StringBuilder();
        int index = 0;
        int len = minlen(strs);
        while (index < len){
            for (int i=1; i<strs.length;i++){
                if (strs[i].charAt(index) != strs[0].charAt(index))
                    return res.toString();
            }
            res.append(strs[0].charAt(index));
            index++;
        }
        return res.toString();
    }
    private int minlen(String[] strs) {
        int min = Integer.MAX_VALUE;
        for(int i=0; i<strs.length;i++)
            min = Math.min(min,strs[i].length());
        return min;
    }
}
