package ch.baws.projectneo.sendService;

import ch.baws.projectneo.ProjectMORPHEUS;

public class ReceiveTimer implements Runnable {

	private ProjectMORPHEUS morpheus;
	private byte[] buffer;
	
	public ReceiveTimer(ProjectMORPHEUS application){
		morpheus = application;
	}
	
	public byte[] inBuffer(){
		return buffer;
	}
	
	@Override
	public void run() {
		buffer = morpheus.bluetoothRead();
	}
	
}
