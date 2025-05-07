import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Arrays;

public class SensorDataProcessor{

    // Sensor data and limits.
    public double[][][] data;
    public double[][] limit;

    // constructor
    public SensorDataProcessor(double[][][] data, double[][] limit) {
        this.data = data;
        this.limit = limit;
    }

    // calculates average of sensor data
    private double average(double[] array) {
        int i = 0;
        double val = 0;
        for (i = 0; i < array.length; i++) {
            val += array[i];
        }

        return val / array.length;
    }

    // calculate data
    public void calculate(double d) {

        int i, j, k = 0;
        double[][][] data2 = new double[data.length][data[0].length][data[0][0].length];

        BufferedWriter out;

        // Write racing stats data into a file
        try {
            out = new BufferedWriter(new FileWriter("RacingStatsData.txt"));

            for (i = 0; i < data.length; i++) {
                for (j = 0; j < data[0].length; j++) {
                    double powLimit = d - Math.pow(limit[i][j], 2.0);
                    for (k = 0; k < data[0][0].length; k++) {
                        data2[i][j][k] = data[i][j][k] / powLimit;


                        double average2 = average(data2[i][j]);
                        if (average2 > 10 && average2 < 50 || data2[i][j][k] > data[i][j][k])
                            break;
                        else if (Math.pow(Math.abs(data[i][j][k]), 3) < Math.pow(Math.abs(data2[i][j][k]), 3)
                                && average(data[i][j]) < data2[i][j][k])
                            data2[i][j][k] *= 2;
                        out.write(Arrays.toString(data2[i][j]) + "\t");
                    }
                }
            }

            out.close();

        } catch (Exception e) {
            System.out.println("Error= " + e);
        }
    }

}