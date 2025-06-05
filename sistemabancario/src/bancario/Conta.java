package bancario;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Conta implements Serializable{

	private static final long serialVersionUID = 1L;
	private String numero;
	private BigDecimal saldo;
	private LocalDate dataCriacao;
	private boolean status;
	
	public Conta (String numero) {
		this.numero = numero;
		this.saldo = BigDecimal.ZERO;
		this.dataCriacao = LocalDate.now();
		this.status = true;
	}
	
	public String getNumero() {
		return numero;
	}
	
	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	public BigDecimal getSaldo() {
		return saldo;
	}
	
	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}
	
	public LocalDate getDataCriacao() {
		return dataCriacao;
	}
	
	public void setDataCriacao(LocalDate dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	
	public boolean isStatus() {
		return status;
	}
	
	public void setStatus(boolean status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Conta [numero=" + numero + ", saldo=" + saldo + ", dataCriacao=" + dataCriacao + ", status=" + status
				+ "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(numero);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Conta other = (Conta) obj;
		return Objects.equals(numero, other.numero);
	}
	
	public void realizarDeposito(BigDecimal valor) {
		if (this.status && valor.compareTo(BigDecimal.ZERO) > 0) {
			this.saldo = this.saldo.add(valor);
		} else if (!this.status) {
			System.out.println("Status da conta: Desativada");
		} else {
			System.out.println("Para realizar o deposito insira um valor maior que zero");
		}
	}
	
	public void realizarSaque (BigDecimal valor) {
		if (this.status && valor.compareTo(BigDecimal.ZERO) > 0 && valor.compareTo(this.saldo) <= 0) {
			this.saldo = this.saldo.subtract(valor);
		} else if (!this.status) {
			System.out.println("Status da conta: Desativada");
		} else {
			System.out.println("Para realizar o saque insira um valor menor ou igual ao seu saldo");
		}
	}
	
	public void realizarTransferencia (BigDecimal valor, Conta destino) {
		if (this.status && destino.status && valor.compareTo(BigDecimal.ZERO ) > 0 && valor.compareTo(this.saldo) <= 0) {
			this.saldo = this.saldo.subtract(valor);
			destino.saldo = destino.saldo.add(valor);
		} else {
			System.out.println("Não foi possível realiza a transferência verifique o status da conta, saldo ou valor");
		}
	}
	
}




