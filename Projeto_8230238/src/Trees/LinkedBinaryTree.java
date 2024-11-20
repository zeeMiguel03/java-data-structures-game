/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Trees;

import Exceptions.ElementNotFoundException;
import Exceptions.EmptyCollectionException;
import Lists.LinkedUnorderedList;
import Lists.UnorderedListADT;
import Queues.LinkedQueue;
import Queues.QueueADT;
import java.util.Iterator;

/**
 * @author Miguel Rocha
 */

public class LinkedBinaryTree<T> implements BinaryTreeADT<T> {
    protected BinaryTreeNode<T> root;
    protected int count;
    
    /**
     * Creates a new binary tree with a specific root
     * 
     * @param element the element to add in root
     */
    public LinkedBinaryTree(T element) {
        root = new BinaryTreeNode<>(element);
        count = 1;
    }
    
    /**
     * Creates a empty binary tree
     */
    public LinkedBinaryTree() {
        root = null;
        count = 0;
    }
    
    /**
     * Returns the element of the root.
     * 
     * @return root element
     * @throws EmptyCollectionException if the tree is empty
     */
    @Override
    public T getRoot() throws EmptyCollectionException {
        if (count == 0) {
            throw new EmptyCollectionException("Empty collection!");
        }
        
        return root.getElement();
    }

    /**
     * Verify if the tree is empty.
     * 
     * @return true if it is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    /**
     * Checks the size of the tree
     * 
     * @return the size of the tree 
     */
    @Override
    public int size() {
        return count;
    }

    /**
     * Verify if the tree contains a specific element.
     * 
     * @param targetElement the element to search
     * @return true if contains the element, false otherwise
     * @throws EmptyCollectionException if the collection is empty
     */
    @Override
    public boolean contains(T targetElement) throws EmptyCollectionException {
        if (count == 0) {
            throw new EmptyCollectionException("Empty collection!");
        }
        
        try {
            find(targetElement);
            return true;
        } catch (ElementNotFoundException e) {
            return false;
        }
    }
    
    /**
     * Returns a reference to the specified target element if it is
     * found in this binary tree. Throws a NoSuchElementException if
     * the specified target element is not found in the binary tree.
     *
     * @param targetElement the element being sought in this tree
     * @return a reference to the specified target
     * @throws ElementNotFoundException if an element not found
     * exception occurs
     */
    @Override
    public T find(T targetElement) throws ElementNotFoundException {
        BinaryTreeNode<T> current = findAgain(targetElement, root);

        if (current == null ) {
            throw new ElementNotFoundException("Element not found!");
        }

        return current.element;
    }
    
    /**
     * Returns a reference to the specified target element if it is
     * found in this binary tree.
     *
     * @param targetElement the element being sought in this tree
     * @param next the element to begin searching from
     */
    private BinaryTreeNode<T> findAgain(T targetElement, BinaryTreeNode<T> next) {
        if (next == null) {
            return null;
        }

        if (next.element.equals(targetElement)) {
            return next;
        }

        BinaryTreeNode<T> temp = findAgain(targetElement, next.left);

        if (temp == null) {
            temp = findAgain(targetElement, next.right);
        }

        return temp;
    }

    /**
     * Performs an inorder traversal on this binary tree by calling an
     * overloaded, recursive inorder method that starts with
     * the root.
     *
     * @return an in order iterator over this binary tree
     */
    @Override
    public Iterator<T> iteratorInOrder() {
        UnorderedListADT<T> tempList = new LinkedUnorderedList<>();
        
        inorder(root, tempList);
        
        return tempList.iterator();
    }
    
    /**
     * Performs a recursive inorder traversal.
     *
     * @param node the node to be used as the root
     * for this traversal
     * @param tempList the temporary list for use in this traversal
     */
    private void inorder (BinaryTreeNode<T> node, UnorderedListADT<T> tempList) {
        if (node != null) {
            inorder(node.left, tempList);
            tempList.addToRear(node.element);
            inorder(node.right, tempList);
        }
    }

    /**
     * Performs a preorder traversal on this binary tree by calling an
     * overloaded, recursive preorder method that starts
     * with the root.
     *
     * @return an iterator over the elements of this binary tree
     */
    @Override
    public Iterator<T> iteratorPreOrder() {
        UnorderedListADT<T> tempList = new LinkedUnorderedList<>();
        
        preOrder(root, tempList);
        
        return tempList.iterator();
    }
    
    /**
     * Performs a recursive preOrder traversal.
     *
     * @param node the node to be used as the root
     * for this traversal
     * @param tempList the temporary list for use in this traversal
     */
    private void preOrder(BinaryTreeNode<T> node, UnorderedListADT<T> tempList) {
        if (node != null) {
            tempList.addToRear(node.getElement());
            preOrder(node.getLeft(), tempList);
            preOrder(node.getRight(), tempList);
        }
    }

    /**
     * Performs a postorder traversal on this binary tree by
     * calling an overloaded, recursive postorder
     * method that starts with the root.
     *
     * @return an iterator over the elements of this binary tree
     */
    @Override
    public Iterator<T> iteratorPostOrder() {
        UnorderedListADT<T> tempList = new LinkedUnorderedList<>();
        
        postOrder(root, tempList);
        
        return tempList.iterator();
    }
    
    /**
     * Performs a recursive postOrder traversal.
     *
     * @param node the node to be used as the root
     * for this traversal
     * @param tempList the temporary list for use in this traversal
     */
    private void postOrder(BinaryTreeNode<T> node, UnorderedListADT<T> tempList) {
        if (node != null) {
            postOrder(node.getLeft(), tempList);
            postOrder(node.getRight(), tempList);
            tempList.addToRear(node.getElement());
        }
    }

    /**
     * Performs a levelorder traversal on the binary tree,
     * using a queue.
     *
     * @return an iterator over the elements of this binary tree
     */
    @Override
    public Iterator<T> iteratorLevelOrder() {
       QueueADT<BinaryTreeNode<T>> tempQueue = new LinkedQueue<>();
       UnorderedListADT<T> tempList = new LinkedUnorderedList<>();
       
       if (count != 0) {
           tempQueue.enqueue(root);
           
            while (!tempQueue.isEmpty()) {
                BinaryTreeNode<T> next = tempQueue.dequeue();

                if (next.getLeft() != null) {
                    tempQueue.enqueue(next.getLeft());
                }

                if (next.getRight() != null) {
                    tempQueue.enqueue(next.getRight());
                }

                tempList.addToRear(next.getElement());
           }
       }
       
       return tempList.iterator();
    } 
}
