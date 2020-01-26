import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class DoublyLinkedListTests {

	@Test
	public void toStringTest() {
		String expected = "[\"Hey\", \"Hello\", \"World\", \"this\", \"is\", \"my\", \"custom\", \"DoublyLinkedList\", \"!\"]";
		DoublyLinkedList<String> list = new DoublyLinkedList<String>();
		list.add("Hey"); list.add("Hello"); list.add("World"); list.add("this"); list.add("is"); list.add("my"); list.add("custom"); list.add("DoublyLinkedList"); list.add("!"); 
		assertEquals(expected, list.toString());
	}
	
	@Test
	public void addFirstTest() {
		String expected = "[\"Hey\", \"Hello\", \"World\", \"this\", \"is\", \"my\", \"custom\", \"DoublyLinkedList\", \"!\"]";
		DoublyLinkedList<String> list = new DoublyLinkedList<String>();
		list.add("Hello"); list.add("World"); list.add("this"); list.add("is"); list.add("my"); list.add("custom"); list.add("DoublyLinkedList"); list.add("!"); 
		
		list.addFirst("Hey");
		assertEquals(expected, list.toString());
	}
	
	@Test
	public void removeAllTest() throws OutOfBoundsException {
		DoublyLinkedList<String> list = new DoublyLinkedList<String>();
		
		assertEquals("[]", list.toString());
		
		list.add("Hey"); list.add("Hello"); list.add("World"); list.add("this"); list.add("is"); list.add("my"); list.add("custom"); list.add("DoublyLinkedList"); list.add("!");
		String expected = "[\"Hello\", \"World\", \"this\", \"is\", \"my\", \"custom\", \"DoublyLinkedList\", \"!\"]";
		list.remove(0);
		assertEquals(expected, list.toString());
		
		expected = "[\"Hello\", \"World\", \"this\", \"is\", \"my\", \"custom\", \"DoublyLinkedList\"]";
		list.remove(7);
		assertEquals(expected, list.toString());

		expected = "[\"Hello\", \"World\", \"this\", \"my\", \"custom\", \"DoublyLinkedList\"]";
		list.remove(3);
		assertEquals(expected, list.toString());
	}
	
	Exception e1 = new OutOfBoundsException("Index is higher than then number of elements in list");
	Exception e2 = new OutOfBoundsException("List starts at zero, no negative indecies supported");
	@Test(expected = OutOfBoundsException.class)
	public void exceptionTest1() throws OutOfBoundsException {
		DoublyLinkedList<String> list = new DoublyLinkedList<String>();
		list.remove(5);
	}
	@Test(expected = OutOfBoundsException.class)
	public void exceptionTest2() throws OutOfBoundsException {
		DoublyLinkedList<String> list = new DoublyLinkedList<String>();
		list.remove(-1);
	}
	@Test(expected = OutOfBoundsException.class)
	public void exceptionTest3() throws OutOfBoundsException {
		DoublyLinkedList<String> list = new DoublyLinkedList<String>();
		list.add("Hey"); list.add("Hello"); list.add("World"); list.add("this"); list.add("is"); list.add("my"); list.add("custom"); list.add("DoublyLinkedList"); list.add("!");
		list.remove(50);
	}
	@Test(expected = OutOfBoundsException.class)
	public void exceptionTest4() throws OutOfBoundsException {
		DoublyLinkedList<String> list = new DoublyLinkedList<String>();
		list.add("Hey"); list.add("Hello"); list.add("World"); list.add("this"); list.add("is"); list.add("my"); list.add("custom"); list.add("DoublyLinkedList"); list.add("!");
		list.clear();
		list.remove(0);
	}
	
	

	@Test
	public void containsTest() {
		DoublyLinkedList<String> list = new DoublyLinkedList<String>();
		assertEquals(false, list.contains(""));
		
		list.add("Hey"); list.add("Hello"); list.add("World"); list.add("this"); list.add("is"); list.add("my"); list.add("custom"); list.add("DoublyLinkedList"); list.add("!");
		
		assertEquals(true, list.contains("Hello"));
		assertEquals(false, list.contains("Halo"));
	}
	
	@Test
	public void sizeTest() {
		DoublyLinkedList<String> list = new DoublyLinkedList<String>();
		assertEquals(0, list.size());

		list.add("Hey"); list.add("Hello"); list.add("World"); list.add("this"); list.add("is"); list.add("my"); list.add("custom"); list.add("DoublyLinkedList"); list.add("!");
		assertEquals(9, list.size());
	}
	
	@Test
	public void clearTest() {
		DoublyLinkedList<String> list = new DoublyLinkedList<String>();
		list.clear();
		assertEquals(0, list.size());
		assertEquals("[]", list.toString());

		list.add("Hey"); list.add("Hello"); list.add("World"); list.add("this"); list.add("is"); list.add("my"); list.add("custom"); list.add("DoublyLinkedList"); list.add("!");
		list.clear();
		assertEquals(0, list.size());
		assertEquals("[]", list.toString());
		
	}
	
	@Test
	public void replaceOne() {
		DoublyLinkedList<String> list = new DoublyLinkedList<String>();
		list.add("A"); list.add("A");list.add("A");list.add("A");list.add("A");list.add("A");list.add("A");list.add("A");
		String expected = "[\"B\", \"A\", \"A\", \"A\", \"A\", \"A\", \"A\", \"A\"]";
		list.replaceFistX("A", "B", 1);
		assertEquals(expected, list.toString());
	}
	
	@Test
	public void replaceTwo() {
		DoublyLinkedList<String> list = new DoublyLinkedList<String>();
		list.add("A"); list.add("A");list.add("A");list.add("A");list.add("A");list.add("A");list.add("A");list.add("A");
		String expected = "[\"B\", \"B\", \"A\", \"A\", \"A\", \"A\", \"A\", \"A\"]";
		list.replaceFistX("A", "B", 2);
		assertEquals(expected, list.toString());
		
		list.clear();
		
		list.add("C"); list.add("A");list.add("F");list.add("A");list.add("B");list.add("A");list.add("A");list.add("A");
		String expected2 = "[\"C\", \"B\", \"F\", \"B\", \"B\", \"A\", \"A\", \"A\"]";
		list.replaceFistX("A", "B", 2);
		assertEquals(expected2, list.toString());

		list.clear();
		
		list.add("C"); list.add("1");list.add("9");list.add("B");list.add("B");list.add("B");list.add("B");list.add("A");
		String expected3 = "[\"C\", \"1\", \"9\", \"B\", \"B\", \"B\", \"B\", \"B\"]";
		list.replaceFistX("A", "B", 2);
		assertEquals(expected3, list.toString());

		list.clear();
		
		list.add("C"); list.add("1");list.add("9");list.add("B");list.add("B");list.add("A");list.add("B");list.add("A");
		String expected4 = "[\"C\", \"1\", \"9\", \"B\", \"B\", \"B\", \"B\", \"B\"]";
		list.replaceFistX("A", "B", 2);
		assertEquals(expected4, list.toString());
		
	}
	
	@Test
	public void replaceAll() throws OutOfBoundsException {
		DoublyLinkedList<String> list = new DoublyLinkedList<String>();
	
		list.add("C"); list.add("1");list.add("9");list.add("B");list.add("B");list.add("B");list.add("B");list.add("A");
		String expected3 = "[\"C\", \"1\", \"9\", \"*\", \"*\", \"*\", \"*\", \"A\"]";
		list.replaceAll("B", "*");
		assertEquals(expected3, list.toString());
		
		list.clear();
		
		list.add("C"); list.add("1");list.add("9");list.add("B");list.add("B");list.add("B");list.add("B");list.add("A");
		String expected1 = "[\"C\", \"1\", \"9\", \"B\", \"B\", \"B\", \"B\", \"A\"]";
		list.replaceAll("0", "*");
		assertEquals(expected1, list.toString());
		
	}
	
	@Test
	public void getIndeciesTest() throws OutOfBoundsException {
		DoublyLinkedList<String> list = new DoublyLinkedList<String>();
		
		list.add("C"); list.add("1");list.add("9");list.add("B");list.add("B");list.add("B");list.add("B");list.add("A");
		
		assertEquals(1, list.getIndexOf("1"));
		assertEquals(0, list.getIndexOf("C"));
		assertEquals(7, list.getIndexOf("A"));
		assertEquals(3, list.getIndexOf("B"));
	}
	
	@Test(expected = OutOfBoundsException.class)
	public void getIndeciesExceptionTest() throws OutOfBoundsException {
		DoublyLinkedList<String> list = new DoublyLinkedList<String>();
		
		list.add("C"); list.add("1");list.add("9");list.add("B");list.add("B");list.add("B");list.add("B");list.add("A");
		
		list.getIndexOf("*");
	}

}
