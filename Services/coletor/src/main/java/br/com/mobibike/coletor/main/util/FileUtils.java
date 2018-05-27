package br.com.mobibike.coletor.main.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;

public class FileUtils {

	public FileUtils() {
		throw new IllegalStateException("Util class");
	}

	/**
	 * Realiza o encode do arquivo passado
	 * @param fileName nome do arquivo com path
	 * @return Bytes 
	 * @throws FileNotFoundException | IOException
	 */
	public static byte[] readFile(String fileName) throws FileNotFoundException, IOException {
		FileInputStream fis = new FileInputStream(fileName);
		
		byte[] encoded = new byte[(int) fis.getChannel().size()];
		fis.read(encoded);
		fis.close();
		return encoded;
	}
	
	/**
	 * Retorna uma instancia de um arquivo
	 * @param fileName nome do arquivo com path
	 * @return {@code File}  
	 * @throws NullPointerException se o parametro fileName for null
	 */
	public static File getFile(String fileName) {
		Objects.requireNonNull(fileName);
		
		return new File(fileName);
	}
	
	
	/**
	 * Verifica se o arquivo existe no sistema de arquivos
	 * @param fileName nome do arquivo com path
	 * @return true se o arquivo existir false se n�o existir
	 * @throws SecurityException
	 */
	public static boolean exists(String fileName){
		return new File(fileName).exists();
	}
}
