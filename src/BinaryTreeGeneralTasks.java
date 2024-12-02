import java.util.HashMap;

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

    //101. Symmetric Tree
    public boolean isSymmetric(TreeNode root) {
        if(root == null) return true;
        return nodeEquals(root.left, root.right);
    }

    private boolean nodeEquals(TreeNode p, TreeNode q) {
        if(p == null && q == null) return true;
        if(p == null || q == null || p.val != q.val)
            return false;
        return nodeEquals(p.left, q.right) && nodeEquals(p.right, q.left);
    }

    //105. Construct Binary Tree from Preorder and Inorder Traversal
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return buildSubtree(inorder, 0, inorder.length - 1, preorder, 0, preorder.length - 1, map);
    }

    private TreeNode buildSubtree(int[] inorder, int inStart, int inEnd, int[] preorder, int preStart, int preEnd, HashMap<Integer, Integer> map) {
        if (inStart > inEnd || preStart > preEnd) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[preStart]);

        int inRootIndex = map.get(root.val);
        int leftTreeSize = inRootIndex - inStart;

        root.left = buildSubtree(inorder, inStart, inRootIndex - 1, preorder, preStart + 1, preStart + leftTreeSize, map);
        root.right = buildSubtree(inorder, inRootIndex + 1, inEnd, preorder, preStart + leftTreeSize + 1, preEnd, map);

        return root;
    }

    //112. Path Sum
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if(root == null && targetSum == 0) return false;
        return recursivePathSum(root, 0, targetSum);
    }

    public boolean recursivePathSum(TreeNode root, int sum, int targetSum) {
        if(root != null) {
            sum += root.val;
        }
        else {
            return false;
        }
        if(root.right == null && root.left == null) {
            return sum == targetSum;
        }
        else {
            return recursivePathSum(root.left, sum, targetSum) || recursivePathSum(root.right, sum, targetSum);
        }
    }

    //222. Count Complete Tree Nodes
    public int countNodes(TreeNode root) {
        if(root == null) {
            return 0;
        }
        return 1 + countNodes(root.left) + countNodes(root.right);
    }
}
