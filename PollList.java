package model;
/** <h1>Poll List class</h1>
 * This class represents a collections of polls that 
 * all collect data from the same election
 * @author Nolan Ruzicki
 * 
 * *Updated 2021-12-05 by @author Scott Salmon to include setPollName(Poll, String) method
 * */

public class PollList {
	Poll[] polls;
	int numOfSeats;
	int MAX_STARS_FOR_VISUALIZATION = 18;
	
	
	/**this method reads the value of the variable polls
	 * and returns it So that polls can be used
	 * @return polls variable
	 * */
	
	public Poll[] toArray() {
		return polls;
	}
	
	/**this method reads the value of the variable numOfSeats
	 * and returns it so that numOfSeats can be used
	 * 
	 * @return variable numOfSeats
	 * */
	
	public int getNumOfSeats() {
		return numOfSeats;
	}
	
	/** This method does not return anything and takes an integer as an argument 
	 * that will update the value of numOfSeats
	 * 
	 * @param and integer will be set to be the new value of numOfSeats
	 * */
	public void setNumOfSeats(int newNumOfSeats) {
		this.numOfSeats = newNumOfSeats;
	}
	
	/**This constructor takes the number of seats that should be available in the election 
	 * as well as the the amount of polls that will be included in this list as a argument
	 * <p>
	 * The number of polls in this list should be at least 1 if not the constructor sets
	 * the number of polls to 5. The number of seats should also be at least 1 
	 * if not then the number of seats will be set to 10
	 * 
	 * @param integer number of polls giving that will be included in the list
	 * @param integer number of seat that are available in the election 
	 * */
	public PollList(int numOfPolls, int Seats) {
		if(numOfPolls < 1) polls = new Poll[5];
		
		else polls = new Poll[numOfPolls];
		
		if(Seats < 1) numOfSeats = 10;
		
		else numOfSeats = Seats;
		
	}
	
	/**This method does not return anything and takes an argument of type 
	 * Poll then checks the poll list for any empty spaces in the poll list
	 * and place the poll that was used as the argument in the first available place
	 * <p>
	 * if the argument is null then this method will print an error statement and if
	 * the list is full then it will print "this list if full and no further polls can be added"
	 * 
	 *  @param Poll that will be placed into the list
	 *  */
	int i = 0;
	public void addPoll(Poll inPoll) {
		if (inPoll == null) System.out.println("error null value was used");

		if(i < polls.length) polls[i++] = inPoll;

		else if (i++ == polls.length) System.out.println("the list is full and no further polls can be added. ");
	}
	
	/**This method returns a party that contains the average number of seats as well as the
	 * average percent of the votes that this party had received
	 * it takes a string as the argument and creates a new party object with this name
	 * the method then calculates the average number of seats won as well as the average percent of 
	 * votes
	 * 
	 * @param string this is the name of the party that the method will create
	 * @return the party that this method creates with the averages in it
	 * */
	public Party getAveragePartyData(String name) {
		float numOfSeats = 0;
		float percentOfVotes = 0;
		int numOfPolls = 0;
		for(int i = 0; i < polls.length; i++) {
			Party partyData = polls[i].getParty(name);
			if(partyData != null) { 
				float seats = partyData.getProjectedNumberOfSeats();
				float votes = partyData.getProjectedPercentageOfVotes();
				percentOfVotes += votes;
				numOfSeats += seats;
				numOfPolls++;
			}
		}
		float averageNumOfSeats = numOfSeats/numOfPolls;
		float averagePercentOfVotes = percentOfVotes/numOfPolls;
		if(Float.isNaN(averageNumOfSeats)) averageNumOfSeats = 0;
		if(Float.isNaN(averagePercentOfVotes)) averagePercentOfVotes = 0;
		Party parties = new Party(name, averageNumOfSeats, averagePercentOfVotes);
		
		return parties;
	}
	
	/** This method return a poll that represents the aggregate of all the polls in the list
	 * it takes an argument of a list of strings that contains all of the names of the parties in
	 * the poll
	 * 
	 * @param list of strings containing all the parties in the poll
	 * @return the aggregate of all the polls in the list
	 * */
	public Poll getAggregatePoll(String[] partyNames) {
		Poll aggregate = new Poll("Aggregate", partyNames.length);
		Party[] parties = aggregate.getParties();
		for(int i = 0; i < partyNames.length; i++) {
			parties[i] = getAveragePartyData(partyNames[i]);
		}
		return aggregate;	
	}
	
	/**This method will return a text visualization for each poll in the list by using seats
	 * 
	 * @return text visualization of the polls by using seats 
	 * */
	public String textVisualizationBySeats() {
		double seatsPerStar = Math.ceil((double)numOfSeats/MAX_STARS_FOR_VISUALIZATION);
		String visSeats = "";
		for(int i = 0; i < polls.length; i++) {
			Poll P = polls[i];
			if(P != null) {
				visSeats += P.textVisualizationBySeats(MAX_STARS_FOR_VISUALIZATION, seatsPerStar) + "\n";
			}
		}
		return visSeats;
	}
	/**This method will return a text visualization for each poll in the list by using votes 
	 *  
	 * @return text visualization of the polls by using votes 
	 * */
	public String textVisualizationByVotes() {
		double votesPerStar = Math.ceil(((double) 1/MAX_STARS_FOR_VISUALIZATION)*100);
		String visVotes = "";
		for(int i = 0; i < polls.length; i++) {
			Poll P = polls[i];
			if(P != null) {
				visVotes += P.textVisualizationByVotes(MAX_STARS_FOR_VISUALIZATION, votesPerStar) + "\n";
			}
		}
		return visVotes;
	}
	
	
	public String toString() {
		return "Number of seats: " + numOfSeats + "\n" + textVisualizationBySeats();
	}
	/*
	 * "Setter" method that allows user to change the poll name from one of the Polls in the
	 * poll list.
	 * 
	 * @param Poll originalPoll - This is the poll that you want to change the name of
	 * @param String newPollName - This string is what you want to change the pollname to
	 */
	public void setPollName(Poll originalPoll, String newPollName) {
		originalPoll.setPollName(newPollName);
	}
}



