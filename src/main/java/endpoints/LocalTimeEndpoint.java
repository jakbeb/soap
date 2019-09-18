package endpoints;

import bebenista.jakub.szkolenie.soap.GetLocalTimeRequest;
import bebenista.jakub.szkolenie.soap.GetLocalTimeResponse;
import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import javax.xml.datatype.XMLGregorianCalendar;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.TimeZone;

@Endpoint
public class LocalTimeEndpoint {
    private static final String NAMESPACE_URI = "http://jakub.bebenista/szkolenie/soap";

    public LocalTimeEndpoint() {
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getLocalTimeRequest")
    @ResponsePayload
    public GetLocalTimeResponse getLocalTime(@RequestPayload GetLocalTimeRequest aRequest) {
        GetLocalTimeResponse response = new GetLocalTimeResponse();

        boolean isValid = Arrays.asList(TimeZone.getAvailableIDs()).contains(aRequest.getTimezone());
        LocalDateTime now = LocalDateTime.now(ZoneId.systemDefault());

        if (isValid) {
            now = LocalDateTime.now(ZoneId.of(aRequest.getTimezone()));
        }

        response.setLocaltime(setXMLGregorianCalendar(now));
        return response;
    }

    //TODO: jaxb conversion, etc.
    private XMLGregorianCalendar setXMLGregorianCalendar(LocalDateTime now) {
        XMLGregorianCalendar xmlGregorianCalendar = new XMLGregorianCalendarImpl();
        xmlGregorianCalendar.setDay(now.getDayOfMonth());
        xmlGregorianCalendar.setMonth(now.getMonthValue());
        xmlGregorianCalendar.setYear(now.getYear());
        xmlGregorianCalendar.setTime(now.getHour(), now.getMinute(), now.getSecond());
        return xmlGregorianCalendar;
    }
}
