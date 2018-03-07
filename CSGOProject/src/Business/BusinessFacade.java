
package Business;
import Interfaces.IBusinessFacade;
import Interfaces.IData;
import data.Data;
/**
 *
 * @author niemeijeren
 */
public class BusinessFacade implements IBusinessFacade {

    private IData datalayer;
    
    
    
    
    
    
    
    
    
    
    @Override
    public void InjectDataLayer(IData data) 
    {
        this.datalayer = data;   
    }
    
}
