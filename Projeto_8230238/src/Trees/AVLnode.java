/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Trees;

/**
 *
 * @author Miguel
 */
public class AVLnode<T> {
    private AVLnode<T> left, right;
    private T element;
    private int height;
    
    public AVLnode(T Element) {
        left = null;
        right = null;
        element = Element;
    }

    public AVLnode<T> getLeft() {
        return left;
    }

    public AVLnode<T> getRight() {
        return right;
    }

    public T getElement() {
        return element;
    }

    public int getHeight() {
        return height;
    }

    public void setLeft(AVLnode<T> left) {
        this.left = left;
    }

    public void setRight(AVLnode<T> right) {
        this.right = right;
    }

    public void setElement(T element) {
        this.element = element;
    }

    public void setHeight(int height) {
        this.height = height;
    }
    
    public void updateHeight() {
        int leftHeight = (left == null) ? 0 : left.getHeight();
        int rightHeight = (right == null) ? 0 : right.getHeight();
        height = Math.max(leftHeight, rightHeight) + 1;
    }
    
    public int getBalance() {
        int leftHeight = (left == null) ? 0 : left.getHeight();
        int rightHeight = (right == null) ? 0 : right.getHeight();
        return leftHeight - rightHeight;
    }
}
