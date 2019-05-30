// Example 1: record sound to TecnoTexto's Buffers

// In order to record sound to the Buffers check your sound options to assignate an input device as your computer's micro

s.boot;

// Assign SonoTexto to a variable

n = SonoTexto;

// Start SonoTexto

n.boot;

// Record or rewrite sound to the Buffers. In this case four Buffers.

n.rec(true, true, true, true);

// Play the Buffers

Synth(\b1);

Synth(\b2);

Synth(\b3);

Synth(\b4);

// You should declare the Synth each time you want to reproduce the Buffer. You can modify some parameters as the sustain of the Buffer. The Buffers 1 and 2 are 5 seconds duration and 2 and 4 ar 10 seconds duration.

Synth(\b1, [\sb1, 5]);

Synth(\b2, [\sb2, 5]);

Synth(\b3, [\sb3, 10]);

Synth(\b4, [\sb4, 10]);


// Write the content of a Buffer in the Recordings path. Just one at time in order to no overwrite the buffers.

n.write(false, false, false, true);