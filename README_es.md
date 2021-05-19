# SonoTexto

Es una clase de SuperCollider para grabar sonido en Buffers y tocar con ellos en el momento. Comencé a escribir esta clase para improvisar junto a músicos que tocan instrumentos acústicos y después la adapté para hacer live coding desde cero con sonido grabado en el momento de la presentación. Además de grabar, la clase puede guardar los sonidos en el disco duro para utilizarlos de manera diferida.

La clase llama a un documento que contiene 4 Buffers capace de alojar sonidos de 5 y 10 segundos de duración, 2 mono y 2 estereo. Los Buffers impares son mono y los pares son estéreo. Los buffers 1 y 2 graban 5 segundos y 3 y 4 10 segundos.

Ten cuidado con la retroalimentción cuando uses SonoTexto con sistemas de sonido grandes.

SonoTexto es desarrollado en el contexto de mi investigación doctoral en Tecnología Musical con el apoyo de CONACyT a través del Posgrado en Música UNAM.

## Instrucciones
1 Baja o clona el repositorio y pon el folder en las Extensiones de tu SuperCollider.

2 Haz un directorio llamado *sonotexto* en el folder Recordings de tu SuperCollider.

3 Abre un nuevo archivo en SuperCollider y haz *s.boot*.

4 Escribe este código:


```
n = SonoTexto

n.boot
```

5 Para grabar los cuatro Buffers escribe esta línea. Puedes escribir solo un buffer o reescribir cualquier buffer, 1 = verdadero, 0 = falso.

```
n.rec(1, 1, 1, 1)
```

6 Para escuchar los Buffers ejecuta estas líneas.

```
Synth(\b1)

Synth(\b2)

Synth(\b3)

Synth(\b4)
```

7 Puedes reescribir los Buffers durante la improvisación, solo manten el argumento verdadero para el Buffer que quieres reescribir.

```
n.rec(0, 1, 0, 1)
```

8 Puedes conservar el sonido del Buffer. 1 = verdadero, 0 = falso.

```
n.write(1, 1, 1, 1)
```

## Mapeo de argumentos
Cada SynthDef tiene estos argumentos:

```
\b1: rate = rate, tg = trigger frequency, st = startPos, lp = loop, pb1 = pan, ab1 = amp, atb1 = attack, sb1 = sustain, rb1 = release, ob1 = out

\b2: rate = rate, tg = trigger frequency, st = startPos, lp = loop, pb2 = pan, ab2 = amp, atb2 = attack, sb2 = sustain, rb2 = release, ob2 = out

\b3: rate = rate, tg = trigger frequency, st = startPos, lp = loop, pb3 = pan, ab3 = amp, atb3 = attack, sb3 = sustain, rb3 = release, ob3 = out

\b4: rate = rate, tg = trigger frequency, st = startPos, lp = loop, pb4 = pan, ab4 = amp, atb4 = attack, sb4 = sustain, rb4 = release, ob4 = out
```

**rate***: puedes modificar la velocidad de la muestra en valores distintos a 0, ej. 1 es la velocidad normal, 2 es el doble de la velocidad, 0.5 es la mitad de la velocidad. Puedes usar valores negativos como -1 y si el valor del parametro de loop es 1, entonces puedes tocar las muestras en reversa.

**tg**: este regresa la reproducción al punto de inicio de la muestra dependiendo de la frecuencia. Puedes poner valores desde 0 hasta los más alto.

**st**: este es el valor inicial desde que la muestra incia a tocar. Puedes modificar este número multiplicando o dividiendo el numFrames de la muestra, ej. ~buf1.numFrames/2 emepzara a tocar a la mitad de la muestra. Ten cuidado de no dividir entre 1, de otra forma no tendrás sonido pues estas empezando al final de la muestra.

**loop**: puedes poner valores de 0 o 1. 0 inactiva el loop, 1 activa el loop.

**pb1**: or **pb3** es el valor de paneo para los buffes 1 y 3. Estos buffers son mono, puedes mover el sonido entre izquierda (-1.0) y derecha (1.0).

**ab1** ... **abn**: este es el valor de amplitud. Usually you multiply amp between 0 and 1 as a normalized value. Como estas trabajando con sonido grabado en el momento algunas veces necesitas ir más allá de 1. Escucha que tanto vas más allá y ten mucho cuidado si vuelves a grabar otra vez en ese buffer porque puedes cuasar una fuerte retroalimentación. Estoy buscando una solución para tener una forma más conveniente de usar este parámetro.

**atb1** ... **atbn**: tiempo de ataque.

**sb1** ... **sbn**: tiempo de sostenimiento. Nota que los buffers 1 y 2 tienen 5 segundos de duración, así que este valor puede estar entre 1.0 y 5.0.

**rb1** ... **rbn**: valor de relajamiento.

**ob1** ... **obn**: este es el valor de salida. El valor inicial es 0, puedes modificarlo si tienes una tarjeta multicanal o quieres enviar tu sonido a través de otro bus, por ejemplo un bus de efectos.

### Ejemplos con parámetros

```
Synth(\b1, [\rate, -1, \lp, 1, \pb1, -0.9, \ab1, 1, \atb1, 1, \sb1, 2, \rb1, 1]);

Synth(\b2, [\rate, 2]);

Synth(\b3, [\st, ~buf3.numFrames/2, \pb3, 0.5]);

Synth(\b4, [\atb4, 0.5, \sb4, 8, \rb4, 1.5]);
```
