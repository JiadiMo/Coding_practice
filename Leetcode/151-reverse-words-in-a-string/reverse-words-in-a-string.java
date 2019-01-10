// Given an input string, reverse the string word by word.
//
// Example:  
//
//
// Input: "the sky is blue",
// Output: "blue is sky the".
//
//
// Note:
//
//
// 	A word is defined as a sequence of non-space characters.
// 	Input string may contain leading or trailing spaces. However, your reversed string should not contain leading or trailing spaces.
// 	You need to reduce multiple spaces between two words to a single space in the reversed string.
//
//
// Follow up: For C programmers, try to solve it in-place in O(1) space.
//


public class Solution {
    public String reverseWords(String s) {
        if(s==null||s.length()==0)
            return s;
        String [] result = s.split(" ");
        if(result==null||result.length==0)
            return "";
            
        ArrayList<String> list = new ArrayList<String>();
        
        for(int i = 0; i<result.length;i++){
            if(!result[i].isEmpty())
                list.add(result[i]);
        }
        Collections.reverse(list);
        
        String ans = new String();
        for(int i = 0; i<list.size()-1;i++){
            ans += list.get(i)+" ";
        }
        ans +=list.get(list.size()-1);
        return ans;
    }
}
