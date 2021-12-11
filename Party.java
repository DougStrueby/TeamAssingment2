package model;


/**
 * The Party Class allows the user to create a "Political Party". This party can be assigned a name and colour, and it can also be assigned 
 * the projected amount of seats or votes the party will receive. Finally, a method that shows a visual representation + text representation
 * of this information is also included in this class.  
 * 
 * @author Scott Salmon UCID 30093320
 * @since 2021-10-30
 * 
 * Updated 2021-12-05 by @author Scott Salmon to change from java.awt.Color class for colors 
 * to javafx.scene.paint.Color class for colors.
 */

import javafx.scene.paint.Color;

public class Party {

private String name;
private float projectedNumberOfSeats;
private float projectedPercentageOfVotes;
private Color partyColour;

/**
 * This is a "getter" method for our political party name.
 * 
 * @return String variable of the name of the Political Party. Returns null if no name has been set.
 */

public String getName() {
	return name;
}

/**
 * This is a "setter" method for our political party name. 
 * 
 * @param 	name 	String variable of whatever you would like to name the Political Party.
 */
private void setName(String name) {
	this.name = name;
}

/**
 * This is a "getter" method for how many Seats are projected to be won by the Political Party.
 * 
 * @return float variable of projected seats won. Returns 0.0f if no projected number of seats has been set.
 */
public float getProjectedNumberOfSeats() {
	return projectedNumberOfSeats;
}

/**
 * This is a "setter" method for how many Seats are projected to be won by the Political Party.
 * 
 * @param	projectedNumberOfSeats	float variable of how many Seats are projected to be won. If a negative value, the amount of seats projected
 * to be won is unchanged, and an Error code is printed instead.
 */
public void setProjectedNumberOfSeats(float projectedNumberOfSeats) {
	if (projectedNumberOfSeats >=0) { 	//detects if an error occurred, and prints a message if one did.
		this.projectedNumberOfSeats = projectedNumberOfSeats;
	} else {
		System.out.println("Error: setProjectedNumberOfSeats() method. Number of Seats must be equal to or greater then 0.");
	}
}
/**
 * This is a "getter" method for the projected percentage of votes towards the Political Party.
 * 
 * @return float variable of projected percentage of votes. Returns 0.0f if no projected percentage of votes has been set.
 */
public float getProjectedPercentageOfVotes() {
	return projectedPercentageOfVotes;
}

/**
 * This is a "setter" method for the projected percentage of votes towards the Political Party.
 * 
 * @param 	projectedPercentageOfVotes	float variable of projected percentage of votes towards Political Party. If lower then 0 or greater then one,
 * the projected vote percentage is unchanged and an Error code is printed instead.
 */
public void setProjectedPercentageOfVotes(float projectedPercentageOfVotes) {
	if (projectedPercentageOfVotes >= 0.0 && projectedPercentageOfVotes <= 100) { //detects if an error occurred, and prints a message if one did.
		this.projectedPercentageOfVotes = projectedPercentageOfVotes/100;
	} 
	
}

/**
 * This is a "getter" method for the colour of the Political Party.
 * 
 * @return color variable representing currently assigned colour of the Political Party. If no colour has been set, returns null instead.
 */
public Color getColour() {
	return partyColour;
}
/**
 * This is a "setter" method for the colour of the Poltical Party.
 * 
 * @param 	partyColours2	color variable of whatever color you want to assign to the Political Party.
 */
public void setColour(Color partyColours2) {
	this.partyColour = partyColours2;
}

/**
 * Allows you to set the name of a Political Party.
 * 
 * @param 	partyname	String variable of whatever you would like to name the Political Party.
 */
public Party(String partyname) {
	this.setName(partyname);
}

/**
 * Allows you to set the Political Parties name, the projected amount of seats to be won, and the projected percentage of the
 * vote the party is going to receive.
 * 
 * @param 	partyname	String variable of whatever you would like to name the Political Party.
 * @param 	projectedSeats	float variable of how many Seats are projected to be won. If a negative value, the amount of seats projected
 * to be won is unchanged, and an Error code is printed instead.
 * @param 	projectedPercentage	float variable of projected percentage of votes towards Political Party. If lower then 0 or greater then one,
 * the projected vote percentage is unchanged and an Error code is printed instead.
 */
public Party(String partyname, float projectedSeats, float projectedPercentage) {

	if (projectedSeats >= 0) {
		this.setProjectedNumberOfSeats(projectedSeats);
	} else {
		System.out.println("Error: Party(String, float, float) method. Number of Seats must be equal to or greater then 0.");
	}
	
	if (projectedPercentage >= 0 && projectedPercentage <= 1) { //detects if an error occurred, and prints a message if one did.
		this.setProjectedPercentageOfVotes(projectedPercentage);
	} else {
		System.out.println("Error: Party(String, float, float) method. Percentage must be equal to or greater then zero, and "
				+ "lower then or equal to one.");
	}
	this.setName(partyname);
}

/**
 * This method returns a String with information about the Party and their projected results from the election.
 * 
 * @return	results		String variable of the election results, in the form: <name>([<colour values>], <projected % of votes>, 
 * <projected seats> seats). If no colour has been assigned to the party, it is in this form instead: <name>(<projected % of votes>, 
 * <projected seats> seats).
 */
public String toString() {
	String results;
	String name = getName();
	int percentage = (int)(getProjectedPercentageOfVotes() * 100);	//converts percentage from decimal to a whole number
	float seats = getProjectedNumberOfSeats();
	
	if (partyColour != null) {
		int red = (int) partyColour.getRed();		//these get___() commands get the RGB values of the assigned colours of the Political Party.
		int green = (int) partyColour.getGreen();
		int blue = (int) partyColour.getBlue();
		results = name + " ([" + red +"," + green +"," + blue + "], " + percentage + "% of votes, "+ seats + " seats)";
	} else {
		results = name + " (" + percentage + "% of votes, " + seats + " seats)";
	}
	return results;
	
}

/**
 * This method allows the user to get the projected percentage of Seats won for the Political Party.
 * 
 * @param 	TotalSeats	int variable that represents the total seats that can be won. Must be greater then 0, otherwise the 
 * calculation is not done and an error code is printed instead. This is because you cannot divide by zero, and there must 
 * be a positive amount of seats.
 * @return	percentage	double variable that represents the projected percentage of Seats won.
 */
public double projectedPercentOfSeats(int TotalSeats) {
	float copyProjectedSeats = projectedNumberOfSeats;
	double percentage = 0.0d;
	if (TotalSeats > 0) { //detects if an error occurred, and prints a message if one did.
		percentage = (copyProjectedSeats / TotalSeats);
	} else {
		System.out.println("Error: projectedPercentOfSeats() method. The total number of seats must be greater then 0.");
	}
	return percentage;
	}
/**
 * This method shows the user a visual representation of how many Seats were won by the Political Party, and if they won enough to have a
 * majority or not. Each Star represents a certain amount of seats won, the printed bar represents the majority point; if the stars are to the
 * left of the bar, the Political Party is projected to not win the election. If the stars are on the right of the printed line, the Party 
 * is projected to win the election.
 * 
 * @param 	maxStars	int variable stating what the maximum amount of Stars printed will be. This is how many stars that would be printed if 
 * the Political Party won 100% of the votes. Must be greater then 1, otherwise an Error code is printed.
 * @param 	numOfSeatsPerStar	double variable stating how many Seats there are per star. Must be greater then 1, otherwise an Error code is
 * printed instead.
 * @return	Returns final version of String created in the calculation method.
 */
public String textVisualizationBySeats(int maxStars, double numOfSeatsPerStar) {
	
	String Visual = null;
	float numOfSeats = getProjectedNumberOfSeats();
	
	if (maxStars < 1 || numOfSeatsPerStar < 1) {	//detects if an error occurred, and prints a message if one did.
		System.out.println("Error: textVisualizationBySeats() method. Both arguments must be greater then or equal to 1.");
		} else {
			Visual = calculation(maxStars, numOfSeatsPerStar, numOfSeats);
		}
	return Visual;
}

/**
 * This method shows the user a visual representation of how many Votes were won by the Political Party. 
 * Each Star represents a certain amount of Votes won, the printed bar represents the majority point; if the stars are to the left 
 * of the bar, the Political Party is projected to not win the majority of the vote. If the stars are on the right of the printed line, 
 * the Party is projected to win the majority of the vote.
 * 
 * @param 	maxStars	int variable stating what the maximum amount of Stars printed will be. This is how many stars that would be printed if 
 * the Political Party won 100% of the votes. Must be greater then 1, otherwise an Error code is printed.
 * @param numOfVotesPerStar		double variable stating how many Votes there are per star. Must be greater then 1, otherwise an Error code is
 * printed instead.
 * @return		Returns final visual representation of how many Votes the party won in the election.
 */
public String textVisualizationByVotes(int maxStars, double numOfVotesPerStar) {
	
	float percentageOfVotes = (getProjectedPercentageOfVotes() * 100);	//converts decimal percentage to whole number percentage
	String Visual = calculation(maxStars, numOfVotesPerStar, percentageOfVotes);
	if (maxStars < 1 || numOfVotesPerStar < 1) {	//detects if an error occurred, and prints a message if one did.
		System.out.println("Error: textVisualizationByVotes() method. Both arguments must be greater then or equal to 1.");
		}
	return Visual;
}

/**
 * Private method to this class that does the calculations required to produce the visual representations shown in the Visualization methods 
 * above, as well as the assembly for the String produced for said Visualization methods. In addition, this method uses the toString(); method 
 * above to also generate a text based representation of the results, and then adds it to the end of the visualization. Then finally, the 
 * whole assembled string is returned to the method that called this method.
 * 
 * @param 	totalStars	int variable stating what the maximum amount of Stars printed will be. This is how many stars that would be printed if 
 * the Political Party won 100% of the votes. Must be greater then 1, otherwise an Error code is printed.
 * @param 	starRatio	double variable that states the ratio of how many votes/seats are per star. For example: 18.0 would mean 18 votes per star.
 * @param 	numOfSeatsOrVotes	float variable for how many seats/votes are projected to be won by the political party.
 * @return	Returns the assembled string showing the visualization of the vote, as well as a Text based String of the results of the vote. 
 * For example, a possible result will look something like this: ****|***  <Party Name>(<projected % of votes>, <projected # of seats won>).
 */
private String calculation(int totalStars, double starRatio, float numOfSeatsOrVotes) {
	
	String representation = "";
	int majorityMarker = 0;	
	double halfofTotal = (((int)(totalStars*starRatio)) * 0.5);	//this is finding the "halfway point" in the amount of total stars to be printed.
	int numberOfStars = (int)(numOfSeatsOrVotes/starRatio);	//this finds how many stars the party has earned in the election.
	
	
	if (totalStars % 2 == 0) {	//this is determining if the total amount of stars is even or odd. If even, this statement is true. 
								//If odd, this statement is false.
		majorityMarker = (int)(halfofTotal/starRatio);	
		//this puts the bar at the direct halfway point. I.E. if total stars was 10, the bar would be between 5 and 6 
		//because you need 6 to win the majority. This is needed when the total amount of stars is an even number.
	} else {
		 majorityMarker = (int)Math.round(halfofTotal/starRatio);
		//this puts the bar where the majority point is. I.E. if the total stars was 13, the bar would be between 6 and 7, 
		//because you need 7 to win the majority. This is needed the the total amount of stars is an odd number.
	}
	
	for (int i = 0; i < totalStars; i++) {		//for loop that assembles the visual representation
		if (i == majorityMarker ) {
			representation = (representation + "|");
		}
		if (i < numberOfStars) {
			representation = (representation + "*");
		} else {
			representation = (representation + " ");
		}
	}
	
	representation = (representation + " " + toString());
	//and this uses the toString() method to add a text representation after the visual representation
	
	return representation;
	}
}

