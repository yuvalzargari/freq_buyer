
public class TestHereFunctionAndClass {

	public String toRoman(int nmber) {
		String[] RCODE = {"M", "CM", "D", "CD", "C", "XC", "L",
				"XL", "X", "IX", "V", "IV", "I"};
		int[]    BVAL  = {1000, 900, 500, 400,  100,   90,  50,
				40,   10,    9,   5,   4,    1};
		if (nmber <= 0 || nmber >= 4000) {
			throw new NumberFormatException("Value outside roman numeral range.");
		}
		String roman = "";         
		for (int i = 0; i < RCODE.length; i++) {
			while (nmber >= BVAL[i]) {
				nmber -= BVAL[i];
				roman  += RCODE[i];
			}
		}
		return roman; 
	}
}
