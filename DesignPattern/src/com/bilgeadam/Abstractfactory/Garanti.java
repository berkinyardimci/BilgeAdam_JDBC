package com.bilgeadam.Abstractfactory;

public class Garanti implements Bank {
	private String bankName;

	public Garanti() {
		this.bankName = "Garanti";
	}

	@Override
	public String getBankName() {

		return bankName;
	}
}
