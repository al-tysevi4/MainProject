import Task2.Point;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PointTest {
    @Test
    public void testPoint() {
        Point p1 = new Point(9,7);
        Point p2 = new Point(4, 7);
        Assertions.assertEquals(p1.distance(p2), 5.0);
    }
}
