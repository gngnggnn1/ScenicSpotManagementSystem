package Car;
/*
 * 停车场栈（两个）
 */
public class Stack <E>{
	
	private Object[] array;
	private int currentSize;
	private int maxSize;
	
	public Stack(int size){
		this.array = new Object[size];
		this.maxSize = size;
		this.currentSize = -1;
	}
	
    public void push(E item) {
    	array[++currentSize] = item;
    }
	
    public boolean isEmpty(){
    	return currentSize==-1;
    }
    
    public boolean isFull(){
    	return currentSize==(maxSize-1);
    }
    
    
    public int getSize(){
    	return this.currentSize;
    }
    
	public E pop(){		
		return (E)array[currentSize--];
	}
}
