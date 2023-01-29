/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Trees;

/**
 *
 * @author leoli
 */
public class Tree {

    private TreeNode root;
    public int searchCount=0;
    public Tree() {

    }
    public int findMax() {
    return findMax(root);
  }

  private int findMax(TreeNode node) {
    if (node == null) {
      return Integer.MIN_VALUE;
    }
    int max = (Integer)node.data;
    max = Math.max(max, findMax(node.left));
    max = Math.max(max, findMax(node.right));
    return max;
  }
    
    

    public void add(Object o, TreeNode temp) {
        Comparable c = (Comparable) o;
        if (root == null) {
            root = new TreeNode(o);
        } else if (c.compareTo(temp.getData()) < 0) {
            //am i smaller then temp? if i am we shift our interest down the tree
            if (temp.left == null) {
                //check if anything left if not we make it that
                temp.left = new TreeNode(o);
            } else {
                add(o, temp.left);//pass along the tree(recursively)
            }
        } else if (c.compareTo(temp.getData()) > 0) {
            //am i bigger then temp? if i am we shift our interest down the tree
            if (temp.right == null) {
                //check if there is anything rght, if there isnt we stay there
                temp.right = new TreeNode(o);
            } else {
                add(o, temp.right);//pass along the tree(recursively)
            }
        } else if ((c.compareTo(temp.getData()) >= 0)) {
            //making the decision anything equal will also g right
            if (temp.right == null) {
                //check if there is anything rght, if there isnt we stay there
                temp.right = new TreeNode(o);
            } else {
                add(o, temp.right);// if something right move down and try again(Recursively
            }
        }

    }

    //helper method
    public void add(Object o) {
        add(o, root);
    }

    public void printTree(TreeNode temp) {
        if (temp.left != null) {
            printTree(temp.left);
        }
        System.out.println(temp.getData());
        if (temp.right != null) {
            printTree(temp.right);
        }
    }
    
    public void printTree(){
        printTree(root);
    }
    
    
    
    public Object searchTree(TreeNode temp, Object o){
        searchCount++;
        Comparable c=(Comparable)o;
        if(c.compareTo(temp.getData())==0){
            return temp.getData();
        }
        
        else if (c.compareTo(temp.getData())<0 && temp.left!=null){
            //is the thing ere looking at less than and is there tree left to compare
            return searchTree(temp.left, o);
        }
        else if (c.compareTo(temp.getData())>0 && temp.right!=null){
            //is the thing ere looking at less than and is there tree right to compare
            return searchTree(temp.right, o);
        }
        else{
            return null;
        }
        
    }
    
    public Object searchTree(Object o){      
        return searchTree(root, o);
    }
    
}

