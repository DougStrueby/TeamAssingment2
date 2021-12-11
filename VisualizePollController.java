package application;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ChoiceBox;
import model.*;

public class VisualizePollController {
	
	Factory p = Factory.getInstance();
	PollList aPollList = p.createRandomPollList();
	private int index = 0;
   
	@FXML
    private PieChart projectedPercentOfVotesChart;

    @FXML
    private PieChart projectedSeatsChart;

    @FXML
    private ChoiceBox<String> pollChoiceBox;

    /**
     * Creates a aggregate poll to be used in the initialize method
     * 
     * @return Poll variable that contains the aggregate poll
     */
    private Poll createAggregatePoll() {
    	
    	Poll agPoll = aPollList.toArray()[0];
    	Party[] agParty = agPoll.getParties();
    	String[] agPartyNames = new String[agParty.length];
    	for(int i = 0; i < agParty.length; i++) {
    		agPartyNames[i] = agParty[i].getName();
    	}
    	return aPollList.getAggregatePoll(agPartyNames);
       }
    
    /**
     * Adds the specified party array to the addTo data set
     * 
     * @param aParty a array of parties you would like to add to the data
     * @param numOfParties a integer of the amount of parties in the poll
     * @param addTo the data that will later be added to the seats data
     */
    private void addPartiesSeats(Party[] aParty,int numOfParties, PieChart.Data[] addTo) {
    	
    	for(int partyIndex = 0; partyIndex < numOfParties; partyIndex++) {
			addTo[partyIndex] = new PieChart.Data(aParty[partyIndex].getName() + "[" + (int) aParty[partyIndex].getProjectedNumberOfSeats() + "]" 
					,aParty[partyIndex].getProjectedNumberOfSeats());
			}
    	}
    /**
     * adds the specified party array to the addTo data set
     * 
     * @param aParty a array of parties you would like to add to the data
     * @param numOfParties a integer of the amount of parties in the poll
     * @param addTo the data that will later be added to the votes data
     */
    private void addPartiesVotes(Party[] aParty,int numOfParties, PieChart.Data[] addTo) {
    	
    	for(int partyIndex = 0; partyIndex < numOfParties; partyIndex++) {
			addTo[partyIndex] = new PieChart.Data(aParty[partyIndex].getName() + "[" + (int)( aParty[partyIndex].getProjectedPercentageOfVotes() * 100) + "]"
					, aParty[partyIndex].getProjectedPercentageOfVotes());
		}
    }
    
    /**
     * function that adds the parties respective colour based on the parties name to the pie chart
     * 
     * @param aPoll a poll you want to add colours to
     * @param data a data set that you want to change the colours of
     */
    private void addColours(Poll aPoll, PieChart.Data[] data) {
    	
    	for(Party aParty :aPoll.getParties()) {
    		for(int i = 0; i < aPoll.getNumberOfParties(); i++) {
    			String partyName = data[i].getName().substring(0, data[i].getName().indexOf('['));
    			if(aParty.getName().equals(partyName)) {
    				data[i].getNode().setStyle("-fx-pie-color:" + aParty.getColour().toString().replace("0x", "#"));
    			}
    		}
    	}
    	
    	  
    }
    
    /**
     * initializes the data within the pie charts and choice box
     * along with setting the default pie charts to be the aggregate poll
     */
    public void initialize(){
    	
    	Poll aPoll = aPollList.toArray()[index];
    	
    	int numOfPolls = aPollList.toArray().length;
    	int numOfParties = aPoll.getNumberOfParties();
    	
    	String[] pollNames = new String[numOfPolls + 1];
    	
    	for(int i = 0; i < numOfPolls; i++) {
    		aPoll = aPollList.toArray()[i];
    		pollNames[i] = aPoll.getPollName();
    	}
    	
    	Poll agPoll = createAggregatePoll();
    	
    	pollNames[numOfPolls] = "Aggregate Poll";
    	
    	/*
    	 *  Creates the data for the expected seats in the poll
    	 */
    	PieChart.Data[][] seatData = new PieChart.Data[numOfPolls + 1][numOfParties];
    	
    	for(int pollIndex = 0; pollIndex < numOfPolls; pollIndex++) {
    		Party[] pollParty = aPollList.toArray()[pollIndex].getParties();
    		addPartiesSeats(pollParty, numOfParties, seatData[pollIndex]);
    		
    	}	
    	
    	addPartiesSeats(agPoll.getParties(), numOfParties, seatData[numOfPolls]);
    	projectedSeatsChart.setData(FXCollections.observableArrayList(seatData[numOfPolls]));  	
    	addColours(aPoll, seatData[numOfPolls]);
    	
    	/*
    	 *  Creates the data for the expected vote percentage in the poll
    	 */
    	PieChart.Data[][] voteData = new PieChart.Data[numOfPolls + 1][numOfParties];
    	
    	for(int pollIndex = 0; pollIndex < numOfPolls; pollIndex++) {
    		Party[] pollParty = aPollList.toArray()[pollIndex].getParties();
    		addPartiesVotes(pollParty, numOfParties, voteData[pollIndex]);
    	}	
    	
    	addPartiesVotes(agPoll.getParties(), numOfParties, voteData[numOfPolls]);
    	projectedPercentOfVotesChart.setData(FXCollections.observableArrayList(voteData[numOfPolls]));
    	addColours(aPoll, voteData[numOfPolls]);
    	
    	projectedPercentOfVotesChart.setLegendVisible(false);
    	projectedSeatsChart.setLegendVisible(false);
    	
    	pollChoiceBox.setItems(FXCollections.observableArrayList(pollNames));
    	pollChoiceBox.setValue(pollNames[numOfPolls]);
    	
    	pollChoiceBox.getSelectionModel().selectedIndexProperty().addListener(
    			new ChangeListener<Number>() {
    				@Override
    				
    				/*
    				 * observes the index of the choice box tab chosen and feeds this information to the pie charts
    				 */
    				public void changed(ObservableValue observable, Number oldValue, Number newValue) {
    					int index = newValue.intValue();

    					if(index >= 0) {
    						projectedSeatsChart.setData(FXCollections.observableArrayList(seatData[index]));
    						projectedPercentOfVotesChart.setData(FXCollections.observableArrayList(voteData[index]));
    						if(index <= numOfPolls - 1) {
    							addColours(aPollList.toArray()[index], seatData[index]);
    							addColours(aPollList.toArray()[index], voteData[index]);
    							}
    					}
    					}
    				}
    		);
    }		
}

