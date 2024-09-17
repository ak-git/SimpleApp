package com.ak.app;

import org.apache.commons.math3.analysis.interpolation.LinearInterpolator;
import org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction;
import org.jfugue.midi.MidiFileManager;
import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;
import org.jfugue.rhythm.Rhythm;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Logger;
import java.util.stream.IntStream;

public class MainApp {
  private MainApp() {
  }

  public static void main(String[] args) throws IOException {
    Logger.getLogger(MainApp.class.getName()).info(() -> "Hello word 2024.6.7-27!");

    final int TEMPO_BPM = 1200;

    String name = "Bestuzhev";
    int iap = 161;
    int minutes = 62;
    double[] dIn = Arrays.copyOf(Training.T_17.doubles(), minutes);

    double[] d = new double[dIn.length + 1];
    System.arraycopy(dIn, 0, d, 0, dIn.length);
    d[d.length - 1] = dIn[0];

    PolynomialSplineFunction interpolate = new LinearInterpolator()
        .interpolate(IntStream.range(0, d.length).asDoubleStream().toArray(), d);

    Player player = new Player();
    Pattern pattern = null;

    for (int i = 0; i < (d.length - 1) * TEMPO_BPM; ) {
      double pulsePBM = Math.clamp(interpolate.value(1.0 * i / TEMPO_BPM) * iap / 166.0, 40, 220);
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
    MidiFileManager.savePatternToMidi(pattern, new File("%s-%02d.midi".formatted(name, 2)));
    player.play(pattern);
  }
}
