package com.ak.app;

import org.apache.commons.math3.analysis.interpolation.LinearInterpolator;
import org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction;
import org.jfugue.midi.MidiFileManager;
import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;
import org.jfugue.rhythm.Rhythm;

import javax.annotation.Nonnull;
import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;
import java.util.stream.IntStream;

public class MainApp {
  private MainApp() {
  }

  public static void main(@Nonnull String[] args) throws IOException {
    Logger.getLogger(MainApp.class.getName()).info(() -> "Hello word 2023.12.21!");

    final double MAX_IAP = 166.0;
    final int TEMPO_BPM = 1200;

    int iap = 171;
    double[] dIn = {80, 90, 100, 135, 135, 135, 120, 115, 115, 140, 140, 140, 125, 120, 120, 145, 145, 145, 130, 125, 125, 150,
        150, 150, 135, 130, 130, 155, 155, 155, 140, 135, 135, 160, 160, 160, 120, 100};

    double[] d = new double[dIn.length + 1];
    System.arraycopy(dIn, 0, d, 0, dIn.length);
    d[d.length - 1] = dIn[0];

    PolynomialSplineFunction interpolate = new LinearInterpolator()
        .interpolate(IntStream.range(0, d.length).asDoubleStream().toArray(), d);

    Player player = new Player();
    Pattern pattern = null;

    for (int i = 0; i < (d.length - 1) * TEMPO_BPM; ) {
      double pulsePBM = Math.max(40, Math.min(interpolate.value(1.0 * i / TEMPO_BPM) * iap / MAX_IAP, 220));
      int cadence = (int) Math.round(pulsePBM / 2.0);
      int len = TEMPO_BPM / cadence;
      i += len;

      Rhythm r = new Rhythm();
      r.addLayer("S" + ".".repeat(len - 1) + ".".repeat(len));
      r.addLayer(".".repeat(len) + "O" + ".".repeat(len - 1));
      Pattern rPattern = r.getPattern();

      if (pattern == null) {
        pattern = rPattern;
        pattern.setTempo(TEMPO_BPM);
      }
      else {
        pattern.add(rPattern);
      }
    }

    Logger.getLogger(MainApp.class.getName()).info(() -> "");
    MidiFileManager.savePatternToMidi(pattern, new File("Training_%d.midi".formatted(iap)));
    player.play(pattern);
  }
}
