import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;
public class MorseTree {
    private TreeNode root;

    public MorseTree() {
        root = new TreeNode("?",null,null);
        Scanner fin = null;
        try {
            fin = new Scanner(new FileInputStream("data.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while(fin.hasNext()){
            //for each line in the file, 
            //  get the letter(char) and the Morse string
            char letter = fin.next().charAt(0);
            String morseStr = fin.next();
            //  call add() with this data
            add(morseStr,letter);
            //  print out the letter and Morse string here for debugging
            //System.out.println(letter+" "+morseStr);
        }
    }

    public void add(String morseStr, char letter) {
        root = insertInSubtree(morseStr, letter, root);
        //System.out.println("Added "+root.data);
    }

    
    private TreeNode<Character> insertInSubtree(String morseStr, char letter, TreeNode subtree) {
        if(morseStr.length() == 0){
            subtree.setData(letter);
        }
        else{
            if(morseStr.charAt(0) == '.'){
                if(subtree.right == null)subtree.setRight(new TreeNode());
                insertInSubtree(morseStr.substring(1),letter,subtree.right);
            }
            else{
                if(subtree.left == null)subtree.setLeft(new TreeNode());
                insertInSubtree(morseStr.substring(1),letter,subtree.left);
            }
        }
        return subtree;
    }

    public Character translate(String morseStr) {
        return findInSubtree(morseStr, root);
    }

    
    private Character findInSubtree(String morseStr, TreeNode subtree) {
        if(morseStr.length() == 0){
            return (Character)subtree.data;
        }  
        else{
            if(morseStr.charAt(0) == '.'){
                if(subtree.right == null)subtree.setRight(new TreeNode());
                return findInSubtree(morseStr.substring(1),subtree.right);
            }
            else{
               if(subtree.left == null)subtree.setLeft(new TreeNode());
               return findInSubtree(morseStr.substring(1),subtree.left);
            }
        }
    }

    
    public String translateString(String tokens) {
        String retVal = "";
        //build a scanner here using tokens as input
        //iterate over the tokens calling translate on each token (substring separated by a space)
        //concat these characters and return them
        Scanner sc = new Scanner(tokens);
        while(sc.hasNext()){
            retVal += translate(sc.next()); 
        }
        sc.close();
        return retVal;
    }

    public String toMorseCode(Character c) {
        String retValue = null;
        Scanner sc = null;
        try {
            sc = new Scanner(new FileInputStream("data.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while(sc.hasNext()){
            char letter = sc.next().charAt(0);
            String morseStr = sc.next();
            if(letter == c){
                retValue = morseStr;
                break;
            }
        }
        return retValue;
    }

    public String toString() {
        return inorderWalk(root);
    }

    private String inorderWalk(TreeNode subtreeRoot) {  
        String retVal = "";
        if(subtreeRoot != null) {
            retVal += inorderWalk(subtreeRoot.left);
            retVal += " " + subtreeRoot.data + " ";
            retVal += inorderWalk(subtreeRoot.right);
        }
        return retVal;
    }  

  
    // Inner class to create the linked structure
    private class TreeNode<Character> {

        Object data;     // holds a given node’s data
        TreeNode right;
        TreeNode left;

        public TreeNode() {
            this.data = null;
            this.right = null;
            this.left = null;
        }

        public TreeNode(Object data,TreeNode right,TreeNode left) {
            this.data = data;
            this.right = right;
            this.left = left;
        }

        public void setRight(TreeNode rightNode) {
            this.right = rightNode;
        }

        public void setLeft(TreeNode leftNode) {
            this.left = leftNode;
        }

        public void setData(Object data) {
            this.data = data;
        }

        public String toString(){
            return data+"";
        }
    }
}
