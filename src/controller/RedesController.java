package controller;

public class RedesController {

		public RedesController() {
			super();
		}
		
		// Identifica e retorna o nome do Sistema Operacional
		private String os() {
			String os = System.getProperty("os.name");
			return os;
		}
}
