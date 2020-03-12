SonoTexto {

	classvar <st;

	*boot {
		thisProcess.interpreter.executeFile((Platform.userExtensionDir ++ "/sonotexto/sonotexto-synths.scd").standardizePath);
		^"SonoTexto Synths"
	}

	*rec { arg b1 = false, b2 = false, b3 = false, b4 = false;
		if(b1, {Synth(\b1rec); "Recording b1 mono file".postln}, {"nil"});
		if(b2, {Synth(\b2rec); "Recording b2 stereo file".postln}, {"nil"});
		if(b3, {Synth(\b3rec); "Recording b3 mono file".postln}, {"nil"});
		if(b4, {Synth(\b4rec); "Recording b4 stereo file".postln}, {"nil"});
		^"SonoTexto Rec"
	}

	*write { arg b1 = false, b2 = false, b3 = false, b4 = false;
		Task {
			1.do{if(b1, {~buf1.write(Platform.recordingsDir +/+ "/sonotexto/" ++ PathName.new("~/.local/share/SuperCollider/Recordings/sonotexto").files.size.asSizedString(4) ++ "-b1-" ++ Date.localtime.stamp ++ ".wav", "WAV", "int16")}, {"nil"}); 0.06.wait};
			1.do{if(b2, {~buf2.write(Platform.recordingsDir +/+ "/sonotexto/" ++ PathName.new("~/.local/share/SuperCollider/Recordings/sonotexto").files.size.asSizedString(4) ++ "-b2-" ++ Date.localtime.stamp ++ ".wav", "WAV", "int16")}, {"nil"}); 0.06.wait};
			1.do{if(b3, {~buf3.write(Platform.recordingsDir +/+ "/sonotexto/" ++ PathName.new("~/.local/share/SuperCollider/Recordings/sonotexto").files.size.asSizedString(4) ++ "-b3-" ++ Date.localtime.stamp ++ ".wav", "WAV", "int16")}, {"nil"}); 0.06.wait};
			1.do{if(b4, {~buf4.write(Platform.recordingsDir +/+ "/sonotexto/" ++ PathName.new("~/.local/share/SuperCollider/Recordings/sonotexto").files.size.asSizedString(4) ++ "-b4-" ++ Date.localtime.stamp ++ ".wav", "WAV", "int16")}, {"nil"}); 0.06.wait};
		}.play
		^"SonoTexto Write"
	}

	*read { arg server;
		st = Dictionary.new;
		st.add(\st -> PathName(Platform.recordingsDir +/+ "/sonotexto/").entries.collect({arg grabacion; Buffer.read(server ? Server.default, grabacion.fullPath)}));
		^"SonoTexto Sounds "
	}
}
