/*Folder/Project Name	: Project8KHang
 * Programmer Name		: Kathleen Hang
 * Date					: 05/23/2017
 * Class Name			: CheckingAccount
 * Project Description	: This checking subclass informs the super class that the user created a checking account.
 * 							It will also process checking account transactions. Calculates $2 withdrawal fee if user withdraws 
 * 							more than $750, and adds default deposit of $100 when account is created.
  */
public class CheckingAccount extends Account
{
	//informs the super class of new checking account information and deposits default $100
	public CheckingAccount(String nameNewString, int pinNewInteger)
	{
		super(nameNewString, pinNewInteger, 'C');
		newCheckingAccountCalc();
	}
	// starts new savings account with $100
	// initializes withdraw charge limit and withdraw fee
	private void newCheckingAccountCalc()
	{
		final float WITHDRAW_CHARGE_LIMIT_FLOAT = 750;
		final float WITHDRAW_FEE_FLOAT = 2;
		final float DEFAULT_CHECKING_DEPOSIT_FLOAT = 100;
		balanceFloat = DEFAULT_CHECKING_DEPOSIT_FLOAT;
		withdrawFeeFloat = WITHDRAW_FEE_FLOAT;
		withdrawChargeLimitFloat = WITHDRAW_CHARGE_LIMIT_FLOAT;
	}
	
	// if withdraw amount is greater than withdraw charge limit, subtract withdraw amount + withdraw fee from balance
	// else subtract withdraw amount from balance
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
	//  add deposit to balance
	private void depositCalc()
	{
			balanceFloat += depositFloat;
	}
}
