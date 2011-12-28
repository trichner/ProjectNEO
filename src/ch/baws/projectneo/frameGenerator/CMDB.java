package ch.baws.projectneo.frameGenerator;

public enum CMDB {
	/* Possible command bytes: 
	 * #define CMD_NONE 		 0
	 * #define CMD_ROTATE_START  1
	 * #define CMD_ROTATE 		 2
	 * #define CMD_ROTATE_END 	 3
	 * 
	 */
	CMD_NONE 		 ((byte) 0x00),
	CMD_ROTATE_START ((byte) 0x01),
	CMD_ROTATE 		 ((byte) 0x02),
	CMD_ROTATE_END	 ((byte) 0x03),
	CMD_DEGUG		 ((byte) 0x04);
	
	private byte value;
	
	CMDB(byte cmd){
		this.value = cmd;
	}
	public byte cmd(){
		return this.value;
	}
	
}
