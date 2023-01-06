package com.bilgeadam.Abstractfactory;

public class FactoryCreator {
	
	public static AbstractFactory getFactory(String cohice) {
		if(cohice.equalsIgnoreCase("Bank")) {
			return new BankFactory();
		}else if(cohice.equalsIgnoreCase("Loan")) {
			return new LoanFactory();
		}
		return null;
	}
}
