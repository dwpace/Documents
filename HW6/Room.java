

public class Room {
	private String name;
	private String description;

	private Maze maze;
	private Room northRoom;
	private Room southRoom;
	private Room westRoom;
	private Room eastRoom;

	public Room(String name, String description, Maze currentDungeon) {
		this.name = name;
		this.description = description;
		this.maze = currentDungeon;
	}

	public Room[] createRoomExits() {// returns an array of its exit rooms. I can build off these in maze.
		Room[] avaliableExits = new Room[4];
		if (!this.getName().equals("Entrance")) {
			if (northRoom == null) {// starts with north, checks if its null
				Room roomNorth = maze.createRoom();// lets create one if it is null
				setRoom(Player.NORTH, roomNorth);// sets the room north of the room we're in to the new one
				roomNorth.setRoom(Player.SOUTH, this);// sets the north room's south to this room object
				avaliableExits[0] = roomNorth;
			}
			if (southRoom == null) {// does the same as above here
				Room roomSouth = maze.createRoom();
				setRoom(Player.SOUTH, roomSouth);// sets this rooms south to the new south room
				roomSouth.setRoom(Player.NORTH, this);// set the new south's north to this room
				avaliableExits[1] = roomSouth;
			}
			if (eastRoom == null) {
				Room roomEast = maze.createRoom();
				setRoom(Player.EAST, roomEast);// set this rooms east to this new east
				roomEast.setRoom(Player.WEST, this);// sets the easts room to this room as west
				avaliableExits[2] = roomEast;

			}
			if (westRoom == null) {
				Room roomWest = maze.createRoom();
				setRoom(Player.WEST, roomWest);// sets this rooms west to new room
				roomWest.setRoom(Player.EAST, this);// sets west rooms east to this room
				avaliableExits[3] = roomWest;
			}
		} else {// if its an entrance, I only want to use south.
			if (southRoom == null) {
				Room roomSouth = maze.createRoom();
				setRoom(Player.SOUTH, roomSouth);
				roomSouth.setRoom(Player.NORTH, this);
				avaliableExits[1] = roomSouth;
			}
		}
		return avaliableExits;
	}

	public void moveRoom(int dir) {
		if (dir == Player.NORTH) {
			if (northRoom != null) {
				Player.setCurrentRoom(northRoom);
				System.out.println("You enter the North Door.");
				System.out.println("" + Player.getCurrentRoom().description);
			} else {
				System.out.println("There isn't a door to your north!");
			}
		} else if (dir == Player.SOUTH) {
			if (southRoom != null) {
				Player.setCurrentRoom(southRoom);
				System.out.println("You enter the South Door.");
				System.out.println("" + Player.getCurrentRoom().description);
			} else {
				System.out.println("There isn't a door to your south!");
			}
		} else if (dir == Player.EAST) {
			if (eastRoom != null) {
				Player.setCurrentRoom(eastRoom);
				System.out.println("You enter the East Door.");
				System.out.println("" + Player.getCurrentRoom().description);
			} else {
				System.out.println("There isn't a door to your EAST!");
			}
		} else if (dir == Player.WEST) {
			if (westRoom != null) {
				Player.setCurrentRoom(westRoom);
				System.out.println("You enter the West Door.");
				System.out.println("" + Player.getCurrentRoom().description);
			} else {
				System.out.println("There isn't a door to your West!");
			}
		}
	}

	public void sendExits() {
		handleSpecial();
		if (northRoom != null)
			System.out.println("There is a door to your north!");
		if (southRoom != null)
			System.out.println("There is a door to your south!");
		if (eastRoom != null)
			System.out.println("There is a door to your east!");
		if (westRoom != null)
			System.out.println("There is a door to your west!");
	}

	public void handleSpecial() {// mobs, extras, etc
		if (getName().equals("Exit")) {
			System.exit(0);
		}
		Constants.exacuteEvents(name, maze);
	}

	public void establishGameExit() {// creates the last room
		if (!maze.hasExit()) {// if there is already an exit we do not need another
			maze.setHasExit(true);// set this to true to say we tried
			Room exit = new Room("Exit", maze.findDescription("Exit"), maze);// creates a new exit
																				// room
			if (northRoom == null)// tries north
				setRoom(Player.NORTH, exit);
			else if (southRoom == null)// tries south
				setRoom(Player.SOUTH, exit);
			else if (eastRoom == null)// tries east
				setRoom(Player.EAST, exit);
			else if (westRoom == null)// tries west
				setRoom(Player.WEST, exit);
			else// if this room doesnt have any slots for an exit
				maze.setHasExit(false);// set the exit to false.
			if (Player.DEBUG)
				System.out.println("Generated Exit! Did it go through? " + maze.hasExit() + " ");
		}
	}

	public void setRoom(int dir, Room room) {
		if (dir == Player.NORTH)
			this.northRoom = room;
		else if (dir == Player.SOUTH)
			this.southRoom = room;
		else if (dir == Player.WEST)
			this.westRoom = room;
		else if (dir == Player.EAST)
			this.eastRoom = room;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

}
