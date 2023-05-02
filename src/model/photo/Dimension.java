package model.photo;

import java.util.Objects;

/**
 * This represents the Dimension Class.
 */
public class Dimension {
  public int Dimension01;
  public int Dimension02;

  /**
   * This represents the Dimension Constructor.
   * @param Dimension01 1st shape dimension.
   * @param Dimension02 2nd shape dimension.
   * @throws IllegalArgumentException if Dimensions are negative
   */
  public Dimension(int Dimension01, int Dimension02) throws IllegalArgumentException{
    if(Dimension01 < 0 || Dimension01 < 0){
      throw new IllegalArgumentException("Invalid Dimensions");
    }
    this.Dimension01 = Dimension01;
    this.Dimension02 = Dimension02;
  }

  /**
   * Get First Dimension Parameter.
   */
  public int getDimension01() {
    return this.Dimension01;
  }

  /**
   * Get Second Dimension Parameter.
   */
  public int getDimension02() {
    return this.Dimension02;
  }


  /**
   * The equals method for the Dimension class.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Dimension dimension = (Dimension) o;
    return Double.compare(dimension.Dimension01, Dimension01) == 0
            && Double.compare(dimension.Dimension02, Dimension02) == 0;
  }

  /**
   * The hashcode method for the Dimension class.
   */
  @Override
  public int hashCode() {
    return Objects.hash(Dimension01, Dimension02);
  }
}
