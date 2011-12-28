package ch.baws.projectneo;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

import ch.baws.projectneo.frameGenerator.Frame;
import ch.baws.projectneo.frameGenerator.PacketGenerator;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.util.Log;

/**
 * class Bluetooth utils
 * provides basic bluetooth functions
 **/

public class BluetoothUtils {
	private static final String TAG = "BN_BTUTILS";
	private static final boolean D = false;
	private static final boolean E = false;
	
	private boolean FLAG_connected = false;
	
	private BluetoothAdapter mBluetoothAdapter = null;
	private BluetoothSocket btSocket = null;
	
	private OutputStream out;
	private InputStream in;
	
	
	// Well known SPP UUID (will *probably* map to
	// RFCOMM channel 1 (default) if not in use);
	// see comments in onResume().
	private static final UUID MY_UUID = 
			UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

	// ==> hardcode your server's MAC address here <==
	private static String address = "00:07:80:85:8B:6E";

	/**
	 * method Available
	 * initializes the BT connection
	 */
	public boolean init()
	{
        if (D)
        	Log.e(TAG, "+++ Init +++");
		mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
		
		return mBluetoothAdapter != null;
	}
	
	public boolean active()
	{
        if (D)
        	Log.e(TAG, "+++ Active +++");
		return mBluetoothAdapter.isEnabled();
	}
	
	/**
	 * method Test
	 * tests if BT connection is working
	 * @return
	 */
	public boolean test()
	{
		//TODO
		return false;
	}
	
	/**
	 * method connect
	 * @param BluetoothAdapter
	 * @param BluetoothSocket
	 * @return void
	 **/
	public void connect() 
	{
   		// When this returns, it will 'know' about the server,
   		// via it's MAC address.
		BluetoothDevice device;
		if(!E){
			device = mBluetoothAdapter.getRemoteDevice(address);
   		// We need two things before we can successfully connect
   		// (authentication issues aside): a MAC address, which we
   		// already have, and an RFCOMM channel.
   		// Because RFCOMM channels (aka ports) are limited in
   		// number, Android doesn't allow you to use them directly;
   		// instead you request a RFCOMM mapping based on a service
   		// ID. In our case, we will use the well-known SPP Service
   		// ID. This ID is in UUID (GUID to you Microsofties)
   		// format. Given the UUID, Android will handle the
   		// mapping for you. Generally, this will return RFCOMM 1,
   		// but not always; it depends what other BlueTooth services
   		// are in use on your Android device.
			try {
				btSocket = device.createRfcommSocketToServiceRecord(MY_UUID);
			} catch (IOException e) {
				Log.e(TAG, "ON RESUME: Socket creation failed.", e);
			}

   		// Discovery may be going on, e.g., if you're running a
   		// 'scan for devices' search from your handset's Bluetooth
   		// settings, so we call cancelDiscovery(). It doesn't hurt
   		// to call it, but it might hurt not to... discovery is a
   		// heavyweight process; you don't want it in progress when
   		// a connection attempt is made.
   		
			mBluetoothAdapter.cancelDiscovery();
		}
   		// Blocking connect, for a simple client nothing else can
   		// happen until a successful connection is made, so we
   		// don't care if it blocks.
   		try {
   			if(!E) btSocket.connect();
   			FLAG_connected = true;
   			Log.e(TAG, "ON RESUME: BT connection established, data transfer link open.");
   		} catch (IOException e) {
   			try {
   				if(!E) btSocket.close();
   			} catch (IOException e2) {
   				Log.e(TAG, 
   					"ON RESUME: Unable to close socket during connection failure", e2);
   			}
   		}		
   		
   		//get streams
   		try {
   			in = btSocket.getInputStream();
			out = btSocket.getOutputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Log.e(TAG, "ON RESUME: Output stream creation failed.", e);
			e.printStackTrace();
		}
   		
   		
	}
	/**
	 * method Send
	 * @param BluetoothAdapter
	 * @param BluetoothSocket
	 * @return void
	 **/
	public void send(int[][] colorArray) 
	{
		if(E) return; //Emulator mode, do nothing
		
   		// Create a data stream so we can talk to server.
   		if (D){
   			Log.e(TAG, "+ ABOUT TO SAY SOMETHING TO SERVER +");
   			Log.e(TAG, "colorArray[0][0]: "+ colorArray[0][0]);
   		}
   		
   		if(btSocket ==null)	Log.e(TAG, "ERROR: Socket is NULL.");

		Frame frame = new Frame(colorArray);
   		byte[] packet = PacketGenerator.pack(frame);

    	try {
    		out.write(packet);
    	} catch (IOException e) {
    		Log.e(TAG, "ERROR: Exception during write. IOException.", e);
    	}
	}
	
	public boolean isConnected(){
		//return this.btSocket.isConnected();  //Why doesn't this work???
		return this.FLAG_connected;
	}
	
	public byte[] read(){
		int read_bytes=0;
		byte[] buffer = new byte[64];
		try {
			read_bytes = in.read(buffer);
		} catch (IOException e) {
			e.printStackTrace();
			Log.e(TAG, "ERROR: Exception during read. IOException.", e);
		}
		if(D) Log.d(TAG,"read " + read_bytes + " bytes from BluetoothSocket");
		return buffer;
	}
	
	
	/**
	 * method Close
	 * shut down BT connection
	 * @return 
	 * @return
	 */
	public void close() //using this method gives FC
	{
   		try	{
   			btSocket.close();
   			this.FLAG_connected = false;
   		} catch (IOException e2) {
   			Log.e(TAG, "ON PAUSE: Unable to close socket.", e2);
		}
	}

	

		
}
