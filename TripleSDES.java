package default_package;


public class TripleSDES {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String test = tripleSDES("0000000000","0000000000","00000000");
		String test1 = tripleSDES("1000101110", "0110101110", "11010111");
		String test2 = tripleSDES("1000101110", "0110101110", "10101010");
		String test3 = tripleSDES("1111111111","1111111111","10101010");

		System.out.println("                                   Encryption");
		System.out.println("Raw Key 1         " + "Raw Key 2             " + "Plain Text                " + "Cipher Text ");
		System.out.println("0000000000        " + "0000000000            " + "00000000                  " + test);
		System.out.println("1000101110        " + "0110101110            " + "11010111                  " + test1);
		System.out.println("1000101110        " + "0110101110            " + "10101010                  " + test2);
		System.out.println("1111111111        " + "1111111111            " + "10101010                  " + test3);
		
		String decryptiontest = decryptionTripleSDES("1000101110","0110101110","11100110");
		String decryptiontest1 = decryptionTripleSDES("1011101111","0110101110","01010000");
		String decryptiontest2 = decryptionTripleSDES("0000000000","0000000000","10000000");
		String decryptiontest3 = decryptionTripleSDES("1111111111","1111111111","10010010");
		
		System.out.println("\n                                   Decryption");
		System.out.println("Raw Key 1         " + "Raw Key 2             " + "Plain Text                " + "Cipher Text ");
		System.out.println("1000101110        " + "0110101110            " + decryptiontest+"                  " + "11100110");
		System.out.println("1011101111        " + "0110101110            " + decryptiontest1+"                  " + "01010000");
		System.out.println("0000000000        " + "0000000000            " + decryptiontest2+"                  " + "10000000");
		System.out.println("1111111111        " + "1111111111            " + decryptiontest3+"                  " + "10010010");
		

		

	}
	public static String tripleSDES(String key1, String key2, String plainText) {
		String firstSDES = SDES.SDES(key1,plainText);
		String secondSDES = SDES.SDESDecryption(key2,firstSDES);
		String lastSDES = SDES.SDES(key1,secondSDES);
		
		return lastSDES;
	}
	public static String decryptionTripleSDES(String key1, String key2, String cipherText) {
		String firstSDES = SDES.SDESDecryption(key1, cipherText);
		String secondSDES = SDES.SDES(key2, firstSDES);
		String lastSDES = SDES.SDESDecryption(key1, secondSDES);
		return lastSDES;
	}

}
