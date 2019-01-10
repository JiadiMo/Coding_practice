// Given a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.
//
// Example:
//
//
// Input: [1,2,3,null,5,null,4]
// Output: [1, 3, 4]
// Explanation:
//
//    1            <---
//  /   \
// 2     3         <---
//  \     \
//   5     4       <---
//


/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        if(root == null) return result;
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);

        while(queue.size() > 0){
            int size = queue.size();

            for(int i=0; i<size; i++){
                TreeNode top = queue.remove();
                if(i==0){
                    result.add(top.val);
                }
                if(top.right != null){
                    queue.add(top.right);
                }
                if(top.left != null){
                    queue.add(top.left);
                }
            }
        }

        return result;
    }
}
