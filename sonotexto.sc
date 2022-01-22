SonoTexto {

	// the variable st means sonotexto, it is intended to make a Dictionary
	classvar <st;

	// the class method *boot check if there is a folder to write sounds and executes the document called sonotexto-synths.scd.
	*boot {

		// this alerts when the sound folder sonotexto is not in the Recordings path.
		if (File.existsCaseSensitive(Platform.recordingsDir ++ "/sonotexto"), {"sonotexto folder is in place".postln}, {"No sound folder is in your Recordings path, please create one with the name 'sonotexto'".postln});

		// this executes a document with the SynthDefs requiered to work.
		thisProcess.interpreter.executeFile((Platform.userExtensionDir ++ "/sonotexto/sonotexto-synths.scd").standardizePath);
		^"SonoTexto Active"
	}

	// the class method *rec allows to rec one, or all, the available Buffers when argument is true
	// it is possible to warn about which syhntdefs are available
	*rec { arg b1 = 0, b2 = 0, b3 = 0, b4 = 0;
		if (b1.asBoolean, {Synth(\b1rec); "Recording b1 mono file".postln}, {"nil"});
		if (b2.asBoolean, {Synth(\b2rec); "Recording b2 stereo file".postln}, {"nil"});
		if (b3.asBoolean, {Synth(\b3rec); "Recording b3 mono file".postln}, {"nil"});
		if (b4.asBoolean, {Synth(\b4rec); "Recording b4 stereo file".postln}, {"nil"});
		^"SonoTexto Rec"
	}

	// the class method *write allows to write the temporary buffer sounds in the folder sonotexto placed in the Recordings Directory
	*write { arg b1 = 0, b2 = 0, b3 = 0, b4 = 0;

		// this fork writes each Buffer in the hard disk when argument is true. It waits 60 miliseconds to avoid writing the same name to all Buffers.
		fork {
			if (b1.asBoolean, {~buf1.write(Platform.recordingsDir +/+ "/sonotexto/" ++ PathName.new("~/.local/share/SuperCollider/Recordings/sonotexto").files.size.asStringToBase(10, 4) ++ "-b1-" ++ Date.localtime.stamp ++ ".wav", "WAV", "int16"); "Writing b1 mono file".postln}, {"nil"});
			0.06.wait;
			if (b2.asBoolean, {~buf2.write(Platform.recordingsDir +/+ "/sonotexto/" ++ PathName.new("~/.local/share/SuperCollider/Recordings/sonotexto").files.size.asStringToBase(10, 4) ++ "-b2-" ++ Date.localtime.stamp ++ ".wav", "WAV", "int16"); "Writing b2 stereo file".postln}, {"nil"});
			0.06.wait;
			if (b3.asBoolean, {~buf3.write(Platform.recordingsDir +/+ "/sonotexto/" ++ PathName.new("~/.local/share/SuperCollider/Recordings/sonotexto").files.size.asStringToBase(10, 4) ++ "-b3-" ++ Date.localtime.stamp ++ ".wav", "WAV", "int16"); "Writing b3 mono file".postln}, {"nil"});
			0.06.wait;
			if (b4.asBoolean, {~buf4.write(Platform.recordingsDir +/+ "/sonotexto/" ++ PathName.new("~/.local/share/SuperCollider/Recordings/sonotexto").files.size.asStringToBase(10, 4) ++ "-b4-" ++ Date.localtime.stamp ++ ".wav", "WAV", "int16"); "Writing b4 stereo file".postln}, {"nil"});
			0.06.wait;
		}
		^"SonoTexto Write"
	}

	// the class method *read accesses sonotexto sound folder. A more accurate way is using the class SampleTexto
	*read { arg server;
		st = Dictionary.new;
		st.add(\st -> PathName(Platform.recordingsDir +/+ "/sonotexto/").entries.collect({arg grabacion; Buffer.read(server ? Server.default, grabacion.fullPath)}));
		^"SonoTexto Sounds "
	}

	// the class method *info says the number of sounds available in the sonotexto folder
	*info {
		("SonoTexto has " ++ PathName.new("~/.local/share/SuperCollider/Recordings/sonotexto").files.size ++ " sounds recorded").postcln;
	}
}
