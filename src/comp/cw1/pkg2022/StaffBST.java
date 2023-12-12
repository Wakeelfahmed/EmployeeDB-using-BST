package comp.cw1.pkg2022;
import java.io.FileWriter;
import java.io.IOException;
public class StaffBST implements IStaffDB {

    private class Node {
        Employee data;
        Node left, right;

        private Node(Employee m) {
            data = m;
            left = null;
            right = null;
        }
    }

    private Node root;
    private int numEntries;

    private static FileWriter logFile;

    static {
        try {
            logFile = new FileWriter("log.txt", false); // Use "true" to append to the existing log file
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public StaffBST() {
        System.out.println("Binary Search Tree");
        clearDB();
    }

    @Override
    public void clearDB() {
        root = null;
        numEntries = 0;
    }

    @Override
    public boolean containsName(String name) { return get(name) != null; }

    private Employee retrieve(Node tree, String name) {
        if (tree == null) {
            return null;
        }

        int compareResult = name.compareTo(tree.data.getName());

        if (compareResult < 0) {
            return retrieve(tree.left, name);
        } else if (compareResult > 0) {
            return retrieve(tree.right, name);
        } else {
            return tree.data;
        }
    }

    @Override
    public Employee get(String name) { return retrieve(root, name); }

    @Override
    public int size() { return numEntries; }

    @Override
    public boolean isEmpty() { return size() == 0; }

    private Node insert(Node tree, Employee employee) {
        if (tree == null) {
            return new Node(employee);
        }

        int compareResult = employee.getName().compareTo(tree.data.getName());

        if (compareResult < 0) {
            tree.left = insert(tree.left, employee);
        } else if (compareResult > 0) {
            tree.right = insert(tree.right, employee);
        } else {
            // Key already exists, update the value or handle accordingly
            tree.data = employee;
        }

        return tree;
    }

    @Override
    public Employee put(Employee member) {
        Employee employee_test = get(member.getName());
        root = insert(root, member);
        if(employee_test != null){
            logEmployeeInfo("Updation", member);
            return employee_test;
        }
        else{
            logEmployeeInfo("Insertion", member);
            numEntries++;
            return null;
        }
    }

    @Override
    public  Employee remove(String name){

        Node parent = null, del, p = null, q = null;
        Employee result;
        del = root;
        while (del != null && !del.data.getName().equals(name)) {
            parent = del;
            if (name.compareTo(del.data.getName()) < 0)
                del = del.left;
            else
                del = del.right;
        }// del == null || del.data.getName().equals(name))
        if(del != null) { // del.data.getName().equals(name)
            // find the pointer p to the node to replace del
            if (del.right == null) p = del.left;
            else if (del.right.left == null) {
                p = del.right; p.left = del.left;
            } else {
                p = del.right;
                while (p.left != null) {q = p; p = p.left;}
                q.left = p.right; p.left = del.left; p.right = del.right;
            }
            if(del == root) root = p;
            else if (del.data.getName().compareTo(parent.data.getName()) < 0)
                parent.left = p;
            else parent.right = p;
            numEntries--;
            result = del.data;
            logEmployeeInfo("Deletion", result);
        }
        else result = null;
        return result;
    } // delete

    private void traverse(Node tree) {
        if (tree != null) {
            traverse(tree.left);
            System.out.println(tree.data.toString()); // or log to file
            traverse(tree.right);
        }
    }

    @Override
    public void displayDB() {
        traverse(root);
    }
        public void logEmployeeInfo(String operation, Employee employee) {
        try {
            logFile.write("Operation: " + operation + "\n");
            logFile.write("Employee Name: " + employee.getName() + "\n");
            logFile.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



//package comp.cw1.pkg2022;
//
//import java.io.FileWriter;
//import java.io.IOException;
//
//public class StaffBST implements IStaffDB {
//    private class Node {
//        Employee data;
//        Node left, right;
//        private Node(Employee m) { data = m; left = null; right = null; }
//    }
//    private Node root;
//    private int numEntries;
//
//    private static FileWriter logFile;
//
//    static {
//        try {
//            logFile = new FileWriter("log.txt", false); // Use "true" to append to the existing log file
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public StaffBST() {
//        System.out.println("Binary Search Tree");
//        clearDB();
//    }
//
//    @Override
//    public void clearDB() {
//        root = null; // garbage collector will tidy up
//        numEntries = 0;
//    }
//    @Override
//    public boolean containsName(String name) {
//        return containsName(root, name);
//    }
//
//    private boolean containsName(Node node, String name) {
//        if (node == null)
//            return false;
//
//
//        int cmp = name.compareTo(node.data.getName());
//        if (cmp < 0)
//            return containsName(node.left, name);
//        else if (cmp > 0)
//            return containsName(node.right, name);
//        else
//            return true; // Employee found
//
//    }
//
//    @Override
//    public Employee get(String name) {
//        return get(root, name);
//    }
//
//    private Employee get(Node node, String name) {
//        if (node == null)
//            return null;
//
//
//        int cmp = name.compareTo(node.data.getName());
//        if (cmp < 0)
//            return get(node.left, name);
//        else if (cmp > 0)
//            return get(node.right, name);
//        else
//            return node.data; // Employee found
//    }
//
//    @Override
//    public int size() {return numEntries;}
//
//    @Override
//    public boolean isEmpty() { return size() == 0; }
//
//    @Override
//    public Employee put(Employee employee) {
//        Employee employee_test = get(employee.getName());
//        root = insert(root, employee);
//        if(employee_test != null){
//            logEmployeeInfo("Updation", employee);
//            return employee_test;
//        }
//        else{
//            logEmployeeInfo("Insertion", employee);
//            return null;
//        }
//    }
//
//    private Node insert(Node node, Employee employee) {
//        if (node == null)
//            return null;
//        if(root == null) {
//            Node newnode = new Node(employee);
//            root = newnode;
//            return root;
//        }
//        int cmp = employee.getName().compareTo(node.data.getName());
//        if (cmp < 0)
//            node.left = insert(node.left, employee);
//        else if (cmp > 0)
//            node.right = insert(node.right, employee);
//        else {
//            // Duplicate employee name, update the affiliation
//            node.data = employee;
//            return node;
//        }
//        return node;
//    }
//
////    @Override
////    public Employee remove(String name) {
////        // Initialize root and parent pointers to null
////        Node current = root;
////        Node parent = null;
////
////
////        // Search for the node to be removed
////        while (current != null) {
////            int cmp = name.compareTo(current.employee.getName());
////
////            if (cmp < 0) {
////                parent = current;
////                current = current.left;
////            } else if (cmp > 0) {
////                parent = current;
////                current = current.right;
////            } else {
////                // Node with the given name found, remove it
////                Employee removedEmployee = current.employee;
////                removeNode(parent, current);
////                logEmployeeInfo("deletion", removedEmployee);
////                return removedEmployee;
////            }
////        }
////
////        // Node with the given name not found
////        return null;
////    }
//public  Employee remove(String name){
//
//    Node parent = null, del, p = null, q = null;
//    Employee result;
//    del = root;
//    while (del != null && !del.data.getName().equals(name)) {
//        parent = del;
//        if (name.compareTo(del.data.getName()) < 0)
//            del = del.left;
//        else
//            del = del.right;
//    }// del == null || del.data.getName().equals(name))
//    if(del != null) { // del.data.getName().equals(name)
//        // find the pointer p to the node to replace del
//        if (del.right == null) p = del.left;
//        else if (del.right.left == null) {
//            p = del.right; p.left = del.left;
//        } else {
//            p = del.right;
//            while (p.left != null) {q = p; p = p.left;}
//            q.left = p.right; p.left = del.left; p.right = del.right;
//        }
//        if(del == root) root = p;
//        else if (del.data.getName().compareTo(parent.data.getName()) < 0)
//            parent.left = p;
//        else parent.right = p;
//        numEntries--;
//        result = del.data;
//    }
//    else result = null;
//    return result;
//} // delete
////    private void removeNode(Node parent, Node nodeToRemove) {
////        if (nodeToRemove.left == null && nodeToRemove.right == null) {
////            // Case 1: Node to be removed is a leaf
////            if (parent == null)
////                root = null; // Node to be removed is the root
////            else if (parent.left == nodeToRemove)
////                parent.left = null;
////            else
////                parent.right = null;
////
////        } else if (nodeToRemove.left == null || nodeToRemove.right == null) {
////            // Case 2: Node to be removed has one child
////            Node child = (nodeToRemove.left != null) ? nodeToRemove.left : nodeToRemove.right;
////
////            if (parent == null)
////                // Node to be removed is the root
////                root = child;
////            else if (parent.left == nodeToRemove)
////                parent.left = child;
////            else
////                parent.right = child;
////
////        } else {
////            // Case 3: Node to be removed has two children
////            Node successor = findSuccessor(nodeToRemove);
////            nodeToRemove.employee = successor.employee;
////            removeNode(nodeToRemove, successor);
////        }
////    }
//
//    private Node findSuccessor(Node node) {
//        Node successorParent = node;
//        Node successor = node.right;
//
//        while (successor.left != null) {
//            successorParent = successor;
//            successor = successor.left;
//        }
//
//        if (successorParent != node)
//            successorParent.left = successor.right;
//        else
//            node.right = successor.right;
//
//        return successor;
//    }
//
//
//    @Override
//    public void displayDB() {
//        displayDB(root);
//    }
//
//    private void displayDB(Node node) {
//        if (node != null) {
//            displayDB(node.left);
//            System.out.println(node.data.toString());
//            displayDB(node.right);
//        }
//    }
//
//    public void logEmployeeInfo(String operation, Employee employee) {
//        try {
//            logFile.write("Operation: " + operation + "\n");
//            logFile.write("Employee Name: " + employee.getName() + "\n");
//            logFile.flush();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}
//
