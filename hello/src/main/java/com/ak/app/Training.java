package com.ak.app;

public enum Training {
  T_0("Очень лёгкая креатинфосфатная тренировка - 1", new double[] {
      80, 90, 100, 120, 130, 130, 140, 142, 100, 95, 130, 140, 140, 140, 145, 110, 100, 130, 140, 140, 140, 145, 110,
      100, 130, 140, 145, 145, 150, 120, 110, 130, 140, 145, 145, 150, 120, 110, 130, 140, 145, 145, 150, 120, 110, 130,
      145, 150, 150, 155, 130, 110, 130, 145, 150, 150, 155, 130, 110, 130, 145, 150, 150, 155, 130, 110, 100
  }),
  T_7("Лёгкая аэробно-анаэробная тренировка", new double[] {
      80, 100, 120, 140, 140, 140, 142, 142, 142, 146, 146, 146, 148, 148, 150, 150, 152, 152, 154, 154, 154, 156,
      156, 156, 158, 158, 160, 160, 160, 162, 162, 162, 164, 164, 164, 166, 166, 168, 168, 170, 170, 170, 172, 172,
      172, 176, 176, 176, 140, 120, 110
  }),
  T_11("Очень лёгкая лактатная тренировка", new double[] {
      80, 90, 100, 120, 124, 128, 132, 136, 140, 140, 140, 105, 95, 130, 134, 138, 142, 142, 142, 105, 95, 130,
      134, 138, 142, 142, 142, 105, 95, 132, 136, 140, 144, 144, 144, 115, 105, 132, 136, 140, 144, 144, 144, 115,
      105, 134, 138, 142, 146, 146, 146, 115, 105, 136, 140, 144, 148, 148, 148, 115, 105, 95
  }),
  T_12("Лёгкая лактатная тренировка", new double[] {
      80, 90, 100, 130, 140, 140, 150, 150, 160, 160, 166, 130, 100, 140, 150, 150, 160, 160, 168, 135, 115, 140,
      150, 150, 160, 160, 168, 135, 115, 147, 156, 156, 166, 166, 170, 140, 120, 150, 158, 158, 164, 164, 172, 140,
      120, 152, 160, 160, 168, 168, 174, 140, 120, 154, 166, 166, 170, 170, 176, 140, 120, 110
  }),
  T_15("Очень лёгкая смешанная тренировка", new double[] {
      80, 90, 100, 135, 135, 135, 120, 115, 115, 140, 140, 140, 125, 120, 120, 145, 145, 145, 130, 125, 125, 150,
      150, 150, 135, 130, 130, 155, 155, 155, 140, 135, 135, 160, 160, 160, 120, 100
  }),
  T_16("Лёгкая смешанная тренировка", new double[] {
      80, 90, 100, 130, 130, 130, 130, 130, 130, 140, 140, 140, 140, 140, 140, 140, 150, 150, 150, 150, 150, 150,
      150, 150, 100, 100, 140, 140, 140, 147, 147, 147, 147, 152, 152, 152, 152, 105, 105, 142, 142, 142, 150, 150,
      150, 150, 157, 157, 157, 157, 110, 110, 145, 145, 145, 155, 155, 155, 155, 162, 162, 162, 162, 115, 105, 100
  }),
  T_17("Смешанная тренировка средней тяжести", new double[] {
      80, 90, 100, 130, 140, 140, 140, 140, 150, 150, 150, 150, 156, 156, 156, 156, 156, 160, 160, 160, 162, 162,
      162, 170, 170, 170, 150, 120, 110, 140, 152, 152, 152, 152, 156, 158, 158, 158, 158, 162, 162, 168, 168, 168,
      172, 172, 160, 130, 120, 150, 160, 160, 166, 166, 170, 170, 172, 172, 178, 160, 130, 120
  });


  private final String title;
  private final double[] doubles;

  Training(String title, double[] doubles) {
    this.title = title;
    this.doubles = doubles;
  }

  public String title() {
    return title;
  }

  public double[] doubles() {
    return doubles;
  }
}