# SonoTexto
Small Library and Class to record and play Buffers in SuperCollider. I write this Library and Class to improvise with musicians that impovise with and acoustic instrument, so I can record small Buffers of the Instrument in the moment of the improvisation.

The Library consist of 4 Buffers, 2 mono and 2 stereo. Odd Buffers are mono and even Buffers are stereo.

## Instructions
Download the repository and put the folder in you SuperCollider Extentions.

1 Open a new file in SuperCollider and boot SC.

2 Write this code lines: 

n = SonoTexto

n.boot

3 In order to record the four Buffers write this line.

n.rec(true, true, true, true)

4 You can rewrite the Buffers during an improvisation, just mantain the true argument for the Buffer you want to rewrite.

n.rec(false, true, false, true)


