package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField textField_usuario;
	private JTextField textField_senha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setLocationRelativeTo(null);
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
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(64, 128, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel_inicio = new JPanel();
		panel_inicio.setBackground(new Color(192, 192, 192));
		panel_inicio.setBounds(91, 11, 260, 239);
		contentPane.add(panel_inicio);
		panel_inicio.setLayout(null);

		JLabel lblNewLabel = new JLabel("Usuário");
		lblNewLabel.setFont(new Font("Verdana", Font.BOLD, 11));
		lblNewLabel.setBounds(38, 44, 53, 20);
		panel_inicio.add(lblNewLabel);

		textField_usuario = new JTextField();
		textField_usuario.setBounds(38, 62, 190, 25);
		panel_inicio.add(textField_usuario);
		textField_usuario.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Senha");
		lblNewLabel_1.setFont(new Font("Verdana", Font.BOLD, 11));
		lblNewLabel_1.setBounds(38, 108, 46, 14);
		panel_inicio.add(lblNewLabel_1);

		textField_senha = new JTextField();
		textField_senha.setBounds(38, 123, 190, 25);
		panel_inicio.add(textField_senha);
		textField_senha.setColumns(10);

		JButton btnNewButton_login = new JButton("Login");
		btnNewButton_login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String usuario = textField_usuario.getText();
				String senha = textField_senha.getText();
				
				System.out.println(usuario);
				System.out.println(senha);

				if (usuario != null && !usuario.isEmpty() && senha != null && !senha.isEmpty()) {

					if (usuario.equals("Admin") && senha.equals("Root")) {
						JOptionPane.showMessageDialog(btnNewButton_login, "Login feito com sucesso!");
						dispose();
						Opcoes Opcoes = new Opcoes();
						Opcoes.setLocationRelativeTo(null);
						Opcoes.setVisible(true);
					} else {
						JOptionPane.showMessageDialog(btnNewButton_login, "Usuario nao cadastrado", "Aviso",
								JOptionPane.WARNING_MESSAGE);
					}

				} else {
					JOptionPane.showMessageDialog(btnNewButton_login, "Algum dos campos esta incorreto ou vazio!", "Aviso",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		
		btnNewButton_login.setForeground(new Color(0, 0, 0));
		btnNewButton_login.setBackground(new Color(64, 128, 128));
		btnNewButton_login.setFont(new Font("Verdana", Font.PLAIN, 11));
		btnNewButton_login.setBounds(73, 159, 113, 30);
		panel_inicio.add(btnNewButton_login);

		JButton btnNewButton_cadastro = new JButton("Cadastre-se");
		btnNewButton_cadastro.setForeground(new Color(0, 0, 0));
		btnNewButton_cadastro.setBackground(new Color(64, 128, 128));
		btnNewButton_cadastro.setFont(new Font("Verdana", Font.PLAIN, 11));
		btnNewButton_cadastro.setBounds(73, 200, 113, 30);
		panel_inicio.add(btnNewButton_cadastro);
	}
}
