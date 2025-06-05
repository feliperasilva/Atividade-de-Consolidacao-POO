package bancario;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Objects;

public class Cliente implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String nome;
	private String cpf;
	private ArrayList<Conta> contas = new ArrayList<>();
	
	
	public Cliente(String cpf) {
		this.cpf = cpf;
	}
	
	public Cliente(String nome, String cpf) {
		this.nome = nome;
		this.cpf = cpf;
	}
	
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getCpf() {
		return cpf;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public void adicionarConta(Conta c) {
		if (contas.contains(c)) {
			System.out.println("Essa conta já está cadastrada");
		} else {
			contas.add(c);
		}
	}
	
	public void removerConta(Conta c) {
		if (contas.contains(c)) {
			contas.remove(c);
		} else {
			System.out.println("Conta não está cadrastrada para esse cliente");
		}
	}
	
	public BigDecimal consultarBalanco() {
		BigDecimal balanco = BigDecimal.ZERO;
		for (Conta conta : contas) {
			balanco = balanco.add(conta.getSaldo());
		} 
		return  balanco;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cpf);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(cpf, other.cpf);
	}

	@Override
	public String toString() {
		return "Cliente [nome=" + nome + ", cpf=" + cpf + ", contas=" + contas + "]";
	}
	
	public ArrayList<Conta> getContas() {
		return contas;
	}
}
