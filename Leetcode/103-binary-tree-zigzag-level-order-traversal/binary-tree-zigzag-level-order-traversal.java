// Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then right to left for the next level and alternate between).
//
//
// For example:
// Given binary tree [3,9,20,null,null,15,7],
//
//     3
//    / \
//   9  20
//     /  \
//    15   7
//
//
//
// return its zigzag level order traversal as:
//
// [
//   [3],
//   [20,9],
//   [15,7]
// ]
//
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
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();

        if (root == null) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        int i = queue.size(); // 记录每层的结点个数
        boolean flag = false;
        TreeNode tempNode = null;
        List<Integer> singleLevel = new ArrayList<>();
        while (!queue.isEmpty()) {
            if (i == 0) {// 一层记录结束
                //
                if (flag) {
                    Collections.reverse(singleLevel);
                }
                result.add(singleLevel);
                //
                flag = !flag;

                i = queue.size();
                singleLevel = new ArrayList<>();
            }
            tempNode = queue.poll();
            singleLevel.add(tempNode.val);

            --i;

            if (tempNode.left != null) {
                queue.add(tempNode.left);
            }
            if (tempNode.right != null) {
                queue.add(tempNode.right);
            }

        }
        if (flag) {
            Collections.reverse(singleLevel);
        }
        result.add(singleLevel);

        return result;
    }
   
}
