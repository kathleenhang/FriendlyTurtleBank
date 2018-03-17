
/*Folder/Project Name	: Project8KHang
 * Programmer Name		: Kathleen Hang
 * Date					: 05/23/2017
 * Class Name			: BankingApplication
 * Project Description	: This banking application allows the user to create new accounts, process transactions, and display all accounts in the system
  */


import java.awt.event.*;   	//for ActionListener
import javax.swing.*;		//for swing components
import java.awt.*;			//for Font
import java.text.*;			//for DecimalFormat class

public class BankingApplication extends JFrame 
	implements ActionListener
{
		
	//added components for first panel	
	//Array for the combo box
	String choiceString [] = {"Create Account","Withdraw or Deposit","Show all Accounts"};	
	JLabel companyLabel = new JLabel ("  Friendly Turtle Bank   ");
	JLabel selectLabel = new JLabel ("Please Select an Action: ");
	JComboBox selectComboBox = new JComboBox(choiceString);
	JButton goButton = new JButton("Go");
	JLabel programmerNameLabel = new JLabel ("programmed by Kathleen Hang");
	
	//added components for second panel	
	JLabel companyLabel2 = new JLabel ("     Friendly Turtle Bank      ");
	JLabel firstNameLabel = new JLabel ("First name ");
	JTextField firstNameTextField = new JTextField(10);
	JLabel lastNameLabel = new JLabel ("     Last name ");
	JTextField lastNameTextField = new JTextField(10);
	JLabel newPinLabel = new JLabel ("         PIN ");
	JTextField newPinTextField = new JTextField(10);
	String typeAccountString [] = {"Accounts", "Checking", "Savings"};
	JComboBox typeAccountComboBox = new JComboBox(typeAccountString);
	JButton processNewAccountButton = new JButton("Process");
	JButton backFromNewAccountButton = new JButton("Back");
	JTextArea accountOutputTextArea = new JTextArea(10,15);
	
	//added components for third panel	
	JLabel companyLabel3 = new JLabel ("       Friendly Turtle Bank        ");
	JRadioButton withdrawRadioButton = new JRadioButton("  Withdraw                               ");
	JRadioButton depositRadioButton = new JRadioButton("   Deposit                                ");
	JRadioButton invisibleRadioButton = new JRadioButton("");
	ButtonGroup transactionButtonGroup = new ButtonGroup();
	JLabel confirmPinLabel = new JLabel("                                    PIN: ");
	JTextField confirmPinTextField = new JTextField(25);
	JLabel amountLabel = new JLabel("                Enter amount: ");
	JTextField amountTextField = new JTextField(25);
	JButton processTransactionButton = new JButton("Process");
	JButton backFromTransactionButton = new JButton("Back");
	JTextArea transactionOutputTextArea = new JTextArea(10,15);
	
	//Added JTextArea for displaying all accounts
	JTextArea displayAllTextArea = new JTextArea(10, 42);
		
	JPanel mainPanel = new JPanel();
	JPanel accountPanel = new JPanel();
	JPanel transactionPanel = new JPanel();
	Font taFont = new Font("Courier", Font.PLAIN, 12);
	Font companyFont = new Font ("Sans Serif", Font.BOLD, 20);
	Font programmerNameFont = new Font ("Sans Serif", Font.ITALIC, 10);
	
	// instance objects and variables
	Account [] myAccount = new Account[10];
	
	int lastAccountInteger = -1;
	final int MAX_ACCOUNTS_INTEGER = 9;
	
	String typeString;
	char typeAccountChar;
	

	// the main method will create an object of itself and 
	//set the default close operation
	public static void main(String[] args)
	{		
		BankingApplication myApplication = new BankingApplication();
		myApplication.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
	}		
	
	//constructor 
	//call the methods to add components to the multiple panels
	//also sets size and visibility	
	public BankingApplication()
	{		
		designMainPanel();
		designAccountPanel();
		designTransactionPanel();
		addListeners();		
		add(mainPanel);
		setSize(370,170);
		setVisible(true);		
	}	
		
	//this method will create the main panel
	public void designMainPanel()
	{
		//add components to the mainPanel
		companyLabel.setFont(companyFont);
		mainPanel.add(companyLabel);
		mainPanel.add(selectLabel);
		mainPanel.add(selectComboBox);
		mainPanel.add(goButton);
		programmerNameLabel.setFont(programmerNameFont);
		mainPanel.add(programmerNameLabel);		
	}
	
	//this method creates the account panel
	public void designAccountPanel()
	{
		//add components to the adminPanel
		accountPanel.setLayout(new FlowLayout(FlowLayout.CENTER,35,10));
		companyLabel2.setFont(companyFont);
		accountPanel.add(companyLabel2);
		accountPanel.add(firstNameLabel);
		accountPanel.add(firstNameTextField);
		accountPanel.add(lastNameLabel);
		accountPanel.add(lastNameTextField);
		accountPanel.add(newPinLabel);
		accountPanel.add(newPinTextField);
		accountPanel.add(new JLabel("Type of Account"));
		accountPanel.add(typeAccountComboBox);
		accountPanel.add(processNewAccountButton);
		accountPanel.add(backFromNewAccountButton);
		accountPanel.add(accountOutputTextArea);
		
		String formattedTitleString = String.format("%-7s%-8s%-13s%-13s %-10s%n",
				"PIN","Name", "Acct Type","Trans Type","Balance");
		accountOutputTextArea.setFont(taFont);
		accountOutputTextArea.setText(formattedTitleString);
	}
	//this method creates the transaction panel
	public void designTransactionPanel()
	{
		//add the container group to the radio buttons
		transactionButtonGroup.add(depositRadioButton);
		transactionButtonGroup.add(withdrawRadioButton);
		transactionButtonGroup.add(invisibleRadioButton);
		companyLabel3.setFont(companyFont);
		transactionPanel.add(companyLabel3);
		transactionPanel.add(depositRadioButton);
		transactionPanel.add(withdrawRadioButton);
		transactionPanel.add(confirmPinLabel);
		transactionPanel.add(confirmPinTextField);
		transactionPanel.add(amountLabel);
		transactionPanel.add(amountTextField);
		transactionPanel.add(processTransactionButton);
		transactionPanel.add(backFromTransactionButton);
		transactionPanel.add(transactionOutputTextArea);
		
		String formattedTitleString = String.format("%-6s%-8s%-15s%-13s%-12s%-9s%-10s%n",
				"PIN","Name","Account Type", "Trans Type", "Trans Amt","Charge","Balance" );
		transactionOutputTextArea.setFont(taFont);
		transactionOutputTextArea.setText(formattedTitleString);
	}
	
	//this method puts action listeners on all the desired buttons
	public void addListeners()
	{
		//add the listeners to the respective components 
		selectComboBox.addActionListener(this);
		goButton.addActionListener(this);
		processNewAccountButton.addActionListener(this);
		backFromNewAccountButton.addActionListener(this);
		processTransactionButton.addActionListener(this);
		backFromTransactionButton.addActionListener(this);
	}	
		
	//this method assigns the actions and methods to the correct button
	public void actionPerformed(ActionEvent evt)
	{
		//buttons fire this event
		Object sourceObject = evt.getSource();
		
		if (sourceObject == goButton)
		{
			if(selectComboBox.getSelectedIndex() == 0)  //Add an account option
			{
				remove(mainPanel);
				invisibleRadioButton.setSelected(true);
				remove(transactionPanel);
				add(accountPanel);
				setSize(380,400);
				setVisible(true);
				firstNameTextField.requestFocus();
			}
			else if (selectComboBox.getSelectedIndex() == 1) //Transaction option
			{
				remove(mainPanel);
				invisibleRadioButton.setSelected(true);
				remove(accountPanel);
				add(transactionPanel);
				setSize(530,350);
				setVisible(true);
				depositRadioButton.requestFocus();
			}
			else if(selectComboBox.getSelectedIndex() == 2)  //Display all transactions option
			{				
				displayAllAccountInfo();
			}
			else
				JOptionPane.showMessageDialog(null, "Please make a selection in the combo box before pressing Go.");
		}
		
		if(sourceObject == processNewAccountButton)
		{
			if(validationNewAccountFields())
				processNewAccount();
		}
		else if(sourceObject == processTransactionButton)
		{
			if(validationTransRadioButtons())
				processTransaction();
			else 
			{
				JOptionPane.showMessageDialog(null, "Please select either withdraw or deposit");
				depositRadioButton.requestFocus();
			}
			
				
		}
		
		else if(sourceObject == backFromNewAccountButton)
		{
			remove(accountPanel);
			invisibleRadioButton.setSelected(true);
			remove(transactionPanel);
			selectComboBox.setSelectedIndex(-1);
			add(mainPanel);
			setSize(370,170);
			setVisible(true);
			firstNameTextField.setText("");
			lastNameTextField.setText("");
			newPinTextField.setText("");
			accountOutputTextArea.setText("");
			typeAccountComboBox.setSelectedIndex(0);
			String formattedTitleString = String.format("%-7s%-8s%-13s%-13s %-10s%n",
					"PIN","Name", "Acct Type","Trans Type","Balance");
			accountOutputTextArea.setText(formattedTitleString);
		}
		else if(sourceObject == backFromTransactionButton)
		{
			remove(transactionPanel);
			remove(accountPanel);
			selectComboBox.setSelectedIndex(-1);
			add(mainPanel);
			setSize(370,170);
			setVisible(true);
			
			
			invisibleRadioButton.setSelected(true);
			confirmPinTextField.setText("");
			amountTextField.setText("");
			transactionOutputTextArea.setText("");
			String formattedTitleString = String.format("%-6s%-8s%-15s%-13s%-12s%-9s%-10s%n",
					"PIN","Name","Account Type", "Trans Type", "Trans Amt","Charge","Balance" );
			transactionOutputTextArea.setText(formattedTitleString);
		}		
		
	}//end of actionPerformed method
	
	
	//validates if something has been entered into the three text fields 
	public boolean validationNewAccountFields()
	{
	    boolean validationBoolean = false;
	    
	    if(!(firstNameTextField.getText()).equals(""))
	    {
	    	if(!(lastNameTextField.getText()).equals(""))
	    	{
	    		if(!(newPinTextField.getText()).equals(""))
	    		{      		   
	    			validationBoolean = true; 
	    		}
    		   
	    		else
	    		{
	    			JOptionPane.showMessageDialog(null, "Please enter a PIN");
	    			newPinTextField.requestFocus();
	    			validationBoolean = false;
	    		}
	    	}
	    	else
	    	{
	    		JOptionPane.showMessageDialog(null, "Please enter Last Name");
	    		lastNameTextField.requestFocus();
	    		validationBoolean = false;}
			}
	    else
	    {
	    	JOptionPane.showMessageDialog(null, "Please enter First name");
	    	firstNameTextField.requestFocus();
	    	validationBoolean = false;
	    }
	    
	    return validationBoolean;
	}
	
	//this method will validate if a radio button was selected
	public boolean validationTransRadioButtons()
	{
		boolean validationBoolean = false;
		
		if(depositRadioButton.isSelected() || withdrawRadioButton.isSelected())
			validationBoolean = true;
		else
			validationBoolean = false;
		return validationBoolean;
	}
	
	//checks if there is a pin match. if there is, then boolean is true. otherwise false.
	public boolean checkPin(int pinNewInteger)
	{
		boolean foundBoolean = false;
		int countInteger = 0;
		
		while((!foundBoolean && lastAccountInteger != -1) && (countInteger <= lastAccountInteger))
		{
			if (pinNewInteger == myAccount[countInteger].getPin())
			{
				foundBoolean = true;
			}
			else
			{
				countInteger++;
			}
		}
		return foundBoolean;
	}
	
	// Assigns account type based on what user selects in account type combo box
	public void getInput()
	{
		//if checking type is selected
		if(typeAccountComboBox.getSelectedIndex() == 1)
		{
			typeAccountChar = 'C';
		}
		
		//if savings type is selected
		else if(typeAccountComboBox.getSelectedIndex() == 2)
		{
			typeAccountChar = 'S';
		}
	}
	
	// checks if user selected a valid option from account type combo box. 
	// if they did, boolean is true. if not, then false.
	private boolean checkAccountEntry()
	{
		// If savings or checking is selected, return true. 
		// Else return false
		if(typeAccountComboBox.getSelectedIndex() == 0)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	//this method will make a new account
	public void processNewAccount()
	{
		//Your create an account code should go here!
		String firstNameString, lastNameString, nameString;
		int pinInteger, pinLengthInteger;
		boolean foundBoolean;
	
		DecimalFormat valueDecimalFormat = new DecimalFormat("$#0.00");
		
		try
		{
			pinInteger = Integer.parseInt(newPinTextField.getText());
			pinLengthInteger = Integer.toString(pinInteger).length();
			
			firstNameString = firstNameTextField.getText();
			lastNameString = lastNameTextField.getText();
			nameString = firstNameString + " " + lastNameString;
			
			foundBoolean = checkPin(pinInteger);
			
			if(pinLengthInteger == 4)
			{
				if(!foundBoolean)
				{
					// if an account type is selected
					if(checkAccountEntry())
					{
						//assigns account type char
						getInput();
						
						// if account type is checking, create a checking account
						if(typeAccountChar == 'C')
						{
							//if it is less than account array size, add account
							if(lastAccountInteger < MAX_ACCOUNTS_INTEGER)
							{
								lastAccountInteger++;
	
								myAccount[lastAccountInteger] = new CheckingAccount(nameString, pinInteger);
								
								String outputString = String.format("%-6d %-6s  %-12s %-14s %7s %n", 
																myAccount[lastAccountInteger].getPin(), 
																myAccount[lastAccountInteger].getName(),
																"Checking", "New",
										valueDecimalFormat.format(myAccount[lastAccountInteger].getBalance()));
								accountOutputTextArea.append(outputString);
								JOptionPane.showMessageDialog(null, "New account has been successfully added");
								
								firstNameTextField.setText("");
								lastNameTextField.setText("");
								firstNameTextField.requestFocus();
								newPinTextField.setText("");
								typeAccountComboBox.setSelectedIndex(0);
							}
							else
							{
								JOptionPane.showMessageDialog(null, "Sorry, there may not be more than 10 accounts within the system");
								newPinTextField.setText("");
								firstNameTextField.setText("");
								lastNameTextField.setText("");
								firstNameTextField.requestFocus();
								typeAccountComboBox.setSelectedIndex(0);
							}
						}
						
						// if account type is savings, then create savings account
						else if (typeAccountChar == 'S')
						{
							
							// if it is less than account array size, add account
							if(lastAccountInteger < MAX_ACCOUNTS_INTEGER)
							{
								lastAccountInteger++;

							
								myAccount[lastAccountInteger] = new SavingsAccount(nameString, pinInteger);
								
								String outputString = String.format("%-6d %-6s  %-12s %-14s %7s %n", 
																myAccount[lastAccountInteger].getPin(), 
																myAccount[lastAccountInteger].getName(),
																"Savings", "New",
										valueDecimalFormat.format(myAccount[lastAccountInteger].getBalance()));
								accountOutputTextArea.append(outputString);
								JOptionPane.showMessageDialog(null, "New account has been successfully added");
								
								firstNameTextField.setText("");
								lastNameTextField.setText("");
								firstNameTextField.requestFocus();
								newPinTextField.setText("");
								typeAccountComboBox.setSelectedIndex(0);
								
								
							}
							else
							{
								JOptionPane.showMessageDialog(null, "Sorry, there may not be more than 10 accounts within the system");
								newPinTextField.setText("");
								firstNameTextField.setText("");
								lastNameTextField.setText("");
								firstNameTextField.requestFocus();
								typeAccountComboBox.setSelectedIndex(0);
							}
						}
					}
					
					else
					{
						JOptionPane.showMessageDialog(null, "Please select account type");
						typeAccountComboBox.requestFocus();
					}	
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Pin number already exists");
					newPinTextField.selectAll();
					newPinTextField.requestFocus();
				}
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Pin number must be 4 digits");
				newPinTextField.selectAll();
				newPinTextField.requestFocus();
			}
		}
		catch (NumberFormatException err)
		{
			JOptionPane.showMessageDialog(null, "Please enter a number");
			newPinTextField.selectAll();
			newPinTextField.requestFocus();
		}
	
	}//end of processNewAccount method
	
	//this method checks if the entered pin is correct and then withdraws or deposits depending on the radio button
	public void processTransaction()
	{
		int pinInteger, countInteger = 0, resultInteger;
		float amountFloat;
		boolean foundBoolean = false;
		String accountTypeString = "";
		
		DecimalFormat valueDecimalFormat = new DecimalFormat("$#0.00");
		// check if pin and amount is valid
		try
			{
				pinInteger = Integer.parseInt(confirmPinTextField.getText());
				try
				{
					amountFloat = Float.parseFloat(amountTextField.getText());
					
					// check if there is a pin match
					while (countInteger <= lastAccountInteger && !foundBoolean && lastAccountInteger != -1)
					{
						
						// assigns "Savings" or "Checking" to accountTypeString for text area output
						if (myAccount[countInteger].getType() == 'S')
						{
							accountTypeString = "Savings";
						}
						else if (myAccount[countInteger].getType() == 'C')
						{
							accountTypeString = "Checking";
						}
						
						//if a pin match is found, set deposit/withdraw amount
						if (pinInteger == myAccount[countInteger].getPin())
						{						
							foundBoolean = true;
							if (depositRadioButton.isSelected())
							{
								typeString = "Deposit";
								//if deposit amount is a positive number, then process the transaction and display output
								if(amountFloat > 0)
								{
									myAccount[countInteger].setDeposit(amountFloat);
									//display transaction
									String outputString = String.format("%-5d %-7s %-14s %-14s %7s %8s %9s %n", myAccount[countInteger].getPin(), 
											 														myAccount[countInteger].getName(),
											 														accountTypeString,
											 														typeString, valueDecimalFormat.format(amountFloat),
											 														"",
											 							valueDecimalFormat.format(myAccount[countInteger].getBalance()));
											 
									transactionOutputTextArea.append(outputString);

								}
								else
								{
									JOptionPane.showMessageDialog(null, "Sorry, you may not deposit a negative amount");
									amountTextField.selectAll();
									amountTextField.requestFocus();
								}
							} 
							// if user selects withdraw
							else if (withdrawRadioButton.isSelected())
							{
								typeString = "Withdrawal";
								
								//validate that withdraw amount is less than or equal to current balance
								if(amountFloat <= myAccount[countInteger].getBalance())
								{
								
									// if withdraw amount is less than or equal to the fee charge limit, then withdraw and display output 
									if (amountFloat <= myAccount[countInteger].getWithdrawChargeLimit())
									{
										myAccount[countInteger].setWithdraw(amountFloat);
										String outputString = String.format("%-5d %-7s %-14s %-14s %7s %8s %9s %n", myAccount[countInteger].getPin(), 
												myAccount[countInteger].getName(),
												accountTypeString,
												typeString, valueDecimalFormat.format(amountFloat),
												"",
												valueDecimalFormat.format(myAccount[countInteger].getBalance()));
										transactionOutputTextArea.append(outputString);
									}
									
									// if withdraw amount + withdraw fee is more than current balance, warn user about overdraw and disallow transaction
									if(amountFloat + myAccount[countInteger].getWithdrawFee() > myAccount[countInteger].getBalance())
									{
										JOptionPane.showMessageDialog(null, "Sorry, withdrawal amount + withdrawal fee will overdraw the account");
									}
									// if withdraw amount is greater than withdraw charge limit AND withdraw amount + withdraw fee will not overdraw account
									// then ask user if they are okay with paying withdraw fee
									if((amountFloat > myAccount[countInteger].getWithdrawChargeLimit()) && (amountFloat + myAccount[countInteger].getWithdrawFee() <= myAccount[countInteger].getBalance()))
									{
										resultInteger = JOptionPane.showConfirmDialog(null, "Additional charges of " + valueDecimalFormat.format(myAccount[countInteger].getWithdrawFee()) +" will be applied", "Confirmation", JOptionPane.YES_NO_OPTION);
										
										// if user selects yes, process transaction and display output
										if(resultInteger == JOptionPane.YES_OPTION)
										{
											myAccount[countInteger].setWithdraw(amountFloat);
											String outputString = String.format("%-5d %-7s %-14s %-14s %7s %8s %9s %n", myAccount[countInteger].getPin(), 
													myAccount[countInteger].getName(),
													accountTypeString,
													typeString, valueDecimalFormat.format(amountFloat),
													valueDecimalFormat.format(myAccount[countInteger].getWithdrawFee()),
													valueDecimalFormat.format(myAccount[countInteger].getBalance()));
											transactionOutputTextArea.append(outputString);
										}
										
										// if user selects no, then inform them that no fee was charged and transaction was cancelled.
										// Display to output
										else
										{
											JOptionPane.showMessageDialog(null, "Transaction cancelled and no fee charged");
										
											//display transaction
											String outputString = String.format("%-5d %-7s %-14s %-14s %7s %8s %9s %n", myAccount[countInteger].getPin(), 
																											myAccount[countInteger].getName(),
																											accountTypeString,
																											typeString, valueDecimalFormat.format(0),
																											"",
																											valueDecimalFormat.format(myAccount[countInteger].getBalance()));
											transactionOutputTextArea.append(outputString);
										}
									}
								}
								else
								{
									JOptionPane.showMessageDialog(null, "Sorry, you may not withdraw more than current balance");
									amountTextField.selectAll();
									amountTextField.requestFocus();
								}
							}					
						} 
						else
						{
							countInteger++;
						}
					}
	
					//reset fields
					amountTextField.setText("");
					invisibleRadioButton.setSelected(true);
					depositRadioButton.requestFocus();

					// if no pin match is found
					if (!foundBoolean)
					{
						JOptionPane.showMessageDialog(null, "Pin does not exist");
						confirmPinTextField.selectAll();
						confirmPinTextField.requestFocus();
					}
				} 
				// error prompt for amount field
				catch (NumberFormatException err)
				{
					JOptionPane.showMessageDialog(null, "Please enter an amount number");
					amountTextField.selectAll();
					amountTextField.requestFocus();
				}
			}
			// error prompt for pin field
			catch (NumberFormatException err)
			{
				JOptionPane.showMessageDialog(null, "Please enter a pin number");
				confirmPinTextField.selectAll();
				confirmPinTextField.requestFocus();
			}
	
	}//end of processTransaction method
	
	//this method shows the account information in a JOption pane
	public void displayAllAccountInfo()
	{
			DecimalFormat valueDecimalFormat = new DecimalFormat("$#0.00");
		 	String formattedTitleString = String.format("%-10s %-18s %-18s %7s %n", "Name", "Account Type", "PIN", "Balance");
		 	String outputString = "", accountTypeString = "";
		 	displayAllTextArea.setFont(taFont);
		 	displayAllTextArea.setText(formattedTitleString);
		 	displayAllTextArea.setEditable(false);
	
		 // display all accounts using text area in JOptionPane dialog box
		for(int countInteger = 0; countInteger <= lastAccountInteger; countInteger++)
		{
			if (myAccount[countInteger].getType() == 'S')
			{
				accountTypeString = "Savings";
			}
			else if (myAccount[countInteger].getType() == 'C')
			{
				accountTypeString = "Checking";
			}
			// print accounts one by one
			outputString = String.format("%-10s %-18s %-19d %7s %n", myAccount[countInteger].getName(), 
																		accountTypeString,
																	 myAccount[countInteger].getPin(),
											valueDecimalFormat.format(myAccount[countInteger].getBalance()));
			displayAllTextArea.append(outputString);
		}
		
	 	JOptionPane.showMessageDialog(null, displayAllTextArea);
	
	}//end of displayAllAccountInfo method	
		
}//end of class
