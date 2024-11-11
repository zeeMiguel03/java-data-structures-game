/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Trees;

/**
 *
 * @author Miguel
 */
public class AVLTree<T> {
    private AVLnode<T> root;
    private int count;
    
    public AVLTree() {
        root = null;
        count = 0;
    }
    
    public AVLTree(T element) {
        root = new AVLnode<>(element);
        count = 1;
    }
    
    public void addElement(T element) {
        AVLnode<T> newNode = new AVLnode<>(element);
        Comparable<T> comparableElement = (Comparable<T>)element;
        
        if (count == 0) {
            root = newNode;
        } else {
            AVLnode<T> current = root;
            boolean added = false;
            
            while (!added) {
                if (comparableElement.compareTo(current.getElement()) < 0) {
                    if (current.getLeft() == null) {
                        current.setLeft(newNode);
                        added = true;
                    } else {
                        current = current.getLeft();
                    }
                } else {
                    if (current.getRight() == null) {
                        current.setRight(newNode);
                        added = true;
                    } else {
                        current = current.getRight();
                    }
                }
            }
        }
        
        newNode.updateHeight();
        balance(newNode);
        count++;
    }
    
    public void balance(AVLnode<T> node) {
        if (node.getHeight() > 1) {
            
        } else if (node.getHeight() < -1) {
            
        }
    }
}
