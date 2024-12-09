import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTreeBFSTasks {

    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    //637. Average of Levels in Binary Tree
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> res = new ArrayList<>();

        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode divider = new TreeNode();

        queue.add(root);
        queue.add(divider);
        long sum = 0;
        int count = 0;

        while(!queue.isEmpty()) {
            var temp = queue.poll();
            if(temp == divider) {
                res.add((double)sum / count);
                if(!queue.isEmpty())
                    queue.add(divider);
                sum = 0;
                count = 0;
            }
            else {
                sum += temp.val;
                count++;
                if(temp.right != null) queue.add(temp.right);
                if(temp.left != null) queue.add(temp.left);
            }
        }

        return res;
    }

    //102. Binary Tree Level Order Traversal
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res; // Handle edge case of an empty tree

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size(); // Number of nodes in the current level
            List<Integer> level = new ArrayList<>();

            for (int i = 0; i < levelSize; i++) {
                TreeNode current = queue.poll();
                level.add(current.val);

                if (current.left != null) queue.add(current.left);
                if (current.right != null) queue.add(current.right);
            }

            res.add(level);
        }

        return res;
    }
}
