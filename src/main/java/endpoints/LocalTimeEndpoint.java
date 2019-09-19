package endpoints;

import bebenista.jakub.szkolenie.soap.GetLocalTimeRequest;
import bebenista.jakub.szkolenie.soap.GetLocalTimeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import service.LocalTimeService;

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

    @Autowired
    LocalTimeService localTimeService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getLocalTimeRequest")
    @ResponsePayload
    public GetLocalTimeResponse getLocalTime(@RequestPayload GetLocalTimeRequest aRequest) {
        GetLocalTimeResponse response = new GetLocalTimeResponse();
        response.setLocaltime(localTimeService.getLocalTime(aRequest.getTimezone()));

        return response;
    }
}
