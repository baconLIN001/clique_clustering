/**
 * Created by bacon on 2017/3/12.
 * 单元格信息
 */
public class Cell {
    private int numberPoints;  // 点的数目
    private int quatified;     // 0/1值，判断密度是否达到阈值
    private int checked;       // 0/1值，是否检查过
    private int clusterNo;     // 所属类别，0表示离散点

    public Cell(){
        numberPoints = 0;
        quatified = 0;
        checked = 0;
        clusterNo = 0;
    }

    //setters and getters
    public void setNumberPoints(int i){
        numberPoints = i;
    }

    public void setQuatified(int i){
        quatified = i;
    }

    public void setChecked(int i){
        checked = i;
    }

    public void setClusterNo(int i){
        clusterNo = i;
    }

    public int getNumberPoints(){
        return numberPoints;
    }

    public int getQuatified(){
        return quatified;
    }

    public int getChecked(){
        return checked;
    }

    public int getClusterNo(){
        return clusterNo;
    }

    public void addnumberPoints(){
        numberPoints += 1;
    }
}
