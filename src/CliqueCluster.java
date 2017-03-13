import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by bacon on 2017/3/12.
 */
public class CliqueCluster {
    private static int cellRow;
    private static int cellColumn;
    private static int numberClusters;
    private static Cell[][] cells;
    private static List clusters;

    public static void main(String[] args){
        try {
            initialize();
            read();
            cluster();
            printResult();
            System.out.println("Cluster by Clique is over.");
        }catch (Exception e)
        {
            System.err.println("Err in main() :: " + e);
            e.printStackTrace();
        }
    }

    private static void initialize(){
        try {
            cellRow = ClusteringConfig.cellRow;
            cellColumn = ClusteringConfig.cellColumn;
            numberClusters = 0;
            cells = new Cell[cellRow + 1][cellColumn + 1];
            clusters = new ArrayList();
            clusters.add(new Cluster(numberClusters));

            for (int i = 1; i <= cellRow; i++){
                for (int j = 1; j <= cellColumn; j++){
                    cells[i][j] = new Cell();
                }
            }
        }catch (Exception e){
            System.err.println("Err in :: " + e);
        }
    }

    private static void read(){
        try {
            BufferedReader inByLine = new BufferedReader(new FileReader(ClusteringConfig.filePath));
            System.out.println("打开文件成功： " + ClusteringConfig.filePath + " opened");
            System.out.println("开始读取...");
            String piontStr = null;
            int i = 0;
            while ((piontStr = inByLine.readLine())!=null){
                double x;
                double y;
                int cellx;
                int celly;
                String[] pointsStr = Pattern.compile("\\s").split(piontStr);
                i++;
                x= Double.valueOf(pointsStr[0]);
                y = Double.valueOf(pointsStr[1]);
                cellx = (int)Math.ceil((x-ClusteringConfig.xmin)/ClusteringConfig.intervalx);
                celly = (int)Math.ceil((y-ClusteringConfig.ymin)/ClusteringConfig.intervaly);
                cells[celly][cellx].addnumberPoints();
                if (cells[celly][cellx].getNumberPoints()>=ClusteringConfig.densityThreshold)
                    cells[celly][cellx].setQuatified(1);
                System.out.println("读入点 " + i +"# : " + x + ", " + y);
            }
            inByLine.close();
            System.out.println("读取结束...");
        }catch (Exception e){
            System.out.println("Err in read() :: " + e);
        }
    }

    private static void cluster(){
        try {
            for(int i=1; i <= ClusteringConfig.cellRow; i++){
                for(int j=1; j <= ClusteringConfig.cellColumn; j++){
                    if (cells[i][j].getClusterNo()==0&&cells[i][j].getChecked()!=1){
                        if (cells[i][j].getQuatified()>0){
                            numberClusters += 1;
                            clusters.add(new Cluster(numberClusters));
                            retrieve(numberClusters,i,j);
                            System.out.println("Cluster() : i = " + i + " , j = "
                                + j + "ClusterBo = " + ((Cluster)clusters.get(numberClusters)).getClusterNo());
                        }else {
                            cells[i][j].setChecked(1);
                            cells[i][j].setClusterNo(0);
                        }
                    }
                }
            }
        }catch (Exception  e){
            System.out.println("Err in cluster() :: " + e);
        }
    }

    private static void retrieve(int k,int i,int j){
        try {
            int l, m;
            if (cells[i][j].getChecked()!=1&&cells[i][j].getClusterNo()==0){
                System.out.println("infor in retrieve( i = " + i + " , j = " + j + " ) :: number of points = "+cells[i][j].getNumberPoints()+"  , number of threshold = "+(int) ((Cluster)clusters.get(k)).getDensity()* ClusteringConfig.densityRatioThreshold);
                if (cells[i][j].getQuatified()>0&&cells[i][j].getNumberPoints()>=(int)((Cluster)clusters.get(k)).getDensity()*ClusteringConfig.densityRatioThreshold){
                    cells[i][j].setChecked(1);
                    cells[i][j].setClusterNo(k);
                    ((Cluster) clusters.get(k)).addCell(cells[i][j].getNumberPoints());
                    if (i!=j){
                        l = i - 1;
                        retrieve(k,l,j);
                    }
                    if (i!=cellColumn){
                        l = i + 1;
                        retrieve(k,l,j);
                    }
                    if (j != 1){
                        m = j - 1;
                        retrieve(k,i,m);
                    }
                }else {
                    cells[i][j].setChecked(1);
                    cells[i][j].setClusterNo(0);
                }
            }
        }catch (Exception e){
            System.out.println("Err in retrieve() :: " + e);
        }
    }

    private static void printResult(){
        try{
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(ClusteringConfig.outPath)));
            for (int j = 0; j <= cellColumn; j++)
                out.printf("%7d", j);
            out.println("\n");

            for (int i = 1; i <= cellRow; i++){
                out.printf("%7d", i);
                for (int j = 1; j <= cellColumn; j++){
                    out.printf("%7d",cells[i][j].getNumberPoints());
                }
                out.println("\n");
            }
            System.out.println("输出单元点数信息完毕，结果写入文件" + ClusteringConfig.outPath);
            out.println("\n");
            out.println("\n");
            out.println("\n");
            out.println("\n");
            out.println("\n");
            out.println("\n");

            for (int j = 0; j <= cellColumn; j++)
                out.printf("%7d", j);
            out.println("\n");
            for (int i = 1; i <= cellRow; i++){
                out.printf("%7d", i);
                for (int j = 1; j <= cellColumn; j++){
                    out.printf("%2d", cells[i][j].getClusterNo());

                    out.printf("%7d",cells[i][j].getNumberPoints());
                    out.printf(":");
                }
                out.println("\n");
            }
            out.println("下面是各点详细信息，其中类别 0 表示离散点");
            BufferedReader inByLine = new BufferedReader(new FileReader(ClusteringConfig.filePath));
            System.out.println("打开文件成功: " + ClusteringConfig.filePath + " opened");
            System.out.println("开始读取: Read() begin");
            String piontStr = null;
            int i =0;
            while ((piontStr = inByLine.readLine())!=null){
                double x;
                double y;
                int cellx;
                int celly;
                String[] pointsStr = Pattern.compile("\\s").split(piontStr);
                i++;
                x = Double.valueOf(pointsStr[0]);
                y = Double.valueOf(pointsStr[1]);
                cellx = (int) Math.ceil((x - ClusteringConfig.xmin)
                        / ClusteringConfig.intervalx);
                celly = (int) Math.ceil((y - ClusteringConfig.ymin)
                        / ClusteringConfig.intervaly);
                out.println("点 ( "+x+" , "+y + " )  ,  类别：" +cells.clone()[celly][cellx].getClusterNo()+"; 该点位于 行号为 "+celly+ " ,列号为 "+cellx+"的单元格内.");
            }
            System.out.println("输出单元聚类信息完毕，结果写入文件" + ClusteringConfig.outPath);
            inByLine.close();
            out.close();
        }catch (Exception e){
            System.out.println("Err in printResult() :: " + e);
        }
    }
}
