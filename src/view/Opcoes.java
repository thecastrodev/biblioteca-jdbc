package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Opcoes extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Opcoes frame = new Opcoes();
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
	public Opcoes() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(64, 128, 128));
		panel.setBounds(0, 0, 384, 261);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton_consultar = new JButton("Consultar Livros");
		btnNewButton_consultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//realizarConsulta();
			}
		});
		btnNewButton_consultar.setFont(new Font("Verdana", Font.PLAIN, 11));
		btnNewButton_consultar.setBounds(109, 67, 161, 35);
		panel.add(btnNewButton_consultar);
		
		JButton btnNewButton_deslogar = new JButton("Sair");
		btnNewButton_deslogar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			Login Login = new Login();
			Login.setLocationRelativeTo(null);
			Login.setVisible(true);
			}
		});
		btnNewButton_deslogar.setFont(new Font("Verdana", Font.PLAIN, 11));
		btnNewButton_deslogar.setBounds(10, 227, 89, 23);
		panel.add(btnNewButton_deslogar);
		
		JButton btnNewButton_minhas_reservas = new JButton("Minhas Reservas");
		btnNewButton_minhas_reservas.setFont(new Font("Verdana", Font.PLAIN, 11));
		btnNewButton_minhas_reservas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_minhas_reservas.setBounds(109, 134, 161, 35);
		panel.add(btnNewButton_minhas_reservas);
	}
	
  /*  private void realizarConsulta() {
        Connection connection = PROPERTIES;
        if (connection != null) {
            try {
                String consulta = "SELECT * FROM equipe494627.livro";
                PreparedStatement statement = connection.prepareStatement(consulta);
                ResultSet resultado = statement.executeQuery();

                while (resultado.next()) {
                    // Processar os dados do resultado da consulta
                    // Exemplo: String valor = resultado.getString("coluna");
                }

                resultado.close();
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Falha na conexão com o banco de dados.","Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public static void main1(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Opcoes Opcoes = new Opcoes();
            Opcoes.setVisible(true);
        });
    }*/
}
