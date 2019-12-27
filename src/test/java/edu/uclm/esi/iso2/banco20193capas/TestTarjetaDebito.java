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
import edu.uclm.esi.iso2.banco20193capas.model.Manager;
import edu.uclm.esi.iso2.banco20193capas.model.MovimientoCuenta;
import edu.uclm.esi.iso2.banco20193capas.model.MovimientoTarjetaCredito;
import edu.uclm.esi.iso2.banco20193capas.model.Tarjeta;
import edu.uclm.esi.iso2.banco20193capas.model.TarjetaCredito;
import edu.uclm.esi.iso2.banco20193capas.model.TarjetaDebito;
import junit.framework.TestCase;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TestTarjetaDebito extends TestCase{

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
	public void testConfirmarCompraTokenInvalido() {
		
		try {
			tdAna.comprarPorInternet(1234, 10);
			try {
				tdAna.confirmarCompraPorInternet(0000);
			} catch (TokenInvalidoException e) {
				
			}
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
		try {
			tdAna.comprar(1234, 500);
		} catch (ImporteInvalidoException | SaldoInsuficienteException | TarjetaBloqueadaException
				| PinInvalidoException e) {
			fail();
		}
	}
	
	
	@Test
	public void testSacarDineroOK() {
		try {
			tdAna.sacarDinero(1234, 500);
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
			
		}
	}
}
