package com.bilgeadam.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.bilgeadam.entity.Book;
import com.bilgeadam.entity.Student;
import com.bilgeadam.service.BookDetailService;
import com.bilgeadam.service.BookService;
import com.bilgeadam.util.BAUtils;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class StudentManager extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private BookService bookService;
	private JTextField txtid;
	private JTextField txtduration;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	
	private int bookId;
	public StudentManager(Optional<Student> student) {

		this.bookService = new BookService();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnNewButton = new JButton("Odunc Al");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Book borrowBook = bookService.find(bookId);
				int duration = Integer.parseInt(txtduration.getText());
				LocalDate date = LocalDate.now();
				borrowBook.getDetail().setBookBorrowDate(date);
				borrowBook.getDetail().setBookReturnDate(date.plusDays(duration));
				borrowBook.getDetail().setBorrowed(true);
				
				borrowBook.getStudentList().add(student.get());
				student.get().getBooks();
				bookService.update(bookId, borrowBook);
				getTable();
			}
		});
		btnNewButton.setBounds(10, 26, 85, 55);
		contentPane.add(btnNewButton);

		JButton btnIadeEt = new JButton("Iade Et");
		btnIadeEt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Book returnBook = bookService.find(bookId);
				List<Book> books = student.get().getBooks();
				returnBook.getDetail().setBorrowed(false);
				returnBook.getDetail().setBookReturnDate(LocalDate.now());
				student.get().getBooks().remove(returnBook);
				bookService.update(bookId, returnBook);
				getTable();
			}
		});
		btnIadeEt.setBounds(10, 94, 85, 55);
		contentPane.add(btnIadeEt);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(194, 26, 209, 196);
		contentPane.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			
			
			

			@Override
			public void mouseClicked(MouseEvent e) {
				int i = table.getSelectedRow();
				txtid.setText(table.getValueAt(i, 0).toString());
				bookId = Integer.parseInt(txtid.getText());
				
				
				
			}
		});
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "id", "Book Name", "AuthorName" }));
		scrollPane.setViewportView(table);
		
		txtid = new JTextField();
		txtid.setBounds(63, 206, 96, 19);
		contentPane.add(txtid);
		txtid.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("id");
		lblNewLabel.setBounds(10, 209, 45, 13);
		contentPane.add(lblNewLabel);
		
		txtduration = new JTextField();
		txtduration.setColumns(10);
		txtduration.setBounds(63, 232, 96, 19);
		contentPane.add(txtduration);
		
		JLabel lblDuration = new JLabel("Duration");
		lblDuration.setBounds(10, 235, 45, 13);
		contentPane.add(lblDuration);
		
		JButton btnOgrenciKitaplar = new JButton("Ogrenci Kitapları");
		btnOgrenciKitaplar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				Object[] column = new Object[3];
				model.setRowCount(0);
				List<Book> books = student.get().getBooks();
				
				for (int i = 0; i < books.size(); i++) {
					column[0] = books.get(i).getId();
					column[1] = books.get(i).getDetail().getTitle();
					column[2] = books.get(i).getAuthor().getFirstName();
					model.addRow(column);
				}
			}
		});
		btnOgrenciKitaplar.setBounds(99, 94, 85, 55);
		contentPane.add(btnOgrenciKitaplar);
		
		JButton btnTumKitaplar = new JButton("Tum Kitaplar");
		btnTumKitaplar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getTable();
			}
		});
		btnTumKitaplar.setBounds(99, 26, 85, 55);
		contentPane.add(btnTumKitaplar);
		getTable();
	}

	public void getTable() {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		Object[] column = new Object[3];
		model.setRowCount(0);
		List<Book> books = bookService.listAll()
				.stream()
				.filter(bookDetail -> !bookDetail.getDetail().isBorrowed())
				.collect(Collectors.toList());
		
		books.forEach(book-> System.out.println(book.getId() + " " + book.getDetail().getTitle()));
		for (int i = 0; i < books.size(); i++) {
			column[0] = books.get(i).getId();
			column[1] = books.get(i).getDetail().getTitle();
			column[2] = books.get(i).getAuthor().getFirstName();
			model.addRow(column);
		}
	}
}
