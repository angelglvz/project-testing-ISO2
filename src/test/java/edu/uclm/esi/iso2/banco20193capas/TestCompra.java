package edu.uclm.esi.iso2.banco20193capas;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import edu.uclm.esi.iso2.banco20193capas.model.Compra;
import junit.framework.TestCase;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestCompra extends TestCase{

	@Test
	public void testSetImporte() {
		Compra c = new Compra(12.3,1);
		c.setImporte(22.9);
		assertTrue(c.getImporte()==22.9);
	}
	
	@Test
	public void testSetToken() {
		Compra c = new Compra(1,2);
		c.setToken(8);
		assertTrue(c.getToken()==8);
	}
}
