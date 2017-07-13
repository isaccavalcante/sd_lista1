package Questao6;

public class SingletonCipher {
	// Variável estática que conterá a instância do método
	private static SingletonCipher instance;

	static {
		// Operações de inicialização da classe
	}

	// Construtor privado. Suprime o construtor público padrão
	private SingletonCipher() {
	}

	// Método público estático de acesso único ao objeto
	public static SingletonCipher getInstance(){

		if(instance == null) 
		{
			inicializaInstancia();
			// O valor é retornado para quem está pedindo

		}
		return instance;
		// Retorna o a instância do objeto

	}

	/*
	 * Este metodo é sincronizado para evitar que devido a concorrencia sejam criados mais de
	 * uma instancia.
	 */
	private static synchronized void inicializaInstancia() 
	{
		if (instance == null) 
		{
			instance = new SingletonCipher();
		}
	}
        
        public static String cifra(String mensagem, int chave){
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < mensagem.length(); i++) {
            char c = (char)(mensagem.charAt(i) + chave);
            builder.append(c);
        }

        return builder.toString();
        }

        public static String decifra(String mensagem, int chave){
            return cifra(mensagem, -chave);
        }
        
}
