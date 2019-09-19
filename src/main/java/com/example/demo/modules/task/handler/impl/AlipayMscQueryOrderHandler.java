/**
 * 
 */
package com.example.demo.modules.task.handler.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.example.demo.modules.task.consts.TaskMessageType;
import com.example.demo.modules.task.consts.TaskQueueConsts;
import com.example.demo.modules.task.context.PayProcessContext;
import com.example.demo.modules.task.domin.TaskQueue;
import com.example.demo.modules.task.entity.PayTransaction;
import com.example.demo.modules.task.entity.Payment;
import com.example.demo.modules.task.handler.HandleResult;
import com.example.demo.modules.task.handler.TaskBaseHandler;


/**
 * 支付宝商户扫码支付主动查询订单
 * 
 * @author li.t
 * @date 2018年5月5日 下午1:24:36
 */
@Component
public class AlipayMscQueryOrderHandler extends TaskBaseHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(AlipayMscQueryOrderHandler.class);
 //   @Resource
 //   private PaymentDao paymentDao;
  //  @Resource
  //  private PayTransactionDao payTransactionDao;
  //  @Resource
  //  private CibAlipayQueryAdapter cibAlipayQueryAdapter;
  //  @Resource
  //  private TradeServiceAssistLogic tradeServiceAssistLogic;

    @Override
    public Integer getMessageType() {
        return TaskMessageType.ALIPAY_MSC_QUERY_ORDER;
    }

    @Override
    public HandleResult handle(TaskQueue task) {
        HandleResult rst = new HandleResult(TaskQueueConsts.HANDLE_RESULT_FAIL_TRY);

        try {
            String transId = task.getTransId();
       //     PayTransaction trans = payTransactionDao.selectByTransId(transId);
       //     Payment payment = paymentDao.selectByPayId(trans.getPayId());

         /*   PayProcessContext.setBrandId(trans.getBrandId());

            AlipayQueryReq qreq = new AlipayQueryReq();
            qreq.setOutTradeNo(transId);

            AlipayQueryRes qres = cibAlipayQueryAdapter.request(qreq);
            
            // 检查交易流水状态
            if(PaymentConsts.TRANS_STATUS_FINISH.equals(trans.getStatus()))   {
                rst.setResult(TaskQueueConsts.HANDLE_RESULT_SUCC);
                return rst;
            }
            
            String tradeState = qres.getTradeState();
            if (CibAlipayConsts.TRADE_STATE_USERPAYING.equals(tradeState)) {
                rst.setResult(TaskQueueConsts.HANDLE_RESULT_FAIL_TRY);

            } else if (CibAlipayConsts.TRADE_STATE_SUCCESS.equals(tradeState)) {
                rst.setResult(TaskQueueConsts.HANDLE_RESULT_SUCC);
                // 更新支付流水状态
                tradeServiceAssistLogic.updatePayTransWhenPayFinish(trans);
                // 更新支付单状态
                tradeServiceAssistLogic.updatePaymentWhenPayFinish(payment, trans);
                doSubmitNotifyOrderTask(payment, trans, qres);
            } else if(CibAlipayConsts.TRADE_STATE_CLOSED.equals(tradeState)){
                rst.setResult(TaskQueueConsts.HANDLE_RESULT_FAIL_NO_TRY);
                // 更新支付流水状态
                tradeServiceAssistLogic.updatePayTransWhenPayFail(trans);
                doSubmitNotifyOrderTask(payment, trans, qres);
            } else {
                rst.setResult(TaskQueueConsts.HANDLE_RESULT_FAIL_DELAY_TRY);
                rst.setDelayTime(30);
                // 更新支付流水状态
                tradeServiceAssistLogic.updatePayTransWhenPayFail(trans);
                doSubmitNotifyOrderTask(payment, trans, qres);
            }

            rst.setResultInfo(JsonUtil.toJson(qres));*/
            rst.setResult(TaskQueueConsts.HANDLE_RESULT_SUCC);
            System.out.println("任务已到达"+task.getMessage());
        } catch (Exception e) {
            rst.setResult(TaskQueueConsts.HANDLE_RESULT_FAIL_TRY);
            LOGGER.error("AlipayMscQueryOrderHandler handle error!", e);
        }

        return rst;
    }

    /**
     * 提交通知订单系统的任务
     * 
     * @param payment
     * @param trans
     * @param qres
     */
  /*  private void doSubmitNotifyOrderTask(Payment payment, PayTransaction trans, AlipayQueryRes qres) {
        TradePayAlipayExtData extdata = new TradePayAlipayExtData();
        extdata.setAliOrderId(qres.getTransactionId());
        tradeServiceAssistLogic.notifyOrderWhenPayFinish(payment, trans, extdata);
    }*/
}
