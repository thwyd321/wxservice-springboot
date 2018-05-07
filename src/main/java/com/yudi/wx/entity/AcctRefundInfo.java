package com.yudi.wx.entity;

import org.apache.ibatis.type.Alias;

import java.util.Date;

@Alias("AcctRefundInfo")
public class AcctRefundInfo {
    //订单流水号
    private String orderNo;
    //商户ID
    private String mrchId;
    //客户号
    private String custId;
    //收款方账户类型
    private String payeeType;
    //收款方账户
    private String payeeAccount;
    //申请金额
    private Integer applyTranAmt;
    //实际到账金额
    private Integer tranAmt;
    //渠道1-支付宝 2-微信
    private String payChannel;
    //订单创建时间
    private Date createTime;
    //外部订单号 由支付宝或微信返回的单据号，成功一定返回，失败可能不返回也可能返回。
    private String outTradeNo;
    //订单状态(1-发起提现等待审核 2-审核通过等待提现 3-提现成功 4-已撤销)
    private String orderStat;
    //提现状态（1-核心支付宝都未扣钱  2-核心扣款成功支付宝失败 3-核心支付宝扣款都成功）
    private String refundStat;
    //支付时间：格式为yyyy-MM-dd HH:mm:ss，仅转账成功返回。
    private Date refundTime;
    //错误码
    private String errorCode;
    //错误信息
    private String errorMsg;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getMrchId() {
        return mrchId;
    }

    public void setMrchId(String mrchId) {
        this.mrchId = mrchId;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getPayeeType() {
        return payeeType;
    }

    public void setPayeeType(String payeeType) {
        this.payeeType = payeeType;
    }

    public String getPayeeAccount() {
        return payeeAccount;
    }

    public void setPayeeAccount(String payeeAccount) {
        this.payeeAccount = payeeAccount;
    }

    public Integer getApplyTranAmt() {
        return applyTranAmt;
    }

    public void setApplyTranAmt(Integer applyTranAmt) {
        this.applyTranAmt = applyTranAmt;
    }

    public Integer getTranAmt() {
        return tranAmt;
    }

    public void setTranAmt(Integer tranAmt) {
        this.tranAmt = tranAmt;
    }

    public String getPayChannel() {
        return payChannel;
    }

    public void setPayChannel(String payChannel) {
        this.payChannel = payChannel;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getOrderStat() {
        return orderStat;
    }

    public void setOrderStat(String orderStat) {
        this.orderStat = orderStat;
    }

    public String getRefundStat() {
        return refundStat;
    }

    public void setRefundStat(String refundStat) {
        this.refundStat = refundStat;
    }

    public Date getRefundTime() {
        return refundTime;
    }

    public void setRefundTime(Date refundTime) {
        this.refundTime = refundTime;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
