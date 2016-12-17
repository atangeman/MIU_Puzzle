import java.io.*;
public class Theorem implements Cloneable {
	private String theorem;
	
	public String axiom;
	public int rule = 0;
	
	public Theorem(String axiom, int rule) {
		this.axiom = axiom;
		this.theorem = axiom;
		this.rule = rule;
	}

	public Theorem(String axiom) {
		this.axiom = axiom;
		this.theorem = axiom;
	}
	
	public String ToString() {
		return theorem.toString();
	}
	
	
	public int Length() {
		return theorem.length();
	}
	
	public String GetTheorem() {
		return theorem;
	}
	
	public String GetAxoim() {
		return axiom;
	}
	
	public void RuleOneCheck() {
		if (theorem.charAt(theorem.length() - 1) == 'I') {
			theorem += 'U';
		}
	}
	
	public void RuleTwoCheck() {
		String s1 = theorem.substring(1, theorem.length()).trim();
		if (s1 == null || s1 == "") return;
		theorem = theorem + s1;
	}

	public void RuleThreeCheck() {
		if (theorem.indexOf("III") > 0) {
			String[] tmpMiuChars = theorem.toString().split("III");
			if(tmpMiuChars.length > 1)
				theorem = tmpMiuChars[0] + "U" + tmpMiuChars[1];
			else
				theorem = tmpMiuChars[0] + "U";
		}
	}

	public void RuleFourCheck() {
		if (theorem.indexOf("UU") > 0) {
			String[] tmpMiuChars = theorem.split("UU");
			theorem = tmpMiuChars[0] + tmpMiuChars[1];
		}
	}
	

	private void println(String string) {
		System.out.println(string);		
	}
	
	public Object clone() throws CloneNotSupportedException {  
		return super.clone();  
	}
}