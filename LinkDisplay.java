import java.net.MalformedURLException;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
	This class displays all links found on a webpage.
	@author Samantha Sturges
	CS 250
	Assignment 3, Problem 2
*/
public class LinkDisplay
{
	private static String url;
	private JButton displayLinks;
	private static JList viewLinks;
	private static JScrollPane listScrollPane;
	private static JPanel panelThree;
	private static ArrayList printLinks = new ArrayList<>();
	private static JTextField enterURL;

	//Empty constructor to call this class and its methods in main.
	public LinkDisplay()
	{
		return;
	}

	public static void main(String[] args) throws Exception
	{
		LinkFinder getLinks = new LinkFinder("http://www.wcsu.edu");

		getLinks.setLinkList();		//Sets the arraylist based on URL information.
		System.out.println(getLinks.getLinkList()); //Gets the arraylist and prints it to console.

		/**
			If working properly, would display get user input and display links in a JList.
			However, the action listener appears to call getText() and setString() after the frame is created.
			Because of this, generateLinks() can not get the users text. Place generateLinks() inside the ActionListener doesn't fix the problem.
			A possible solution could include making another class that implements JFrame.
		*/
		LinkDisplay createFrame = new LinkDisplay();

		createFrame.linkFrame();
	}

	/**
		Generates a frame with three panels.
		@return frame the frame that is created.
	*/
	public JFrame linkFrame()
	{
		JFrame frame = new JFrame();
		frame.setSize(400, 600);
		frame.setLayout(new GridLayout(3, 1));
		frame.setTitle("Web Page Links");

		JPanel panelOne = new JPanel();
		JPanel panelTwo = new JPanel();

		enterURL = new JTextField("", 20);	//Text field that is displayed in the first panel.
		panelOne.add(enterURL);

		displayLinks = new JButton("Display Links");	//Button that is displayed in the second panel.

		displayLinks.addActionListener(new ActionListener(){	//Button gets the text from enterURL.
			public void actionPerformed(ActionEvent event)
			{
				getText();
				setString();

			}});

		panelTwo.add(displayLinks);

		frame.add(panelOne);
		frame.add(panelTwo);
		frame.add(generateLinks());		//Supposed to generate links based on user text in panelOne.

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		return frame;
	}

	/**
		Calls to the class LinkFinder to generate an ArrayList of Strings representing links found in the given url.
		Stores the data in a scrollable JList.
		@return showAll the panel containing the JList.
	*/
	public static JPanel generateLinks()
	{
		JPanel showAll = new JPanel();
		LinkFinder getLinks = new LinkFinder(url);

		getLinks.setLinkList();
		printLinks = getLinks.getLinkList();

		DefaultListModel<String> toList = new DefaultListModel<>();
		for (int i = 0; i < printLinks.size(); i++)
		{
			toList.addElement(printLinks.get(i).toString());
		}

		viewLinks = new JList(toList);
		viewLinks.setVisibleRowCount(12);
		viewLinks.setFixedCellWidth(200);
		viewLinks.setFixedCellHeight(15);
		listScrollPane = new JScrollPane(viewLinks);
		showAll.add(listScrollPane);
		listScrollPane.setVisible(true);

		return showAll;
	}

	/**
		Gets the string from a text field.
		@return enterURL.getText().toString() the string from text field enterURL.
	*/
	public static String getText()
	{
		return enterURL.getText().toString();
	}

	/**
		Sets the variable url to the return value of getText()
	*/
	public static void setString()
	{
		url = getText();
	}
}