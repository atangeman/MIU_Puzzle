
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.awt.Container;
import java.io.*;
import java.security.InvalidKeyException;

import processing.core.PApplet;

/**
 * Class to run main instance of PApplet using the Processing-2 framework.
 * 
 * @author ATangeman
 */
public class Main extends PApplet {

	private char letter;
	private String miuChars = "MI";
	private String input = "";
	ArrayList<TheoremCollection> miuCollection;
	TheoremCollection theoremCollection;

	/**
	 * Main method to initialize applet
	 * @param args runtime command parameters to pass.
	 */
	public static void main(String[] args) {
		PApplet.main("UsingProcessing");
	}

	public void settings() {
		size(640, 600);
		// Create the font
		textFont(createFont("Georgia", 36));
	}

	public void setup() {
		fill(120, 50, 240);
		miuCollection = new ArrayList<TheoremCollection>();
		theoremCollection = new TheoremCollection(new Theorem("MI"));
		miuCollection.add(theoremCollection);
	}

	public void draw() {
		background(0); // Set background to black

		// Draw the letter to the center of the screen
		textSize(14);
		text("Click on the program, then type to add to the String", 50, 50);
		text("Current input: " + input, 50, 70);
		text("The String is " + miuChars.length() + " characters long", 50, 90);

		int heightAdd = 0;
		for (TheoremCollection theoremCol : miuCollection) {
			for (Theorem theorem : theoremCol) {
				// System.out.println("Theorem: " + theorem.GetTheorem());
				textSize(36);
				text(theorem.ToString(), 50, 120 + heightAdd, 540, 300);
				heightAdd += 50;
			}
		}
	}

	public void UpdateTheorems() {
		List<TheoremCollection> thingsToBeAdd = new ArrayList<TheoremCollection>();
		for (Iterator<TheoremCollection> iterator = miuCollection.iterator(); iterator.hasNext(); ) {
			TheoremCollection value = iterator.next();
			thingsToBeAdd.add(value.smartExpand());
		}
		miuCollection.addAll(thingsToBeAdd);
	}

	public void UpdateTheorems(int rule) {
		try {
			boolean attempt = theoremCollection.expand(rule);
			if (attempt == false) {
				theoremCollection = new TheoremCollection(new Theorem("MI"));
				miuCollection.add(theoremCollection);
			}
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void keyPressed() {
		// The variable "key" always contains the value
		// of the most recent key pressed.
		println("keyPressed() called at " + System.currentTimeMillis());
		if (key == ' ') {
			return;
		}
		if ((key >= '1' && key <= '4')) {
			input += key;
		}
		/*
		 * char c = Character.toUpperCase(key); if ((c == 'M' || c == 'I') || c
		 * == 'U') { letter = c; input += c; }
		 */
		if (key == ENTER || key == RETURN) {
			if (input == "") {
				UpdateTheorems();
				return;
			}
			UpdateTheorems(Integer.parseInt(input.trim()));
			input = "";
		}

		else if (key == BACKSPACE) {
			input = input.substring(0, input.length() - 1);
		}
		// Write the letter to the console
	}
}
