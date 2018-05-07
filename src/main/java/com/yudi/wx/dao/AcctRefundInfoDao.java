package com.yudi.wx.dao;


import com.yudi.wx.entity.AcctRefundInfo;

import java.util.List;
import java.util.Map;

public interface AcctRefundInfoDao {

    public void saveAcctRefundInfo(AcctRefundInfo acctRefundInfo);

    /**
     * 获取客户提现后 提现订单状态为
     * 1 -发起提现待审核
     * 或
     * 2- 审核通过待付款
     * 的客户订单状态
     * @param acctRefundInfo
     * @return
     */
    public String getOrderStatByCustIdWithRefunding(AcctRefundInfo acctRefundInfo);

    /**
     * 提现撤销
     * @param acctRefundInfo
     */
    public void updateOrderStatBackOut(AcctRefundInfo acctRefundInfo);

    /**
     * 查询微信提现中 已通过审核
     * 并且 核心支付宝都未扣钱 的所有记录
     * @param acctRefundInfo
     * @return
     */
    public List<AcctRefundInfo> getOderdetailBypayChannel(String minutesAgo);

    public void updateOrderStatFail(Map map);

}
