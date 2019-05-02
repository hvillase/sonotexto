//=======================================
// SonoTexto
// Small Library to rec and play Synths
//=======================================

(
// Buffers time in seconds
~durb1 = 5;
~durb2 = 5;
~durb3 = 10;
~durb4 = 10;

// Buffer assignment
~buf1 = Buffer.alloc(s, s.sampleRate * ~durb1, 1); // odd mono
~buf2 = Buffer.alloc(s, s.sampleRate * ~durb2, 2); // even stereo
~buf3 = Buffer.alloc(s, s.sampleRate * ~durb3, 1);
~buf4 = Buffer.alloc(s, s.sampleRate * ~durb4, 2);

// Synths to record mono and stereo Buffer
(
SynthDef(\b1rec, {|in = 0, ovdub = 0|
RecordBuf.ar(SoundIn.ar(in), ~buf1.bufnum, 0, 1, ovdub, loop:0, doneAction:2);
}).add
);

(
SynthDef(\b2rec, {|in1 = 0, in2 = 1, ovdub = 0|
RecordBuf.ar(SoundIn.ar([in1, in2]), ~buf2.bufnum, 0, 1, ovdub, loop:0, doneAction:2);
}).add
);

(
SynthDef(\b3rec, {|in = 0, ovdub = 0|
RecordBuf.ar(SoundIn.ar(in), ~buf3.bufnum, 0, 1, ovdub, loop:0, doneAction:2);
}).add
);

(
SynthDef(\b4rec, {|in1 = 0, in2 = 1, ovdub = 0|
RecordBuf.ar(SoundIn.ar([in1, in2]), ~buf4.bufnum, 0, 1, ovdub, loop:0, doneAction:2);
}).add
);

// Synth Buffer Mono
(
SynthDef(\b1play, {|rate=1, st=0, pb1=0, ab1=1, atb1=0.01, rb1=0.1, ob1=0|
	var sen, env;
	sen = PlayBuf.ar(~buf1.numChannels, ~buf1.bufnum, rate, startPos:st);
	sen = Pan2.ar(sen, pb1, ab1);
	env = EnvGen.kr(Env.perc(atb1, rb1), doneAction:2);
	Out.ar(ob1, sen * env);
}).add
);

// Synth Buffer stereo
(
SynthDef(\b2play, {|rate=1, st=0, ab2=1, atb2=0.1, sb2=1, rb2=0.1, ob2=0|
	var sen, env;
	sen = PlayBuf.ar(~buf2.numChannels, ~buf2.bufnum, rate, startPos: st);
	env = EnvGen.kr(Env.new([0, 1, 1, 0], [atb2, sb2, rb2] ), doneAction: 2);
	Out.ar(ob2, (sen * env) * ab2);
}).add
);

// Synth Buffer Mono
(
SynthDef(\b3play, {|rate=1, st=0, pb3=0, ab3=1, atb3=0.01, rb3=0.1, ob3=0|
	var sen, env;
	sen = PlayBuf.ar(~buf3.numChannels, ~buf3.bufnum, rate, startPos:st);
	sen = Pan2.ar(sen, pb3, ab3);
	env = EnvGen.kr(Env.perc(atb3, rb3), doneAction:2);
	Out.ar(ob3, sen * env);
}).add
);

// Synth Buffer stereo
(
SynthDef(\b4play, {|rate=1, st=0, ab4=1, atb4=0.1, sb4=1, rb4=0.1, ob4=0|
	var sen, env;
	sen = PlayBuf.ar(~buf4.numChannels, ~buf4.bufnum, rate, startPos:st);
	env = EnvGen.kr(Env.new([0, 1, 1, 0], [atb4, sb4, rb4] ), doneAction:2);
	Out.ar(ob4, (sen * env) * ab4);
}).add
);

);