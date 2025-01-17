import java.util.*;

public class BinaryTreeGeneralTasks {
    public class TreeNode {
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

    //104. Maximum Depth of Binary Tree
    public int maxDepth(TreeNode root) {
        if(root == null) return 0;
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    //100. Same Tree
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p == null && q == null) return true;
        if(p == null && q != null || p != null && q == null || p != null && q != null && p.val != q.val)
            return false;
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    //226. Invert Binary Tree
    public TreeNode invertTree(TreeNode root) {
        if(root == null) return null;
        var temp = root.left;
        root.left = invertTree(root.right);
        root.right = invertTree(temp);
        return root;
    }

    //106. Construct Binary Tree from Inorder and Postorder
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        Map<Integer, Integer> leftSide = new HashMap<>(postorder.length);
        Map<Integer, Integer> rightSide = new HashMap<>(inorder.length);
        Set<Integer> setNodes = new HashSet<>();
        for (int i = 0; i < inorder.length; i ++) {
            rightSide.put(inorder[i], i);
            leftSide.put(postorder[i], i);
        }
        int rootVal = postorder[postorder.length - 1];
        TreeNode root = addNode(rootVal, leftSide, inorder, rightSide, postorder, setNodes);
        return root;
     }

     public TreeNode addNode(int val, Map<Integer, Integer> leftSide, int[] left, Map<Integer, Integer> rightSide, int[] right, Set<Integer> setNodes) {

        setNodes.add(val);
        var res = new TreeNode(val);
        int indexLeft = leftSide.get(val);
        int indexRight = rightSide.get(val);
        if(indexLeft > 0) {
            res.left = addNode(left[indexLeft], leftSide, left, rightSide, right, setNodes);
        }
        if(!setNodes.contains(val)) {
            res.right = addNode(right[indexRight], leftSide, left, rightSide, right, setNodes);
        }
        return res;
     }
}
