package controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

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
				if (os().contains("Windows")) {
					while (linha != null) {
						if (linha.contains("Adaptador") || linha.contains("adapter")) {
							adaptador = linha;
						}
						if (linha.contains("IPv4")) {
							System.out.println(adaptador + linha.split(":")[1]);
						}
						linha = buffer.readLine();
					}
				} else {
					while (linha != null) {
						if (!linha.startsWith(" ")) {
							adaptador = linha.split(" ")[0];
						}
						if (linha.contains("inet ")) {
							System.out.println(adaptador + " " + linha.split("inet ")[1].split(" ")[0]);
						}
						linha = buffer.readLine();
					}
				}
				buffer.close();
				leitor.close();
				fluxo.close();
			} catch (Exception e) {
				e.printStackTrace();
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
				while (linha != null) {
					ultLinha = linha;
					linha = buffer.readLine();
				}
				buffer.close();
				leitor.close();
				fluxo.close();
				if (os().contains("Windows")) {
					System.out.println("\nMédia = " + ultLinha.split(" = ")[3]);
				} else {
					System.out.println("\nMédia = " + ultLinha.split("/")[4] + "ms");
					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
}
