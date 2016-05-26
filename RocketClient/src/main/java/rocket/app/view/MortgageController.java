package rocket.app.view;

import java.net.URL;
import java.util.ResourceBundle;

import eNums.eAction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import rocket.app.MainApp;
import rocketCode.Action;
import rocketData.LoanRequest;

public class MortgageController {

	private MainApp mainApp;
	
	//	TODO - RocketClient.RocketMainController
	
	//Text Fields
	@FXML
	private TextField txtIncome;
	@FXML
	private TextField txtExpenses;
	@FXML
	private TextField txtCreditScore;
	@FXML
	private TextField txtHouseCost;
	@FXML
	private TextField txtDownPayment;
	
	// Loan term
	@FXML
	private ComboBox loanTerm;
	
	
	//Labels
	@FXML
	private Label lblIncome;
	@FXML
	private Label lblExpenses;
	@FXML
	private Label lblCreditScore;
	@FXML
	private Label lblHouseCost;
	@FXML
	private Label lblLoanTerm;
	@FXML
	private Label lblDownPayment;
	@FXML
	private Label lblPayment;
	//Buttons
	@FXML
	private Button btnPayment;
	
	//Error messages
	@FXML
	private Label lblErrorMsg;
	
	
	//	Create private instance variables for:
	//		TextBox  - 	txtIncome
	//		TextBox  - 	txtExpenses
	//		TextBox  - 	txtCreditScore
	//		TextBox  - 	txtHouseCost
	//		ComboBox -	loan term... 15 year or 30 year
	//		Labels   -  various labels for the controls
	//		Button   -  button to calculate the loan payment
	//		Label    -  to show error messages (exception throw, payment exception)

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		//Add the loan terms to the combo box when starting up
	
		loanTerm.getItems().add((Integer)15);
		loanTerm.getItems().add((Integer)30);
		
	}
	
	//	TODO - RocketClient.RocketMainController
	//			Call this when btnPayment is pressed, calculate the payment
	@FXML
	public void btnCalculatePayment(ActionEvent event)
	{
		Object message = null;
		//	TODO - RocketClient.RocketMainController
		
		Action a = new Action(eAction.CalculatePayment);
		LoanRequest lq = new LoanRequest();
		//	TODO - RocketClient.RocketMainController
		//			set the loan request details...  rate, term, amount, credit score, downpayment
		//			I've created you an instance of lq...  execute the setters in lq
		lq.setdAmount(Double.parseDouble(txtHouseCost.getText())-Double.parseDouble(txtDownPayment.getText()));
		lq.setdExpenses(Double.parseDouble(txtExpenses.getText()));
		lq.setdIncome(Double.parseDouble(txtIncome.getText()));
		lq.setiCreditScore(Integer.parseInt(txtCreditScore.getText()));
		lq.setiTerm((Integer)(loanTerm.getSelectionModel().getSelectedItem()));
		a.setLoanRequest(lq);
		
		//	send lq as a message to RocketHub		
		mainApp.messageSend(lq);
	}
	
	public void HandleLoanRequestDetails(LoanRequest lRequest)
	{
		double pmt = Math.abs(lRequest.getdPayment());
		double maxPmt1 = .28 * lRequest.getdIncome();
		double maxPmt2 = .36 * lRequest.getdIncome()-lRequest.getdExpenses();
		double maxPmt = Math.min(maxPmt1, maxPmt2);
		String stringPmt = String.format("%.2f", pmt);
		if (pmt > maxPmt){
			lblPayment.setText("Monthly payment is to high ("+maxPmt+" maximum payment, your payment would be "+stringPmt+")");
		}
		else {
			
			lblPayment.setText("Your monthly payment will be "+stringPmt+" at a rate of "+ lRequest.getdRate());
		}
		
		//	TODO - RocketClient.HandleLoanRequestDetails
		//			lRequest is an instance of LoanRequest.
		//			after it's returned back from the server, the payment (dPayment)
		//			should be calculated.
		//			Display dPayment on the form, rounded to two decimal places
		
	}
}
