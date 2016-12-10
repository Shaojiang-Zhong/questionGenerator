import javax.swing.*;

import java.awt.*; 
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class QuestionGenerator implements  ActionListener{

    // Definition of global values and items that are part of the GUI.
	static String filePath = System.getProperty("user.dir");
	static String rightAnswer = "";
	class Node{
		char value;
		Node next;
		public Node(char value, Node next) {
			// TODO Auto-generated constructor stub
			this.value = value;
			this.next = next;
		}
	}

    JPanel charactorPanel, selectPanel, answerPanel;
    JLabel label1, label2, label3, answerLabel;
    JButton fiveButton, sevenButton, answerButton;

    public JPanel createContentPane (){

        // We create a bottom JPanel to place everything on.
        JPanel totalGUI = new JPanel();
        totalGUI.setLayout(null);

        // Creation of a Panel to contain the title labels
        charactorPanel = new JPanel();
        charactorPanel.setLayout(null);
        charactorPanel.setLocation(10, 10);
        charactorPanel.setSize(200, 150);
        totalGUI.add(charactorPanel);

        label1 = new JLabel("");
        label1.setLocation(0, 0);
        label1.setSize(200, 50);
        label1.setHorizontalAlignment(0);
        label1.setForeground(Color.black);
        charactorPanel.add(label1);

        label2 = new JLabel("");
        label2.setLocation(0, 50);
        label2.setSize(200, 50);
        label2.setHorizontalAlignment(0);
        label2.setForeground(Color.black);
        charactorPanel.add(label2);
        
        label3 = new JLabel("");
        label3.setLocation(0, 100);
        label3.setSize(200, 50);
        label3.setHorizontalAlignment(0);
        label3.setForeground(Color.black);
        charactorPanel.add(label3);

        // Creation of a Panel to contain the score labels.
        selectPanel = new JPanel();
        selectPanel.setLayout(null);
        selectPanel.setLocation(220, 10);
        selectPanel.setSize(200, 150);
        totalGUI.add(selectPanel);

        fiveButton = new JButton("生成五言诗题目");
        fiveButton.setForeground(Color.blue);
        fiveButton.setLocation(0, 30);
        fiveButton.setSize(200, 30);
        fiveButton.addActionListener(this);
        selectPanel.add(fiveButton);

        sevenButton = new JButton("生成七言诗题目");
        sevenButton.setForeground(Color.red);
        sevenButton.setLocation(0, 90);
        sevenButton.setSize(200, 30);
        sevenButton.addActionListener(this);
        selectPanel.add(sevenButton);

        // Creation of a Panel to contain all the JButtons.
        answerPanel = new JPanel();
        answerPanel.setLayout(null);
        answerPanel.setLocation(10, 170);
        answerPanel.setSize(365, 50);
        totalGUI.add(answerPanel);

        // We create a button and manipulate it using the syntax we have
        // used before. Now each button has an ActionListener which posts 
        // its action out when the button is pressed.
        answerButton = new JButton("正确答案");
        answerButton.setLocation(0, 0);
        answerButton.setSize(155, 50);
        answerButton.addActionListener(this);
        answerPanel.add(answerButton);

        answerLabel = new JLabel("");
        answerLabel.setLocation(155, 0);
        answerLabel.setSize(210, 50);
        answerLabel.setHorizontalAlignment(0);
        answerLabel.setForeground(Color.black);
        answerPanel.add(answerLabel);

       
        
        totalGUI.setOpaque(true);
        return totalGUI;
    }

    // This is the new ActionPerformed Method.
    // It catches any events with an ActionListener attached.
    // Using an if statement, we can determine which button was pressed
    // and change the appropriate values in our GUI.
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == fiveButton)
        {
        	
            File fiveFile = new File(filePath + "/data/wuyan.txt");
            try {
				BufferedReader fiveReader = new BufferedReader
						(new InputStreamReader(new FileInputStream(fiveFile), "UTF-8"));
				Node first = handleFive(fiveReader);
				label1.setText
				(first.value + "      " + first.next.value + "      " + 
				first.next.next.value);
				Node forth = first.next.next.next;
				label2.setText
				(forth.value + "      " + forth.next.value + "      " + 
				forth.next.next.value);
				Node seventh = forth.next.next.next;
				label3.setText
				(seventh.value + "      " + seventh.next.value + "      " + 
				seventh.next.next.value);
				answerLabel.setText("");
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (HeadlessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
           
        }
        else if(e.getSource() == sevenButton)
        {
        	File sevenFile = new File(filePath + "/data/qiyan.txt");
            try {
				BufferedReader sevenReader = new BufferedReader
						(new InputStreamReader(new FileInputStream(sevenFile), "UTF-8"));
				Node first = handleSeven(sevenReader);
				label1.setText
				(first.value + "      " + first.next.value + "      " + 
				first.next.next.value + "      " + first.next.next.next.value);
				Node fifth = first.next.next.next.next;
				label2.setText
				(fifth.value + "      " + fifth.next.value + "      " + 
						fifth.next.next.value + "      " + fifth.next.next.next.value);
				Node ninth = fifth.next.next.next.next;
				label3.setText
				(ninth.value + "      " + ninth.next.value + "      " + 
						ninth.next.next.value + "      " + ninth.next.next.next.value);
				answerLabel.setText("");
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (HeadlessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        }
        else if(e.getSource() == answerButton)
        {
            answerLabel.setText(rightAnswer);
        }
    }
    private Node handleFive(BufferedReader br) throws IOException{
    	StringBuilder buildFive = readFile(br, getRandomNum(116), getRandomNum(116));
    	rightAnswer = buildFive.substring(0, 5);
    	buildFive.deleteCharAt(5 + getRandomNum(5));
    	return disorganize(buildFive);	
    }
    private Node handleSeven(BufferedReader br) throws IOException{
    	StringBuilder buildSeven = readFile(br, getRandomNum(198), getRandomNum(198));
    	rightAnswer = buildSeven.substring(0, 7);
    	buildSeven.deleteCharAt(7 + getRandomNum(7));
    	buildSeven.deleteCharAt(7 + getRandomNum(6));
    	
    	
    	return disorganize(buildSeven);	
    }
    
    
    private static int getRandomNum(int total){
    	return (int) (Math.random()*total);
    }
    
    private StringBuilder readFile 
    (BufferedReader br, int numb1, int numb2) throws IOException{
    	int max = 0;
    	int min = 0;
    	StringBuilder sb = null;
    	if (numb1 > numb2) {max = numb1; min = numb2;}
    	else if (numb1 < numb2) {max = numb2; min = numb1;}
    	else return null;
    	for (int i = 0;; i++) {
			String tmp = br.readLine();
			if (i == min){sb = new StringBuilder(tmp);}
			if (i == max){sb.append(tmp);return sb;}
		}
    }
    private Node disorganize (StringBuilder sb){
    	int N = sb.length();
    	Stack<Node> nodeStack = new Stack<Node>();
    	for (int i = N; i > 0; i--) {
    		int tmpNumb = getRandomNum(i);
    		Node tmpNode = new Node(sb.charAt(tmpNumb), null);
    		sb.deleteCharAt(tmpNumb);
    		if (nodeStack.isEmpty()){nodeStack.push(tmpNode);}
    		else {
    			tmpNode.next = nodeStack.pop();
    			nodeStack.push(tmpNode);
    		}
			
		}
    	return nodeStack.pop();
    	
    }

    private static void createAndShowGUI() {

        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("诗词大赛题目生成器");

        //Create and set up the content pane.
        QuestionGenerator demo = new QuestionGenerator();
        frame.setContentPane(demo.createContentPane());

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 250);
        frame.setVisible(true);
    }
    
   

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
   	 
    }
}