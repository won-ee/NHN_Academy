package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class XBinarySearchTree<T extends Comparable<T>> implements XBinaryTree<T>{
    Node<T> root;
    int size;

    public XBinarySearchTree(){
        this.root = null;
        this.size = 0;
    }


    private static class Node<T> {
        T value;
        Node<T> left;
        Node<T> right;

        private Node(T value){
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }


    @Override
    public void insert(T value) {
        if (value==null){
            throw new NullPointerException("Value cannot be null");
        }
        root = insertRec(root,value);
//        Node<T> current = root;
//        while (true){
//            int cmp = value.compareTo(current.value);
//
//            if(cmp<0){
//                if(current.left==null){
//                    current.left = new Node<>(value);
//                    size++;
//                    return;
//                }
//                current=current.left;
//            }else if(cmp>0){
//                if (current.right ==null){
//                    current.right = new Node<>(value);
//                    size++;
//                    return;
//                }
//                current = current.right;
//            }else {
//                return;
//            }
//        }
    }

    private Node<T> insertRec(Node<T> node,T value){
        if (node == null){
            size++;
            return new Node<>(value);
        }
        int cmp = value.compareTo(node.value);
        if(cmp<0){
            node.left = insertRec(node.left,value);
        }else if (cmp>0){
            node.right = insertRec(node.right,value);
        }
        return node;
    }

    @Override
    public boolean search(T value) {
        if (value==null){
            throw new NullPointerException("Value cannot be null");
        }
        return searchRec(root,value);
//        Node<T> current = root;
//        while (current !=null){
//            int cmp = value.compareTo(current.value);
//            if (cmp<0){
//                current=current.left;
//            } else if(cmp>0){
//                current=current.right;
//            }else {
//                return true;
//            }
//        }
//        return false;
    }

    private boolean searchRec(Node<T> node, T value){
        if (node == null){
            return false;
        }
        int cmp = value.compareTo(node.value);
        if(cmp<0){
            return searchRec(node.left,value);
        }else if(cmp>0){
            return searchRec(node.right,value);
        }else {
            return true;
        }
    }

    @Override
    public void delete(T value) {
        if (value == null){
            throw new NullPointerException("Value cannot be null");
        }
        root = deleteRec(root, value);
    }

    private Node<T> deleteRec(Node<T> node,T value){
        if (node==null){
            return null;
        }
        int cmp = value.compareTo(node.value);
        if(cmp<0){
            node.left = deleteRec(node.left, value);
        }else if(cmp>0){
            node.right = deleteRec(node.right,value);
        }else {
            if (node.right ==null && node.left==null){
                size--;
                return null;
            }
            if (node.left==null){
                size--;
                return node.right;
            }
            if (node.right==null){
                size--;
                return node.left;
            }
            Node<T> successor = findMin(node.right);
            node.value = successor.value;
            node.right = deleteRec(node.right,successor.value);
        }
        return node;
    }

    private Node<T> findMin(Node<T> node){
        while (node.left!=null){
            node= node.left;
        }
        return node;
    }


    @Override
    public int size() {
        return size;
    }

    @Override
    public int height() {
        return heightRec(root);
    }

    private int heightRec(Node<T> node){
        if (node==null) {
            return -1;
        }
        int leftHeight = heightRec(node.left);
        int rightHeight = heightRec(node.right);

        return Math.max(leftHeight,rightHeight) + 1;
    }


    @Override
    public List<T> inorderTraversal() {
        List<T> lst = new ArrayList<>();
        inorder(root,lst);
        return lst;
    }
    private void inorder(Node<T> node, List<T> lst){
        if (node==null){
            return ;
        }
        inorder(node.left,lst);
        lst.add(node.value);
        inorder(node.right,lst);
    }

}
