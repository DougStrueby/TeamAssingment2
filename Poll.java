package model;
/**
 * The Poll class imports parties into a poll 
 * and prepares them to be placed into a poll list
 * 
 * @author Doug Strueby
 * @UCID 30122048
 * @lecture 01
 *
 *Updated 2021-12-05 by @author ScottSalmon to include setPollName() method
 *
 */

public class Poll {
	
	private String name;
	private Party[] parties;
	private int partiesInPoll;

   /* 
	* Constructor that creates a poll which contains the name of the poll
	*  and the amount of parties in the poll
	* 
	* @param aName This is the first parameter used in the constructor
	* @param pp This is the second parameter used in the constructor
	*
	*/ 
	
	public Poll(String aName, int pp) {
		
		this.name = aName;
		
		if(pp <= 0) {
			pp = 10;
		}
		
		this.parties = new Party[pp];
		this.partiesInPoll = 0;
	}
	
	/*
	 * Method that adds a inputed party to the Poll
	 * 
	 * @param aParty The first parameter to the addParty method
	 * 
	 */
	
	public void addParty(Party aParty){
		
		int size = getNumberOfParties();
		
		for(int i = 0; i < size; i++) {
			if(aParty.getName().equalsIgnoreCase(parties[i].getName())) {
				parties[i] = aParty;
				return;
			}
		}
		
		if(aParty == null) {
			System.out.println("Error : Variable is null");
		}
		
		if(size == parties.length) {
			System.out.println("Error : Poll is full no further parties can be added");
			return;
		}
		
		parties[size] = aParty;
		partiesInPoll ++;
	}
	
	/*
	 * Gets the specified party
	 * 
	 * @param name This parameter is the name of the party we want to get
	 * @return parties[i] This returns the party that we want to get
	 * 
	 */
	
	public Party getParty(String name){
		for(int i = 0; i != partiesInPoll; i++) {
			if(parties[i].getName().equalsIgnoreCase(name)) {
				return parties[i];
			}
		}
		
		return null;
	}
	
	/*
	 * Gets the list of parties
	 * 
	 * @return parties This returns all the parties added to the poll
	 * 
	 */
	public Party[] getParties() {
		return parties;
	}
	
	/*
	 *  prints a visual representation of how many seats are needed
	 *  to get the majority
	 *  
	 *  @param maxStars This is the first parameter of the method
	 *  @param numOfSeatsPerStar This is the second parameter of the method
	 *  @return result This returns the maxStars and numOfSeatsPerStar under the textVisualizationBySeats method
	 *  
	 */
	
	public String textVisualizationBySeats(int maxStars, double numOfSeatsPerStar) {
	
		String result = name + "\n";
		
		if(partiesInPoll > 0) {
			for(Party p : this.parties) {
				if(p == null) {
					System.out.println("Error : Variable is null");
					break;
			}
			
				result += p.textVisualizationBySeats(maxStars, numOfSeatsPerStar) + "\n";
		}
	}
		
		return result;
}
	
	/*
	 * prints a visual representation the percentage of vote needed
	 * to get the majority
	 *  
	 *  @param maxStars This is the first parameter of the method
	 *  @param percentOfVotesPerStar This is the second parameter of the method
	 *  @return result This returns the maxStars and percentOfVotesPerStar under the textVisualizationByVotes method
	 *  
	 */
	
	
	public String textVisualizationByVotes(int maxStars, double percentOfVotesPerStar) {
		
		String result = name + "\n";
		
		if(partiesInPoll > 0) {
			for(Party p : this.parties) {
				if(p == null) {
					System.out.println("Error : Variable is null");
					break;
			}
			
				result += p.textVisualizationByVotes(maxStars, percentOfVotesPerStar) + "\n";
		}
	}
		
		return result;
}
	
	/*
	 *  Allows the poll and all of the parties within the poll to be printed
	 *  
	 *  @return result This returns the list of parties in string form
	 */
	
	public String toString() {
		
		String result = name + "\n";
			for(Party p : parties) {
				if(p == null) {
					System.out.println("Error : Variable is null");
					break;
			}
			
				result += p.toString() + "\n";
		}
		
			return result;
	}
	
	/*
	 * Gets the list of parties
	 * 
	 * @return name This returns the poll name
	 * 
	 */
	public String getPollName() {
		return name;
	}
	/* "setter" method to change the name of the poll
	 * 
	 * @param String newName this string is what will be set as the new poll name
	 */
	public void setPollName(String newName) {
		this.name = newName;
	}

	/*
	 * Gets the list of parties
	 * 
	 * @return name This returns the number of parties in the poll
	 * 
	 */
	public int getNumberOfParties() {
		return partiesInPoll;
	}
			
}
