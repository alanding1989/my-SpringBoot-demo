package com.alanding.mp.util;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.additional.ChainWrapper;
import com.baomidou.mybatisplus.extension.service.additional.query.impl.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.additional.update.impl.LambdaUpdateChainWrapper;

/**
 *  @Author      :   AlanDing
 *  @Time        :   2019/11/24 下午4:26
 *  @File        :   GetQueryWrapper.java
 *  @Description :
 */

public interface GetWrapper<T> extends ChainWrapper<T> {

    /**
     * 默认返回 LambdaQueryWrapper
     */
    @Override
    default Wrapper<T> getWrapper() {
        return getLambdaQuery();
    }


    default QueryWrapper<T> getQueryWrapper() {
        return new QueryWrapper<>();
    }

    default QueryWrapper<T> getQueryWrapper(T entity) {
        return new QueryWrapper<>(entity);
    }

    default LambdaQueryWrapper<T> getLambdaQuery() {
        return new LambdaQueryWrapper<>();
    }

    default LambdaQueryWrapper<T> getLambdaQuery(T entity) {
        return new LambdaQueryWrapper<>(entity);
    }

    default LambdaQueryChainWrapper<T> getLambdaQueryChain() {
        return new LambdaQueryChainWrapper<>(getBaseMapper());
    }


    default UpdateWrapper<T> getUpdateWrapper() {
        return new UpdateWrapper<>();
    }

    default UpdateWrapper<T> getUpdateWrapper(T entity) {
        return new UpdateWrapper<>(entity);
    }

    default LambdaUpdateWrapper<T> getLambdaUpdate() {
        return new LambdaUpdateWrapper<>();
    }

    default LambdaUpdateWrapper<T> getLambdaUpdate(T entity) {
        return new LambdaUpdateWrapper<>(entity);
    }

    default LambdaUpdateChainWrapper<T> getLambdaUpdateChain() {
        return new LambdaUpdateChainWrapper<>(getBaseMapper());
    }
}
