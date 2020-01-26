import static org.junit.Assert.*;

import org.junit.Test;

public class StackTest {
	Stack<String> stack = new Stack<>();
	
	@Test
	public void pushTest() {
		stack.push("A");
		stack.push("B");
		stack.push("C");
		stack.push("D");
		stack.push("E");
	}

	@Test
	public void popTest() {
		stack.push("A");
		stack.push("B");
		stack.push("C");
		stack.push("D");
		stack.push("E");
		assertEquals("E", stack.pop());
		assertEquals("D", stack.pop());
		assertEquals("C", stack.pop());
		assertEquals("B", stack.pop());
		assertEquals("A", stack.pop());
		// now its empty
		assertEquals(null, stack.pop());
	}	

	@Test
	public void sizeTest() {
		assertEquals(0, stack.size());
		stack.push("1");
		assertEquals(1, stack.size());
		stack.push("2");
		stack.push("3");
		stack.push("4");
		assertEquals(4, stack.size());
	}
	
	@Test
	public void isEmptyTest() {
		stack.clear();
		assertEquals(true, stack.isEmpty());
		stack.push("0");
		assertEquals(false, stack.isEmpty());
		stack.pop(); stack.pop(); stack.pop(); stack.pop(); stack.pop(); stack.pop(); stack.pop(); 
		assertEquals(true, stack.isEmpty());
	}

	@Test
	public void peekTest() {
		assertEquals(null, stack.peek());
		stack.push("A");
		assertEquals("A", stack.peek());
		stack.push("B");
		stack.push("C");
		stack.push("D");
		stack.push("E");
		assertEquals("E", stack.peek());
		assertEquals("E", stack.peek());
		assertEquals("E", stack.peek());
	}

	@Test
	public void toStringTest() {
		
	}

	@Test
	public void miscTest() {
		stack.clear();
		assertEquals(true, stack.isEmpty());
		assertEquals(null, stack.peek());
		stack.pop();
		stack.push("A");
		assertEquals("A", stack.peek());
		assertEquals(false, stack.isEmpty());
		stack.push("B"); stack.push("C");
		assertEquals("C", stack.peek());
		assertEquals("C", stack.pop());
		assertEquals("B", stack.peek());
		stack.push("X"); stack.push("Y");
		assertEquals("Y", stack.peek());
		assertEquals("Y", stack.pop());
		assertEquals("X", stack.pop());
		assertEquals("B", stack.pop());
		assertEquals("A", stack.pop());
		assertEquals(null, stack.pop());
	}

}
