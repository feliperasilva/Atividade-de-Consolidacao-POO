package bancario;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;

import persistencia.PersistenciaArquivo;

public class Programa {
	
	public static void main(String[] args) {
		PersistenciaArquivo pa = new PersistenciaArquivo();
		Scanner sc = new Scanner(System.in);
		
		int opcao;
		
		do {
            System.out.println("1 - Cadastrar cliente");
            System.out.println("2 - Listar clientes cadastrados");
            System.out.println("3 - Consultar cliente por CPF");
            System.out.println("4 - Remover cliente");
            System.out.println("5 - Criar conta e associar ao cliente");
            System.out.println("6 - Listar contas de um cliente");
            System.out.println("7 - Remover conta de um cliente");
            System.out.println("8 - Realizar depósito");
            System.out.println("9 - Realizar saque");
            System.out.println("10 - Efetuar transferência");
            System.out.println("11 - Consultar saldo de uma conta");
            System.out.println("12 - Consultar balanço total do cliente");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            
            opcao = sc.nextInt();
            sc.nextLine();
            
            switch (opcao) {
            case 1: {
                System.out.print("Digite o nome do cliente: ");
                String nome = sc.nextLine();
                
                System.out.print("Digite o CPF do cliente: ");
                String cpf = sc.nextLine();
                
                Cliente novoCliente = new Cliente(nome, cpf);
                pa.cadastrarCliente(novoCliente);
                
                System.out.println("Cliente cadastrado com sucesso!");
                break;
            }
      
            case 2: {
            	 ArrayList<Cliente> clientes = pa.getClientesCadastrados();
            	    if (clientes.isEmpty()) {
            	        System.out.println("Nenhum cliente cadastrado.");
            	    } else {
            	        System.out.println("Clientes cadastrados:");
            	        for (Cliente cliente : clientes) {
            	            System.out.println(cliente);
            	        }
            	    }
            	    break;
            }
            
            case 3: {
            	System.out.println("Digite o CPF do cliente");
            	String cpf = sc.nextLine();
            	
            	Cliente temp = pa.consultarCliente(cpf);
            	if (temp!= null) {
            		System.out.println("Cliente Encontrado!");
            		System.out.println(temp);
            	} else {
            		System.out.println("Cliente não encontrado");
            	}
            	break;
            }
            
            case 4: {
                System.out.print("Digite o CPF do cliente a ser removido: ");
                String cpf = sc.nextLine();
                
                Cliente temp = pa.consultarCliente(cpf);
                
                if (temp != null) {
                	pa.removerCliente(temp);
                	System.out.println("Cliente removido com sucesso");
                } else {
                	System.out.println("Cliente não encontrado");
                }
                break;
            }
            
            case 5: {
            	System.out.println("Digite o CPF do cliente: ");
            	String cpf = sc.nextLine();
            	
            	Cliente temp = pa.consultarCliente(cpf);
            	
            	if (temp != null) {
            		System.out.println("Digite o número da nova conta: ");
                	String numero = sc.nextLine();
                	Conta novaConta = new Conta(numero);
                	
                	 temp.adicionarConta(novaConta);
                     pa.atualizarCliente(temp);
                     System.out.println("Conta criada e associada ao cliente com sucesso");
            	} else {
            		System.out.println("Cliente não encontrado.");
            	}
            	break;
            }
            
            case 6: {
            	System.out.println("Digite o CPF do cliente: ");
            	String cpf = sc.nextLine();
            	
            	Cliente temp = pa.consultarCliente(cpf);
            	
            	if (temp != null) {
            		if (temp.getContas().isEmpty()) {
            			System.out.println("Esse cliente não possui contas");
            		} else {
            			System.out.println("Contas do Cliente: ");
            			for (Conta conta : temp.getContas()) {
							System.out.println(conta);
						}
            		}
            	} else {
            		System.out.println("Cliente não encontrado");
            	}
            	break;
            }
            
            case 7: {
                System.out.println("Digite o CPF do cliente: ");
                String cpf = sc.nextLine();

                Cliente temp = pa.consultarCliente(cpf);

                if (temp != null) {
                    if (!temp.getContas().isEmpty()) {
                        System.out.println("Digite o número da conta a remover:");
                        String numero = sc.nextLine();

                        Conta conta = new Conta(numero);

                        if (temp.getContas().contains(conta)) {
                            temp.removerConta(conta);
                            pa.atualizarCliente(temp);
                            System.out.println("Conta removida com sucesso");
                        } else {
                            System.out.println("Essa conta não está cadastrada para esse cliente");
                        }
                    } else {
                        System.out.println("Esse cliente não possui contas");
                    }
                } else {
                    System.out.println("Cliente não encontrado");
                }
                break;
            }
            
            case 8: {
                System.out.println("Digite o CPF do cliente:");
                String cpf = sc.nextLine();

                Cliente cliente = pa.consultarCliente(cpf);

                if (cliente != null) {
                    if (!cliente.getContas().isEmpty()) {
                        System.out.println("Digite o número da conta:");
                        String numeroConta = sc.nextLine();

                        Conta conta = new Conta(numeroConta);

                        if (cliente.getContas().contains(conta)) {
                            int index = cliente.getContas().indexOf(conta);
                            Conta contaReal = cliente.getContas().get(index);

                            System.out.println("Digite o valor para depósito:");
                            BigDecimal valor = sc.nextBigDecimal();
                            sc.nextLine();

                            contaReal.realizarDeposito(valor);

                            pa.atualizarCliente(cliente);

                            System.out.println("Depósito realizado com sucesso!");
                        } else {
                            System.out.println("Conta não encontrada para este cliente.");
                        }
                    } else {
                        System.out.println("Este cliente não possui contas.");
                    }
                } else {
                    System.out.println("Cliente não encontrado.");
                }
                break;
            }

            case 9: {
                System.out.println("Digite o CPF do cliente:");
                String cpf = sc.nextLine();

                Cliente cliente = pa.consultarCliente(cpf);

                if (cliente != null) {
                    if (!cliente.getContas().isEmpty()) {
                        System.out.println("Digite o número da conta:");
                        String numeroConta = sc.nextLine();

                        Conta conta = new Conta(numeroConta);

                        if (cliente.getContas().contains(conta)) {
                            int index = cliente.getContas().indexOf(conta);
                            Conta contaReal = cliente.getContas().get(index);

                            System.out.println("Digite o valor para saque:");
                            BigDecimal valor = sc.nextBigDecimal();
                            sc.nextLine();

                            contaReal.realizarSaque(valor);

                            pa.atualizarCliente(cliente);

                            System.out.println("Saque realizado com sucesso!");
                        } else {
                            System.out.println("Conta não encontrada para este cliente.");
                        }
                    } else {
                        System.out.println("Este cliente não possui contas.");
                    }
                } else {
                    System.out.println("Cliente não encontrado.");
                }
                break;
            }

            case 10: {
                System.out.println("Digite o CPF do cliente de origem:");
                String cpfOrigem = sc.nextLine();

                Cliente clienteOrigem = pa.consultarCliente(cpfOrigem);

                if (clienteOrigem != null) {
                    System.out.println("Digite o número da conta de origem:");
                    String numContaOrigem = sc.nextLine();

                    Conta contaOrigem = new Conta(numContaOrigem);

                    if (clienteOrigem.getContas().contains(contaOrigem)) {
                        int indexOrigem = clienteOrigem.getContas().indexOf(contaOrigem);
                        Conta contaRealOrigem = clienteOrigem.getContas().get(indexOrigem);

                        System.out.println("Digite o CPF do cliente de destino:");
                        String cpfDestino = sc.nextLine();

                        Cliente clienteDestino = pa.consultarCliente(cpfDestino);

                        if (clienteDestino != null) {
                            System.out.println("Digite o número da conta de destino:");
                            String numContaDestino = sc.nextLine();

                            Conta contaDestino = new Conta(numContaDestino);

                            if (clienteDestino.getContas().contains(contaDestino)) {
                                int indexDestino = clienteDestino.getContas().indexOf(contaDestino);
                                Conta contaRealDestino = clienteDestino.getContas().get(indexDestino);

                                System.out.println("Digite o valor para transferir:");
                                BigDecimal valor = sc.nextBigDecimal();
                                sc.nextLine();

                                contaRealOrigem.realizarTransferencia(valor, contaRealDestino);

                                pa.atualizarCliente(clienteOrigem);
                                pa.atualizarCliente(clienteDestino);

                                System.out.println("Transferência realizada com sucesso!");
                            } else {
                                System.out.println("Conta de destino não encontrada.");
                            }
                        } else {
                            System.out.println("Cliente de destino não encontrado.");
                        }
                    } else {
                        System.out.println("Conta de origem não encontrada.");
                    }
                } else {
                    System.out.println("Cliente de origem não encontrado.");
                }
                break;
            }

            case 11: {
                System.out.println("Digite o CPF do cliente:");
                String cpf = sc.nextLine();

                Cliente cliente = pa.consultarCliente(cpf);

                if (cliente != null) {
                    if (!cliente.getContas().isEmpty()) {
                        System.out.println("Digite o número da conta:");
                        String numeroConta = sc.nextLine();

                        Conta conta = new Conta(numeroConta);

                        if (cliente.getContas().contains(conta)) {
                            int index = cliente.getContas().indexOf(conta);
                            Conta contaReal = cliente.getContas().get(index);

                            System.out.println("Saldo atual da conta: " + contaReal.getSaldo());
                        } else {
                            System.out.println("Conta não encontrada para este cliente.");
                        }
                    } else {
                        System.out.println("Este cliente não possui contas.");
                    }
                } else {
                    System.out.println("Cliente não encontrado.");
                }
                break;
            }

            case 12: {
                System.out.println("Digite o CPF do cliente:");
                String cpf = sc.nextLine();

                Cliente cliente = pa.consultarCliente(cpf);

                if (cliente != null) {
                    BigDecimal balanco = cliente.consultarBalanco();
                    System.out.println("Balanço total do cliente: " + balanco);
                } else {
                    System.out.println("Cliente não encontrado.");
                }
                break;
            }

            }
            
		} while (opcao != 0);
		
		sc.close();
	}
}
