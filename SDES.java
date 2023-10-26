package default_package;
public class SDES {

	public static void main(String[] args) {
		String key = p10(1010000010);
		String shiftedKey = leftShift(key,1);
		String firstRoundKey = p8(shiftedKey);
		
		System.out.println(firstRoundKey);
		
	}
	public static String leftShift(String k, int s){
		//create String Builder to split number
		StringBuilder sb = new StringBuilder();
		StringBuilder leftString = new StringBuilder();
		StringBuilder rightString = new StringBuilder();
		sb.append(k);
		System.out.println("This is the original key: " + sb);
		for(int i = 0 ; i  < 5;i++) {
			leftString.append(sb.toString().charAt(i));
		}
		for(int i = 5; i<10;i++ ) {
			rightString.append(sb.toString().charAt(i));
		}
		System.out.println("This is the left side: "+leftString.toString());
		System.out.println("This is the right side: " + rightString.toString());
		
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
		
		System.out.println("\nThis is shifted left 1 LeftKey: " + shiftedLeft.toString());
		System.out.println("This is shifted left 1 RightKey: " + shiftedRight.toString());
		
		StringBuilder realAnswer = new StringBuilder();
		realAnswer.append(shiftedLeft);
		realAnswer.append(shiftedRight);
		
		return realAnswer.toString();
	}
	public static String p10(int k) {
		StringBuilder sb = new StringBuilder();
		StringBuilder answer = new StringBuilder();
		
		sb.append(k);
		int[] indexes = {3,5,2,7,4,10,1,9,8,6};
		
		for(int i = 0 ; i < indexes.length;i++) {
			answer.append(sb.toString().charAt(indexes[i]-1));
		}	
		System.out.println("This is the key after the p10: " + answer + "\n");
		
		return answer.toString();
	}
	public static String p8(String k) {
		StringBuilder sb = new StringBuilder();
		int[] indexes = {6,3,7,4,8,5,10,9};
		for(int i = 0 ; i < indexes.length;i++) {
			sb.append(k.charAt(indexes[i]-1));
		}
		System.out.println("this is the p8 key: " + sb.toString());
		return sb.toString();
	}

}
