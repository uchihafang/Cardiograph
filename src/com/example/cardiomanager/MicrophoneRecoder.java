package com.example.cardiomanager;


import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;


public class MicrophoneRecoder
{
	private static int RECORDER_SAMPLERATE = 8000; //In device use samplerate to 44100 for better quality
	private static int RECORDER_CHANNELS = AudioFormat.CHANNEL_IN_MONO;
	private static int RECORDER_AUDIO_ENCODING = AudioFormat.ENCODING_PCM_16BIT;
	private AudioRecord recorder = null;
	private Thread recordingThread = null;
	private boolean isRecording = false;
	private AudioFilter audioFilter;
	private int bufferSize;

	public MicrophoneRecoder(AudioFilter aF)
	{
		audioFilter = aF;
		
		if(checkConfigurations())
			aF.showMassage(7);
		else
			aF.showMassage(8);
		
		bufferSize = AudioRecord.getMinBufferSize(RECORDER_SAMPLERATE,
	            RECORDER_CHANNELS, RECORDER_AUDIO_ENCODING);
		aF.setBufferSize(bufferSize);	
	}
	
	int BufferElements2Rec = 1024; // want to play 2048 (2K) since 2 bytes we use only 1024
	int BytesPerElement = 2; // 2 bytes in 16bit format

	public void startRecording() {
	    recorder = new AudioRecord(MediaRecorder.AudioSource.MIC,
	            RECORDER_SAMPLERATE, RECORDER_CHANNELS,
	            RECORDER_AUDIO_ENCODING, bufferSize);//BufferElements2Rec * BytesPerElement);
	    //audioFilter.showMassage(3);
	    try {
	    	recorder.startRecording();
	    } catch(Exception e)
	    {
	    	recorder.release();
	    }
	    isRecording = true;
	    recordingThread = new Thread(new Runnable() {
	        public void run() {
	            writeAudioDataToFile();
	        }
	    }, "AudioRecorder Thread");
	    recordingThread.start();
	}

	 //convert short to byte
	/*private byte[] short2byte(short[] sData) {
	    int shortArrsize = sData.length;
	    byte[] bytes = new byte[shortArrsize * 2];
	    for (int i = 0; i < shortArrsize; i++) {
	        bytes[i * 2] = (byte) (sData[i] & 0x00FF);
	        bytes[(i * 2) + 1] = (byte) (sData[i] >> 8);
	        sData[i] = 0;
	    }
	    return bytes;
	}*/

	private void writeAudioDataToFile() {
	    // Write the output audio in byte
	    short sData[] = new short[BufferElements2Rec];

	    while (isRecording) {
	        // gets the voice output from microphone to byte format

	        recorder.read(sData, 0, BufferElements2Rec);  
	        audioFilter.setBuffer(sData);
	        System.out.println("Recorded");
	    }
	}

	public void stopRecording() {
	    // stops the recording activity
	    if (null != recorder) {
	        isRecording = false;
	        recorder.stop();
	        recorder.release();
	        recorder = null;
	        recordingThread = null;
	    }
	}
	
	static public boolean checkConfigurations()
	{
		//check anaible formats
		/*int[] rates = {8000, 11025, 22050, 44100, 48000, 96000 };
		int[] chans = {AudioFormat.CHANNEL_IN_MONO, AudioFormat.CHANNEL_IN_STEREO};
		int[] encs  = {AudioFormat.ENCODING_PCM_8BIT, AudioFormat.ENCODING_PCM_16BIT};
		for(int enc : encs) {
		    for(int ch : chans)    {
		        for(int rate : rates) {
		            int t = AudioRecord.getMinBufferSize(rate, ch, enc);
		            if((t != AudioRecord.ERROR) && (t != AudioRecord.ERROR_BAD_VALUE))
		            {
		                //aF.showFormat(rate, ch, enc);
		            }}}}*/
		
		int t = AudioRecord.getMinBufferSize(RECORDER_SAMPLERATE,
	            RECORDER_CHANNELS, RECORDER_AUDIO_ENCODING);
		if((t != AudioRecord.ERROR) && (t != AudioRecord.ERROR_BAD_VALUE))
			return true;
		else
			return false;
	}
	
	static public void setRECORDER_SAMPLERATE(int value) {
		RECORDER_SAMPLERATE = value;
	}
	
	static public void setRECORDER_CHANNELS(Boolean value) {
		if(!value)
			RECORDER_CHANNELS = AudioFormat.CHANNEL_IN_MONO;
		else
			RECORDER_CHANNELS = AudioFormat.CHANNEL_CONFIGURATION_STEREO;
			
	}
	
	static public void setRECORDER_AUDIO_ENCODING(Boolean value) {
		if(!value)
			RECORDER_AUDIO_ENCODING = AudioFormat.ENCODING_PCM_8BIT;
		else
			RECORDER_AUDIO_ENCODING = AudioFormat.ENCODING_PCM_16BIT;
	}
	
}