package com.commons.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.commons.beans.ResultBean;
import com.commons.exception.CheckException;
import com.commons.exception.UnloginException;
import com.commons.mail.MailSendUtil;
import com.commons.utils.Constants;

public class ControllerAOP {

	private static final Logger logger = LoggerFactory.getLogger(ControllerAOP.class);

	private String mailSubject = "请求错误";
	
	public Object handlerControllerMethod(ProceedingJoinPoint pjp) {
		long startTime = System.currentTimeMillis();

		ResultBean<?> result;

		try {
			result = (ResultBean<?>) pjp.proceed();
			logger.info(pjp.getSignature() + "use time:" + (System.currentTimeMillis() - startTime));
		} catch (Throwable e) {
			result = handlerException(pjp, e);
		}

		return result;
	}

	private ResultBean<?> handlerException(ProceedingJoinPoint pjp, Throwable e) {
		ResultBean<?> result = new ResultBean();
		// 已知异常
		if (e instanceof CheckException) {
			result.setMsg(e.getLocalizedMessage());
			result.setCode(ResultBean.FAIL);
		} else if (e instanceof UnloginException) {
			result.setMsg("Unlogin");
			result.setCode(ResultBean.NO_LOGIN);
		}  else {
			logger.error(pjp.getSignature() + " error ", e);
			result.setMsg(e.getMessage());
			result.setCode(ResultBean.FAIL);
			/*MailSendUtil.sendMail(Constants.ERROR_MAIL_SEND_USER,
					mailSubject, e.toString());*/
		}

		return result;
	}
	
	
}
