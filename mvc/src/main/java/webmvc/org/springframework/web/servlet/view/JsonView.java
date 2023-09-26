package webmvc.org.springframework.web.servlet.view;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import web.org.springframework.http.MediaType;
import webmvc.org.springframework.web.servlet.View;

import java.util.Map;

public class JsonView implements View {

    @Override
    public void render(final Map<String, ?> model, final HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        ObjectMapper objectMapper = new ObjectMapper();
        if (model.size() == 1) {
            objectMapper.writeValue(response.getOutputStream(), model.values().iterator().next());
            return;
        }
        objectMapper.writeValue(response.getOutputStream(), model);
    }

    @Override
    public String getViewName() {
        return "jsonView";
    }
}
