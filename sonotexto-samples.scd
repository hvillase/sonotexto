(
~std = Dictionary.new;
~std.add(\stx -> PathName(Platform.recordingsDir +/+ "/sonotexto/").entries.collect({arg grabacion; Buffer.read(s, grabacion.fullPath)}));
);
