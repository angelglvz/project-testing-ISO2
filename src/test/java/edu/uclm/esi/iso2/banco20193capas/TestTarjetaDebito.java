package edu.uclm.esi.iso2.banco20193capas;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
public class TestTarjetaDebito extends TestCase{

	
	@Test
	public void testCompraPorInternetOK() {
		TarjetaDebito td = new TarjetaDebito();
		td.setPin(123);
		
		try {
			td.comprarPorInternet(123, 10);
		} catch (TarjetaBloqueadaException e) {
			fail();
		} catch (PinInvalidoException e) {
			fail();
		} catch (SaldoInsuficienteException e) {
			fail();
		} catch (ImporteInvalidoException e) {
			fail();
		}
	}
	
	
	@Test
	public void testComprarOK() {
		TarjetaDebito td = new TarjetaDebito();
		Cuenta cuenta = new Cuenta();
		
		td.setCuenta(cuenta);
		td.setPin(123);
		
		try {
			td.comprar(123, 10.0);
		} catch (ImporteInvalidoException e) {
			fail();
		} catch (SaldoInsuficienteException e) {
			fail();
		} catch (TarjetaBloqueadaException e) {
			fail();
		} catch (PinInvalidoException e) {
			fail();
		}
	}
	
	
	@Test
	public void testCambiarPinOK() {
		TarjetaDebito td = new TarjetaDebito();
		int pn = td.getPin();
		
		try {
			td.cambiarPin(pn, 111);
		} catch (PinInvalidoException e) {
			fail();
		}
	}
	
	
	@Test
	public void testCambiarPinInvalido() {
		TarjetaDebito td = new TarjetaDebito();
	
		try {
			td.cambiarPin(123, 123);
		} catch (PinInvalidoException e) {
			fail();
		}
		
	}
	
	@Test
	public void testCompraTarjetaBloqueada() {
		TarjetaDebito td = new TarjetaDebito();
		td.setActiva(false);
		try {
			td.comprar(123, 20.0);
		} catch (ImporteInvalidoException e) {
			fail();
		} catch (SaldoInsuficienteException e) {
			fail();
		} catch (TarjetaBloqueadaException e) {
			fail();
		} catch (PinInvalidoException e) {
			fail();
		}
	}
		
	@Test
	public void testCompraNoValidaPinIncorrecto() {
		TarjetaDebito td = new TarjetaDebito();
		try {
			td.comprar(1, 10);
		} catch (ImporteInvalidoException e) {
			fail();
		} catch (SaldoInsuficienteException e) {
			fail();
		} catch (TarjetaBloqueadaException e) {
			fail();
		} catch (PinInvalidoException e) {
			fail();
		}
	}
	
	
	@Test
	public void testCompraNoValidaImporteIncorrecto() {
		TarjetaDebito td = new TarjetaDebito();
		try {
			td.comprar(123, -10);
		} catch (ImporteInvalidoException e) {
			fail();
		} catch (SaldoInsuficienteException e) {
			fail();
		} catch (TarjetaBloqueadaException e) {
			fail();
		} catch (PinInvalidoException e) {
			fail();
		}
	}
}
