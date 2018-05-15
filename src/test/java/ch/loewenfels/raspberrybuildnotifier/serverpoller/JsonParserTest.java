package ch.loewenfels.raspberrybuildnotifier.serverpoller;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.time.LocalDateTime;

import org.junit.Test;

import ch.loewenfels.raspberrybuildnotifier.BuildInformationDto;

public class JsonParserTest {
    @Test
    public void feature_when_then() {
        //arrange
        final String testinput =
            "{\"jobName\":\"SampleJobName\",\"jobStatus\":\"SUCCESS\",\"jobTimeLocal\":{\"nano\":0,\"monthValue\":3,\"year\":2018,\"month\":\"MARCH\",\"dayOfMonth\":7,\"dayOfWeek\":\"WEDNESDAY\",\"dayOfYear\":66,\"second\":20,\"minute\":30,\"hour\":8,\"chronology\":{\"calendarType\":\"iso8601\",\"id\":\"ISO\"}}}";
        //act
        BuildInformationDto result = null;
        try {
            result = new JsonParser().parse(testinput);
        } catch (final Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //assert
        assertThat(result.getJobTime(), equalTo(LocalDateTime.of(2018, 3, 7, 8, 30, 20)));
    }
}
