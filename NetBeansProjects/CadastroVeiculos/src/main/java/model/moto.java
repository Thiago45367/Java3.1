/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author aluno.den
 */
public class moto extends veiculo  {
    private int cilindrada;

    public moto(String marca, String modelo, int cilindrada) {
        super(marca, modelo);
        this.cilindrada = cilindrada;
    }

    public int getCilindrada() { return cilindrada; }
    public void setCilindrada(int cilindrada) { this.cilindrada = cilindrada; }

   
    
    public String getDescricao() {
        return "Moto: " + getMarca() + " " + getModelo() + ", Cilindrada: " + cilindrada + "cc";
    }
}
