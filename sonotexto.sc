SonoTexto {

	// this variable means sonotexto (st) and is intended to make a Dictionary
	classvar <st;

	// the class method boot executes some SynthDefs written in a sc document and check if a folder to write sounds is available. You can change here the path of this folder.
	*boot {

		// this if alerts when the sound folder sonotexto is not in Recordings path.
		if(File.existsCaseSensitive(Platform.recordingsDir ++ "/sonotexto"), {"sonotexto".postln}, {"Not sounds Folder in your Recordings path, please create one with the name 'sonotexto'".postln});

		// this piece of code executes a document with the SynthDefs requiered to work.
		thisProcess.interpreter.executeFile((Platform.userExtensionDir ++ "/sonotexto/sonotexto-synths.scd").standardizePath);
		^"SonoTexto Synths"
	}

	// the class method rec allows to rec one, or all, of the four Buffers available when argument is true.
	*rec { arg b1 = false, b2 = false, b3 = false, b4 = false;
		if(b1, {Synth(\b1rec); "Recording b1 mono file".postln}, {"nil"});
		if(b2, {Synth(\b2rec); "Recording b2 stereo file".postln}, {"nil"});
		if(b3, {Synth(\b3rec); "Recording b3 mono file".postln}, {"nil"});
		if(b4, {Synth(\b4rec); "Recording b4 stereo file".postln}, {"nil"});
		^"SonoTexto Rec"
	}

	// the class method write allows to write the temporary sound in the folder sonotexto. That is why you need to create it in the Recordings Directory.
	*write { arg b1 = false, b2 = false, b3 = false, b4 = false;

		// this Task routine writes each Buffer in hard disk when argument is trues and waits 60 miliseconds to avoid having the same name for all the Buffers.
		Task {
			1.do{if(b1, {~buf1.write(Platform.recordingsDir +/+ "/sonotexto/" ++ PathName.new("~/.local/share/SuperCollider/Recordings/sonotexto").files.size.asSizedString(4) ++ "-b1-" ++ Date.localtime.stamp ++ ".wav", "WAV", "int16"); "Writing b1 mono file".postln}, {"nil"}); 0.06.wait};
			1.do{if(b2, {~buf2.write(Platform.recordingsDir +/+ "/sonotexto/" ++ PathName.new("~/.local/share/SuperCollider/Recordings/sonotexto").files.size.asSizedString(4) ++ "-b2-" ++ Date.localtime.stamp ++ ".wav", "WAV", "int16"); "Writing b2 stereo file".postln}, {"nil"}); 0.06.wait};
			1.do{if(b3, {~buf3.write(Platform.recordingsDir +/+ "/sonotexto/" ++ PathName.new("~/.local/share/SuperCollider/Recordings/sonotexto").files.size.asSizedString(4) ++ "-b3-" ++ Date.localtime.stamp ++ ".wav", "WAV", "int16"); "Writing b3 mono file".postln}, {"nil"}); 0.06.wait};
			1.do{if(b4, {~buf4.write(Platform.recordingsDir +/+ "/sonotexto/" ++ PathName.new("~/.local/share/SuperCollider/Recordings/sonotexto").files.size.asSizedString(4) ++ "-b4-" ++ Date.localtime.stamp ++ ".wav", "WAV", "int16"); "Writing b4 stereo file".postln}, {"nil"}); 0.06.wait};
		}.play
		^"SonoTexto Write"
	}

	// The read method allows you to access the sounds of your sonotexto folder. A more accurate way is using SampleTexto class.
	*read { arg server;
		st = Dictionary.new;
		st.add(\st -> PathName(Platform.recordingsDir +/+ "/sonotexto/").entries.collect({arg grabacion; Buffer.read(server ? Server.default, grabacion.fullPath)}));
		^"SonoTexto Sounds "
	}
}
