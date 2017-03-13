/**
 * Created by bacon on 2017/3/12.
 * 类别信息
 */
public class Cluster {
    private int clusterNo;   //means outlier;
    private int numberCells; //该聚类中有点的单元格的数目
    private int density;     //密度

    public Cluster(int i){
        clusterNo = i;
        density = 0;
        numberCells = 0;
    }

    //将属于同一类别的单元格并入一个聚类中
    public void addCell(int numberPoints){
        density = (numberCells*density + numberPoints)/(numberCells + 1);
        numberCells += 1;
    }

    public void setClusterNo(int i){
        clusterNo = i;
    }

    public void setNumberCells(int i){
        numberCells = i;
    }

    public void setDensity(int i){
        density = i;
    }

    public int getClusterNo(){
        return clusterNo;
    }

    public int getNumberCells(){
        return numberCells;
    }

    public int getDensity(){
        return density;
    }

}
