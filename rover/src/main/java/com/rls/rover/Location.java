package com.rls.rover;

/**
* @author Robin Stone
* 
*  Models a coordinate and heading
*
*/
public final class Location {
	
	final int x;
	final int y;
	final Heading heading;
	
	public Location(String location) {
		super();
		String data[] = location.split(" ");
		if (data.length > 2) {
			this.x = Integer.parseInt(data[0]);
			this.y = Integer.parseInt(data[1]);
			this.heading = Enum.valueOf(Heading.class, data[2]);
		}
		else {
			this.x = Integer.parseInt(data[0]);
			this.y = Integer.parseInt(data[1]);
			this.heading = Heading.N;
		}
	}

	public Location(int x, int y, Heading heading) {
		super();
		this.x = x;
		this.y = y;
		this.heading = heading;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((heading == null) ? 0 : heading.hashCode());
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Location other = (Location) obj;
		if (heading != other.heading)
			return false;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Heading getHeading() {
		return heading;
	}

	@Override
	public String toString() {
		return  x + " " + y + " " + heading.name();
	}
	
	

}
