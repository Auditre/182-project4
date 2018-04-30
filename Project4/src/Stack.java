
public class Stack {
	
	private Room[] rooms = new Room[10];
	private int top = -1;
	
	
	
	
	public void push(Room r) {
		if(rooms[9] != null)
			System.out.println("The stack is full!!!");
		else 
			rooms[++top] = r;
	}
	
	
	public Room pop() {
		Room r = rooms[top];
		rooms[top] = null;
		top--;
//		r = rooms[top];
		return r;
	}
	
	
	public Room peek() {
		return rooms[top];
	}
	
	
	public boolean empty() {
		if(rooms[0] == null)
			return true;
		else
			return false;
	}

	
	public Room getRoom(int i) {
		return rooms[i];
		
	}
	
	public int getTop() {
		return top;
	}
}
