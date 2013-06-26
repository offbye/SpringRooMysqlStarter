package cn.fnic.dhc.jcaptcha;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.octo.captcha.service.CaptchaService;
import com.octo.captcha.service.CaptchaServiceException;

/**
 * JCaptcha Filter
 * 
 * @author zxt
 * 
 */
public class JCaptchaFilter implements Filter {

	// parameter in web.xml
	public static final String PARAM_CAPTCHA_PARAMTER_NAME = "captchaParamterName";
	public static final String PARAM_CAPTCHA_SERVICE_ID = "captchaServiceId";
	public static final String PARAM_FILTER_PROCESSES_URL = "filterProcessesUrl";
	public static final String PARAM_FAILURE_URL = "failureUrl";
	public static final String PARAM_AUTO_PASS_VALUE = "autoPassValue";

	// default
	public static final String DEFAULT_FILTER_PROCESSES_URL = "/resources/j_spring_security_check";
	public static final String DEFAULT_CAPTCHA_SERVICE_ID = "captchaService";
	public static final String DEFAULT_CAPTCHA_PARAMTER_NAME = "j_captcha";

	private static Logger logger = LoggerFactory
			.getLogger(JCaptchaFilter.class);

	private String failureUrl;
	private String filterProcessesUrl = DEFAULT_FILTER_PROCESSES_URL;
	private String captchaServiceId = DEFAULT_CAPTCHA_SERVICE_ID;
	private String captchaParamterName = DEFAULT_CAPTCHA_PARAMTER_NAME;
	private String autoPassValue;

	private CaptchaService captchaService;

	/**
	 * Filter callback init
	 */
	public void init(FilterConfig filterConfig) throws ServletException {
		initParameters(filterConfig);
		initCaptchaService(filterConfig);

	}

	public void doFilter(ServletRequest theRequest,
			ServletResponse theResponse, FilterChain chain) throws IOException,
			ServletException {
		HttpServletRequest request = (HttpServletRequest) theRequest;
		HttpServletResponse response = (HttpServletResponse) theResponse;
		String servletPath = request.getServletPath();
		logger.info("servletPath:" + servletPath + "  ==" + filterProcessesUrl);
		
		// check captcha first
		if (StringUtils.startsWith(servletPath, filterProcessesUrl)) {
			logger.info("enter filter jcaptcha check");
			boolean validated = validateCaptchaChallenge(request);
			if (validated) {
				chain.doFilter(request, response);
			} else {
				logger.info("jcaptcha validate fail");
				redirectFailureUrl(request, response);
			}
		} else {
			genernateCaptchaImage(request, response);
		}
	}

	public void destroy() {
		// TODO Auto-generated method stub

	}

	/**
	 * filter init-param.
	 */
	protected void initParameters(final FilterConfig fConfig) {
		if (StringUtils.isBlank(fConfig.getInitParameter(PARAM_FAILURE_URL))) {
			throw new IllegalArgumentException(
					"CaptchaFilter have no failureUrl");
		}

		failureUrl = fConfig.getInitParameter(PARAM_FAILURE_URL);
		logger.info("failureUrl:" + failureUrl);

		if (StringUtils.isNotBlank(fConfig
				.getInitParameter(PARAM_FILTER_PROCESSES_URL))) {
			filterProcessesUrl = fConfig
					.getInitParameter(PARAM_FILTER_PROCESSES_URL);
		}

		if (StringUtils.isNotBlank(fConfig
				.getInitParameter(PARAM_CAPTCHA_SERVICE_ID))) {
			captchaServiceId = fConfig
					.getInitParameter(PARAM_CAPTCHA_SERVICE_ID);
		}

		if (StringUtils.isNotBlank(fConfig
				.getInitParameter(PARAM_CAPTCHA_PARAMTER_NAME))) {
			captchaParamterName = fConfig
					.getInitParameter(PARAM_CAPTCHA_PARAMTER_NAME);
		}

		if (StringUtils.isNotBlank(fConfig
				.getInitParameter(PARAM_AUTO_PASS_VALUE))) {
			autoPassValue = fConfig.getInitParameter(PARAM_AUTO_PASS_VALUE);
		}
	}

	/**
	 * get CaptchaService instance from ApplicatonContext
	 */
	protected void initCaptchaService(final FilterConfig fConfig) {
		ApplicationContext context = WebApplicationContextUtils
				.getWebApplicationContext(fConfig.getServletContext());
		captchaService = (CaptchaService) context.getBean(captchaServiceId);
	}

	/**
	 * generate captcha image
	 */
	protected void genernateCaptchaImage(final HttpServletRequest request,
			final HttpServletResponse response) throws IOException {

		setDisableCacheHeader(response);
		response.setContentType("image/jpeg");

		ServletOutputStream out = response.getOutputStream();
		try {
			String captchaId = request.getSession(true).getId();
			BufferedImage challenge = (BufferedImage) captchaService
					.getChallengeForID(captchaId, request.getLocale());
			ImageIO.write(challenge, "jpg", out);
			out.flush();
		} catch (CaptchaServiceException e) {
			logger.error(e.getMessage(), e);
		} finally {
			out.close();
		}
	}

	/**
	 * validate CaptchaChallenge
	 */
	protected boolean validateCaptchaChallenge(final HttpServletRequest request) {
		try {
			String captchaID = request.getSession().getId();
			logger.info("captchaID:" + captchaID);
			String challengeResponse = request
					.getParameter(captchaParamterName);
			logger.info("challengeResponse:" + challengeResponse);

			// autoPassValue
			if (StringUtils.isNotBlank(autoPassValue)
					&& autoPassValue.equals(challengeResponse)) {
				return true;
			}
			return captchaService.validateResponseForID(captchaID,
					challengeResponse);
		} catch (CaptchaServiceException e) {
			logger.error(e.getMessage(), e);
			return false;
		}
	}

	/**
	 * got fail page
	 * 
	 * TODO add SpringSecurity的Exception.
	 */
	protected void redirectFailureUrl(final HttpServletRequest request,
			final HttpServletResponse response) throws IOException {
		logger.info("go fail page:" + request.getContextPath() + failureUrl);
		response.sendRedirect(request.getContextPath() + failureUrl);
	}

	/**
	 * set Header.
	 */
	public static void setDisableCacheHeader(HttpServletResponse response) {
		// Http 1.0 header
		response.setDateHeader("Expires", 1L);
		response.addHeader("Pragma", "no-cache");
		// Http 1.1 header
		response.setHeader("Cache-Control", "no-cache, no-store, max-age=0");
	}

}
