package view;

import javax.swing.JOptionPane;

import controller.RedesController;

public class Main {

	public static void main(String[] args) {
		String opc = "";
		RedesController redesController = new RedesController();
		
		try {
			do {
				opc = JOptionPane.showInputDialog("Menu : \n1 - Configuração de IP \n2- Chamada de ping em IPv4 \n3 - Finalizar");
				switch (opc) {
					case "1":
						redesController.ip();
						break;
					case "2":
						redesController.ping();
						break;
					case "3": 
						JOptionPane.showMessageDialog(null, "Encerrando execução.");
						break;
					default: 
						JOptionPane.showMessageDialog(null, "Opção inválida!");
						break;
					}
			} while (!opc.equals("3"));
		} catch (NullPointerException e) {
			JOptionPane.showMessageDialog(null, "Encerrando execução.");
		}
	}
}
