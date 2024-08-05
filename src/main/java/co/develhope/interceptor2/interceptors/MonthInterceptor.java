package co.develhope.interceptor2.interceptors;

import co.develhope.interceptor2.entities.Month;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.ArrayList;
import java.util.List;

@Component
public class MonthInterceptor implements HandlerInterceptor {

    private List<Month> months = new ArrayList<>();

    public MonthInterceptor() {
        months.add(new Month() {{ setMonthNumber(1); setEnglishName("January"); setItalianName("Gennaio"); setGermanName("Januar"); }});
        months.add(new Month() {{ setMonthNumber(2); setEnglishName("February"); setItalianName("Febbraio"); setGermanName("Februar"); }});
        months.add(new Month() {{ setMonthNumber(3); setEnglishName("March"); setItalianName("Marzo"); setGermanName("März"); }});
        months.add(new Month() {{ setMonthNumber(4); setEnglishName("April"); setItalianName("Aprile"); setGermanName("April"); }});
        months.add(new Month() {{ setMonthNumber(5); setEnglishName("May"); setItalianName("Maggio"); setGermanName("Mai"); }});
        months.add(new Month() {{ setMonthNumber(6); setEnglishName("June"); setItalianName("Giugno"); setGermanName("Juni"); }});
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String monthNumberStr = request.getHeader("monthNumber");
        if (monthNumberStr == null || monthNumberStr.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "monthNumber header is missing");
            return false;
        }

        int monthNumber;
        try {
            monthNumber = Integer.parseInt(monthNumberStr);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "monthNumber must be an integer");
            return false;
        }

        Month month = months.stream()
                .filter(m -> m.getMonthNumber() == monthNumber)
                .findFirst()
                .orElse(new Month() {{ setMonthNumber(monthNumber); setEnglishName("nope"); setItalianName("nope"); setGermanName("nope"); }});

        request.setAttribute("month", month);
        return true;
    }
}