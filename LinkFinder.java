import javax.swing.JOptionPane;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.InputStream;
import java.net.*;
import java.io.IOException;

/**
	This class gets all links found on a webpage.
	@author Samantha Sturges
	CS 250
	Assignment 3, Problem 2
*/
public class LinkFinder
{
	private ArrayList<String> linkList;
	private String webStr;

	/**
		Constructor takes the url as a parameter for use in main.
		@param webAddress the url to find links for.
	*/
	public LinkFinder(String webAddress)
	{
		webStr = webAddress;
	}

	/**
		Loops through the arraylist and returns its data.
		@return linkList the arraylist of links.
	*/
	public ArrayList<String> getLinkList()
	{
		for (int i = 0; i <linkList.size(); i++)
		{
			linkList.get(i);
		}
		return linkList;
	}

	/**
		Sets the arraylist by getting all links from a webpage.
	*/
	public void setLinkList()
	{
		try{
		linkList = new ArrayList<String>();
		URL u = new URL(webStr);
		URLConnection connection = u.openConnection();
		InputStream stream = connection.getInputStream();
		Scanner in = new Scanner(stream).useDelimiter("\\Z");

		String pattern = "(\\<a\\s+href.+\\>)(.+)(\\<\\/a\\>)";	//Pattern to find links.
		String temp = in.next();
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(temp);

		while (m.find())		//Sets the arraylist to anything starting with <a href and ending with </a>
		{
			linkList.add(temp.substring(m.start(), m.end())+ "\n");
		}
		} catch(MalformedURLException urlEx)				//Catches exception of bad url.
			{
				JOptionPane.showMessageDialog(null, "Bad URL " + urlEx);
			}
			catch (IOException ioEx)						//Catches exception of bad input.
			{
				JOptionPane.showMessageDialog(null, "Bad URL " + ioEx);
			}
	}
}