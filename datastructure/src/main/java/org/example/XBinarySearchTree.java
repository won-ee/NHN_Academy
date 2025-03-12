package org.example;

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
            throw new NullPointerException();
        }
        if (root==null){
            root = new Node<>(value);
            size++;
            return;
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
            throw new NullPointerException();
        }
        if (root==null){
            throw new NoSuchElementException();
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
        }else if(cmp<0){
            return searchRec(node.right,value);
        }else {
            return true;
        }
    }

    @Override
    public void delete(T value) {
        if (root == null){
            throw new NoSuchElementException();
        }
        if (value == null){
            throw new NullPointerException();
        }
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public int height() {
        return 0;
    }

    @Override
    public List<T> inorderTraversal() {
        return List.of();
    }
}
