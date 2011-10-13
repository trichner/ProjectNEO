package ch.baws.projectneo;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.nio.LongBuffer;


public class Frame {
										// head + cmdb + 3x 8Byte
	private final static int PACKETLENGTH = 1+1+3*Long.SIZE/8;
	private final static byte head = (byte) 0x42;
	private byte cmdb = (byte) 0x00;
	
	private long blub = 0;
	private long redb = 0;
	private long greb = 0;
	
	//===== Generators
	/**
	 * Generates a Frame Packet out of a single array
	 * @param arr Array which has !0 for active LEDs
	 * @return Frame-Packet
	 */
	public byte[] generate(int[][] arr){
		
		blub = Bitfields.toBit(arr);
		redb=0;
		greb=0;
		return finish();
	}
	/**
	 * Generates a Frame Packet out of a single array
	 * @param arr Array which has !0 for active LEDs
	 * @return
	 */
	public byte[] generate(int[][] blue,int[][] red,int[][] green){
		blub = Bitfields.toBit(blue);
		redb = Bitfields.toBit(red);
		greb = Bitfields.toBit(green);
		return finish();
	}

	
	//===== Finisher
	private byte[] finish(){
		ByteBuffer packet = ByteBuffer.allocate(PACKETLENGTH);
		packet.put(head);
		packet.put(cmdb);
		//---write the three matrices
		packet.putLong(redb);
		packet.putLong(greb);
		packet.putLong(blub);

		return packet.array();
	}
	
	//===== Utils
	
	public static void print(byte[] packet){
		for(int i=0;i<PACKETLENGTH;i++){
			System.out.println(i + ' ' + Integer.toBinaryString(packet[i]));
		}
	}
}
