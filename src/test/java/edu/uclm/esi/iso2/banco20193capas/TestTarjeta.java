package edu.uclm.esi.iso2.banco20193capas;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import edu.uclm.esi.iso2.banco20193capas.exceptions.CuentaSinTitularesException;
import edu.uclm.esi.iso2.banco20193capas.exceptions.CuentaYaCreadaException;
import edu.uclm.esi.iso2.banco20193capas.exceptions.ImporteInvalidoException;
import edu.uclm.esi.iso2.banco20193capas.exceptions.PinInvalidoException;
import edu.uclm.esi.iso2.banco20193capas.exceptions.SaldoInsuficienteException;
import edu.uclm.esi.iso2.banco20193capas.exceptions.TarjetaBloqueadaException;
import edu.uclm.esi.iso2.banco20193capas.exceptions.TokenInvalidoException;
import edu.uclm.esi.iso2.banco20193capas.model.Cliente;
import edu.uclm.esi.iso2.banco20193capas.model.Compra;
import edu.uclm.esi.iso2.banco20193capas.model.Cuenta;
import edu.uclm.esi.iso2.banco20193capas.model.MovimientoCuenta;
import edu.uclm.esi.iso2.banco20193capas.model.MovimientoTarjetaCredito;
import edu.uclm.esi.iso2.banco20193capas.model.Tarjeta;
import edu.uclm.esi.iso2.banco20193capas.model.TarjetaCredito;
import edu.uclm.esi.iso2.banco20193capas.model.TarjetaDebito;
import junit.framework.TestCase;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TestTarjeta extends TestCase{
	

	
	@Test
	public void testGetCuenta() {
		Tarjeta t = new TarjetaDebito();
		Cuenta c = new Cuenta();
		t.setCuenta(c);
		assertTrue(t.getCuenta()==c);
	}
	
	@Test
	public void testGetTitular() {
		Tarjeta t = new TarjetaDebito();
		Cliente c = new Cliente();
		t.setTitular(c);
		assertTrue(t.getTitular()==c);
	}
	
	@Test
	public void testSetActiva() {
		Tarjeta t = new TarjetaDebito();
		t.setActiva(false);
		assertTrue(t.isActiva()==false);
	}
	
	@Test
	public void testSetPin() {
		Tarjeta t = new TarjetaDebito();
		t.setPin(123);
		assertTrue(t.getPin()==123);
	}
	
	@Test
	public void testSetId() {
		Tarjeta t = new TarjetaDebito();
		long id = 123;
		t.setId(id);
		assertTrue(t.getId()==123);
	}
	
	
	
}