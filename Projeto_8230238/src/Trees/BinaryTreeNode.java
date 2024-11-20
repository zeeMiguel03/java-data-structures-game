/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Trees;

/**
 * @author Miguel Rocha
 */

public class BinaryTreeNode<T> {
    protected T element;
    protected BinaryTreeNode<T> left, right;
    
    /**
     * Creates a new tree node with the specified data.
     *
     * @param obj the element that will become a part of
     * the new tree node
     */
    public BinaryTreeNode(T obj) {
        element = obj;
        left = null;
        right = null;
    }
    
    /**
     * Returns the number of non-null children of this node.
     * This method may be able to be written more efficiently.
     *
     * @return the integer number of non-null children of this node
     */
    public int numChildren() {
        int children = 0;
        
        if (left != null) {
            children = 1 + left.numChildren();
        }
        
        if (right != null) {
            children = children + 1 + right.numChildren();
        }
        
        return children;
    }

    /**
     * Sets the element of the node.
     * @param element the element to add
     */
    public void setElement(T element) {
        this.element = element;
    }

    /**
     * Sets the left child of the node.
     * @param left the child to add
     */
    public void setLeft(BinaryTreeNode<T> left) {
        this.left = left;
    }

    /**
     * Sets the right child of the node.
     * @param right the child to add
     */
    public void setRight(BinaryTreeNode<T> right) {
        this.right = right;
    }

    /**
     * Gets the element of the node.
     * @return the element
     */
    public T getElement() {
        return element;
    }

    /**
     * Gets the left child of the node.
     * @return the left child
     */
    public BinaryTreeNode<T> getLeft() {
        return left;
    }

    /**
     * Gets the right child of the node.
     * @return the right child
     */
    public BinaryTreeNode<T> getRight() {
        return right;
    }
}
