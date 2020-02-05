package yma_dictionary;

import java.util.Stack;

class Node {

    String keyWord;
    String meaningValue;
    Node left;
    Node right;

    public Node(String data, String meaning) {
        this.keyWord = data.toUpperCase();
        this.meaningValue = meaning;
        left = null;
        right = null;
    }

    public static String printKeyWord(Node data) {
        //System.out.print(data.keyWord);
        return data.keyWord;
    }

    public static String printMeaningValue(Node data) {
        //System.out.print(data.meaningValue);
        return data.meaningValue;
    }
}

public class BinaryTree {
    //Declare a private attribute for ROOT

    static Node root;

    //BinarySearch Tree Constructor
    public BinaryTree() {
        this.root = null;
    }

    //Find if word is already exits in the binary search tree
    public boolean isExistsWord(String data) {
        String upperCase_Data = data.toUpperCase(); //convert to UPPERCASE input word
        Node current = root;
        while (current != null) {
            if (current.keyWord.equals(upperCase_Data)) {
                return true;
            } else if (Node.printKeyWord(current).compareTo(upperCase_Data) > 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        return false;
    }

    //Add data to binary search tree
    public void addNode(String data, String meaning) {

        if (isExistsWord(data) == false) {
            Node dataNode = new Node(data, meaning);
        
            if (root == null) {
                root = dataNode;
                //System.out.println("New Entry is added to Root");
                return;
            }

            Node current = root;
            Node parent = null;

            while (true) {
                parent = current;
                String upperCase_Data = data.toUpperCase(); //convert to UPPERCASE input word
                if (Node.printKeyWord(current).compareTo(upperCase_Data) > 0) {
                    current = current.left;
                    if (current == null) {
                        parent.left = dataNode;
                        //System.out.println("New Entry is added to left child of "+Node.printKeyWord(parent));
                        return;
                        
                    }
                } else {
                    current = current.right;
                    if (current == null) {
                        parent.right = dataNode;
                        //System.out.println("New Entry is added to right child of "+Node.printKeyWord(parent));
                        return;
                    }
                }              
            }
        } else {
            System.out.println(data+" Added New Word is Already exists");
        }
    }
    
    //Add single nodes to the BST
    public String addNewNode(String data, String meaning) {

        if (isExistsWord(data) == false) {
            FileReadInputs file1 = new FileReadInputs();
            Node dataNode = new Node(data, meaning);
        
            if (root == null) {
                root = dataNode;
                //System.out.println("New Entry is added to Root");
                String newWord = data+"-"+meaning;
                file1.appendWords(newWord);
                return data+" Added to the dictionary SUCCESSFULLY!";
            }

            Node current = root;
            Node parent = null;

            while (true) {
                parent = current;
                String upperCase_Data = data.toUpperCase(); //convert to UPPERCASE input word
                if (Node.printKeyWord(current).compareTo(upperCase_Data) > 0) {
                    current = current.left;
                    if (current == null) {
                        parent.left = dataNode;
                        String newWord = data+"-"+meaning;
                        file1.appendWords(newWord);
                        //System.out.println("New Entry is added to left child of "+Node.printKeyWord(parent));
                        return data+" Added to the dictionary SUCCESSFULLY!";
                        
                    }
                } else {
                    current = current.right;
                    if (current == null) {
                        parent.right = dataNode;
                        String newWord = data+"-"+meaning;
                        file1.appendWords(newWord);
                        //System.out.println("New Entry is added to right child of "+Node.printKeyWord(parent));
                        return data+" Added to the dictionary SUCCESSFULLY!";
                    }
                }              
            }
        } else {
            return data+" is already in the dictionary!";
        }
    }

    //Search given data and return the mening of it
    public String searchData(String data) {
        String upperCase_Data = data.toUpperCase(); //convert to UPPERCASE input word
        Node current = root;

        while (current != null) {
            if (current.keyWord.equals(upperCase_Data)) {
                return Node.printMeaningValue(current);
            } else if (Node.printKeyWord(current).compareTo(upperCase_Data) > 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        return "You searched word not Found";
    }

    //Delete the entered word
    public String deleteData(String data) {
        FileReadInputs file1 = new FileReadInputs();
        String upperCase_Data = data.toUpperCase(); //convert to UPPERCASE input word
        Node parent = root;
        Node current = root;
        boolean isLeftChild = false; //To find that node is left or right child

        while (!current.keyWord.equals(upperCase_Data)) {
            parent = current;

            if (Node.printKeyWord(current).compareTo(upperCase_Data) > 0) {
                isLeftChild = true;
                current = current.left;
            } else {
                isLeftChild = false;
                current = current.right;
            }

            if (current == null) {
                return "There is no word to Delete";
            }
        }

        //If word to be deleted has no child (left and right both)
        if (current.left == null && current.right == null) {
            if (current == root) {
                root = null;
            } else if (isLeftChild == true) {
                parent.left = null;
            } else {
                parent.right = null;
            }
        }

        //If word to be deleted has one left child
        if (current.right == null) {
            if (current == root) {
                root = current.left;
            } else if (isLeftChild == true) {
                parent.left = current.left;
            } else {
                parent.right = current.left;
            }
        }

        //If word to be deleted has one right child
        if (current.left == null) {
            if (current == root) {
                root = current.right;
            } else if (isLeftChild == true) {
                parent.left = current.right;
            } else {
                parent.right = current.right;
            }
        }

        //If word to be deleted has both left and right child
        if (current.left != null && current.right != null) {
            Node successor = getSuccessor(current);

            if (current == root) {
                root = successor;
            } else if (isLeftChild == true) {
                parent.left = successor;
            } else {
                parent.right = successor;
            }

            successor.left = current.left;
        }
        file1.removeInputs();
        file1.writeInputs();
        return "Word " + upperCase_Data + " Successfully DELETED";
    }

    //Get Successor(Lef most child in right sub tree) to replace with word to be deleted
    public Node getSuccessor(Node deleteNode) {
        Node successor = null;
        Node successor_Parent = null;
        Node current = deleteNode.right;

        while (current != null) {
            successor_Parent = successor;
            successor = current;
            current = current.left;
        }

        /*After this loop we can get Leftmost child in right subTree
		  If successor have a right child add it to left child of successor_Parent
		  and make successor to parent of successor_Parent*/
        if (successor != deleteNode.right) {
            successor_Parent.left = successor.right;
            successor.right = deleteNode.right;
        }

        return successor;
    }

    //Find Similar words of enterd one
    public String findSimilarWord(String data) {
        Stack<Node> nodeStack = new Stack<Node>();
        //BinaryTree temp = new BinaryTree();
        String upperCase_Data = data.toUpperCase(); //convert to UPPERCASE input word
        String meaningData = this.searchData(data);
        String similarWords = "";
        nodeStack.push(root);

        while (nodeStack.empty() == false) {
            Node topNode = nodeStack.pop();
            //System.out.println(Node.printMeaningValue(topNode));

            if (topNode.meaningValue.equals(meaningData) && !(upperCase_Data.equals(topNode.keyWord))) {
                String temp = Node.printKeyWord(topNode);
                similarWords += temp.substring(0, 1).toUpperCase() + temp.substring(1).toLowerCase() + ", ";
            }

            if (topNode.right != null) {
                nodeStack.push(topNode.right);
            }

            if (topNode.left != null) {
                nodeStack.push(topNode.left);
            }
        }

        if (similarWords.length() == 0) {
            return "There is no any similar words";
        } else {
            return similarWords.substring(0, similarWords.length() - 2);
        }
    }

}
