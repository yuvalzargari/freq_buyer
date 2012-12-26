import static org.junit.Assert.*;

import org.junit.Test;


public class UnitTests {

	@Test
	public void tests() {
		Data_base_mock d = new Data_base_mock();
		assertEquals(true, d.SignUp("yu@val@gmail.com", "pass"));
		assertEquals(true, d.SignUp("yuval2@gmail.com", "pass"));
		assertEquals(true, d.SignUp("yuval3@gmail.com", "pass"));
		System.out.println(d.toString());
	}
	

}
