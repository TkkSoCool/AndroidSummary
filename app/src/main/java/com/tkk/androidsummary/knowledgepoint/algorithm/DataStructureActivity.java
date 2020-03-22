package com.tkk.androidsummary.knowledgepoint.algorithm;

import android.support.annotation.IntRange;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.ArrayMap;
import android.util.Log;
import android.util.SparseArray;
import com.tkk.androidsummary.R;
import com.tkk.androidsummary.annotation.BindLayout;
import com.tkk.androidsummary.annotation.KnowledgeInfo;
import com.tkk.androidsummary.base.BaseActivity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;
import java.util.function.BinaryOperator;

/**
 * Created  on 2017/11/30
 * @author 唐开阔
 * @describe 常用数据结构，链表，列队，栈，速度，树，图，哈希表
 * 集合框架：
 * 一：Collection接口：包含了容器类的主要方法。
    直接子类
        1)List接口：是一个元素有序的、可以重复、可以为 null 的集合（序列）。
 * 二：Map接口
 *      主要实现类 HashMap无序，其主要实现是一个数组，每一项是一个单链表。
 *      LinkHashMap:有序,因为护着一个运行于所有条目的双重链接列表
 *      1)构建：：初始容量：initialCapacity默认16
 *               加载因子：loadFactor默认0.75
 *               哈希表： table数组
 *      2)put(K,V)过程：
 *            1. 传入key和value，判断key是否为null，如果为null，则调用putForNullKey，以null作为key存储到哈希表中；
　　          2. 然后计算key的hash值，根据hash值搜索在哈希表table中的索引位置，若当前索引位置不为null，
                    则对该位置的Entry链表进行遍历，如果链中存在该key，则用传入的value覆盖掉旧的value，同时把旧的value返回，结束；
　　          3. 否则调用addEntry，用key-value创建一个新的节点，并把该节点插入到该索引对应的链表的头部
 *      3)get(K)过程
 *          读取的步骤比较简单，调用hash（key）求得key的hash值，然后调用indexFor（hash）求得hash值对应的table的索引位置，然后遍历索引位置的链表，如果存在key，则把key对应的Entry返回，否则返回null
 *
 *  三：Set
 *      Set是一种不包括重复元素的Collection。它维持它自己的内部排序，所以随机访问没有任何意义。与List一样，它同样运行null的存在但是仅有一个。
 *      由于Set接口的特殊性，所有传入Set集合中的元素都必须不同，同时要注意任何可变对象，
 *      如果在对集合中元素进行操作时，导致e1.equals(e2)==true，则必定会产生某些问题。实现了Set接口的集合有：EnumSet、HashSet、TreeSet。
 *      3.3、HashSet
　　      HashSet堪称查询速度最快的集合，因为其内部是以HashMap来实现的。
        它内部元素的顺序是由哈希码来决定的，所以它不保证set 的迭代顺序；特别是它不保证该顺序恒久不变。
        3.4、LinkedHashSet
　　      LinkedHashSet是HashSet的子类，此实现与 HashSet 的不同之外在于，后者维护着一个运行于所有条目的双链表。
        此链接列表定义了迭代顺序，即按照将元素插入到 set 中的顺序（插入顺序）进行迭代。
        注意，插入顺序不 受在 set 中重新插入的 元素的影响。
　　也就是说，LinkedHashSet是迭代有序的HashSet
 * 四：集合的工具类 Collections
 */
@BindLayout(R.layout.activity_data_structure)
@KnowledgeInfo(catalog = KnowledgeInfo.ALGROITHM, desc = "常用数据结构")
public class DataStructureActivity extends BaseActivity {
    @Override
    protected void initView() {
        HashMap<String,Integer> map = new HashMap<>();
        map.put("1",1);
        HashSet a;
        ArrayMap bb = null;
        bb.put(1,1);
        SparseArray ddd = null;
        ddd.put(1,"2");
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
