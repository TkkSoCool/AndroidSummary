package com.tkk.androidsummary.knowledgepoint.algorithm;

/**
 * Created  on 2017/12/19
 *
 * @author 唐开阔
 * @describe
 */

/**
 * 二叉树
 * 二叉排序树 又称二叉查找树：左子树比父节点小，右子树比父节点大
 *
 */
public class BinaryTree<T> {
    private TreeNode<T> rootNode;//根节点
    IiterateListener<T> listener;

    public BinaryTree() {
    }

    public BinaryTree(TreeNode<T> rootNode) {
        this.rootNode = rootNode;
    }

    public TreeNode<T> getRootNode() {
        return rootNode;
    }

    public void setRootNode(TreeNode<T> rootNode) {
        this.rootNode = rootNode;
    }

    public int height() {
        return getHeight(rootNode);
    }

    public int size() {
        return getSize(rootNode);
    }

    /**
     * 获取节点的高度(递归算法从出口向前分析)
     *
     * @param node 节点
     * @return 高度
     */
    public int getHeight(TreeNode<T> node) {
        if (node == null) {      //递归出口
            return 0;
        }
        //所有的左节点高度
        int leftChildHeight = getHeight(node.leftChild);
        int rightChildHeight = getHeight(node.rightChild);
        int max = Math.max(leftChildHeight, rightChildHeight);
        return max + 1; //加上自己本身
    }

    /**
     * 获取一个节点的所有节点
     *
     * @param node
     * @return
     */
    public int getSize(TreeNode<T> node) {
        if (node == null) {
            return 0;
        }
        int leftChildHeight = getSize(node.leftChild);
        int rightChildHeight = getSize(node.rightChild);
        return leftChildHeight + rightChildHeight + 1; //加上自己本身
    }

    /**
     * 二叉树的遍历
     *
     * @param type     1,2,3  前中后
     * @param listener
     */
    public void iterateFirstOrde(int type, IiterateListener listener) {
        this.listener = listener;
        switch (type) {
            case 1:
                iterateFirstOrder(rootNode);
                break;
            case 2:
                iterateMediumOrder(rootNode);
                break;
            case 3:
                iterateLastOrder(rootNode);
                break;
                default:
                    break;


        }
    }

    /**
     * 先序遍历 根-左-右
     *
     * @param node
     */
    private void iterateFirstOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        listener.getData((T) node.data);
        iterateFirstOrder(node.leftChild);
        iterateFirstOrder(node.rightChild);

    }

    /**
     * 中序遍历 左-根-右
     *
     * @param node
     */
    private void iterateMediumOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        iterateFirstOrder(node.leftChild);
        listener.getData((T) node.data);
        iterateFirstOrder(node.rightChild);

    }


    /**
     * 后序遍历 左-右-根
     *
     * @param node
     */
    private void iterateLastOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        iterateFirstOrder(node.leftChild);
        iterateFirstOrder(node.rightChild);
        listener.getData((T) node.data);
    }

    /**
     * 节点类
     */
    public static class TreeNode<T> {
        public T data;
        public TreeNode<T> leftChild;
        public TreeNode<T> rightChild;

        public TreeNode(T data) {
            this.data = data;
        }

        public TreeNode(T data, TreeNode<T> leftChild, TreeNode<T> rightChild) {
            this.data = data;
            this.leftChild = leftChild;
            this.rightChild = rightChild;
        }
    }
    public interface IiterateListener<T> {
        void getData(T data);
    }
}
