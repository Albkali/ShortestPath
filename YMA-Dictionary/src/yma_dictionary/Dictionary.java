package yma_dictionary;

public class Dictionary {
       public static BinaryTree t1;
       
    public static void main(String[] args) {    
        
        FileReadInputs file = new FileReadInputs();            
        t1 = file.readInputs();
        Interface inter = new Interface(t1);
 
    }
}
