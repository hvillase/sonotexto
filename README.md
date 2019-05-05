# SonoTexto
Small Library and Class to record and play Buffers in SuperCollider. I write this Library and Class to improvise with musicians that play acoustic instruments, so I can record small Buffers of the Instrument in the moment of the improvisation.

The Library consist of 4 Buffers, 2 mono and 2 stereo. Odd Buffers are mono and even Buffers are stereo.

## Instructions
Download the repository and put the folder in you SuperCollider Extentions.

1 Open a new file in SuperCollider and boot SC.

2 Write this code lines: 

n = SonoTexto

n.boot

3 In order to record the four Buffers write this line.

n.rec(true, true, true, true)

4 To listen the Buffers run this lines:

Synth(\b1play)

Synth(\b2play)

Synth(\b3play)

Synth(\b4play)

5 You can rewrite the Buffers during an improvisation, just mantain the true argument for the Buffer you want to rewrite.

n.rec(false, true, false, true)

## Mapping
Each SynthDef have the next arguments:

b1play: rate = rate, st = startPos, pb1 = pan, ab1 = amp, atb1 = attack, sb1 = sustain, rb1 = release, ob1 = out 

b2play: rate = rate, st = startPos, pb2 = pan, ab2 = amp, atb2 = attack, sb2 = sustain, rb2 = release, ob2 = out

b3play: rate = rate, st = startPos, pb3 = pan, ab3 = amp, atb3 = attack, sb3 = sustain, rb3 = release, ob3 = out

b4play: rate = rate, st = startPos, pb4 = pan, ab4 = amp, atb4 = attack, sb4 = sustain, rb4 = release, ob4 = out
