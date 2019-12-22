package edu.uclm.esi.iso2.banco20193capas;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import edu.uclm.esi.iso2.banco20193capas.model.Cuenta;
import edu.uclm.esi.iso2.banco20193capas.model.MovimientoCuenta;
import edu.uclm.esi.iso2.banco20193capas.model.MovimientoTarjetaCredito;
import edu.uclm.esi.iso2.banco20193capas.model.TarjetaCredito;
import junit.framework.TestCase;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TestMovimientoTarjetaCredito extends TestCase{
	
	@Test
	public void testSetId() {
		MovimientoTarjetaCredito mtc = new MovimientoTarjetaCredito();
		long a = 123;
		mtc.setId(a);
		assertTrue(mtc.getId()==123);
	}
	
	@Test
	public void testSetTarjeta() {
		MovimientoTarjetaCredito mtc = new MovimientoTarjetaCredito();
		TarjetaCredito tarjeta = new TarjetaCredito();
		mtc.setTarjeta(tarjeta);
		assertTrue(mtc.getTarjeta()==tarjeta);
	}
	
	@Test
	public void testSetImporte() {
		MovimientoTarjetaCredito mtc = new MovimientoTarjetaCredito();
		mtc.setImporte(32.1);
		assertTrue(mtc.getImporte()==32.1);
	}
	
	@Test
	public void testSetConcepto() {
		MovimientoTarjetaCredito mtc = new MovimientoTarjetaCredito();
		mtc.setConcepto("concepto");
		assertTrue(mtc.getConcepto()=="concepto");
	}
	
	

}
