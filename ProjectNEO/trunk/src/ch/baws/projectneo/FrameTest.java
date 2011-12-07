package ch.baws.projectneo;


public class FrameTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[][] arr = GeneralUtils.randomArray(8,8);
		
		Frame frame = new Frame();
		byte[] packet = frame.generate(arr);
		Frame.print(packet);
	}

}
