package Controller;

import java.util.concurrent.Semaphore;

public class ThreadBilheteria extends Thread {

	private int id;
	private Semaphore semaforo;
	private static int ingresso = 100;

	public ThreadBilheteria(int idPessoa, Semaphore semaforo) {
		this.id = idPessoa;
		this.semaforo = semaforo;
	}

	@Override
	public void run() {
		login();
	}

	private void login() {
		System.out.println("Pessoa " + id + " Efetue seu login");
		System.out.println("Pessoa " + id + " Digite seu �suario : ");
		System.out.println("Pessoa " + id + " Digite sua senha : ");
		try {
			int tempo = (int) (Math.random() * 1950) + 51;
			System.out.println("Pessoa " + id + " Aguade a autentifica��o...");
			sleep(tempo);
			if (tempo >= 1000) {
				System.out.println("Pessoa " + id + "Login realizado com sucesso, prossiga para a prox�ma etapa");
				compra();
			} else {
				System.out.println(
						"Pessoa " + id + " Erro de carregamento \n N�o ser� possivel realizar a compra do ingresso");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	private void compra() {
		System.out.println("Pessoa " + id + " Bem-vindo ao processo de compra do show ");
		try {
			int tempo = (int) (Math.random() * 3001) + 1001;
			System.out.println("Aguarde a autentifica��o de compra");
			sleep(tempo);
			if (tempo <= 2500) {
				System.out.println("Autentifica��o autorizada ");
				finalizar();
			} else {
				System.out.println(
						"Pessoa " + id + " Seu tempo de sess�o expirou, e n�o ser� possivel realizar a compra");
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void finalizar() {
		try {
			semaforo.acquire();
			int compras = (int) (Math.random() * 4) + 1;
			if (compras <= ingresso) {
				System.out.println("Parabens Pessoa" + id + " Voc� comprou " + compras + " ingressos");
				ingresso -= compras;
				System.out.println("Ainda restam " + ingresso + " ingressos");
			} else {
				System.out.println(
						"Infelizmente n�o temos esse tanto de ingressos disponivel. N�o ser� possivel realizar a compra ");
			}
			if (ingresso == 0) {
				System.out.println("OS ingressos acabaram");
			}

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			semaforo.release();
		}

	}

}
