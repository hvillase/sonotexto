SonoTexto {

	*boot{
		this.synths;
		^"SonoTexto Ready"
	}

	*synths{
		thisProcess.interpreter.executeFile((Platform.userExtensionDir ++ "/sonotexto/sonotexto-synths.scd").standardizePath);
		^"SonoTexto Synths"
	}

	*rec{ arg b1 = false, b2 = false, b3 = false, b4 = false;
		if(b1, {Synth(\b1rec); "Recording b1 mono file".postln}, {"nil"});
		if(b2, {Synth(\b2rec); "Recording b2 stereo file".postln}, {"nil"});
		if(b3, {Synth(\b3rec); "Recording b3 mono file".postln}, {"nil"});
		if(b4, {Synth(\b4rec); "Recording b4 stereo file".postln}, {"nil"});
		^"SonoTexto Rec"
	}

	*write{ arg b1 = false, b2 = false, b3 = false, b4 = false;
		if(b1, {~buf1.write(Platform.recordingsDir +/+ "/sonotexto/b1-" ++ Date.localtime.stamp ++ ".wav", "WAV", "int16")}, {"nil"});
		if(b2, {~buf2.write(Platform.recordingsDir +/+ "/sonotexto/b2-" ++ Date.localtime.stamp ++ ".wav", "WAV", "int16")}, {"nil"});
		if(b3, {~buf3.write(Platform.recordingsDir +/+ "/sonotexto/b3-" ++ Date.localtime.stamp ++ ".wav", "WAV", "int16")}, {"nil"});
		if(b4, {~buf4.write(Platform.recordingsDir +/+ "/sonotexto/b4-" ++ Date.localtime.stamp ++ ".wav", "WAV", "int16")}, {"nil"});
		^"SonoTexto Write"
	}
}
