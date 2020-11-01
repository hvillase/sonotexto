SonoTexto {

	// this variable means sonotexto (st) and it is intended to make a Dictionary
	classvar <st;

	// the class method boot check if there is a folder to write sounds and executes a the document called sonotexto-synths.scd.
	*boot {

		// this alerts when the sound folder sonotexto is not in the Recordings path.
		if(File.existsCaseSensitive(Platform.recordingsDir ++ "/sonotexto"), {"sonotexto folder is on place".postln}, {"No sound folder is in your Recordings path, please create one with the name 'sonotexto'".postln});

		// this executes a document with the SynthDefs requiered to work.
		thisProcess.interpreter.executeFile((Platform.userExtensionDir ++ "/sonotexto/sonotexto-synths.scd").standardizePath);
		^"SonoTexto Active"
	}

	// the class method rec allows to rec one, or all, the available Buffers when argument is true.
	*rec { arg b1 = false, b2 = false, b3 = false, b4 = false;
		if(b1, {Synth(\b1rec); "Recording b1 mono file".postln}, {"nil"});
		if(b2, {Synth(\b2rec); "Recording b2 stereo file".postln}, {"nil"});
		if(b3, {Synth(\b3rec); "Recording b3 mono file".postln}, {"nil"});
		if(b4, {Synth(\b4rec); "Recording b4 stereo file".postln}, {"nil"});
		^"SonoTexto Rec"
	}

	// the class method write allows to write the temporary buffer sounds in the folder sonotexto placed in the Recordings Directory.
	*write { arg b1 = false, b2 = false, b3 = false, b4 = false;

		// this Task routine writes each Buffer in the hard disk when argument is true. It waits 60 miliseconds to avoid having the same name for all the Buffers.
		Task {
			1.do{if(b1, {~buf1.write(Platform.recordingsDir +/+ "/sonotexto/" ++ PathName.new("~/.local/share/SuperCollider/Recordings/sonotexto").files.size.asSizedString(4) ++ "-b1-" ++ Date.localtime.stamp ++ ".wav", "WAV", "int16"); "Writing b1 mono file".postln}, {"nil"}); 0.06.wait};
			1.do{if(b2, {~buf2.write(Platform.recordingsDir +/+ "/sonotexto/" ++ PathName.new("~/.local/share/SuperCollider/Recordings/sonotexto").files.size.asSizedString(4) ++ "-b2-" ++ Date.localtime.stamp ++ ".wav", "WAV", "int16"); "Writing b2 stereo file".postln}, {"nil"}); 0.06.wait};
			1.do{if(b3, {~buf3.write(Platform.recordingsDir +/+ "/sonotexto/" ++ PathName.new("~/.local/share/SuperCollider/Recordings/sonotexto").files.size.asSizedString(4) ++ "-b3-" ++ Date.localtime.stamp ++ ".wav", "WAV", "int16"); "Writing b3 mono file".postln}, {"nil"}); 0.06.wait};
			1.do{if(b4, {~buf4.write(Platform.recordingsDir +/+ "/sonotexto/" ++ PathName.new("~/.local/share/SuperCollider/Recordings/sonotexto").files.size.asSizedString(4) ++ "-b4-" ++ Date.localtime.stamp ++ ".wav", "WAV", "int16"); "Writing b4 stereo file".postln}, {"nil"}); 0.06.wait};
		}.play
		^"SonoTexto Write"
	}

	// the class method read accesses sonotexto sound folder. A more accurate way is using the class SampleTexto.
	*read { arg server;
		st = Dictionary.new;
		st.add(\st -> PathName(Platform.recordingsDir +/+ "/sonotexto/").entries.collect({arg grabacion; Buffer.read(server ? Server.default, grabacion.fullPath)}));
		^"SonoTexto Sounds "
	}
}
