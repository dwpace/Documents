import java.util.ArrayList;
import java.util.Random;

public class Maze {

	private String name;
	private int size = 0;
	private int maxSize = 0;
	private boolean hasExit;
	private Room workingRoom;
	private ArrayList<Room> instanceRooms = new ArrayList<Room>();

	public Maze(String name, int maxSize) {
		this.name = name;
		this.setMaxSize(maxSize);
	}

	public String getName() {
		return name;
	}

	public void generateMaze() {
		loop();// starts the loop for generating
	}

	public void loop() {
		Random random = new Random();
		while (true) {// constantly looping.
			if (getSize() >= getMaxSize()) {// if the size is greater or equal to the max we need to create an exit
				workingRoom.establishGameExit();// lets create an exit for the last working room.
				// System.out.println("yeye");
				return;
			} else if (keepBuilding()) {// if the exit hasnt been set, we should be generating rooms and their exits.
				if (workingRoom == null)// checks if working room is assigned
					workingRoom = createRoom();// if not lets create a new room with a random name, if size is 0 we need
												// to create entrance
				for (int i = 0; i <= random.nextInt(2); i++) {// this is the most evil thing i've ever done, I want
																// rooms with 4 doors and dead ends.
					Room[] newRoomExits = workingRoom.createRoomExits();// randomly assigns new rooms as the working
																		// room's
																		// exits. I need to use these exits to continue
																		// building.
					workingRoom = selectRandomExit(newRoomExits);// lets select a random exit to build off of, this
																	// leaves
				} // dead ends and 1 rabbit hole
			}
		}
	}

	private Room selectRandomExit(Room[] rooms) {
		Random random = new Random();
		Room roomNorth = null, roomSouth = null, roomEast = null, roomWest = null;
		ArrayList<Room> nonNull = new ArrayList<Room>();
		roomNorth = rooms[0];
		roomSouth = rooms[1];
		roomEast = rooms[2];
		roomWest = rooms[3];
		if (roomNorth != null)
			nonNull.add(roomNorth);
		if (roomSouth != null)
			nonNull.add(roomSouth);
		if (roomEast != null)
			nonNull.add(roomEast);
		if (roomWest != null)
			nonNull.add(roomWest);
		return nonNull.get(random.nextInt(nonNull.size()));// returns a random nonnull room. SOUTH SHOULD BE THE ONLY
															// NONNULL FOR ENTRANCE

	}

	private boolean keepBuilding() {
		// if the random rooms dont fill the dungeon, we're gonna recall the properties,
		// it'll just keep adding to one another till its full.

		if ((getSize() < getMaxSize() && !hasExit())) {
			if (Player.DEBUG)
				System.out.println("Current maze isnt filled and the exit hasnt been set! KEEP BUILDING");
			return true;
		}

		return false;
	}

	public ArrayList<Room> getInstanceRooms() {
		return instanceRooms;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getMaxSize() {
		return maxSize;
	}

	public void setMaxSize(int maxSize) {
		this.maxSize = maxSize;

	}

	public boolean hasExit() {
		return hasExit;
	}

	public void setHasExit(boolean hasExit) {
		this.hasExit = hasExit;
	}

	public Room createRoom() {
		String randomRoom = generateName();
		Room newRoom = null;
		if (getSize() == 0) {
			newRoom = new Room(Constants.NAME[0], Constants.DESCRIPTION[0], this);// creates start room
			Player.setCurrentRoom(newRoom);// player needs to be assigned somewhere
		} else// if size isnt 0 create random
			newRoom = new Room(randomRoom, findDescription(randomRoom), this);
		setSize((getSize() + 1));// ups the size by 1
		getInstanceRooms().add(newRoom);// adds room to array
		if (Player.DEBUG)
			System.out.println("Current amount of rooms: " + getSize());
		return newRoom;// returns the new room for further handling
	}

	public String generateName() {
		String name = pickName();
		if (Player.DEBUG)
			System.out.println("Picked name: " + name);
		while (roomExists(name) || name.equals("Exit")) {
			if (Player.DEBUG)
				System.out.println("" + name + " already exists, trying again");
			name = pickName();
			if (avaliableRoomCount() <= 0) {
				name = "Filler Room";
				break;
			}
		}
		if (Player.DEBUG)
			System.out.println("Settled with " + name);
		return name;
	}

	public String findDescription(String roomName) {
		for (int i = 0; i < Constants.NAME.length; i++) {
			if (Constants.NAME[i].equals(roomName))
				return Constants.DESCRIPTION[i];
		}
		return "Couldn't find room description?";
	}

	public Room findRoom(String name) {
		Room currentRoom = null;
		for (int i = 0; i < getInstanceRooms().size(); i++) {
			 currentRoom = getInstanceRooms().get(i);
			if (currentRoom.getName().equals(name))
				return currentRoom;
		}
		return currentRoom;
	}

	private String pickName() {
		Random num = new Random();
		String randomName = Constants.NAME[num.nextInt(Constants.NAME.length)];// picks random name.
		return randomName;
	}

	private int avaliableRoomCount() {
		int rooms = Constants.NAME.length - 1;

		for (int i = 0; i < Constants.NAME.length; i++) {
			if (roomExists(Constants.NAME[i])) {
				rooms--;
			}
		}
		if (Player.DEBUG)
			System.out.println("Avalible rooms left: " + rooms);
		return rooms;
	}

	private boolean roomExists(String name) {
		for (int i = 0; i < getInstanceRooms().size(); i++) {// iterate current rooms and see if name
																// exists
			Room currentRoom = getInstanceRooms().get(i);
			if (currentRoom.getName().equals(name)) // if name exists
				return true;
		}
		return false;
	}

}
