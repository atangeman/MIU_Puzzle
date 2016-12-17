
import java.security.InvalidKeyException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class TheoremCollection extends ArrayList<Theorem> {
	private static final long serialVersionUID = 1L;
	private ArrayList<Theorem> theorems;

	public TheoremCollection() {
		theorems = new ArrayList<Theorem>();
		theorems.add(new Theorem("MI"));
	}

	public TheoremCollection(Theorem initTheorem) {
		theorems = new ArrayList<Theorem>();
		theorems.add(initTheorem);
	}

	public TheoremCollection(ArrayList<Theorem> theorems) {
		this.theorems = theorems;
	}

	public Iterator<Theorem> iterator() {
		return theorems.iterator();
	}

	public int size() {
		return theorems.size();
	}

	public Theorem get(int idx) {
		return theorems.get(idx);
	}
	
	public Theorem getLast() {
		return theorems.get(theorems.size() - 1);
	}
	
	public boolean add(Theorem theorem) {
		return theorems.add(theorem);
	}

	public TheoremCollection smartExpand() {
		for(Theorem t : theorems) {
			smartExpand(t, 4);
		}
		return new TheoremCollection(theorems);
	}

	private void smartExpand(Theorem t, int rule) {
		try {
			if ((rule >= 1 && rule <= 4)) {
				Theorem t2 = BuildMIU(t, rule);
				if (t2.ToString().equals(t.ToString()) == false) { 
					System.out.println("Adding theorem: " + t2.GetTheorem() + " rule: " + rule);
					theorems.add(t2); 
				}
				smartExpand(t, rule - 1);
			} else {
				return;
			}
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean expand(int rule) throws InvalidKeyException {
		Theorem t = theorems.get(theorems.size() - 1);
		try {
			if ((rule >= 1 && rule <= 4)) {
				BuildMIU(t, rule);
			} else
				throw new InvalidKeyException("Not a valid rule type");
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean expand(int idx, int rule) throws InvalidKeyException {
		Theorem t = theorems.get(idx);
		try {
			if ((rule >= 1 && rule <= 4)) {
				BuildMIU(t, rule);
			} else
				throw new InvalidKeyException("Not a valid rule type");
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

	private static int randInt(int min, int max) {

		// Usually this can be a field rather than a method variable
		Random rand = new Random();

		// nextInt is normally exclusive of the top value,
		// so add 1 to make it inclusive
		int randomNum = rand.nextInt((max - min) + 1) + min;

		return randomNum;
	}
	/*
	 * void AddToCollection(String value) { JSONObject oJsonInner = new
	 * JSONObject(); oJsonInner.put("string",value); aProjects.add(oJsonInner);
	 * }
	 */

	private Theorem BuildMIU(Theorem theorem, int rule) throws CloneNotSupportedException {
		Theorem t2 = null;
		t2 = (Theorem) theorem.clone();
		if (t2 != null) {
			switch (rule) {
			case 1:
				t2.RuleOneCheck(); // If last letter is 'I', add on 'U'
				return t2;
			case 2:
				t2.RuleTwoCheck();
				return t2;
			case 3:
				t2.RuleThreeCheck();
				return t2;
			case 4:
				t2.RuleFourCheck();
				return t2;
			default:
				System.out.println("Not a valid entry");
				return theorem;
			}
		} else
			return theorem;
	}
}
