package model.photo;

import java.util.Objects;

/**
 * This represents the Color Class.
 */
public class Color {
  private final int c1;
  private final int c2;
  private final int c3;

  /**
   * This represents the color constructor class.
   *
   * @param c1 The R color component.
   * @param c2 The G color component.
   * @param c3 The B color component.
   * @throws IllegalArgumentException if any of the color components are negative.
   */
  public Color(int c1, int c2, int c3) throws IllegalArgumentException {
    if (c1 < 0 || c2 < 0 || c3 < 0) {
      throw new IllegalArgumentException("Invalid Color!");
    }
    this.c1 = c1;
    this.c2 = c2;
    this.c3 = c3;
  }

  /**
   * Get First color input.
   */
  public int getC1() {
    return this.c1;
  }

  /**
   * Get Second color input.
   */
  public int getC2() {
    return this.c2;
  }

  /**
   * Get Third color input.
   */
  public int getC3() {
    return this.c3;
  }

  /**
   * The To String representation of the colors.
   */
  @Override
  public String toString() {
    return "(" + this.c1 + "," + this.c2 + "," + this.c3 + ")";
  }


  /**
   * The equals method for the Dimension class.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Color color = (Color) o;
    return Double.compare(color.c1, c1) == 0 && Double.compare(color.c2, c2) == 0 && Double.compare(color.c3, c3) == 0;
  }

  /**
   * The hashcode method for the Dimension class.
   */
  @Override
  public int hashCode() {
    return Objects.hash(c1, c2, c3);
  }


}
