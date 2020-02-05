package yma_dictionary;

import java.awt.*;
import java.io.*;
import java.util.*; 
import javax.swing.*;
import java.awt.event.*;
import javax.swing.BorderFactory;

public class Interface implements ActionListener{
  BinaryTree dictionary;
  
  JFrame frame;
  JTextArea meaningArea,addmeaningArea;
  JTextField searchArea,similarArea,addWordArea,deleteArea,alertArea;
  JLabel heading,miniHeading,lineBreak1,Lable1,Lable2,Lable3,lineBreak2,Lable4,Lable5,lineBreak3,Lable6,Lable7;
  JButton searchButton,addButton,deleteButton,clearData;

  Interface(BinaryTree dictionary){
	this.dictionary = dictionary;
    frame = new JFrame("YMA Dictionary");	

    heading = new JLabel("YMA-Dictionary");
	heading.setFont(new Font("Calibri", Font.PLAIN, 18));
	miniHeading = new JLabel("Applied BinaryTree");
	clearData = new JButton("Clear All"); 
    lineBreak1 = new JLabel("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    
	//Search Word Interface
	Lable1 = new JLabel("Word to Search : ");
    searchArea = new JTextField();
   searchArea.setBorder(BorderFactory.createLineBorder(Color.BLUE));

    searchButton = new JButton("Search");  
	Lable2 = new JLabel("Meaning : ");
	meaningArea = new JTextArea();
	meaningArea.setEditable(false);
    meaningArea.setColumns(1);
    meaningArea.setLineWrap(true);
    meaningArea.setRows(3);	
	meaningArea.setBorder(BorderFactory.createLineBorder(Color.BLUE));
    Lable3 = new JLabel("Similar words: ");
    similarArea = new JTextField();
	similarArea.setEditable(false);
	similarArea.setBackground(new java.awt.Color(255, 255, 255));
	similarArea.setBorder(BorderFactory.createLineBorder(Color.BLUE));
	
	//Add Word Interface
    lineBreak2 = new JLabel("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    Lable4 = new JLabel("Word to Add  : ");
    addWordArea = new JTextField();
    addWordArea.setBorder(BorderFactory.createLineBorder(Color.BLUE));

    addButton = new JButton("Add");  
	Lable5 = new JLabel("Meaning to Add : ");
	addmeaningArea = new JTextArea();
    addmeaningArea.setColumns(1);
    addmeaningArea.setLineWrap(true);
    addmeaningArea.setRows(3);	
	addmeaningArea.setBorder(BorderFactory.createLineBorder(Color.BLUE));
  
	//Delete Word Interface
    lineBreak3 = new JLabel("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    Lable6 = new JLabel("Word to Delete: ");
	Lable7 = new JLabel("Output : ");
    deleteArea = new JTextField();
    deleteArea.setBorder(BorderFactory.createLineBorder(Color.BLUE));

    deleteButton = new JButton("Delete");
	alertArea = new JTextField();
	alertArea.setEditable(false);
	alertArea.setBackground(new java.awt.Color(255, 255, 255));
	alertArea.setBorder(BorderFactory.createLineBorder(Color.BLUE));
   	
    heading.setBounds(10,15,305,20);
	miniHeading.setBounds(10,35,305,20);
	clearData.setBounds(430,15,100,30);
	//Search Words SetBounds
    lineBreak1.setBounds(1,55,700,10);
    Lable1.setBounds(20,90,100,20);
	Lable2.setBounds(20,140,100,20);
    searchArea.setBounds(125,85,260,30);
	meaningArea.setBounds(125,125,410,90);
    searchButton.setBounds(410,85,120,30);
    Lable3.setBounds(20,225,200,20);
    similarArea.setBounds(125,225,410,30);
	
	//Add Words SetBounds
	lineBreak2.setBounds(1,270,700,10);
    Lable4.setBounds(20,310,100,20);
	Lable5.setBounds(20,360,100,20);
    addWordArea.setBounds(125,310,260,30);
	addmeaningArea.setBounds(125,355,410,90);
    addButton.setBounds(410,310,120,30);
	
	//Delete Words SetBounds
    lineBreak3.setBounds(1,460,700,10);
    Lable6.setBounds(20,500,100,20);
	Lable7.setBounds(20,545,410,30);
	deleteArea.setBounds(125,500,260,30);
    deleteButton.setBounds(410,500,120,30);
    alertArea.setBounds(125,545,410,30);
	
	
    frame.setLayout(null);
    frame.setSize(560,630);
    frame.setVisible(true);
    frame.setResizable(false);
	
	//Search Words add to the JFrame
	frame.add(searchArea);
	frame.add(heading);
	frame.add(miniHeading);
	frame.add(clearData);
	frame.add(lineBreak1);
	frame.add(Lable1);
	frame.add(Lable2);
	frame.add(searchButton);
	frame.add(meaningArea);	
	frame.add(Lable3);
	frame.add(similarArea);
	
	//Add Words add to the JFrame
	frame.add(lineBreak2);
	frame.add(Lable4);
	frame.add(Lable5);
	frame.add(addWordArea);
	frame.add(addmeaningArea);
	frame.add(addButton);
	
	//Delete Words add to the JFrame
	frame.add(lineBreak3);
	frame.add(Lable6);
	frame.add(Lable7);
	frame.add(deleteArea);
	frame.add(deleteButton);
	frame.add(alertArea);

    searchButton.addActionListener(this);
    addButton.addActionListener(this);
    deleteButton.addActionListener(this);
	clearData.addActionListener(this);

	frame.setLocationRelativeTo(null);
  }


public void actionPerformed(ActionEvent event){
 
    try{
		
      if(event.getSource()==searchButton){
        
        String word = searchArea.getText();      
        String meaning = dictionary.searchData(word);
        meaningArea.setText(meaning);
        
        String similar = dictionary.findSimilarWord(word);
        similarArea.setText(similar);
      }
	  
      if(event.getSource()==addButton){
        String meaning = addmeaningArea.getText();
        String word = addWordArea.getText();
        if(!word.equals("") && !meaning.equals("")){    
           String reaction = dictionary.addNewNode(word,meaning);
           addmeaningArea.setText(reaction);
        }else if(word.equals("") && !meaning.equals("")){
            addWordArea.setText("Please Enter the word to add");
        }else if(meaning.equals("") && !word.equals("")){
            addmeaningArea.setText("Please Enter the meaning of the word to add");
        }else if(word.equals("") && meaning.equals("")){
            addmeaningArea.setText("Field Empty");
            addWordArea.setText("Field Empty");
        }
      }
	  
      if(event.getSource()==deleteButton){
        String wordDelete = deleteArea.getText();
        String alert = dictionary.deleteData(wordDelete);
        alertArea.setText(alert);
      }
	  
	  if(event.getSource()==clearData){
        searchArea.setText("");
		meaningArea.setText("");
		similarArea.setText("");
		addWordArea.setText("");
		addmeaningArea.setText("");
		deleteArea.setText("");
		alertArea.setText("");
      }
    }
    catch(Exception e){
      System.out.println("Error occured "+e);
    }
  }
}