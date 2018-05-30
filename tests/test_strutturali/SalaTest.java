package test_strutturali;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import myclasses.Sala;

public class SalaTest {

	Sala s;

	int cinemaRoom = 2;
	int rows = 10;
	int columns = 10;
	int capacity = rows*columns;				

	@Before
	public void setUp() throws Exception { 
		s = new Sala(cinemaRoom, rows, columns);
	}

	@After
	public void tearDown() throws Exception {
		s = null;
	}

	@Test
	public void testSala() {
		assertNotNull(s);
	}

	@Test
	public void testGetCinemaRoom() {
		assertEquals(s.getCinemaRoom(),cinemaRoom);
	}

	@Test
	public void testGetCapacity() {
		assertEquals(s.getCapacity(),capacity);
	}

	@Test
	public void testGetRows() {
		assertEquals(s.getRows(),rows);
	}

	@Test
	public void testGetColumns() {
		assertEquals(s.getColumns(),columns);
	}

}
