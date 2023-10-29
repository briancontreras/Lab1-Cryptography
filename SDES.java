package project1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SDES {

	public static void main(String[] args) {
		System.out.println("           All listed TestCases");
		System.out.println("Raw Key        Plaintext          CipherTest");
		String firstTestCase = SDES("0000000000","10101010");
		System.out.println("0000000000"+"      "+"10101010           "+firstTestCase);
		String secondTestCase = SDES("1110001110","10101010");
		System.out.println("1110001110"+"      "+"10101010           "+secondTestCase);
		String thirdTestCase  = SDES("1110001110","01010101");
		System.out.println("1110001110"+"      "+"01010101           "+thirdTestCase);
		String fourthTestCase = SDES("1111111111","10101010");
		System.out.println("1111111111"+"      "+"10101010           "+fourthTestCase);
		
		System.out.println("\n           Solved TestCases");
		System.out.println("Raw Key        Plaintext          CipherTest");
		System.out.println("0000000000"+"      "+"00000000           "+SDES("0000000000","00000000"));
		System.out.println("1111111111"+"      "+"11111111           "+SDES("1111111111","11111111"));
		System.out.println("0000011111"+"      "+"00000000           "+SDES("0000011111","00000000"));
		System.out.println("0000011111"+"      "+"11111111           "+SDES("0000011111","11111111"));
		System.out.println("1000101110"+"      "+"11111111           "+SDES("1000101110","01010000"));

		System.out.println("\nSolved with Decryption: 1st row(Decryption)   2nd row(Encryption)");
		System.out.println("1000101110"+"      "+SDESDecryption("1000101110","00011100")+"           "+ "00011100");
		System.out.println("1000101110"+"      "+"00111000           "+SDES("1000101110","00111000"));
		System.out.println("1000101110"+"      "+SDESDecryption("1000101110","11000010")+"           "+ "11000010");
		System.out.println("1000101110"+"      "+"00001100           "+SDES("1000101110","00001100"));
		System.out.println("0010011111"+"      "+SDESDecryption("0010011111","10011101")+"           "+ "10011101");
		System.out.println("0010011111"+"      "+"11111100           "+SDES("0010011111","11111100"));
		System.out.println("0010011111"+"      "+SDESDecryption("0010011111","10010000")+"           "+ "10010000");
		System.out.println("0010011111"+"      "+"01110011           "+SDES("0010011111","01110011"));


		

		
	}
	public static String leftShift(String k, int s){
		//create String Builder to split number
		if(s == 1) {
			StringBuilder sb = new StringBuilder();
			StringBuilder leftString = new StringBuilder();
			StringBuilder rightString = new StringBuilder();
			sb.append(k);
			for(int i = 0 ; i  < 5;i++) {
				leftString.append(sb.toString().charAt(i));
			}
			for(int i = 5; i<10;i++ ) {
				rightString.append(sb.toString().charAt(i));
			}
			
			//keeps track of the first value in the key
			char leftCarry = leftString.toString().charAt(0);
			char rightCarry = rightString.toString().charAt(0);
			
			StringBuilder shiftedLeft = new StringBuilder();
			StringBuilder shiftedRight = new StringBuilder();
			
			for(int i = 0 ; i< leftString.toString().length();i++) {
				if(i==4) {
					shiftedLeft.append(leftCarry);
					shiftedRight.append(rightCarry);
				}
				else {
					shiftedLeft.append(leftString.toString().charAt(i+1));
					shiftedRight.append(rightString.toString().charAt(i+1));	
				}
			}
			
			
			StringBuilder realAnswer = new StringBuilder();
			realAnswer.append(shiftedLeft);
			realAnswer.append(shiftedRight);
			
			return realAnswer.toString();	
		}
		else {
			StringBuilder sb = new StringBuilder();
			StringBuilder leftString = new StringBuilder();
			StringBuilder rightString = new StringBuilder();
			sb.append(k);
			for(int i = 0 ; i  < 5;i++) {
				leftString.append(sb.toString().charAt(i));
			}
			for(int i = 5; i<10;i++ ) {
				rightString.append(sb.toString().charAt(i));
			}
			StringBuilder leftCarry = new StringBuilder();
			StringBuilder rightCarry = new StringBuilder();
			
			leftCarry.append(leftString.toString().charAt(0));
			leftCarry.append(leftString.toString().charAt(1));
			rightCarry.append(rightString.toString().charAt(0));
			rightCarry.append(rightString.toString().charAt(1));
			
			leftString.delete(0, 2);
			rightString.delete(0, 2);
			
			leftString.append(leftCarry.toString());
			rightString.append(rightCarry.toString());

			StringBuilder realAnswer = new StringBuilder();
			realAnswer.append(leftString);
			realAnswer.append(rightString);

			
			return realAnswer.toString();
		}
	}
	public static String p10(String k) {
		StringBuilder sb = new StringBuilder();
		StringBuilder answer = new StringBuilder();
		
		sb.append(k);
		int[] indexes = {3,5,2,7,4,10,1,9,8,6};
		
		for(int i = 0 ; i < indexes.length;i++) {
			answer.append(sb.toString().charAt(indexes[i]-1));
		}	
		return answer.toString();
	}
	public static String p8(String k) {
		StringBuilder sb = new StringBuilder();
		int[] indexes = {6,3,7,4,8,5,10,9};
		for(int i = 0 ; i < indexes.length;i++) {
			sb.append(k.charAt(indexes[i]-1));
		}
		return sb.toString();
	}
	public static String IP(String bitKey) {
		int[] indexes = {2,6,3,1,4,8,5,7};
		StringBuilder answer = new StringBuilder();
		for(int i = 0 ; i < indexes.length;i++) {
			answer.append(bitKey.charAt(indexes[i]-1));
		}
		return answer.toString();
	}
	public static String EP(String IP,int diff) {
		int[] indexes = {4,1,2,3,2,3,4,1};
		StringBuilder answer = new StringBuilder();
		for(int i = 0 ; i < indexes.length;i++) {
			answer.append(IP.charAt(indexes[i]+diff));
		}
		return answer.toString();
	}
	public static String SBox(String key,int box) {
		StringBuilder row = new StringBuilder();
		row.append(key.charAt(0));
		row.append(key.charAt(3));
		
		StringBuilder col = new StringBuilder();
		col.append(key.charAt(1));
		col.append(key.charAt(2));
		
		int rowNum = binaryCalc(row.toString());
		int colNum = binaryCalc(col.toString());
		
		if(box == 0) {
		String[][] S0 = {
				{"01","00","11","10"}
				,{"11","10","01","00"},
				{"00","01","01","11"},
				{"11","01","11","10"}
				};
		return S0[rowNum][colNum];
		}
		else if(box == 1) {
			String[][] S1 = {
					{"00","01","10","11"},
					{"10","00","01","11"},
					{"11","00","01","00"},
					{"10","01","00","11"}
			};
			return S1[rowNum][colNum];
		}
		return "";
	}
	public static int binaryCalc(String bit) {
		if(bit.equals("00")) {
			return 0;
		}
		else if(bit.equals("01")) {
			return 1;
		}
		else if(bit.equals("10")) {
			return 2;
		}
		else if(bit.equals("11")) {
			return 3;
		}
		return 0;
	}
	public static String XOR(String EP, String key1) {
		StringBuilder answer = new StringBuilder();
		for(int i = 0; i < key1.length();i++) {
			if(EP.charAt(i) == key1.charAt(i)) {
				answer.append("0");
			}
			else {
				answer.append("1");
			}
		}
		return answer.toString();
	}
	public static String P4(String key) {
		int[] indexes = {2,4,3,1};
		StringBuilder sb = new StringBuilder();
		for(int i = 0 ;  i< indexes.length;i++) {
			sb.append(key.charAt(indexes[i]-1));
		}
		return sb.toString();
	}
	public static String reverseIP(String input) {
		int[] indexes = {4,1,3,5,7,2,8,6};
		StringBuilder sb = new StringBuilder();
		for(int i = 0 ; i < indexes.length;i++) {
			sb.append(input.charAt(indexes[i]-1));
		}
		return sb.toString();
	}
	public static String SDES(String rawKey, String plainText) {

			
		String key10 = p10(rawKey);
		String shiftedKey = leftShift(key10,1);
		String roundKey1 = p8(shiftedKey);
		String shiftedKey2 = leftShift(shiftedKey,2);
		String roundKey2 = p8(shiftedKey2);
		String IP = IP(plainText);
		String EP = EP(IP,3);
		String fk1 = XOR(EP,roundKey1);
		
		StringBuilder left = new StringBuilder();
		StringBuilder right = new StringBuilder();
		for(int i = 0; i < 4;i++) {
			left.append(fk1.charAt(i));
			right.append(fk1.charAt(i+4));
		}
		
		String S0Answer = SBox(left.toString(),0);
		String S1Answer = SBox(right.toString(),1);
		StringBuilder P4 = new StringBuilder();
		P4.append(S0Answer);
		P4.append(S1Answer);
		String P4Answer = P4(P4.toString());

		StringBuilder leftPT = new StringBuilder();
		StringBuilder rightPT = new StringBuilder();
		for(int i = 0 ; i < 4;i++) {
			leftPT.append(IP.charAt(i));
			rightPT.append(IP.charAt(i+4));
		}
		String answerXor = XOR(leftPT.toString(),P4Answer);
		
		StringBuilder finalKey = new StringBuilder();
		finalKey.append(rightPT.toString());
		finalKey.append(answerXor);

		String EP2 = EP(answerXor,-1);

		String Xor2 = XOR(EP2,roundKey2);
		
		StringBuilder ndLeft = new StringBuilder();
		StringBuilder ndRight = new StringBuilder();
		
		for(int i = 0 ; i < 4;i++) {
			ndLeft.append(Xor2.charAt(i));
			ndRight.append(Xor2.charAt(i+4));
		}
		
		String S02Answer = SBox(ndLeft.toString(),0);
		String S12Answer = SBox(ndRight.toString(),1);
		
		String p42nd = P4(S02Answer + S12Answer);

		
		String finalXor = XOR(rightPT.toString(),p42nd);

		
		String finalAnswer = reverseIP(finalXor+answerXor);
		return finalAnswer;
			

	}
	public static String SDESDecryption(String rawKey, String cipherText) {
		String key10 = p10(rawKey);
		String shiftedKey = leftShift(key10,1);
		String roundKey1 = p8(shiftedKey);
		String shiftedKey2 = leftShift(shiftedKey,2);
		String roundKey2 = p8(shiftedKey2);
		
		String firstIP = IP(cipherText);
		StringBuilder left = new StringBuilder();
		StringBuilder right = new StringBuilder();
		for(int i = 0; i < 4;i++) {
			left.append(firstIP.charAt(i));
			right.append(firstIP.charAt(i+4));
		}
		
		String firstEP = EP(right.toString(),-1);
		
		String firstXOR =XOR(roundKey2,firstEP);
		StringBuilder leftXOR = new StringBuilder();
		StringBuilder rightXOR = new StringBuilder();
		for(int i = 0; i < 4;i++) {
			leftXOR.append(firstXOR.charAt(i));
			rightXOR.append(firstXOR.charAt(i+4));
		}
		
		String firstSBox = SBox(leftXOR.toString(),0);
		String secondSBox = SBox(rightXOR.toString(),1);
		
		StringBuilder combinedFirstSbox = new StringBuilder();
		combinedFirstSbox.append(firstSBox);
		combinedFirstSbox.append(secondSBox);
		
		String firstP4 = P4(combinedFirstSbox.toString());
		
		String finalRoundXOR = XOR(firstP4,left.toString());
		
		StringBuilder secondRoundSwitch = new StringBuilder();
		secondRoundSwitch.append(right.toString());
		secondRoundSwitch.append((finalRoundXOR));
		//end of first round
		
		String secondLeft = right.toString();
		String secondRight = finalRoundXOR;
		
		String secondEP = EP(secondRight,-1);
		
		String secondXOR = XOR(roundKey1,secondEP);
		StringBuilder secondLeftXOR = new StringBuilder();
		StringBuilder secondRightXOR = new StringBuilder();
		for(int i = 0; i < 4;i++) {
			secondLeftXOR.append(secondXOR.charAt(i));
			secondRightXOR.append(secondXOR.charAt(i+4));
		}
		
		String secondLeftSBox = SBox(secondLeftXOR.toString(),0);
		String secondRightSBox = SBox(secondRightXOR.toString(),1);
		
		String finalSbox = P4(secondLeftSBox + secondRightSBox);
		String finalLeft = XOR(secondLeft,finalSbox);
		
		String answer = reverseIP(finalLeft+secondRight);
		
		return answer;
	}

}
