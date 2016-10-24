/**
 Name of the Student: Sai Gatram
 Class:3345.
 Section:003
 Semester: Fall 2016
 Project : Red black tree
 Description: This is the REdBlackTree class. It has all the fucntions needed.
 */

import java.util.Scanner;

/**
 * Created by Sai Gatram on 10/23/2016.
 */
class RedBlackTree {

       private class Node {
        int element;
        Node left=      baseNode;
        Node right=     baseNode;
        Node parent=    baseNode;
        boolean isRed=  false;

        //this is the only constructor that will be used
        Node(int element) {
            this.element = element;
        }
    }

    Node baseNode = new Node(-1);
    Node root = baseNode; // making the root Node for the tree which is default black

    public void printTree(Node node) {
        if (node == baseNode) {
            return;
        }
        printTree(node.left);
        System.out.print(((node.isRed==true)?"*":" ")+node.element+" ");
        printTree(node.right);
    }
    /*
    If the new node is red, and it's inserted as the child of
    a black node then no violations occur at all.
    if the new node is black, a black violation always occurs
     */
    private void insert(Node node) {
        Node temp = root;
        if (root == baseNode) {
            root = node;
            node.isRed = false;
            node.parent = baseNode;
        } else {
            node.isRed = true;
            while (true) {
                if (node.element < temp.element) {
                    if (temp.left == baseNode) {
                        temp.left = node;
                        node.parent = temp;
                        break;
                    } else {
                        temp = temp.left;
                    }
                } else if (node.element >= temp.element) {
                    if (temp.right == baseNode) {
                        temp.right = node;
                        node.parent = temp;
                        break;
                    } else {
                        temp = temp.right;
                    }
                }
            }
            fixTree(node);
        }
    }
    private void fixTree(Node node) {
        while (node.parent.isRed == true) {
            Node sibling;
            if (node.parent == node.parent.parent.left) {
                sibling = node.parent.parent.right;

                if (sibling != baseNode && sibling.isRed) {
                    node.parent.isRed = false;
                    sibling.isRed = false;
                    node.parent.parent.isRed = true;
                    node = node.parent.parent;
                    continue;
                }
                if (node == node.parent.right) {
                    //Double rotation
                    node = node.parent;
                    rotateLeft(node);
                }
                node.parent.isRed = false;
                node.parent.parent.isRed = true;
                //is a case where we only need a single rotation
                rotateRight(node.parent.parent);
            } else {
                sibling = node.parent.parent.left;
                if (sibling != baseNode && sibling.isRed) {
                    node.parent.isRed = false;
                    sibling.isRed = false;
                    node.parent.parent.isRed = true;
                    node = node.parent.parent;
                    continue;
                }
                if (node == node.parent.left) {
                    //Double rotation
                    node = node.parent;
                    rotateRight(node);
                }
                node.parent.isRed = false;
                node.parent.parent.isRed = true;

                // case where we only a single rotation
                rotateLeft(node.parent.parent);
            }
        }
        root.isRed = false;
    }

    void rotateLeft(Node node)
    {
        if (node.parent != baseNode) {
            if (node == node.parent.left)
                node.parent.left = node.right;
             else
                node.parent.right = node.right;

            node.right.parent = node.parent;
            node.parent = node.right;
            if (node.right.left != baseNode)
                node.right.left.parent = node;

            node.right = node.right.left;
            node.parent.left = node;
        }
        else
        {//Need to rotate root
            Node right = root.right;
            root.right = right.left;
            right.left.parent = root;
            root.parent = right;
            right.left = root;
            right.parent = baseNode;
            root = right;
        }
    }

    void rotateRight(Node node)
    {
        if (node.parent != baseNode) {
            if (node == node.parent.left)
                node.parent.left = node.left;
             else
                node.parent.right = node.left;

            node.left.parent = node.parent;
            node.parent = node.left;
            if (node.left.right != baseNode)
                node.left.right.parent = node;

            node.left = node.left.right;
            node.parent.right = node;
        }
        else
        {   // This is when the root will be rotated
            Node left = root.left;
            root.left = root.left.right;
            left.right.parent = root;
            root.parent = left;
            left.right = root;
            left.parent = baseNode;
            root = left;
        }
    }
    public void loop ()
    {
        int choice;
        RedBlackTree Tree = new RedBlackTree();
        //this is menu system. a do while with a switch case inside of it
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("Binary Search Tree:");
            System.out.println("  1. Insert");
            System.out.println("  2. Contains");
            System.out.println("  3. Print Tree");
            System.out.println("  4. Exit Program");
            System.out.println("     Your Choice:");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Enter Element: ");
                    int chosen = scanner.nextInt();
                    scanner.nextLine();
                    Node node = new Node(chosen);
                    insert(node);
                    System.out.println("Element insertion complete");
                    break;
                case 2:
                    System.out.println("Enter Element: ");
                    int contain = scanner.nextInt();
                    scanner.nextLine();
                    contains(contain,root);
                    break;
                case 3:
                    printTree(root);
                    break;
                case 4:
                    System.out.println("Done.");
                    return;
            }
        } while( choice <5);
    }
    public void contains (int check, Node node)
    {
        if (check < node.element && (node.left != null))
             contains(check, node.left);
        else if (check > node.element&& (node.right != null))
             contains(check, node.right);
        else if (check == node.element)
            System.out.println("The element exists in the tree!");
        else
            System.out.println("Element does not exist.");
    }

}
