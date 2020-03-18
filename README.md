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

**rate**: you can modifiy the speed of the sample in values other than 0, eg. 1 is normal speed, 2 is twice the speed, 0.5 y half the speed. You can use minus values as -1 and if the value of loop parameter is 1, the you can play samples in reverse.

**tg**: this returns the playback to the start pioint of the sample depending on a frequency. You can put values from 0 to higher.

**st**: this is the initial point from which the sample starts to play. You can modifiy this number dividing the numFRames of the sample. eg. ~buf1.numFrames/2 will start at the half of the sample. Be careful not to divide in 1, otherwhise you have no sound becaus you are starting at the end of the sample.

**loop**: you can put values form 0 to 1. 0 is loop inactive, 1 is loop active.

**pb1**: or **pb3** is the pan value for buffer 1 and 3. These buffers are mono, you can move the sound between Left (-1.0) and Right (1.0).

**ab1**...**abn**: this is the amplitude value. Usually you multiply amp between 0 and 1 as a normalized value. As you are working with recorded sound on the moment some times you need to go beyond 1. You may listen how much you go beyond 1, and be extremely careful if you record again a buffer as you can have a strong feedback. I looking fir a solution to have more convenient way to approach this parameter.

**atb1** ... **atbn**: this is the attack time value.

**sb1** ... **sbn**: this is the sustain time value. Notice that buffers 1 and 2 have 5 seconds durations, son this value can be between 0 and 5.

**rb1** ... **rbn**: this is the sustain time value.

**ob1** ... **obn**: this is the output. The default is 0, you can modify it to another value if you have a multichannel sound card or want to send your sample through another bus, eg. an effect bus.

### An example with parameters

```
Synth(\b1, [\rate, -1, \lp, 1, \pb1, -0.9, \ab1, 1, \atb1, 1, \sb1, 2, \rb1, 1]);

Synth(\b2, [\rate, 2]);

Synth(\b3, [\st, ~buf3.numFrames/2, \pb3, 0.5]);

Synth(\b4, [\atb4, 0.5, \sb4, 8, \rb4, 1.5]);
```
