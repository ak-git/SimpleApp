package com.ak.app;

import org.apache.commons.math3.analysis.interpolation.LinearInterpolator;
import org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction;
import org.jfugue.midi.MidiFileManager;
import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;
import org.jfugue.rhythm.Rhythm;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.IntStream;

public class MainApp {
  private MainApp() {
  }

  static void main() throws IOException {
    final int TEMPO_BPM = 1200;
    int iap = 166;

    for (Training training : Training.values()) {
      double[] dIn = training.doubles();

      double[] d = new double[dIn.length + 1];
      System.arraycopy(dIn, 0, d, 0, dIn.length);
      d[d.length - 1] = dIn[0];

      PolynomialSplineFunction interpolate = new LinearInterpolator()
          .interpolate(IntStream.range(0, d.length).asDoubleStream().toArray(), d);
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
      String pathname = "%s.midi".formatted(training);
      MidiFileManager.savePatternToMidi(pattern, new File(pathname));
      Logger.getLogger(MainApp.class.getName()).log(Level.INFO, () -> "MIDI file saved to %s".formatted(pathname));
      if (training == Training.values()[Training.values().length - 1]) {
        Player player = new Player();
        player.play(pattern);
      }
    }
  }
}
