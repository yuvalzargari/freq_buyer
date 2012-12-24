import static org.junit.Assert.*;

import org.junit.Test;


public class UnitTests {

	@Test
	public void tests() {
		Data_base_mock d = new Data_base_mock();
		assertEquals(true, d.Login("yuval@gmail.com", "pass"));
		
	}
	

}
