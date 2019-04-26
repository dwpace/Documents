
public class Constants {
	//I know I could have made a 2d array, didnt think to do it at first.
	public static String[] NAME = { 
			"Entrance",
			"Bedroom",
			"Dinning Room",
			"Torture Room",
			"Bathroom",
			"Egg room",
			"Dragon Room",
			"Goblin Room",
			"Trophy Room",
			"Dungeon Room",
			"Take me to church",
			"Treasure Room",
			"Yeehaw",
			"Virtual Pet",
			"Random Room",
			"Lab Room",
			"Filler Room",
			"Exit"};
	public static String[] DESCRIPTION = { 
			"You are currently back at the entrance.", 
			"You wander into a bedroom, after looking around you notice some dust on the furniture.", 
			"You twist the door knob and walk into a nice looking dining room, this is an awful pretty maze.",
			"Maybe this door wasn't the best choice afterall, it looks like some sort of torture room.",
			"You walk into the restroom.. it's pretty torn up with a lot of holes in the walls.", 
			"You find a room filled with.. eggs? I wonder what they're for..",
			"RAWRRRRRRRRRRRRRRRRRRRR! You get attacked... Oh wait, nevermind he's friendly.",
			"You run into a bunch of Goblins! Oh no!!", 
			"You walk into a room filled with trophies. The pictures on the walls have eyes that follow your every move.",
			"You stumble into a weird, poorly lit, cobweb infested, epidemically contagious room of people yawning.",
			"You walk into a huge chapel area, looks satanic.",
			"Horrey Crap! There's a million tons of gold here! Now to try and smuggle these bad boys out. Uhh which way is that again?",
			"You found some horses in the back! Where's Miley Cyrus?",
			"Oh hey! Its that kamkatchee pet thing I lost about 20 years ago.",
			"You come across this really random looking room. (I'm running out of ideas to be honest)", 
			"Oh cool, looks like a chemistry lab is in here, I wonder if I can mix things up for later.",
			"There is not much interesting in this room.",
			"Congradulations, you found the exit! Good job! Now do it again! Try a harder difficulty next time!"};
	
	public static void exacuteEvents(String roomName, Maze maze) {
		switch(roomName.toLowerCase()) {//I'm sorry for this, doing this out of convenience 
		case "dragon room":
		//	Player.setCurrentRoom(maze.findRoom("Entrance"));
			break;
		}
	}

}
