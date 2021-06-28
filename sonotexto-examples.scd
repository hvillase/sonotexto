// Example 1: record sound to SonoTexto's Buffers

// In order to record sound to the Buffers check your sound options to assignate an input device as your computer's micro or sound card.

s.boot;

// Assign SonoTexto to a variable

n = SonoTexto;

// Start SonoTexto

n.boot;

// If you run for the first time SonoTexto it awares you to make a directory called <sonotexto> in the Recordings dir. Run this line to know where the Recordings path is.

Platform.recordingsDir;

// Record or rewrite sound to the Buffers. In this case four Buffers.

n.rec(1); // this records the first buffer

n.rec(0, 0, 0, 1); // this records the last buffer

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


// Write the content of a Buffer in the Recordings path.

n.write(1); // this writes only the first buffer

n.write(0, 0, 0, 1); // this writes the four buffers

// Read the buffer dictionary pointing to your sonotexto recordings folder.

n.read;

n.st[\st][2];

n.st[\st][1].play; // this plays the sound in index 1

n.st[\st][(n.st.[\st].size)-1].play; // to look for the last archive

n.st[\st][4].plot;

// Informs the number of samples in the  sonotexto folder

n.info;
