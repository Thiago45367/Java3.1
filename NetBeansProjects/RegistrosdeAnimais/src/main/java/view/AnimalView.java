/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import controller.AnimalController;
import model.Animal;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class AnimalView extends JFrame {
     private JTextField txtId, txtNome, txtEspecie, txtIdade;
    private JButton btnSalvar, btnAtualizar, btnExcluir, btnListar, btnLimpar;
    private JList<String> listaAnimais;
    private DefaultListModel<String> listModel;
    private List<Animal> animais;

    private AnimalController controller = new AnimalController();

    public AnimalView() {
        setTitle("Cadastro de Animais");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel painelCampos = new JPanel(new GridLayout(5, 2));
        txtId = new JTextField();
        txtId.setEditable(false);

        painelCampos.add(new JLabel("ID:"));
        painelCampos.add(txtId);
        painelCampos.add(new JLabel("Nome:"));
        txtNome = new JTextField();
        painelCampos.add(txtNome);
        painelCampos.add(new JLabel("Espécie:"));
        txtEspecie = new JTextField();
        painelCampos.add(txtEspecie);
        painelCampos.add(new JLabel("Idade:"));
        txtIdade = new JTextField();
        painelCampos.add(txtIdade);

        add(painelCampos, BorderLayout.NORTH);

        JPanel painelBotoes = new JPanel();
        btnSalvar = new JButton("Salvar");
        btnAtualizar = new JButton("Atualizar");
        btnExcluir = new JButton("Excluir");
        btnListar = new JButton("Listar");
        btnLimpar = new JButton("Limpar");

        painelBotoes.add(btnSalvar);
        painelBotoes.add(btnAtualizar);
        painelBotoes.add(btnExcluir);
        painelBotoes.add(btnListar);
        painelBotoes.add(btnLimpar);

        add(painelBotoes, BorderLayout.SOUTH);

        listModel = new DefaultListModel<>();
        listaAnimais = new JList<>(listModel);
        add(new JScrollPane(listaAnimais), BorderLayout.CENTER);

        // Ações
        btnSalvar.addActionListener(e -> salvar());
        btnAtualizar.addActionListener(e -> atualizar());
        btnExcluir.addActionListener(e -> excluir());
        btnListar.addActionListener(e -> listar());
        btnLimpar.addActionListener(e -> limpar());

        listaAnimais.addListSelectionListener(e -> selecionar());

        setVisible(true);
    }

    private void salvar() {
        try {
            Animal a = new Animal();
            a.setNome(txtNome.getText());
            a.setEspecie(txtEspecie.getText());
            a.setIdade(Integer.parseInt(txtIdade.getText()));
            controller.adicionar(a);
            listar();
            limpar();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
        }
    }

    private void atualizar() {
        try {
            Animal a = new Animal();
            a.setId(Integer.parseInt(txtId.getText()));
            a.setNome(txtNome.getText());
            a.setEspecie(txtEspecie.getText());
            a.setIdade(Integer.parseInt(txtIdade.getText()));
            controller.atualizar(a);
            listar();
            limpar();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
        }
    }

    private void excluir() {
        try {
            int id = Integer.parseInt(txtId.getText());
            controller.excluir(id);
            listar();
            limpar();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
        }
    }

    private void listar() {
        try {
            listModel.clear();
            animais = controller.obterTodos();
            for (Animal a : animais) {
                listModel.addElement(a.getId() + " - " + a.getNome() + " (" + a.getEspecie() + ", " + a.getIdade() + " anos)");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
        }
    }

    private void limpar() {
        txtId.setText("");
        txtNome.setText("");
        txtEspecie.setText("");
        txtIdade.setText("");
    }

    private void selecionar() {
        int index = listaAnimais.getSelectedIndex();
        if (index >= 0 && index < animais.size()) {
            Animal a = animais.get(index);
            txtId.setText(String.valueOf(a.getId()));
            txtNome.setText(a.getNome());
            txtEspecie.setText(a.getEspecie());
            txtIdade.setText(String.valueOf(a.getIdade()));
        }
    }
}
