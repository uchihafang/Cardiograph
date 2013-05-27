package com.example.cardiomanager;


import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;


public class MicrophoneRecoder
{
	private static final int RECORDER_SAMPLERATE = 8000; //In device use samplerate to 44100 for better quality
	private static final int RECORDER_CHANNELS = AudioFormat.CHANNEL_IN_MONO;
	private static final int RECORDER_AUDIO_ENCODING = AudioFormat.ENCODING_PCM_16BIT;
	private AudioRecord recorder = null;
	private Thread recordingThread = null;
	private boolean isRecording = false;
	private AudioFilter audioFilter;
	private int bufferSize;

	public MicrophoneRecoder(AudioFilter aF)
	{
		//aF.showMassage(1);
		bufferSize = AudioRecord.getMinBufferSize(RECORDER_SAMPLERATE,
	            RECORDER_CHANNELS, RECORDER_AUDIO_ENCODING);
		audioFilter = aF;
		//aF.showMassage(2);

		//check anaible formats
		/*int[] rates = {8000, 11025, 22050, 44100, 48000, 96000 };
		int[] chans = {AudioFormat.CHANNEL_IN_MONO, AudioFormat.CHANNEL_IN_STEREO};
		int[] encs  = {AudioFormat.ENCODING_PCM_8BIT, AudioFormat.ENCODING_PCM_16BIT};

		for(int enc : encs)
		{
		    for(int ch : chans)
		    {
		        for(int rate : rates)
		        {
		            int t = AudioRecord.getMinBufferSize(rate, ch, enc);
		            
		            if((t != AudioRecord.ERROR) && (t != AudioRecord.ERROR_BAD_VALUE))
		            {
		                //aF.showFormat(rate, ch, enc);
		            }
		        }
		    }
		}*/
	}

	int BufferElements2Rec = 1024; // want to play 2048 (2K) since 2 bytes we use only 1024
	int BytesPerElement = 2; // 2 bytes in 16bit format

	public void startRecording() {
	    recorder = new AudioRecord(MediaRecorder.AudioSource.MIC,
	            RECORDER_SAMPLERATE, RECORDER_CHANNELS,
	            RECORDER_AUDIO_ENCODING, BufferElements2Rec * BytesPerElement);
	    //audioFilter.showMassage(3);
	    //recorder.release();
	    recorder.startRecording();
	    isRecording = true;
	    recordingThread = new Thread(new Runnable() {
	        public void run() {
	            writeAudioDataToFile();
	        }
	    }, "AudioRecorder Thread");
	    recordingThread.start();
	}

	 //convert short to byte
	private byte[] short2byte(short[] sData) {
	    int shortArrsize = sData.length;
	    byte[] bytes = new byte[shortArrsize * 2];
	    for (int i = 0; i < shortArrsize; i++) {
	        bytes[i * 2] = (byte) (sData[i] & 0x00FF);
	        bytes[(i * 2) + 1] = (byte) (sData[i] >> 8);
	        sData[i] = 0;
	    }
	    return bytes;

	}

	private void writeAudioDataToFile() {
	    // Write the output audio in byte
	    short sData[] = new short[BufferElements2Rec];

	    while (isRecording) {
	        // gets the voice output from microphone to byte format

	        recorder.read(sData, 0, BufferElements2Rec);  
	        audioFilter.setBuffer(sData);
	        System.out.println("Recorded");
	        //try {
	            // // writes the data to file from buffer
	            // // stores the voice buffer
	            //byte bData[] = short2byte(sData);

	        //} catch (Exception e) {
	        //    e.printStackTrace();
	        //}
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
}