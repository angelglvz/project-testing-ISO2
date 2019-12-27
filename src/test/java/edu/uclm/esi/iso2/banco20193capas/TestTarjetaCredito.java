package edu.uclm.esi.iso2.banco20193capas;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import edu.uclm.esi.iso2.banco20193capas.model.Cuenta;
import edu.uclm.esi.iso2.banco20193capas.model.Manager;
import edu.uclm.esi.iso2.banco20193capas.exceptions.ClienteNoAutorizadoException;
import edu.uclm.esi.iso2.banco20193capas.exceptions.ClienteNoEncontradoException;
import edu.uclm.esi.iso2.banco20193capas.exceptions.CuentaInvalidaException;
import edu.uclm.esi.iso2.banco20193capas.exceptions.CuentaSinTitularesException;
import edu.uclm.esi.iso2.banco20193capas.exceptions.CuentaYaCreadaException;
import edu.uclm.esi.iso2.banco20193capas.exceptions.ImporteInvalidoException;
import edu.uclm.esi.iso2.banco20193capas.exceptions.PinInvalidoException;
import edu.uclm.esi.iso2.banco20193capas.exceptions.SaldoInsuficienteException;
import edu.uclm.esi.iso2.banco20193capas.exceptions.TarjetaBloqueadaException;
import edu.uclm.esi.iso2.banco20193capas.model.Cliente;
import edu.uclm.esi.iso2.banco20193capas.model.Tarjeta;
import edu.uclm.esi.iso2.banco20193capas.model.TarjetaCredito;
import edu.uclm.esi.iso2.banco20193capas.model.TarjetaDebito;
import junit.framework.TestCase;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TestTarjetaCredito extends TestCase{
	
	private Cuenta cuentaAna;
	private Cliente  ana;
	private TarjetaDebito  tdAna;
	private TarjetaCredito  tcAna;
	
	
	@Before
	public void setUp() {
		Manager.getMovimientoDAO().deleteAll();
		Manager.getMovimientoTarjetaCreditoDAO().deleteAll();
		Manager.getTarjetaCreditoDAO().deleteAll();
		Manager.getTarjetaDebitoDAO().deleteAll();
		Manager.getCuentaDAO().deleteAll();
		Manager.getClienteDAO().deleteAll();


		this.ana = new Cliente("98765F", "Ana", "López");
		this.ana.insert();
		this.cuentaAna = new Cuenta((long)2);
		try {

			this.cuentaAna.addTitular(ana);
			this.cuentaAna.insert();
			this.cuentaAna.ingresar(5000);
			this.tcAna = this.cuentaAna.emitirTarjetaCredito(ana.getNif(), 10000);
			this.tcAna.cambiarPin(this.tcAna.getPin(), 1234);
			this.tdAna = this.cuentaAna.emitirTarjetaDebito(ana.getNif());
			this.tdAna.cambiarPin(this.tdAna.getPin(), 1234);
		} catch (Exception e) {
			fail("Excepción inesperada en setUp(): " + e);
		}
	}
	
	@Test
	public void testSacarDineroOK() {
		try {
			tcAna.sacarDinero(1234, 100);
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
	public void testSacarDineroSaldoInsufieciente() {
		try {
			tcAna.sacarDinero(1234, 1000000);
		} catch (ImporteInvalidoException e) {
			fail();
		} catch (SaldoInsuficienteException e) {
			
		} catch (TarjetaBloqueadaException e) {
			fail();
		} catch (PinInvalidoException e) {
			fail();
		}
	}
	
	
	@Test
	public void testSacarDineroImporteInvalido() {
		try {
			tcAna.sacarDinero(1234, -100);
		} catch (ImporteInvalidoException e) {
			
		} catch (SaldoInsuficienteException e) {
			fail();
		} catch (TarjetaBloqueadaException e) {
			fail();
		} catch (PinInvalidoException e) {
			fail();
		}
	}
	
	
	@Test
	public void testComprarPorInternetImporteInvalido() {
		try {
			tcAna.comprarPorInternet(1234, -10);
		} catch (TarjetaBloqueadaException e) {
			fail();
		} catch (PinInvalidoException e) {
			fail();
		} catch (SaldoInsuficienteException e) {
			fail();
		} catch (ImporteInvalidoException e) {
			
		}
	}
	
	
	@Test
	public void testComprarPorInternetSaldoInsuficiente() {
		try {
			tcAna.comprarPorInternet(1234, 1000000);
		} catch (TarjetaBloqueadaException e) {
			fail();
		} catch (PinInvalidoException e) {
			fail();
		} catch (SaldoInsuficienteException e) {
			
		} catch (ImporteInvalidoException e) {
			fail();
		}
	}
	
	
	@Test
	public void testComprarImporteInvalido() {
		try {
			tcAna.comprar(1234, -10);
		} catch (ImporteInvalidoException e) {
			
		} catch (SaldoInsuficienteException e) {
			fail();
		} catch (TarjetaBloqueadaException e) {
			fail();
		} catch (PinInvalidoException e) {
			fail();
		}
	}
	
	
	@Test
	public void testComprarSaldoInsuficiente() {
		try {
			tcAna.comprar(1234, 1000000);
		} catch (ImporteInvalidoException e) {
			fail();
		} catch (SaldoInsuficienteException e) {
			
		} catch (TarjetaBloqueadaException e) {
			fail();
		} catch (PinInvalidoException e) {
			fail();
		}
	}
	
	
	@Test
	public void testGetCredito() {
		TarjetaCredito tc = new TarjetaCredito();
		double cred = 10.0;
		tc.setCredito(cred);
		assertTrue(tc.getCredito()==cred);
	}
	
	
	@Test
	public void testCambiarPinInvalido() {
		TarjetaCredito tc = new TarjetaCredito();
		tc.setPin(123);
		
		try {
			tc.cambiarPin(111, 333);
		} catch (PinInvalidoException e) {
			
		}
	}
		
	
	@Test
	public void testCambiarPin() {
		TarjetaCredito tc = new TarjetaCredito();
		int pn = tc.getPin();
		
		try {
			tc.cambiarPin(pn, 123);
		} catch (PinInvalidoException e) {
			fail();
		}
	}
	
	
	@Test
	public void testCompraPinInvalido() {
		TarjetaCredito tc = new TarjetaCredito();
		tc.setPin(123);
		
		try {
			tc.comprar(321, 10);
		} catch (ImporteInvalidoException e) {
			fail();
		} catch (SaldoInsuficienteException e) {
			fail();
		} catch (TarjetaBloqueadaException e) {
			fail();
		} catch (PinInvalidoException e) {
			
		}
	
	}
	
	
	
}
