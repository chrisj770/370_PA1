import java.util.*;
import java.io.*;

public class pa1 
{
	public static void main (String [] line)
	{
		// Setup DFA components
		File infile = null;
		int numStates = 0, startState = 0;
		ArrayList<Integer> accept = new ArrayList<Integer>();
		ArrayList<String> alphabet = new ArrayList<String>();
		
		Scanner in = null;
		try 
		{
			infile = new File (line[0]);
			in = new Scanner (infile);
		}
		catch (Exception e)
		{
			System.out.println("File not found");
			System.exit(1);
		}
		
	
		// Read in number of States
		numStates = in.nextInt();

		// Read in alphabet
		String inputAlphabet = in.next();
		for (int i = 0; i < inputAlphabet.length(); i++)
		{
			alphabet.add(Character.toString(inputAlphabet.charAt(i)));
		}
		int[] transitions1 = new int [alphabet.size() * numStates];

		// Read Transitions, create Mapping objects
		for (int j = 0; j < (alphabet.size() * numStates); j++)
		{
			String trans = in.nextLine();
			if (trans.length() == 0) 
			{
				j--;
				continue;
			}
			
			String[] splitTrans = trans.split(" ");
			int startPos = Integer.parseInt(splitTrans[0]);
			int endPos = Integer.parseInt(splitTrans[2]);
			String symbol = splitTrans[1].substring(1, splitTrans[1].length() - 1);
			for (int a = 0; a < alphabet.size(); a++)
			{
				if (alphabet.get(a).equals(symbol))
				{
					int transIndex = (startPos -1) * (alphabet.size()) + a;
					transitions1[transIndex] = endPos;
					break;
				}
			}
		}
		startState = in.nextInt();
		in.nextLine();
		
		// Read and parse accepting states
		String acc = in.nextLine();
		for (String d : acc.split(" "))
		{
			accept.add(Integer.parseInt(d));
		}

		// Main loop
		while (in.hasNextLine())
		{
			int curState = startState;
			String input = in.nextLine();
			for (int k = 0; k < input.length(); k++)
			{
				//char symbol1 = input.charAt(k);
				for (int b = 0; b < alphabet.size(); b++)
				{
					if (alphabet.get(b).equals(input.substring(k, k+1)))
					{
						int trans = (curState -1) * (alphabet.size()) + b;
						curState = transitions1[trans];
						break;
					}
				}
			}
			if (accept.contains(curState))
				System.out.println("Accept");
			else System.out.println("Reject");
		}
	}
}
