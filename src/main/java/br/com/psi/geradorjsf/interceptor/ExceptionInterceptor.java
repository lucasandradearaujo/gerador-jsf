package br.com.psi.geradorjsf.interceptor;

import br.com.psi.geradorjsf.annotation.ExceptionHandler;
import br.com.psi.geradorjsf.custom.CustomObjectMapper;
import br.com.psi.geradorjsf.persistence.model.support.ErrorDetail;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.HttpStatusCodeException;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.io.IOException;
import java.io.Serializable;

/**
 * @author Hiago.
 */
@Interceptor
@ExceptionHandler
public class ExceptionInterceptor implements Serializable {
    private final ExternalContext externalContext;

    @Inject
    public ExceptionInterceptor(ExternalContext externalContext) {
        this.externalContext = externalContext;
    }

    @AroundInvoke
    public Object invoke(InvocationContext context) throws IOException {
        Object result = null;
        try {
            result = context.proceed();
        } catch (Exception e) {
            if (e instanceof HttpClientErrorException  || e instanceof HttpServerErrorException) {
                HttpStatusCodeException httpException = (HttpStatusCodeException) e;
                String responseBodyAsString = httpException.getResponseBodyAsString();
                ErrorDetail errorDetail = new CustomObjectMapper().readValue(httpException.getResponseBodyAsString(), ErrorDetail.class);
                addMessage(FacesMessage.SEVERITY_ERROR, errorDetail.getMessage(), true);
            } else {
                e.printStackTrace();
            }
        }
        return result;
    }

    private void addMessage(FacesMessage.Severity severity, String msg, boolean keepMessages) {
        final FacesMessage facesMessage = new FacesMessage(severity, msg, "");
        externalContext.getFlash().setKeepMessages(keepMessages);
        externalContext.getFlash().setRedirect(true);
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
    }
}