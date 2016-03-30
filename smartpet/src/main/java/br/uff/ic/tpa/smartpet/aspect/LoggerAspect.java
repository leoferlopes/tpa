/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uff.ic.tpa.smartpet.aspect;

/**
 *
 * @author leonardo
 */
import br.uff.ic.tpa.smartpet.excecao.ApplicationException;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class LoggerAspect {

    @Around("execution(* br.uff.ic.tpa.smartpet.service.*.*(..))")
    public Object exceptionAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Logger log = Logger.getLogger(joinPoint.getClass().getName());
        try {
            return joinPoint.proceed();
        } catch (ApplicationException e) {
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }
}
