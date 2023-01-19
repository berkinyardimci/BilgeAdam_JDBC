package com.productMS;

import com.productMS.util.HibernateUtils;
import com.productMS.util.ProductMenu;

public class Test {

	public static void main(String[] args) {
		// HibernateUtils.getSessionFactory().openSession();
		ProductMenu menu = new ProductMenu();
		while (true) {
			menu.menu();
		}
	}
}

//Şifreleri hashleyim Maven, sha256 
//stok kontreoller --> bir ürünü stoku bittiyse eğer stok kullanıcdan gelens toktan küçükse satın alma işlemi
//gerçekleşmesin

//Category eklerken eğer aynı isimde bir category var ise category eklemesin
//Kullanıcalr sisteme kayıt olurken
// Berkin.123
//şifreleri 8 karakterden uzun olsun (içinde en az 1 tane büyük harf, özel karakter, sayı )
//araştırma konusu (regex)
//mail formatını --> @gmail.com, @outlook.com,
//register(parametre olarak alalım veritabına gerek yok) olurken bizden repassword istesin.
//tc kimlik tamamen sayılardan ve max min 11 olsun.
//email, tckimlik bunlar uniqu olsun
//kullanıcı register olunca ne zaman kayıt oldugunu veritabanında tutalım (entity ekleme yapmamız lazım)
//satın alma tarihi
//iade etsin ama satın alma tarihinden 30 gün içinde iade edebilir.(30 günü geçtikten sonra iade kabul etmiyoruz)
//product stok adeti güncellencek, Customer'in bakiyesi güncellencek.
//iade ettikten sonra 1 dakika sonra para hesaba gelsin.

//Account hesap bakiyesi
//Dolar ,TL ,Euro 
//Hesap numarası otomatik oluşcak string ve 6 haneli "45987"
//10.000 tl den fazla para yükleyemsin
//Customerlar hesaplarına bakiye yükleyebilcek
//alışveriş yaparken bakiye kontrol bakiyesi yetmiyosa mesaj vercez
//şifre sıfırlama--> şifremi sıfırla --> otomatik bir şifre atasın bana
//consolda yazsın aynı zamanda veritabanındada güncellensin

//Admin giriş çıkış
//en fazla satışın yapıldıgı tarih
//en fazla satış yapılan ürün
//indirim uygulasın istediği ürüne indirim yapsın
//birden fazla ürüne aynı anda indirim yapsın
//bir categorydeki bütün ürünlere indirim uygulasın
//hesap numarası ile bakiye kontrol etsin

//hesabını kapat --> 

//***
//giriş yaptıktan 5 dakika sonra sistemden atsın. 
//***
