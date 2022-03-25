package View;

import java.util.concurrent.Semaphore;

import Controller.ThreadBilheteria;

public class Principal {

	public static void main(String[] args) {
		int permissoes = 1;
		Semaphore semaforo = new Semaphore(permissoes);

		for (int idPessoa = 1; idPessoa <= 300; idPessoa++) {
			Thread pessoa = new ThreadBilheteria(idPessoa, semaforo);
			pessoa.start();
		}

	}
}
