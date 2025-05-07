/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import model.*;
/**
 *
 * @author aluno.den
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;


public class TelaCadastro extends JFrame {
    private JTextField txtMarca, txtModelo, txtNumeroPortas, txtCilindrada;
    private JButton btnCadastrar, btnListar, btnAlterar, btnRemover, btnLimpar;
    private JComboBox<String> cbTipo;
    private JList<String> lista;
    private DefaultListModel<String> listModel;

    private ArrayList<veiculo> veiculos = new ArrayList<>();
    private int veiculoSelecionado = -1;

    public TelaCadastro() {
        setTitle("Cadastro de Veículos");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Painel de Campos
        JPanel painelCampos = new JPanel(new GridLayout(6, 2));
        painelCampos.add(new JLabel("Tipo:"));
        cbTipo = new JComboBox<>(new String[]{"Carro", "Moto"});
        painelCampos.add(cbTipo);

        painelCampos.add(new JLabel("Marca:"));
        txtMarca = new JTextField();
        painelCampos.add(txtMarca);

        painelCampos.add(new JLabel("Modelo:"));
        txtModelo = new JTextField();
        painelCampos.add(txtModelo);

        painelCampos.add(new JLabel("Número de Portas:"));
        txtNumeroPortas = new JTextField();
        painelCampos.add(txtNumeroPortas);

        painelCampos.add(new JLabel("Cilindrada:"));
        txtCilindrada = new JTextField();
        painelCampos.add(txtCilindrada);

        add(painelCampos, BorderLayout.NORTH);

        // Painel de Botões
        JPanel painelBotoes = new JPanel();
        btnCadastrar = new JButton("Cadastrar");
        btnListar = new JButton("Listar");
        btnAlterar = new JButton("Alterar");
        btnRemover = new JButton("Remover");
        btnLimpar = new JButton("Limpar Campos");

        painelBotoes.add(btnCadastrar);
        painelBotoes.add(btnListar);
        painelBotoes.add(btnAlterar);
        painelBotoes.add(btnRemover);
        painelBotoes.add(btnLimpar);

        add(painelBotoes, BorderLayout.SOUTH);

        // Lista
        listModel = new DefaultListModel<>();
        lista = new JList<>(listModel);
        add(new JScrollPane(lista), BorderLayout.CENTER);

        // Eventos
        cbTipo.addActionListener(e -> atualizarCampos());

        btnCadastrar.addActionListener(e -> cadastrarVeiculo());
        btnListar.addActionListener(e -> listarVeiculos());
        btnAlterar.addActionListener(e -> alterarVeiculo());
        btnRemover.addActionListener(e -> removerVeiculo());
        btnLimpar.addActionListener(e -> limparCampos());

        lista.addListSelectionListener(e -> selecionarVeiculo());

        atualizarCampos();
        setVisible(true);
    }

    private void atualizarCampos() {
        boolean isCarro = cbTipo.getSelectedItem().equals("Carro");
        txtNumeroPortas.setEnabled(isCarro);
        txtCilindrada.setEnabled(!isCarro);
    }

    private void cadastrarVeiculo() {
        String marca = txtMarca.getText();
        String modelo = txtModelo.getText();

        if (cbTipo.getSelectedItem().equals("Carro")) {
            int portas = Integer.parseInt(txtNumeroPortas.getText());
            veiculos.add(new carro(marca, modelo, portas));
        } else {
            int cilindrada = Integer.parseInt(txtCilindrada.getText());
            veiculos.add(new moto(marca, modelo, cilindrada));
        }

        limparCampos();
        listarVeiculos();
    }

    private void listarVeiculos() {
        listModel.clear();
        for (veiculo v : veiculos) {
            listModel.addElement(v.getDescricao());
        }
    }

    private void alterarVeiculo() {
        if (veiculoSelecionado >= 0) {
            String marca = txtMarca.getText();
            String modelo = txtModelo.getText();

            veiculo v = veiculos.get(veiculoSelecionado);
            v.setMarca(marca);
            v.setModelo(modelo);

            if (v instanceof carro) {
                ((carro) v).setNumeroDePortas(Integer.parseInt(txtNumeroPortas.getText()));
            } else if (v instanceof moto) {
                ((moto) v).setCilindrada(Integer.parseInt(txtCilindrada.getText()));
            }

            listarVeiculos();
        }
    }

    private void removerVeiculo() {
        if (veiculoSelecionado >= 0) {
            veiculos.remove(veiculoSelecionado);
            listarVeiculos();
            limparCampos();
        }
    }

    private void limparCampos() {
        txtMarca.setText("");
        txtModelo.setText("");
        txtNumeroPortas.setText("");
        txtCilindrada.setText("");
        veiculoSelecionado = -1;
    }

    private void selecionarVeiculo() {
        veiculoSelecionado = lista.getSelectedIndex();
        if (veiculoSelecionado >= 0) {
            veiculo v = veiculos.get(veiculoSelecionado);
            txtMarca.setText(v.getMarca());
            txtModelo.setText(v.getModelo());
            if (v instanceof carro) {
                cbTipo.setSelectedItem("Carro");
                txtNumeroPortas.setText(String.valueOf(((carro) v).getNumeroDePortas()));
            } else {
                cbTipo.setSelectedItem("Moto");
                txtCilindrada.setText(String.valueOf(((moto) v).getCilindrada()));
            }
            atualizarCampos();
        }
    }
}
