package org.battles.battles.response;

import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ResponseService {

    public <T> SingleResult<T> getSingleResult(T data) {
        SingleResult<T> result = new SingleResult<>();
        result.setData(data);
        setSuccessResult(result);
        return result;
    }

    public <T> ListResult<T> getListResult(List<T> list) {
        ListResult<T> result = new ListResult<>();
        result.setDataList(list);
        setSuccessResult(result);
        return result;
    }

    public CommonResult getSuccessResult() {
        CommonResult result = new CommonResult();
        setSuccessResult(result);
        return result;
    }

    public CommonResult getFailResult() {
        CommonResult result = new CommonResult();
        result.setSuccess(false);
        result.setCode(CommonResultType.FAIL.getCode());
        result.setMsg(CommonResultType.FAIL.getMsg());
        return result;
    }

    public CommonResult getFailResultWithMsg(String msg) {
        CommonResult result = new CommonResult();
        result.setSuccess(false);
        result.setCode(CommonResultType.FAIL.getCode());
        result.setMsg(msg);
        return result;
    }

    private void setSuccessResult(CommonResult result) {
        result.setSuccess(true);
        result.setCode(CommonResultType.SUCCESS.getCode());
        result.setMsg(CommonResultType.SUCCESS.getMsg());
    }
}
