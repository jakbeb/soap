import mockit.MockUp;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import service.LocalTimeService;

import java.time.*;
import java.util.TimeZone;

@RunWith(MockitoJUnitRunner.class)
public class LocalTimeServiceTest {
    String systemTimeZone;
    Clock clock;
    LocalDateTime customTime;

    @Mock
    LocalTimeService localTimeService;

    @Before
    public void init() {
        systemTimeZone = TimeZone.getDefault().toString();
        customTime = LocalDateTime.of(2019, 01, 01, 21, 39, 44);
        clock = Clock.fixed(customTime.atZone(TimeZone.getDefault().toZoneId()).toInstant(), TimeZone.getDefault().toZoneId());
        Mockito.when(localTimeService.getLocalTime(Mockito.anyString())).thenCallRealMethod();
    }

    @Test
    public void shouldReturnSystemDefaultTimeZoneLocalTime() {
      
        System.out.println(localTimeService.getLocalTime("s"));
        System.out.println(localTimeService.getLocalTime("UTC"));
    }
}
