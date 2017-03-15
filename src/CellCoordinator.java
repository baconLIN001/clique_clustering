import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by bacon on 2017/3/14.
 */
public class CellCoordinator {

    private static CellCoordinator instance = new CellCoordinator();

    public LinkedBlockingQueue<FieldSet>fieldSetQueue = new LinkedBlockingQueue<FieldSet>();

    public static CellCoordinator getInstance(){
        return instance;
    }
}
