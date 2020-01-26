import static org.junit.Assert.*;

import org.junit.Test;

public class QueueTest {
	Queue<String> q = new Queue<>();
	
	@Test
	public void emptyTest() {
		assertEquals(null, q.dequeue());
		assertEquals(null, q.peek());
		assertEquals(0, q.size());
		assertEquals(true, q.isEmpty());
	}

	@Test
	public void enqueueTest() {
		q.enqueue("0"); q.enqueue("1"); q.enqueue("2");
		assertEquals("[0 -> 1 -> 2]", q.toString());
	}
	
	@Test
	public void dequeueTest() {
		q.clear();
		q.enqueue("0"); q.enqueue("1"); q.enqueue("2");
		
		assertEquals("0", q.dequeue());
		assertEquals("1", q.dequeue());
		assertEquals("2", q.dequeue());
		assertEquals(null, q.dequeue());
	}
	
	@Test
	public void sizeTest() {
		q.clear();
		
		assertEquals(0, q.size());
		q.enqueue("0"); q.enqueue("0");
		assertEquals(2, q.size());
		q.enqueue("0"); 
		assertEquals(3, q.size());
		q.dequeue();
		assertEquals(2, q.size());
	}
	
	@Test
	public void peekTest() {
		q.enqueue("0");
		assertEquals("0", q.peek());
		assertEquals("0", q.peek());
		q.enqueue("5"); 
		assertEquals("0", q.peek());
		q.enqueue("A"); q.enqueue("B");
		assertEquals("0", q.peek());
		q.dequeue();
		assertEquals("5", q.peek());
		q.dequeue(); q.dequeue();
		assertEquals("B", q.peek());
	}
	
	@Test
	public void miscTest() {
		q.clear();
		q.enqueue("A"); q.enqueue("B"); q.enqueue("C"); 
		assertEquals("A", q.peek());
		assertEquals("A", q.dequeue());
		
		q.enqueue("1"); q.enqueue("2"); q.enqueue("3");
		assertEquals("B", q.dequeue());
		assertEquals("C", q.dequeue());
		assertEquals("1", q.dequeue());
		assertEquals("2", q.dequeue());
		assertEquals("3", q.dequeue());
		assertEquals(null, q.dequeue());
	}
}
