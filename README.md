# SonoTexto
A SuperCollider class to record and play Buffers. I write this Class to improvise with musicians that play acoustic instruments, so I can record small Buffers of the instruments in the moment of the improvisation. Also it can be used to record small fragments of sound environment in order to perform a live coding session with sound as material.

The class call a document that contains 4 Buffers, 2 mono and 2 stereo. Odd Buffers are mono and even Buffers are stereo.

Be aware of feedback when you use this Class with big sound systems.

## Instructions
1 Download or clone the repository and put the folder in your SuperCollider Extentions.

2 Make a directory called *sonotexto* in the SuperCollider's Recrodings folder.

3 Open a new file in SuperCollider and boot SuperCollider.

4 Write this code lines:

```
n = SonoTexto

n.boot
```

5 In order to record the four Buffers write this line. You can write just one buffer or rewrite any buffer.

```
n.rec(true, true, true, true)
```

6 To listen the Buffers run this lines:

```
Synth(\b1)

Synth(\b2)

Synth(\b3)

Synth(\b4)
```

7 You can rewrite the Buffers during the improvisation, just mantain the true argument for the Buffer you want to rewrite.

```
n.rec(false, true, false, true)
```

8 You can keep the Buffer's sound.

```
n.write(true, true, true, true)
```

## Mapping arguments
Each SynthDef have these arguments:

```
\b1: rate = rate, tg = trigger frequency, st = startPos, lp = loop, pb1 = pan, ab1 = amp, atb1 = attack, sb1 = sustain, rb1 = release, ob1 = out

\b2: rate = rate, tg = trigger frequency, st = startPos, lp = loop, pb2 = pan, ab2 = amp, atb2 = attack, sb2 = sustain, rb2 = release, ob2 = out

\b3: rate = rate, tg = trigger frequency, st = startPos, lp = loop, pb3 = pan, ab3 = amp, atb3 = attack, sb3 = sustain, rb3 = release, ob3 = out

\b4: rate = rate, tg = trigger frequency, st = startPos, lp = loop, pb4 = pan, ab4 = amp, atb4 = attack, sb4 = sustain, rb4 = release, ob4 = out
```