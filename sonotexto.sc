SonoTexto {

	*boot{
		SonoTexto.synths;
		^"SonoTexto Ready"
	}

	*synths{
		thisProcess.interpreter.executeFile((Platform.userExtensionDir ++ "/sonotexto/sonotexto-synths.scd").standardizePath);
		^"SonoTexto Synths"
	}

	*rec{ arg b1 = false, b2 = false, b3 = false, b4 = false;
		if(b1, {Synth(\b1rec)}, {"nada"});
		if(b2, {Synth(\b2rec)}, {"nada"});
		if(b3, {Synth(\b3rec)}, {"nada"});
		if(b4, {Synth(\b4rec)}, {"nada"});
		^"SonoTexto Rec"
	}
}