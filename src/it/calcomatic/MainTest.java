package it.calcomatic;

import java.text.ParseException;
import java.util.Scanner;

import it.calcomatic.parser.MathematicalParser;
import it.calcomatic.parser.ParseTree;

public class MainTest {

	public static void main(String[] args) throws ParseException {
		Scanner s = new Scanner(System.in);
		
		String input = s.nextLine();
		MathematicalParser parser = new MathematicalParser();
		parser.parse(input);
		
		ParseTree tree = parser.getTree();
		
		System.out.println(tree.toString());
		
		s.close();
	}
}
