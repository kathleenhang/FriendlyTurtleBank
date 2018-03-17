/*Folder/Project Name	: Project8KHang
 * Programmer Name		: Kathleen Hang
 * Date					: 05/23/2017
 * Class Name			: SavingsAccount
 * Project Description	: This savings subclass informs the super class that the user created a savings account.
 * 							It will also process savings account transactions. Calculates $2.50 withdrawal fee if user withdraws 
 * 							more than $2000, and adds default deposit of $500 when account is created.
  */
public class SavingsAccount extends Account
{

	//informs the super class of new savings account information and deposits default $500
	public SavingsAccount(String nameNewString, int pinNewInteger)
	{
		super(nameNewString, pinNewInteger,'S');
		newSavingsAccountCalc();
	}
	
	// starts new savings account with $500
	//initializes withdraw fee and withdraw charge limit
	private void newSavingsAccountCalc()
	{
		final float WITHDRAW_CHARGE_LIMIT_FLOAT = 2000;
		final float WITHDRAW_FEE_FLOAT = 2.50f;
		final float DEFAULT_SAVINGS_DEPOSIT_FLOAT = 500;
		balanceFloat = DEFAULT_SAVINGS_DEPOSIT_FLOAT;
		withdrawFeeFloat = WITHDRAW_FEE_FLOAT;
		withdrawChargeLimitFloat = WITHDRAW_CHARGE_LIMIT_FLOAT;
	}

	// if withdraw amount is less than or equal to balance, subtract amount from balance
	// if withdraw amount greater than withdraw charge limit AND withdraw amount + withdraw fee is less than or equal to balance,
	// then subtract withdraw fee amount from balance
	private void withdrawCalc()
	{
		  if(withdrawFloat > withdrawChargeLimitFloat)
		  {
		  		balanceFloat -= withdrawFloat + withdrawFeeFloat;
		  }
		  else
		  {
		  		balanceFloat -= withdrawFloat;
		  }
	}
	
	// assign public withdraw amount to protected 
	// call the withdrawCalc method to process transaction
	public void setWithdraw(float withdrawNewFloat)
	{
			withdrawFloat = withdrawNewFloat;
			withdrawCalc();
	}
	// assign public deposit to protected
	// call depositCalc method to process transaction
	public void setDeposit(float depositNewFloat)
	{
			depositFloat = depositNewFloat;
			depositCalc();
	}

	// add deposit to balance
	private void depositCalc()
	{
		
			balanceFloat += depositFloat;
	}
}
