package com.tkk.androidsummary.knowledgepoint.algorithm;

import android.support.annotation.IntRange;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.tkk.androidsummary.R;
import com.tkk.androidsummary.annotation.BindLayout;
import com.tkk.androidsummary.annotation.KnowledgeInfo;
import com.tkk.androidsummary.base.BaseActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.function.BinaryOperator;

/**
 * Created  on 2017/11/30
 * @author 唐开阔
 * @describe 常用数据结构，链表，列队，栈，速度，树，图，哈希表
 *
 * 集合框架：
 * 一：Collection接口：包含了容器类的主要方法。
    直接子类
        1)List接口：是一个元素有序的、可以重复、可以为 null 的集合（序列）。
 * 二：Map接口
 *
 * 三：集合的工具类 Collections
 */
@BindLayout(R.layout.activity_data_structure)
@KnowledgeInfo(catalog = KnowledgeInfo.ALGROITHM, desc = "常用数据结构")
public class DataStructureActivity extends BaseActivity {
    @Override
    protected void initView() {
        ArrayList<Integer> arrayList = new ArrayList<>();
        LinkedList aa;
        SinglyLinkedList<Integer> linkedList = new SinglyLinkedList<Integer>();
        for (int i = 1; i < 10; i++) {
            int data = i*i;
            linkedList.add(data);
        }
        for (int i = 0; i < linkedList.size(); i++) {
//            Log.d(TAG, ">>>initView---" + linkedList.get(i));
        }
        BinaryTree.TreeNode<String> rootNode = new BinaryTree.TreeNode<String>("A");
        BinaryTree.TreeNode<String> nodeB = new BinaryTree.TreeNode<String>("B");
        BinaryTree.TreeNode<String> nodeC = new BinaryTree.TreeNode<String>("C");
        BinaryTree.TreeNode<String> nodeD = new BinaryTree.TreeNode<String>("D");
        BinaryTree.TreeNode<String> nodeE = new BinaryTree.TreeNode<String>("E");
        BinaryTree.TreeNode<String> nodeF = new BinaryTree.TreeNode<String>("F");
        BinaryTree.TreeNode<String> nodeG = new BinaryTree.TreeNode<String>("G");
        BinaryTree.TreeNode<String> nodeH = new BinaryTree.TreeNode<String>("H");
        rootNode.leftChild = nodeB;
        rootNode.rightChild = nodeC;
        nodeB.leftChild = nodeD;
        nodeB.rightChild = nodeE;
        nodeC.leftChild = nodeF;
        nodeC.rightChild = nodeG;
        nodeF.leftChild = nodeH;
        BinaryTree<String> binaryTree = new BinaryTree<>(rootNode);
        Log.d(TAG, ">>>initView---深度" + binaryTree.height());
        Log.d(TAG, ">>>initView---所有节点" + binaryTree.size());
        binaryTree.iterateFirstOrde(2, new BinaryTree.IiterateListener<String>() {
            @Override
            public void getData(String data) {
                Log.d(TAG, ">>>initView---遍历" + data);
            }
        });
    }

    public void digui(int n){
        if (n>0) {
            n--;
            digui(n);
            Log.d(TAG, ">>>digui---" + n);
        }
    }



    /**
     * 单链表。
     */
    class SinglyLinkedList<T> {
        private int size = 0; //链表的长度
        private Node<T> first;//表头
        private Node<T> last;//表尾,方便元素的添加
        /**
         * 插入一条数据到指定位置
         * @param data  要插入的数据
         * @param index
         */
        public void add(T data, int index) {
            final Node<T> newNode = new Node<>(data, null);
            if (!isPositionIndex(index)){
                return ;
            }else {
                if (index == 0){//表头
                    addFirst(data);
                }else if (index == size){//表尾
                    addLast(data);
                }else {//中间
                    addIn(data,node(index));
                }
            }
        }

        public int size(){
            return size;
        }

        public void add(T data){
            addLast(data);
        }

        public  T get(int index){
            if (size == 0 || index > size){
                return null;
            }
            Node<T> node = first;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
            return node.item;
        }

        /**
         *插入数据
         * @param data
         * @param node
         */
        private void addIn(T data, Node<T> node) {
            Node<T> next = node.next;
            Node<T> newNode = new Node<>(data,next);
            node.next = newNode;
            size++;
        }

        /**
         * 获取插入位置的前一个节点
         * @param index 位置
         * @return 返回节点数据
         */
        Node<T> node(int index) {
            Node<T> x = first;
            for (int i = 0; i < index; i++) {
                x = x.next;
            }
            return x;
        }

        /**
         * 表头添加一个元素
         * @param data
         */
        private void addFirst(T data){
            final Node<T> newNode = new Node<>(data, null);
            if (first == null){
                first = last = newNode;

            }else {
               final  Node<T> f = first;
               newNode.next = f;
               first = newNode;
            }
            size++;
        }


        /**
         * 向表尾添加一个元素
         * @param data
         */
        private void addLast(T data) {
            //构建一个新的节点对象
            final Node<T> newNode = new Node<>(data, null);
            final Node<T> l = last;
            last = newNode;
            if (l == null) {
                first = newNode;
            } else {
                l.next = newNode;

            }
            size++;
        }

        private boolean isPositionIndex(int index) {
            return index >= 0 && index <= size;
        }

        //一个节点的数据。
        class Node<T> {
            T item;//当前数据
            Node<T> next;//指向的另一个数据

            public Node(T item, Node<T> next) {
                this.item = item;
                this.next = next;
            }
        }
    }


}
