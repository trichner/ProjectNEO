package ch.baws.projectneo;

import java.nio.ByteBuffer;
import java.util.Random;

/*
 *   Frame Generator
 * 
 * This class processes values representing the NEO Matrix
 * and generates a Frame Package.
 * Those Frame Packages are then transmitted
 * 
 * Format:
 *  _____________   _____________   _______________________   _______________________   _______________________ 
 * | Headbyte    | | Commandbyte | | 8x Bytes Red Matrix   | | 8x Bytes Green Matrix | | 8x Bytes Blue Matrix  |
 * |_____________| |_____________| |_______________________| |_______________________| |_______________________|
 * 
 */

public class Frame {
										// head + cmdb + 3x 8Byte
	private final static int PACKETLENGTH = 1+1+3*Long.SIZE/8;
	public static final int NEO_RED = 1;
	public static final int NEO_GREEN = 2;
	public static final int NEO_BLUE = 3;
	public static final int NEO_OFF = 0;
	
	public static final int NEO_YELLOW = 4;
	public static final int NEO_TURK = 5;
	public static final int NEO_PINK = 6;
	
	public static final int NEO_WHITE = 7;
	
	private final static byte head = (byte) 0x17;
	
	private byte cmdb = (byte) 0x00;
	
	private long blub = 0;
	private long redb = 0;
	private long greb = 0;
	
	//===== Generators
	/**
	 * Generates a Frame Packet out of a single array
	 * 0 = white/off
	 * 1 = NEO_RED
	 * 2 = NEO_GREEN
	 * 3 = NEO_BLUE
	 * @param arr Array which has !0 for active LEDs
	 * @return Frame-Packet
	 */
	public byte[] generate(int[][] arr){
		redb=0; greb=0; blub=0;
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++){
				switch(arr[i][j]){
					case Frame.NEO_RED:
						redb |= Bitfields.xyToBit(i, j);
						break;
					case Frame.NEO_GREEN:
						greb |= Bitfields.xyToBit(i, j);
						break;
					case Frame.NEO_BLUE:
						blub |= Bitfields.xyToBit(i, j);
						break;
					case Frame.NEO_YELLOW: 
						redb |= Bitfields.xyToBit(i, j);
						greb |= Bitfields.xyToBit(i, j);
						break;
					case Frame.NEO_TURK:
						blub |= Bitfields.xyToBit(i, j);
						greb |= Bitfields.xyToBit(i, j);
						break;
					case Frame.NEO_PINK:
						redb |= Bitfields.xyToBit(i, j);
						blub |= Bitfields.xyToBit(i, j);
						break;
					case Frame.NEO_WHITE:
						redb |= Bitfields.xyToBit(i, j);
						greb |= Bitfields.xyToBit(i, j);
						blub |= Bitfields.xyToBit(i, j);
						break;
				}
			}
		}
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
		//write the head
		packet.put(head);
		//write the commandbyte
		packet.put(cmdb);
		//---write the three matrices
		packet.putLong(redb);
		packet.putLong(greb);
		packet.putLong(blub);

		return packet.array();
	}
	
	//===== Utils
	
	public byte[] generateDebug(){
		redb = ('t' << 56) | ('e' << 48) | ('s' << 40) | ('t' << 32) | ('m' << 24) | ('e' << 16) | ('s' << 8) | ( 's' << 0);
		greb = ('a' << 56) | ('g' << 48) | ('e' << 40) | ('1' << 32) | ('2' << 24) | ('3' << 16) | ('4' << 8) | ('5' << 0);
		
		
		blub = 0;
		Random rand = new Random();
		int chr;
		for(int i=0;i<8;i++){
			chr = (int) 'A' + rand.nextInt(26);
			blub <<= 8;
			blub |= chr;
		}

		return finish();
	}
	
	public static String print(byte[] packet){
		String str = new String();
		for(int i=0;i<PACKETLENGTH;i++){
			str+= ("-"+Integer.toBinaryString(packet[i]));
		}
		return str;
	}
}
