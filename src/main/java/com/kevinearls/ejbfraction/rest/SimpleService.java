package com.kevinearls.ejbfraction.rest;

import io.opentracing.Span;
import io.opentracing.util.GlobalTracer;

import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.ejb.Stateless;
import java.util.Date;
import java.util.logging.Logger;

@Stateless
public class SimpleService {
        private static final Logger log = Logger.getLogger(SimpleService.class.getName());

        @Resource
        EJBContext context;

        public String doSomething() {
            log.info("In SIMPLESERVICE.doSomething");
            Span span = GlobalTracer.get()
                    .buildSpan("doSomething")
                    //.asChildOf(context)
                    .startManual();

            String caller = context.getCallerPrincipal().getName();
            String response = "SIMPLESERVICE called by ["
                    + caller
                    + "] at "
                    + new Date().toString();
            span.finish();
            return response;
        }

}
