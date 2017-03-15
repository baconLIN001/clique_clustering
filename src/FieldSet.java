/**
 * Created by bacon on 2017/3/14.
 */
public class FieldSet {
    private String sourceFieldName = "";
    private String toFieldName = "";
    private double[][] rawData;
    private int sampleSize;

    public FieldSet(String srcName,String tgtName, int sSize){
        sourceFieldName = srcName;
        toFieldName = tgtName;
        rawData = new double[2][sSize];
        sampleSize = sSize;
    }

    public String getSourceFieldName(){
        return sourceFieldName;
    }

    public String getToFieldName(){
        return toFieldName;
    }

    public int getSampleSize(){
        return sampleSize;
    }

    public void setRawData(double src,double tgt,int rowIndex){
        rawData[0][rowIndex]=src;
        rawData[1][rowIndex]=tgt;
    }

    public double[][] getRawData() {
        return rawData;
    }

    public Facet SetupFacet(){
        Facet rstFacet = new Facet();
        Cell[][] cells;
        double xmin = 0.0;
        double xmax = 30.0;
        double ymin = 0.0;
        double ymax = 0.0;

        int cellRow = ClusteringConfig.cellRow;
        int cellColumn = ClusteringConfig.cellColumn;
        int cellx;
        int celly;

        cells = new Cell[cellRow+1][cellColumn + 1];
        try {
            for(int i = 1; i<= ClusteringConfig.cellRow; i++){
                for (int j = 1; j< = ClusteringConfig.cellColumn; j++){
                    cells[i][j] = new Cell();
                }
            }

            xmin = xmax = rawData[0][0];
            ymin = ymax = rawData[1][0];

            for (int i = 1; i < sampleSize; i++){
                if (xmin > rawData[0][1])
                    xmin = rawData[0][1];
                if (xmax < rawData[0][i])
                    xmax = rawData[0][i];

                if (ymin > rawData[1][i])
                    ymin = rawData[1][i];

                if (ymax < rawData[1][i])
                    ymax = rawData[1][i];
            }
        }
    }
}
