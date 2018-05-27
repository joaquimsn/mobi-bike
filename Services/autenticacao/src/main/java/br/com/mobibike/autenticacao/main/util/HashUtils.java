package br.com.mobibike.autenticacao.main.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.com.mobibike.autenticacao.main.exceptions.ApplicationException;
import br.com.mobibike.autenticacao.main.exceptions.enums.TypeApplicationException;


public class HashUtils {
	private static Logger logger = LogManager.getLogger(HashUtils.class);

	private static final String SHA_256 = "SHA-256";
	private static final String MD5 = "MD5";

	private HashUtils() {
		throw new IllegalStateException("util class");
	}

	public static String gerarHashSHA256(String string) {
		return gerarHash(string, SHA_256);
	}

	public static String gerarHashMD5(String string) {
		return gerarHash(string, MD5);
	}

	private static String gerarHash(String string, String algoritimo) throws ApplicationException{
		Objects.requireNonNull(string, "Não é possivel");

		MessageDigest messageDigest = null;

		try {
			messageDigest = MessageDigest.getInstance(algoritimo);
			messageDigest.update(string.getBytes("UTF-8"));

			return convertByteArrayToHexString(messageDigest.digest());
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			logger.error(e);
			throw TypeApplicationException.GENERIC_ERROR.buildException();
		}
	}

	private static String convertByteArrayToHexString(byte[] arrayBytes) {
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < arrayBytes.length; i++) {
			stringBuilder.append(Integer.toString((arrayBytes[i] & 0xff) + 0x100, 16)
					.substring(1));
		}

		return stringBuilder.toString();
	}
}
