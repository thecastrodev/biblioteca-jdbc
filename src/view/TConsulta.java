package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import model.dao.LivroDao;
import model.entities.Livro;
import model.dao.DaoFactory;

public class TConsulta extends JFrame {

    private JPanel contentPane;
    private JTextField textField;
    private JTable table;
    private LivroDao livroDao;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TConsulta frame = new TConsulta();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public TConsulta() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 614, 392);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Crie uma instância do LivroDao
        livroDao = DaoFactory.createLivroDao();

        JButton btn_MostrarConsulta = new JButton("Consultar");
        btn_MostrarConsulta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Ao clicar no botão "Consultar", execute o método findAll do LivroDao
                List<Livro> livros = livroDao.findAll();

                // Limpe a tabela antes de adicionar os novos dados
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                model.setRowCount(0);

                // Adicione os livros à tabela
                for (Livro livro : livros) {
                    Object[] rowData = { livro.getISBN(), livro.getTitulo(), livro.getEditora() };
                    model.addRow(rowData);
                }
            }
        });
        btn_MostrarConsulta.setBounds(442, 28, 89, 25);
        contentPane.add(btn_MostrarConsulta);

        textField = new JTextField();
        textField.setBounds(48, 36, 176, 20);
        contentPane.add(textField);
        textField.setColumns(10);

        JLabel lblNewLabel = new JLabel("Pesquisar");
        lblNewLabel.setBounds(48, 21, 73, 14);
        contentPane.add(lblNewLabel);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(48, 84, 524, 233);
        contentPane.add(scrollPane);

        table = new JTable();
        table.setModel(new DefaultTableModel(
            new Object[][] {},
            new String[] { "ISBN", "Título", "Autor" }
        ));
        scrollPane.setViewportView(table);
    }
}