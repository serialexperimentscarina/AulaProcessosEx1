package controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.swing.JOptionPane;

public class RedesController {

		public RedesController() {
			super();
		}
		
		// Identifica e retorna o nome do Sistema Operacional
		private String os() {
			String os = System.getProperty("os.name");
			return os;
		}
		
		// Verifica o Sistema Operacional e, de acordo com o S.O., faz a chamada de configuração de IP.
		public void ip() {
			String process = (os().contains("Windows")) ? "ipconfig" : "ifconfig";
			try {
				Process p = Runtime.getRuntime().exec(process);
				InputStream fluxo = p.getInputStream();
				InputStreamReader leitor = new InputStreamReader(fluxo);
				BufferedReader buffer = new BufferedReader(leitor);
				String linha = buffer.readLine();
				String adaptador = "";
				StringBuffer resultado = new StringBuffer();
				if (os().contains("Windows")) {
					while (linha != null) {
						if (linha.contains("Adaptador") || linha.contains("adapter")) {
							adaptador = linha;
						}
						if (linha.contains("IPv4")) {
							resultado.append(adaptador + linha.split(":")[1] + "\n");
						}
						linha = buffer.readLine();
					}
				} else {
					while (linha != null) {
						if (!linha.startsWith(" ")) {
							adaptador = linha.split(" ")[0];
						}
						if (linha.contains("inet ")) {
							resultado.append(adaptador + " " + linha.split("inet ")[1].split(" ")[0] + "\n");
						}
						linha = buffer.readLine();
					}
				}
				buffer.close();
				leitor.close();
				fluxo.close();
				JOptionPane.showMessageDialog(null, resultado);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
			}
		}
		
		// Verifica o Sistema Operacional e, de acordo com o S.O. e, faz a chamada de ping em IPv4 com 10 iterações.
		public void ping() {
			String process = (os().contains("Windows")) ? "ping -4 -n 10 www.google.com.br" : "ping -4 -c 10 www.google.com.br";
			try {
				Process p = Runtime.getRuntime().exec(process);
				InputStream fluxo = p.getInputStream();
				InputStreamReader leitor = new InputStreamReader(fluxo);
				BufferedReader buffer = new BufferedReader(leitor);
				String linha = buffer.readLine();
				String ultLinha = "";
				JOptionPane.showMessageDialog(null, "Computando resultados... Por favor, clique em 'OK' e aguarde.");
				while (linha != null) {
					ultLinha = linha;
					linha = buffer.readLine();
				}
				buffer.close();
				leitor.close();
				fluxo.close();
				if (os().contains("Windows")) {
					JOptionPane.showMessageDialog(null, "Média = " + ultLinha.split(" = ")[3]);
				} else {
					JOptionPane.showMessageDialog(null, "Média = " + ultLinha.split("/")[4] + "ms");
					
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
			}
		}
}
