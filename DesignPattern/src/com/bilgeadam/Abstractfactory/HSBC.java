package com.bilgeadam.Abstractfactory;

public class HSBC implements Bank {
	private String bankName;

	public HSBC() {
		this.bankName = "HSBC";
	}

	@Override
	public String getBankName() {

		return bankName;
	}
}
