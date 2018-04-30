
public class Room {
	private String color;
	private int key;
	
	
	
	//CONSTRUCTOR
	public Room(String c) {
		color = c;
	}
	
	
	
	public void setColor(String c) {
		
		String[] colors = {"green", "pink","brown","blue","red","yellow","gold"};
		for(String e:colors) {
			if(c.toLowerCase() == e)
				this.color = c.toLowerCase();
		}
		
		
	}
	
	public String getColor() {
		return this.color;
	}
	
	public void setKey(int k) {
		this.key = k;
	}
	
	public int getKey() {
		return this.key;
	}

}
