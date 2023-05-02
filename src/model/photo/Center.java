package model.photo;

import java.util.Objects;

public class Center {
  public int point1;
  public int point2;


  public Center(int point1, int point2) throws IllegalArgumentException{
    if(point1 < 0 || point2 < 0){
        throw new IllegalArgumentException("Invalid Start Point");
      }
      this.point1 = point1;
      this.point2 = point2;
    }

    public int getPoint1() {
      return this.point1;
    }

    public int getPoint2() {
      return this.point2;
  }

  @Override
  public String toString(){
    return  String.format("%d",this.point1) + "," + String.format("%d",this.point2);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Center center = (Center) o;
    return Double.compare(center.point1, point1) == 0 && Double.compare(center.point2, point2) == 0;
  }

  @Override
  public int hashCode() {
    return Objects.hash(point1, point2);
  }
}
