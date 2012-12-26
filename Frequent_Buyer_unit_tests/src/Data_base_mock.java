import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Data_base_mock 
{
	String db [][];
	
	public Data_base_mock() {
		db = new String [10][5];
		db[0][0] = "id";
		db[0][1] = "email";
		db[0][2] = "type";
		db[0][3] = "password";
		db[0][4] = "numToCoupon";
		
		db[1][0] = "0";
		db[1][1] = "eitan@test.com";
		db[1][2] = "owner";
		db[1][3] = "1234";
		db[1][4] = "100";
		
		//System.out.println(toString());
	}
	public boolean SignUp(String email,String pass) {
		if (!is_email_valid(email)) {
			return false;
		}
		for (int i = 2; i < db.length ; i++) 
		{
			if (email == db[i][1] && pass == db[i][3]) {
				return false;
			}
		}
		int temp;
		for (int i = 2; i < db.length; i++) {
			if (db[i][0] == null) 
			{
				temp = Integer.parseInt( db[i-1][0]);
				db[i][0] = temp+1 +"";
				db[i][1] = email;
				db[i][2] = "client";
				db[i][3] = pass;
				db[i][4] = "10";
				break;
			}
		}
		return true;
	}
	//checks if mail is valid
	private boolean is_email_valid(String hex) 
	{
		Pattern p = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
		Matcher m = p.matcher(hex);
		boolean matchFound = m.matches();
		return matchFound;
	}
	
	public String toString() {
		String s = "";
		for (int i = 0; i < db.length; i++) 
		{
			for (int j = 0; j < db[0].length; j++) 
			{
				s +=db[i][j];
				if (db[i][j] != null) {
					for (int j2 = db[i][j].length(); j2 < 20; j2++) {
						s += " ";
					}
				}	
				else {
					for (int j2 = 4; j2 < 20; j2++) {
						s += " ";
					}
				}
			}
			s+="\n";
		}
		return s;
	}
	public boolean Login(String email,String pass) 
	{
		for (int i = 1; i < db.length ; i++) 
		{
			if (email == db[i][1] && pass == db[i][3]) {
				return true;
			}
		}
		return false;
	}
}


