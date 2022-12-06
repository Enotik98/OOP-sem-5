import ThreeDiagonal.MatrixValidator;
import ThreeDiagonal.Solver;
import ThreeDiagonal.ThreeDiagonalMatrix;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ThreeDiagonalMatrixTest {
    private static final MatrixValidator validator = new MatrixValidator();

    @Test
    public void isValidMatrix(){
        //given
        List<Double> validA = new ArrayList<>(Arrays.asList(1.0, 3.0, 1.0, 1.0));
        List<Double> validC = new ArrayList<>(Arrays.asList(2.0, 2.0, 4.0, 2.0, 3.0));
        List<Double> validB = new ArrayList<>(Arrays.asList(1.0, 1.0, 1.0, 1.0));
        List<Double> validF = new ArrayList<>(Arrays.asList(1.0, 2.0, 1.0, 1.0, 1.0));
        //when
        ThreeDiagonalMatrix validMatrix = new ThreeDiagonalMatrix(validA, validC, validB, validF);
        //then
        assertTrue(validator.isTridiagonal(validMatrix));
        //given
        List<Double> notValidB = new ArrayList<>(Arrays.asList(10.0, 10.0, 10.0, 10.0));
        //when
        ThreeDiagonalMatrix notValidMatrix = new ThreeDiagonalMatrix(validA, validC, notValidB, validF);
        //then
        assertFalse(validator.isTridiagonal(notValidMatrix));
    }

    @Test
    public void solve(){
        //given
        Solver solver = new Solver(validator);
        List<Double> a = new ArrayList<>(Arrays.asList(1.0, 1.0, 1.0, 1.0));
        List<Double> c = new ArrayList<>(Arrays.asList(2.0, 2.0, 2.0, 2.0, 2.0));
        List<Double> b = new ArrayList<>(Arrays.asList(1.0, 1.0, 1.0, 1.0));
        List<Double> f = new ArrayList<>(Arrays.asList(1.0, 1.0, 1.0, 1.0, 1.0));
        //when
        ThreeDiagonalMatrix matrix = new ThreeDiagonalMatrix(a, c, b, f);
        //then
        assertTrue(validator.isTridiagonal(matrix));

        //given
        List<Double> expectedResult = new ArrayList<>(Arrays.asList(0.5, 0.0, 0.5, 0.0, 0.5));
        //when
        List<Double> result = solver.solve(matrix);
        //then
        assertEquals(expectedResult, result);
    }
    @Test
    public void createMatrixFromFile(){
        //given
        ThreeDiagonalMatrix matrix = new ThreeDiagonalMatrix(new File("src/test/resources/testMatrix.txt"));
        //when
        List<Double> expectedA = new ArrayList<>(Arrays.asList(1.0, 3.0, 1.0, 1.0));
        List<Double> expectedC = new ArrayList<>(Arrays.asList(2.0, 2.0, 4.0, 2.0, 3.0));
        List<Double> expectedB = new ArrayList<>(Arrays.asList(1.0, 1.0, 1.0, 1.0));
        List<Double> expectedF = new ArrayList<>(Arrays.asList(1.0, 2.0, 1.0, 1.0, 1.0));
        //then
        assertEquals(expectedA, matrix.getA());
        assertEquals(expectedC, matrix.getC());
        assertEquals(expectedB, matrix.getB());
        assertEquals(expectedF, matrix.getF());
    }
}
