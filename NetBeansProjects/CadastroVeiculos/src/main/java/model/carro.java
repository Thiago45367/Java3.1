/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author aluno.den
 */
public class carro extends veiculo  {
    private int numeroDePortas;

    public carro(String marca, String modelo, int numeroDePortas) {
        super(marca, modelo);
        this.numeroDePortas = numeroDePortas;
    }

    public int getNumeroDePortas() { return numeroDePortas; }
    public void setNumeroDePortas(int numeroDePortas) { this.numeroDePortas = numeroDePortas; }

    
    public String getDescricao() {
        return "Carro: " + getMarca() + " " + getModelo() + ", Portas: " + numeroDePortas;
    }
}
