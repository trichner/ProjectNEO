package ch.baws.projectneo.frameGenerator;

import java.nio.ByteBuffer;


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

public class PacketGenerator {
	//===== Finisher
	private final static int PACKETLENGTH = 1+1+3*Long.SIZE/8;
	public final static byte head = (byte) 0x17;
	
	public static byte[] pack(Frame frame){
		// head + cmdb + 3x 8Byte
		ByteBuffer packet = ByteBuffer.allocate(PACKETLENGTH);
		//write the head
		packet.put(head);
		//write the commandbyte
		packet.put(frame.getCmdb().cmd());
		//---write the three matrices
		packet.putLong(frame.getRedb());
		packet.putLong(frame.getGreb());
		packet.putLong(frame.getBlub());

		return packet.array();
	}
	
	public static byte[] packRotate(Frame[] frames){
		if((frames.length<2) || (frames.length>64)) return new byte[0];
		// head + cmdb + 3x 8Byte
		ByteBuffer packet = ByteBuffer.allocate(PACKETLENGTH*frames.length);
		//write the head
		packet.put(head);
		//write the commandbyte
		packet.put(CMDB.CMD_ROTATE_START.cmd());
		//---write the three matrices
		packet.putLong(frames[0].getRedb());
		packet.putLong(frames[0].getGreb());
		packet.putLong(frames[0].getBlub());
		
		int i=1;
		for(;i<(frames.length-1);i++){
			//write the head
			packet.put(head);
			//write the commandbyte
			packet.put(CMDB.CMD_ROTATE.cmd());
			//---write the three matrices
			packet.putLong(frames[i].getRedb());
			packet.putLong(frames[i].getGreb());
			packet.putLong(frames[i].getBlub());
		}
		
		//write the head
		packet.put(head);
		//write the commandbyte
		packet.put(CMDB.CMD_ROTATE_END.cmd());
		//---write the three matrices
		packet.putLong(frames[i].getRedb());
		packet.putLong(frames[i].getGreb());
		packet.putLong(frames[i].getBlub());

		return packet.array();
	}
}
