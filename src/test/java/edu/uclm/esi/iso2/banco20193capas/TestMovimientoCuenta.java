package edu.uclm.esi.iso2.banco20193capas;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import edu.uclm.esi.iso2.banco20193capas.model.Cuenta;
import edu.uclm.esi.iso2.banco20193capas.model.MovimientoCuenta;
import junit.framework.TestCase;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TestMovimientoCuenta extends TestCase{

	@Test
	public void testSetId() {
		MovimientoCuenta mc = new MovimientoCuenta(); 
		long a = 123;
		mc.setId(a);
		assertTrue(mc.getId()==123);
	}
	
	@Test
	public void testSetCuenta() {
		MovimientoCuenta mc = new MovimientoCuenta();
		Cuenta c = new Cuenta();
		mc.setCuenta(c);
		assertTrue(mc.getCuenta()==c);
	}
	
	@Test
	public void testSetImporte() {
		MovimientoCuenta mc = new MovimientoCuenta();
		mc.setImporte(99.9);
		assertTrue(mc.getImporte()==99.9);
	}
	
	@Test
	public void testSetConcepto() {
		MovimientoCuenta mc = new MovimientoCuenta();
		mc.setConcepto("concepto");
		assertTrue(mc.getConcepto()=="concepto");
	}
	
}
