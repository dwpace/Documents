import java.util.Scanner;

public class Player {
	public static int NORTH = 0;
	public static int SOUTH = 1;
	public static int EAST = 2;
	public static int WEST = 3;
	private static Room currentRoom;
	public static boolean DEBUG = true;
	private static boolean menuOptions = true;

	public static void main(String args[]) {
		String[] dialogue = { "This is a Maze simulator. Every Maze is unique! See if you can find the exit!", "", "Select a difficulty:", "[1] Easy - 5 Rooms", "[2] Medium - 10 Rooms", "[3] Hard - 20 Rooms" };
		appendMessages(dialogue);
		handleInput();
	}

	private static void handleInput() {
		Scanner input = new Scanner(System.in);
		String dir = input.nextLine();
		if (menuOptions) {
			if(dir.equalsIgnoreCase("1")) {
				menuOptions = false;
				Maze maze = new Maze("Easy", 5);
				maze.generateMaze();
				String[] dialogue = { "You wake up in a strange place.", "After looking around.. There is a door south of you.",
						"Better try and find your way out of this maze!", "", "Select An Option:", "N : North", "S : South",
						"E : East", "W : West" };
				appendMessages(dialogue);
				handleInput();
			} else if(dir.equalsIgnoreCase("2")) {
				menuOptions = false;
				Maze maze = new Maze("Medium", 10);
				maze.generateMaze();
				String[] dialogue = { "You wake up in a strange place.", "After looking around.. There is a door south of you.",
						"Better try and find your way out of this maze!", "", "Select An Option:", "N : North", "S : South",
						"E : East", "W : West" };
				appendMessages(dialogue);
				handleInput();
			} else if(dir.equalsIgnoreCase("3")) {
				menuOptions = false;
				Maze maze = new Maze("Hard", 20);
				maze.generateMaze();
				String[] dialogue = { "You wake up in a strange place.", "After looking around.. There is a door south of you.",
						"Better try and find your way out of this maze!", "", "Select An Option:", "N : North", "S : South",
						"E : East", "W : West" };
				appendMessages(dialogue);
				handleInput();
			} else {
				System.out.println("Invalid input, please type '1' for Easy '2' for Medium '3' for Hard");
				handleInput();
			}
		} else {
			if (dir.equalsIgnoreCase("s")) {
				currentRoom.moveRoom(SOUTH);
				currentRoom.sendExits();
			} else if (dir.equalsIgnoreCase("n")) {
				currentRoom.moveRoom(NORTH);
				currentRoom.sendExits();
			} else if (dir.equalsIgnoreCase("w")) {
				currentRoom.moveRoom(WEST);
				currentRoom.sendExits();
			} else if (dir.equalsIgnoreCase("e")) {
				currentRoom.moveRoom(EAST);
				currentRoom.sendExits();
			} else if (dir.equalsIgnoreCase("q")) {
				System.exit(0);
			} else {
				System.out.println("Invalid input, try again");
			}
			sendPrompt();
		}
		input.close();
	}

	private static void sendPrompt() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String[] dialogue = { "Select An Option:", "N : North", "S : South", "E : East", "W : West", "Q : Quit" };
		appendMessages(dialogue);
		handleInput();
	}

	public static void appendMessages(String[] dialogue) {
		for (String message : dialogue) {
			System.out.println(message);
		}
	}

	public static Room getCurrentRoom() {
		return currentRoom;
	}

	public static void setCurrentRoom(Room currentRoom) {
		Player.currentRoom = currentRoom;
	}

}
