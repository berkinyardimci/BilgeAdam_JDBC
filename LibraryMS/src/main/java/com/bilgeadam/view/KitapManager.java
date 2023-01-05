package com.bilgeadam.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.bilgeadam.controller.BookController;
import com.bilgeadam.entity.Author;
import com.bilgeadam.entity.Book;
import com.bilgeadam.entity.BookDetail;
import com.bilgeadam.entity.Student;
import com.bilgeadam.service.AuthorService;
import com.bilgeadam.service.BookDetailService;
import com.bilgeadam.service.BookService;
import com.bilgeadam.service.StudentService;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class KitapManager extends JFrame {

	private JPanel contentPane;
	private JTextField txtbook;
	private JTextField txtYazar;
	private JTextField txtYazarSoyad;
	
	private BookService bookService;
	private AuthorService authorService;
	private BookDetailService bookDetailService;
	private StudentService studentService;
	
	private JTable table;
	private JScrollPane scrollPane;
	private JButton btnDelete;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KitapManager frame = new KitapManager();
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
	private int id;
	
	public KitapManager() {
		this.bookService = new BookService();
		this.authorService = new AuthorService();
		this.bookDetailService = new BookDetailService();
		this.studentService= new StudentService();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Kitap Ismi");
		lblNewLabel.setBounds(27, 30, 64, 13);
		contentPane.add(lblNewLabel);

		JLabel lblYazarIsmi = new JLabel("Yazar Ismi");
		lblYazarIsmi.setBounds(27, 63, 64, 13);
		contentPane.add(lblYazarIsmi);

		JLabel lblYazarSoyad = new JLabel("Yazar Soyadı");
		lblYazarSoyad.setBounds(27, 97, 64, 13);
		contentPane.add(lblYazarSoyad);

		txtbook = new JTextField();
		txtbook.setToolTipText("Kitap İsmi");
		txtbook.setBounds(102, 27, 96, 19);
		contentPane.add(txtbook);
		txtbook.setColumns(10);

		txtYazar = new JTextField();
		txtYazar.setToolTipText("Yazar İsmi");
		txtYazar.setColumns(10);
		txtYazar.setBounds(101, 60, 96, 19);
		contentPane.add(txtYazar);

		txtYazarSoyad = new JTextField();
		txtYazarSoyad.setToolTipText("Yazar Soyadı");
		txtYazarSoyad.setColumns(10);
		txtYazarSoyad.setBounds(102, 94, 96, 19);
		contentPane.add(txtYazarSoyad);

		
		JButton btnNewButton = new JButton("save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Author author;
				Book book = new Book();
				BookDetail bookDetail = new BookDetail();
				
				String title = txtbook.getText();
				String authorName = txtYazar.getText();
				String authorSurname = txtYazarSoyad.getText();
				
				bookDetail.setTitle(title);
				bookDetailService.create(bookDetail);
				
				author = authorService.findByName(authorName, authorSurname);
				if (author == null) {
					Author author2 = new Author(authorName, authorSurname);
					authorService.create(author2);
					book.setAuthor(author2);
				} else {
					book.setAuthor(author);
				}
				book.setDetail(bookDetail);
				bookService.create(book);
				getTable();

			}
		});
		btnNewButton.setBounds(10, 181, 85, 21);
		contentPane.add(btnNewButton);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(231, 30, 177, 205);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = table.getSelectedRow();
				id = Integer.parseInt(table.getValueAt(i, 0).toString());
			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {},
			new String[] {
				"\u0130d", "Yazar", "Kitap Ism\u0131", "Geri \u0130ade Tarih"
			}
		));
		table.getColumnModel().getColumn(3).setPreferredWidth(85);
		scrollPane.setViewportView(table);
		
		btnDelete = new JButton("delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bookService.delete(id);
			}
		});
		btnDelete.setBounds(113, 181, 85, 21);
		contentPane.add(btnDelete);
		getTable();
	}
	public void getTable() {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		Object[] column = new Object[4];
		model.setRowCount(0);
		List<Book> books = bookService.listAll();

		for (int i = 0; i < books.size(); i++) {
			column[0] = books.get(i).getId();
			column[1] = books.get(i).getDetail().getTitle();
			column[2] = books.get(i).getAuthor().getFirstName();
			column[3] = books.get(i).getDetail().getBookReturnDate();
			model.addRow(column);
		}
	}

}
