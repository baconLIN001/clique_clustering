import java.util.ArrayList;
import java.util.List;

/**
 * Created by bacon on 2017/3/14.
 */
public class Facet {

    private List<String>masterIndex = new ArrayList<String>();
    private List<String>slaveIndex = new ArrayList<String>();
    private List<Cell> Cells = new ArrayList<Cell>();

    public void AddCell(String mIndex, String sIndex, Cell cell){
        masterIndex.add(mIndex);
        slaveIndex.add(sIndex);
        Cells.add(cell);
    }

    public List<String>getMasterIndexs(){
        return masterIndex;
    }

    public List<String> getSlaveIndexs(){
        return slaveIndex;
    }

    public String getSlaveIndex(int i){
        return slaveIndex.get(i);
    }

    public String getMasterIndex(int i){
        return masterIndex.get(i);
    }

    public List<Cell>getCells(){
        return Cells;
    }

    public Cell getCell(int i){
        return Cells.get(i);
    }

    public String BuildNewMasterIndex(int i){
        System.out.println(masterIndex.get(i) + "_" + slaveIndex.get(i));
        return masterIndex.get(i) + "_" + slaveIndex.get(i);
    }

    public int Size(){
        return slaveIndex.size();
    }

    public Facet MergeToNewFacet(Facet toFacet){
        Facet rstFacet = new Facet();
        List<Integer>tmpCommonPoint = null;
        int j = 0;

        for (int i = 0; i < masterIndex.size(); i++){
            j = toFacet.getMasterIndexs().indexOf(getMasterIndex(i));
            if (j==-1)continue;

            while (j<toFacet.getMasterIndexs().size()){
                if (masterIndex.get(i)==toFacet.getMasterIndex(j)){
                    tmpCommonPoint = Cells.get(i).getCommonSamples(toFacet.getCell(j).getSamples());

                    if (tmpCommonPoint.size()>=ClusteringConfig.densityThreshold){
                        Cell cell = new Cell();
                        cell.setSamples(tmpCommonPoint);
                        rstFacet.AddCell(BuildNewMasterIndex(i),toFacet.getSlaveIndex(j),cell);
                    }
                }else {
                    break;
                }

                j++;
            }
        }
        return rstFacet;
    }
}
