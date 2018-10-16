package by.iba.training;

import java.util.Date;
import java.util.LinkedList;
import java.util.ListIterator;


public class TimeoutQueue<T> {
	private LinkedList<Elem> queue = new LinkedList<Elem>();
	
	private class Elem<T> {
		private long time;
		private T item;
		public Elem(T o, long mills) {
			item =o;
			time = new Date().getTime() + mills;
		}
		public long getTime() {
			return time;
		}
		public T getItem() {
			return item;
		}
		
		
	}
	

	public static void main(String[] args) {
		
		TimeoutQueue<String> timequ= new TimeoutQueue<>();
		timequ.offer("abc",100);
		timequ.offer("show1", 700);
		timequ.offer("show2", 1200);
		timequ.offer("show3", 2000);
		
		System.out.println(timequ.size());
		System.out.println();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
	
			e.printStackTrace();
		}
		System.out.println(timequ.size());
		System.out.println();
		System.out.println(timequ.poll());
		System.out.println(timequ.poll());
		

	}
	
	public void offer(T o, long mills) {
		Elem newItem = new Elem(o, mills);
		queue.add(newItem);
	}
	
	public T poll() {
		if(queue.size() == 0) return null;
		
		Elem check = queue.get(0);
		Date delete = new Date(check.getTime());
		if(new Date().after(delete)) {
			queue.remove(check);
			return poll();
		}
		else {
			queue.remove(check);
			return (T) check.getItem();
		}
		
	
	}
	
	
	public int size() {
		int size =0;
		for(Elem el: queue) {
			if(new Date().before(new Date(el.getTime()))) {
				size++;
			}
		}
		return size;
	}
	

}
