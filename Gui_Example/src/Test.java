import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import javax.swing.JButton;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

import javax.swing.JTable;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Test {

	private JFrame frame;
	private JTextField txtproductName;
	private JTextField txtStock;
	private JTextField txtPrice;
	private JTextField productId;
	private Connection connection;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Test window = new Test();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void getTable() {
		String sql = "select * from product";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			table.setModel(DBUtils.resultSetToTableModel(resultSet));

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the application.
	 */
	public Test() {
		initialize();
		connection = utils.DBConnection.connect();
		getTable();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 674, 485);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(null, "Register", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(25, 86, 218, 247);
		frame.getContentPane().add(panel);

		JLabel lblNewLabel_1 = new JLabel("Product Name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(10, 22, 100, 13);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Stock");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_1.setBounds(10, 68, 100, 13);
		panel.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_1_1 = new JLabel("Price");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_1_1.setBounds(10, 119, 100, 13);
		panel.add(lblNewLabel_1_1_1);

		txtproductName = new JTextField();
		txtproductName.setColumns(10);
		txtproductName.setBounds(112, 21, 96, 19);
		panel.add(txtproductName);

		txtStock = new JTextField();
		txtStock.setColumns(10);
		txtStock.setBounds(112, 67, 96, 19);
		panel.add(txtStock);

		txtPrice = new JTextField();
		txtPrice.setColumns(10);
		txtPrice.setBounds(112, 118, 96, 19);
		panel.add(txtPrice);

		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql = "insert into product (name,price,stock) values(?,?,?) ";

				try {
					PreparedStatement preparedStatement = connection.prepareStatement(sql);
					preparedStatement.setString(1, txtproductName.getText());
					preparedStatement.setInt(2, Integer.parseInt(txtPrice.getText()));
					preparedStatement.setInt(3, Integer.parseInt(txtStock.getText()));
					preparedStatement.executeUpdate();
					JOptionPane.showMessageDialog(null, "Success");

					txtproductName.setText("");
					txtPrice.setText("");
					txtStock.setText("");
					getTable();

				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBackground(Color.GREEN);
		btnNewButton.setBounds(10, 180, 85, 21);
		panel.add(btnNewButton);

		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtproductName.setText("");
				txtPrice.setText("");
				txtStock.setText("");
				productId.setText("");
			}
		});
		btnClear.setBackground(Color.CYAN);
		btnClear.setBounds(10, 216, 85, 21);
		panel.add(btnClear);

		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteById(Integer.parseInt(productId.getText()));
				getTable();
			}
		});
		btnDelete.setBackground(Color.CYAN);
		btnDelete.setBounds(112, 216, 85, 21);
		panel.add(btnDelete);

		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				updateById(Integer.parseInt(productId.getText()));
				getTable();
			}
		});
		btnUpdate.setBackground(Color.GREEN);
		btnUpdate.setBounds(112, 180, 85, 21);
		panel.add(btnUpdate);

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(null, "Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(25, 343, 260, 95);
		frame.getContentPane().add(panel_1);

		JLabel lblNewLabel_2 = new JLabel("Product id");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(10, 25, 82, 34);
		panel_1.add(lblNewLabel_2);

		productId = new JTextField();
		productId.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {

				searchById(Integer.parseInt(productId.getText()));

			}
		});
		productId.setColumns(10);
		productId.setBounds(91, 33, 121, 24);
		panel_1.add(productId);

		JLabel lblNewLabel = new JLabel("Product Manager");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel.setBounds(141, 0, 248, 72);
		frame.getContentPane().add(lblNewLabel);

		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//System.exit(0);
				frame.dispose();
			}
		});
		btnExit.setBackground(Color.RED);
		btnExit.setBounds(495, 350, 131, 88);
		frame.getContentPane().add(btnExit);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(303, 85, 323, 247);
		frame.getContentPane().add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
	}

	protected void deleteById(int i) {
		String sql = "delete from  product where id =  " + i;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	protected void updateById(int i) {
		String sql = "update  product set name=? ,price=?,stock=? where id =  " + i;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, txtproductName.getText());
			preparedStatement.setInt(2, Integer.parseInt(txtPrice.getText()));
			preparedStatement.setInt(3, Integer.parseInt(txtStock.getText()));
			int affectedRow = preparedStatement.executeUpdate();
			if(affectedRow >0) {
				JOptionPane.showMessageDialog(null, "Success");
			}else {
				JOptionPane.showMessageDialog(null, "Faild");
			}
			
		} catch (SQLException e) {

			e.printStackTrace();
		}
		
	}

	protected void searchById(int id) {
		String sql = "select * from product where id = " + id;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				txtproductName.setText(resultSet.getString(2));
				txtPrice.setText(resultSet.getString(3));
				txtStock.setText(resultSet.getString(4));
			} else {
				JOptionPane.showMessageDialog(null, "Id kontrol edin");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NumberFormatException e1) {
			e1.printStackTrace();
		}
	}

}
