package ch.baws.projectneo;

import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.util.Log;
/**
 * class Bluetooth utils
 * provides basic bluetooth functions
 **/

public class BluetoothUtils {
	private static final String TAG = "BN_BTUTILS";
	private static final boolean D = true;
	private OutputStream outStream = null;
	
	private BluetoothAdapter mBluetoothAdapter = null;
	private BluetoothSocket btSocket = null;
	
	
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
		if (mBluetoothAdapter == null) {
			return false;
		}
		else {
		return true;
		}
	}
	
	public boolean active()
	{
        if (D)
        	Log.e(TAG, "+++ Active +++");
		if (!mBluetoothAdapter.isEnabled()) {
			 return false;
		}
		else return true;
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
	 * method Send
	 * @param BluetoothAdapter
	 * @param BluetoothSocket
	 * @return void
	 **/
	public void send(String message) 
	{
   		// When this returns, it will 'know' about the server,
   		// via it's MAC address.
   		BluetoothDevice device = mBluetoothAdapter.getRemoteDevice(address);
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

   		// Blocking connect, for a simple client nothing else can
   		// happen until a successful connection is made, so we
   		// don't care if it blocks.
   		try {
   			btSocket.connect();
   			Log.e(TAG, "ON RESUME: BT connection established, data transfer link open.");
   		} catch (IOException e) {
   			try {
   				btSocket.close();
   			} catch (IOException e2) {
   				Log.e(TAG, 
   					"ON RESUME: Unable to close socket during connection failure", e2);
   			}
   		}
   		// Create a data stream so we can talk to server.
   		if (D)
   			Log.e(TAG, "+ ABOUT TO SAY SOMETHING TO SERVER +");

   		try {
   			outStream = btSocket.getOutputStream();
   		} catch (IOException e) {
    		Log.e(TAG, "ON RESUME: Output stream creation failed.", e);
    	}

		Frame frame = new Frame();
		byte[] packet = frame.generate(GeneralUtils.randomArray());
		//Frame.print(packet);
    	//message = "Hello message from client to server.";
    	//byte[] msgBuffer = message.getBytes();
		byte[] msgBuffer = packet;
    	try {
    		outStream.write(msgBuffer);
    	} catch (IOException e) {
    		Log.e(TAG, "ON RESUME: Exception during write.", e);
    	}
	}
	
	
	/**
	 * method Close
	 * shut down BT connection
	 * @return 
	 * @return
	 */
	public void close() //using this method gives FC
	//TODO
	{
   		try	{
   			btSocket.close();
   		} catch (IOException e2) {
   			Log.e(TAG, "ON PAUSE: Unable to close socket.", e2);
		}
	}

	

		
}
