/**
 * Created by bacon on 2017/3/12.
 */
public class ClusteringConfig {
    public static int densityThreshold = 20;  //密度阈值
    public static double densityRatioThreshold = 0.6;//新单元与簇的密度比阈值
    public static double xmin = 0.0;
    public static double xmax = 30.0;
    public static double ymin = 0.0;
    public static double ymax = 30.0;

    public static String filePath = "sample.txt";
    public static int cellColumn = 60;
    public static int cellRow = 60;
    public static double intervalx = (xmax - xmin)/cellColumn;
    public static double intervaly = (ymax - ymin)/cellRow;

    public static String outPath = "result.txt";
}
