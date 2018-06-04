package ch.loewenfels.raspberrybuildnotifier.serverpoller;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.time.LocalDateTime;

import org.junit.Test;

import ch.loewenfels.raspberrybuildnotifier.BuildInformationDto;

import com.google.gson.JsonSyntaxException;

public class JsonParserTest {
    @Test
    public void jsonParse_whenIsCorrectJson_thenResultMuesBeCorrect() {
        //arrange
        final String testinput =
            "{\"jobName\":\"SampleJobName\",\"jobStatus\":\"SUCCESS\",\"jobTimeLocal\":{\"nano\":0,\"monthValue\":3,\"year\":2018,\"month\":\"MARCH\",\"dayOfMonth\":7,\"dayOfWeek\":\"WEDNESDAY\",\"dayOfYear\":66,\"second\":20,\"minute\":30,\"hour\":8,\"chronology\":{\"calendarType\":\"iso8601\",\"id\":\"ISO\"}}}";
        //act
        BuildInformationDto result = null;
        try {
            result = new JsonParser().parseBuildInformation(testinput);
        } catch (final Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //assert
        assertThat(result.getJobTime(), equalTo(LocalDateTime.of(2018, 3, 7, 8, 30, 20)));
    }

    //    @Test
    //    public void jsonParseGet_whenCanNotFindJob_thenResultMuesBeError() {
    //        //arrange
    //        Optional<BuildInformationDto> optional = null;
    //        try {
    //            optional = new JsonParser().get();
    //        } catch (final Exception e) {
    //            // TODO Auto-generated catch block
    //            e.printStackTrace();
    //        }
    //        //assert
    //        assertTrue(optional.isPresent());
    //    }
    @Test
    public void jsonParse_whenIsInvalidJobTimeLocal_thenResultMuesBeNull() throws Exception {
        //arrange
        final String testinput =
            "{\"jobName\":\"SampleJobName\",\"jobStatus\":\"SUCCESS\",\"falseJobTime\":{\"nano\":0,\"monthValue\":3,\"year\":2018,\"month\":\"MARCH\",\"dayOfMonth\":7,\"dayOfWeek\":\"WEDNESDAY\",\"dayOfYear\":66,\"second\":20,\"minute\":30,\"hour\":8,\"chronology\":{\"calendarType\":\"iso8601\",\"id\":\"ISO\"}}}";
        //act
        final BuildInformationDto result = new JsonParser().parseBuildInformation(testinput);
        //assert
        assertNull(result.getJobTime());
    }

    @Test
    public void jsonParse_whenIsInvalidJobName_thenResultMuesBeNull() throws Exception {
        //arrange
        final String testinput =
            "{\"falseJobName\":\"SampleJobName\",\"jobStatus\":\"SUCCESS\",\"jobTimeLocal\":{\"nano\":0,\"monthValue\":3,\"year\":2018,\"month\":\"MARCH\",\"dayOfMonth\":7,\"dayOfWeek\":\"WEDNESDAY\",\"dayOfYear\":66,\"second\":20,\"minute\":30,\"hour\":8,\"chronology\":{\"calendarType\":\"iso8601\",\"id\":\"ISO\"}}}";
        //act
        final BuildInformationDto result = new JsonParser().parseBuildInformation(testinput);
        //assert
        assertNull(result.getJobName());
    }

    @Test
    public void jsonParse_whenIsInvalidJobStatus_thenResultMuesBeNull() throws Exception {
        //arrange
        final String testinput =
            "{\"jobName\":\"SampleJobName\",\"jobStatus\":\"Gegi\",\"jobTimeLocal\":{\"nano\":0,\"monthValue\":3,\"year\":2018,\"month\":\"MARCH\",\"dayOfMonth\":7,\"dayOfWeek\":\"WEDNESDAY\",\"dayOfYear\":66,\"second\":20,\"minute\":30,\"hour\":8,\"chronology\":{\"calendarType\":\"iso8601\",\"id\":\"ISO\"}}}";
        //act
        final BuildInformationDto result = new JsonParser().parseBuildInformation(testinput);
        //assert
        assertNull(result.getJobStatus());
    }

    @Test(expected = JsonSyntaxException.class)
    public void jsonParse_whenIsInvalidJobTimeLocal_thenResultMuesBeError() throws Exception {
        //arrange
        final String testinput =
            "{\"jobName\":\"SampleJobName\",\"jobStatus\":\"SUCCESS\",\"year\":2018,\"month\":\"MARCH\",\"dayOfMonth\":7,\"dayOfWeek\":\"WEDNESDAY\",\"dayOfYear\":66,\"second\":20,\"minute\":30,\"hour\":8,\"chronology\":{\"calendarType\":\"iso8601\",\"id\":\"ISO\"}}}";
        //act
        new JsonParser().parseResponsilble(testinput);
    }
}
