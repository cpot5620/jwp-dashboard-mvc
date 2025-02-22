package webmvc.org.springframework.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import webmvc.org.springframework.web.servlet.mvc.adaptor.HandlerAdaptors;
import webmvc.org.springframework.web.servlet.mvc.mapping.HandlerMappings;
import webmvc.org.springframework.web.servlet.mvc.adaptor.HandlerAdaptor;
import webmvc.org.springframework.web.servlet.view.ModelAndView;

public class DispatcherServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(DispatcherServlet.class);

    private final HandlerMappings handlerMappings;
    private final HandlerAdaptors handlerAdaptors;

    public DispatcherServlet() {
        this.handlerMappings = new HandlerMappings();
        this.handlerAdaptors = new HandlerAdaptors();
    }

    @Override
    public void init() {
        handlerMappings.initialize();
    }

    @Override
    protected void service(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException {
        log.debug("Method : {}, Request URI : {}", request.getMethod(), request.getRequestURI());

        try {
            Object handler = handlerMappings.getHandler(request);
            HandlerAdaptor handlerAdaptor = handlerAdaptors.getAdaptor(handler);
            ModelAndView modelAndView = handlerAdaptor.handle(handler, request, response);

            modelAndView.getView().render(modelAndView.getModel(), request, response);
        } catch (Throwable e) {
            log.error("Exception : {}", e.getMessage(), e);
            throw new ServletException(e.getMessage());
        }
    }

}
