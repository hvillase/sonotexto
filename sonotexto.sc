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
		if(b1, {Synth(\b1rec)}, {"nil"});
		if(b2, {Synth(\b2rec)}, {"nil"});
		if(b3, {Synth(\b3rec)}, {"nil"});
		if(b4, {Synth(\b4rec)}, {"nil"});
		^"SonoTexto Rec"
	}

	*write{ arg b1 = false, b2 = false, b3 = false, b4 = false;
		if(b1, {~buf1.write(Platform.recordingsDir +/+ "/sonotexto/st" ++ Date.localtime.stamp ++ ".wav", "WAV", "int16")}, {"nil"});
		if(b2, {~buf2.write(Platform.recordingsDir +/+ "/sonotexto/st" ++ Date.localtime.stamp ++ ".wav", "WAV", "int16")}, {"nil"});
		if(b3, {~buf3.write(Platform.recordingsDir +/+ "/sonotexto/st" ++ Date.localtime.stamp ++ ".wav", "WAV", "int16")}, {"nil"});
		if(b4, {~buf4.write(Platform.recordingsDir +/+ "/sonotexto/st" ++ Date.localtime.stamp ++ ".wav", "WAV", "int16")}, {"nil"});
		^"SonoTexto Write"
	}
}
