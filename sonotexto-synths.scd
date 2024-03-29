// SonoTexto SynthDefs and Buffers

(
// Assign variables for the Buffers length in seconds
~durb1 = 5;
~durb2 = 5;
~durb3 = 10;
~durb4 = 10;

// Allocate Buffers, odd mono and even stereo
~buf1 = Buffer.alloc(s, s.sampleRate * ~durb1, 1);
~buf2 = Buffer.alloc(s, s.sampleRate * ~durb2, 2);
~buf3 = Buffer.alloc(s, s.sampleRate * ~durb3, 1);
~buf4 = Buffer.alloc(s, s.sampleRate * ~durb4, 2);

// Record mono Buffer 1 with 5 seconds length
(
SynthDef(\b1rec, { |in = 0, ovdub = 0|
	RecordBuf.ar(SoundIn.ar(in), ~buf1.bufnum, 0, 1, ovdub, loop:0, doneAction:2);
}).add
);

// Record stereo Buffer 2 with 5 seconds length
(
SynthDef(\b2rec, { |in1 = 0, in2 = 1, ovdub = 0|
	RecordBuf.ar(SoundIn.ar([in1, in2]), ~buf2.bufnum, 0, 1, ovdub, loop:0, doneAction:2);
}).add
);

// Record mono Buffer 3 with 10 seconds length
(
SynthDef(\b3rec, { |in = 0, ovdub = 0|
	RecordBuf.ar(SoundIn.ar(in), ~buf3.bufnum, 0, 1, ovdub, loop:0, doneAction:2);
}).add
);

// Record stereo Buffer 4 with 10 seconds length
(
SynthDef(\b4rec, { |in1 = 0, in2 = 1, ovdub = 0|
	RecordBuf.ar(SoundIn.ar([in1, in2]), ~buf4.bufnum, 0, 1, ovdub, loop:0, doneAction:2);
}).add
);

// Play Synth Buffer1 Mono. SynthDef has a low cut frequency at 20 Hz.
(
SynthDef(\b1, { |rate = 1, tg = 0, st = 0, lp = 0, pb1 = 0, ab1 = 1, atb1 = 0.1, sb1 = 4.8, rb1 = 0.1, ob1 = 0|
	var sen, hpf, pan, env;
	var trg = Impulse.kr(tg);
	sen = PlayBuf.ar(~buf1.numChannels, ~buf1.bufnum, BufRateScale.kr(~buf1.bufnum) * rate, trg, st, lp);
	hpf = HPF.ar(sen, 20);
	pan = Pan2.ar(hpf, pb1, ab1);
	env = EnvGen.kr(Env.new([0, 1, 1, 0], [atb1, sb1, rb1] ), doneAction: 2);
	Out.ar(ob1, pan * env);
}).add
);

// Play Synth Buffer2 Stereo. SynthDef has a low cut frequency at 20 Hz.
(
SynthDef(\b2, { |rate = 1, tg = 0, st = 0, lp = 0, ab2 = 1, atb2 = 0.1, sb2 = 4.8, rb2 = 0.1, ob2 = 0|
	var sen, hpf, env;
	var trg = Impulse.kr(tg);
	sen = PlayBuf.ar(~buf2.numChannels, ~buf2.bufnum, BufRateScale.kr(~buf2.bufnum) * rate, trg, st, lp);
	hpf = HPF.ar(sen, 20);
	env = EnvGen.kr(Env.new([0, 1, 1, 0], [atb2, sb2, rb2] ), doneAction: 2);
	Out.ar(ob2, (hpf * env) * ab2);
}).add
);

// Play Synth Buffer3 Mono. SynthDef has a low cut frequency at 20 Hz.
(
SynthDef(\b3, { |rate = 1, tg = 0, st = 0, lp = 0, pb3 = 0, ab3 = 1, atb3 = 0.1, sb3 = 9.8, rb3 = 0.1, ob3 = 0|
	var sen, hpf, pan, env;
	var trg = Impulse.kr(tg);
	sen = PlayBuf.ar(~buf3.numChannels, ~buf3.bufnum, BufRateScale.kr(~buf3.bufnum) * rate, trg, st, lp);
	hpf = HPF.ar(sen, 20);
	pan = Pan2.ar(hpf, pb3, ab3);
	env = EnvGen.kr(Env.new([0, 1, 1, 0], [atb3, sb3, rb3] ), doneAction: 2);
	Out.ar(ob3, pan * env);
}).add
);

// Play Synth Buffer4 Stereo. SynthDef has a low cut frequency at 20 Hz.
(
SynthDef(\b4, { |rate = 1, tg = 0, st = 0, lp = 0, ab4 = 1, atb4 = 0.1, sb4 = 9.8, rb4 = 0.1, ob4 = 0|
	var sen, hpf, env;
	var trg = Impulse.kr(tg);
	sen = PlayBuf.ar(~buf4.numChannels, ~buf4.bufnum, BufRateScale.kr(~buf4.bufnum) * rate, trg, st, lp);
	hpf = HPF.ar(sen, 20);
	env = EnvGen.kr(Env.new([0, 1, 1, 0], [atb4, sb4, rb4] ), doneAction: 2);
	Out.ar(ob4, (hpf * env) * ab4);
}).add
);

);
