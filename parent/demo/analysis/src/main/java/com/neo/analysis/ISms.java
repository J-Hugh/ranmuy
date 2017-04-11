package com.neo.analysis;

import java.math.BigDecimal;

/**
 * Created by neoThe on 2017/4/11.
 */
public interface ISms {


    /**
     * 判断是否为有效的内容
     * @return
     */
    public Boolean isValid();

    /**
     * 解析出金额
     * @return
     */
    public BigDecimal anaAmount();

    /**
     * 解析
     * @return
     */
    public void anaBase();

    /**
     * 解析出余额
     * @return
     */
    public BigDecimal anaBalance();
}
