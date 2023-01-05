package com.bilgeadam.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminManager extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminManager frame = new AdminManager();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AdminManager() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Kitap Islemleri");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				KitapManager kitapManager = new KitapManager();
				kitapManager.setVisible(true); //studen logini açar
				dispose();
			}
		});
		btnNewButton.setBounds(118, 57, 127, 21);
		contentPane.add(btnNewButton);
		
		JButton btnOgrenciIslemler = new JButton("Ogrenci Islemlerı");
		btnOgrenciIslemler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminStudentManager adminStudentManager = new AdminStudentManager();
				adminStudentManager.setVisible(true); //studen logini açar
				dispose();
			}
		});
		btnOgrenciIslemler.setBounds(118, 113, 127, 21);
		contentPane.add(btnOgrenciIslemler);
	}

}
