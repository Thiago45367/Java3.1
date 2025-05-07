/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.Animal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AnimalDAO {
     public void inserir(Animal animal) throws Exception {
        Connection conn = Conexao.getConnection();
        String sql = "INSERT INTO animal (nome, especie, idade) VALUES (?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, animal.getNome());
        stmt.setString(2, animal.getEspecie());
        stmt.setInt(3, animal.getIdade());
        stmt.executeUpdate();
        conn.close();
    }

    public List<Animal> listar() throws Exception {
        List<Animal> lista = new ArrayList<>();
        Connection conn = Conexao.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM animal");
        while (rs.next()) {
            Animal a = new Animal();
            a.setId(rs.getInt("id"));
            a.setNome(rs.getString("nome"));
            a.setEspecie(rs.getString("especie"));
            a.setIdade(rs.getInt("idade"));
            lista.add(a);
        }
        conn.close();
        return lista;
    }

    public void atualizar(Animal animal) throws Exception {
        Connection conn = Conexao.getConnection();
        String sql = "UPDATE animal SET nome=?, especie=?, idade=? WHERE id=?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, animal.getNome());
        stmt.setString(2, animal.getEspecie());
        stmt.setInt(3, animal.getIdade());
        stmt.setInt(4, animal.getId());
        stmt.executeUpdate();
        conn.close();
    }

    public void remover(int id) throws Exception {
        Connection conn = Conexao.getConnection();
        String sql = "DELETE FROM animal WHERE id=?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        stmt.executeUpdate();
        conn.close();
    }
}
