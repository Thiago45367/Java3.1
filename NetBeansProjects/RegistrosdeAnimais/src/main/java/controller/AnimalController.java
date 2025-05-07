/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.AnimalDAO;
import model.Animal;

import java.util.List;

public class AnimalController {
      private AnimalDAO dao = new AnimalDAO();

    public void adicionar(Animal a) throws Exception {
        dao.inserir(a);
    }

    public List<Animal> obterTodos() throws Exception {
        return dao.listar();
    }

    public void atualizar(Animal a) throws Exception {
        dao.atualizar(a);
    }

    public void excluir(int id) throws Exception {
        dao.remover(id);
    }
}
