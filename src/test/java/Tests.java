import com.yudi.wx.Application;
import com.yudi.wx.dao.AcctRefundInfoDao;
import com.yudi.wx.entity.AcctRefundInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableAutoConfiguration
public class Tests {
    @Autowired
    AcctRefundInfoDao acctRefundInfoDao;
    @Test
    public void test1() {
        Map map = new HashMap();

        List<AcctRefundInfo> list = acctRefundInfoDao.getOderdetailBypayChannel("5");
        for (AcctRefundInfo acctRefundInfo : list){
            System.out.println(acctRefundInfo.getCustId());
        }
    }
}
