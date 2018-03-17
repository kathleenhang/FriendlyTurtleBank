/*Folder/Project Name	: Project8KHang
 * Programmer Name		: Kathleen Hang
 * Date					: 05/23/2017
 * Class Name			: Account
 * Project Description	: This is the base class that contains code common to both savings and checking account 
 * 							such as name, pin, account type, deposit and withdraw. 
  */
public abstract class Account 
{
	private String nameString;
	private int pinInteger;
	protected float balanceFloat, depositFloat, withdrawFloat, withdrawChargeLimitFloat, withdrawFeeFloat;
	private char accountTypeChar;

	//sets name, pin and account type
	public Account(String nameNewString, int pinNewInteger, char typeNewChar)
	{
		setName(nameNewString);
		setPin(pinNewInteger);
		setType(typeNewChar);
	}
	
	// forces subclasses to have setWithdraw method
	public abstract void setWithdraw(float withdrawNewFloat);
	public abstract void setDeposit(float depositNewFloat);

	// assign public name to private
	private void setName(String nameNewString)
	{
		nameString = nameNewString;
	}
	// assign public pin to private
	private void setPin(int pinNewInteger)
	{
		pinInteger = pinNewInteger;
	}
	// assign public account type to private
	private void setType(char typeNewChar)
	{
		accountTypeChar = typeNewChar;
	}
	

	// retrieve protected balance
	public float getBalance()
	{
		return balanceFloat;
	}
	// retrieve private pin
	public int getPin()
	{
		return pinInteger;
	}
	// retrieve private name
	public String getName()
	{
		return nameString;
	}
	// retrieve private account type
	public char getType()
	{
		return accountTypeChar;
	}
	// retrieve protected withdraw charge limit
	public float getWithdrawChargeLimit()
	{
		return withdrawChargeLimitFloat;
	}
	// retrieve protected withdraw fee
	public float getWithdrawFee()
	{
		return withdrawFeeFloat;
	}
}
