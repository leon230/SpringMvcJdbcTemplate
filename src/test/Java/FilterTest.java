import com.tickets.model.Filter;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import junit.framework.Assert;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Leon on 2016-10-31.
 */
public class FilterTest {
    @Autowired
    Filter filter;

    @Test
    public void CheckClusterStringList(){
/**
 * Check if condition for SQL query is generated properly
  */
        filter = new Filter();
        String conditionOutput = "CLUSTER IN ('Reporting') AND STATUS IN ('Open') AND PRIORITY IN ('High')";
        List<String> clustersList = new ArrayList<String>();
        List<String> statusesList = new ArrayList<String>();
        List<String> prioritiesList = new ArrayList<String>();

        clustersList.add("Reporting");
        statusesList.add("Open");
        prioritiesList.add("High");

        filter.setClusters(clustersList);
        filter.setPriorities(prioritiesList);
        filter.setStatuses(statusesList);

        filter.setCondition();

        Assert.assertEquals(conditionOutput,Filter.getCondition());


    }
}
