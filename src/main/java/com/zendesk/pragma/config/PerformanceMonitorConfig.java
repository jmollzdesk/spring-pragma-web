/**
 * 
 */
package com.zendesk.pragma.config;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.interceptor.PerformanceMonitorInterceptor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author jmoll
 *
 */

@Aspect
@Configuration
public class PerformanceMonitorConfig {

	/**
	 * Pointcut to define public methods
	 */
	@Pointcut("execution(public * *(..))")
	public void publicMethods() {
		// no implementation needed for Pointcut methods
	}
	
    /**
     * Pointcut to match all the public methods that are defined under the Class
     * marked with the Repository annotation
     */
    @Pointcut("execution(public * (@org.springframework.stereotype.Repository *..*).*(..))")
    public void repositoryAnnotationMethods() {
        // no implementation needed for Pointcut methods
    }
    
    /**
     * Pointcut to match all public methods that are annotated with RequestMapping
     */
    @Pointcut("publicMethods() && (@annotation(org.springframework.web.bind.annotation.GetMapping) || (@annotation(org.springframework.web.bind.annotation.PostMapping)))")
    public void requestMappingMethods() {
        // no implementation needed for Pointcut methods
    }
    
//    @Pointcut("target(com.zmoll.search.persistence.ZsearchRepository)")
//    public void searchRepository() {
//    	// no implementation needed for Pointcut
//    }
    
    /**
     * This is a catch all pointcut, add additional pointcuts to this one with || syntax.
     */
    @Pointcut("repositoryAnnotationMethods() || requestMappingMethods()")
    public void performanceMonitor() {
        // no implementation needed for Pointcut methods
    }
    
    /**
     * Create the Spring {@link PerformanceMonitorInterceptor}
     * 
     * @return a PerformanceMonitorInterceptor bean
     */
    @Bean
    public PerformanceMonitorInterceptor performanceMonitorInterceptor() {
        final PerformanceMonitorInterceptor interceptor = new PerformanceMonitorInterceptor();
        interceptor.setLogTargetClassInvocation(false);
        return interceptor;
    }
    
    /**
     * Create the AOP Advisor and tie it to the {@link PerformanceMonitorInterceptor} and
     * the performanceMonitor pointcut.
     * 
     * @return a performanceMonitorAdvisor bean
     */
    @Bean
    public Advisor performanceMonitorAdvisor() {
        final AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("com.zendesk.pragma.config.PerformanceMonitorConfig.performanceMonitor()");
        return new DefaultPointcutAdvisor(pointcut, performanceMonitorInterceptor());
    }
}
