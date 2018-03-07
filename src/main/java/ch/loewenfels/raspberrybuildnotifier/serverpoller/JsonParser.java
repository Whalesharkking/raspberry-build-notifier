package ch.loewenfels.raspberrybuildnotifier.serverpoller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.loewenfels.raspberrybuildnotifier.BuildInformationDto;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonParser {
    private static final String USER_AGENT = "Mozilla/5.0";
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonParser.class);

    public Optional<BuildInformationDto> get() {
        final String jobName = "SampleJobName";
        final String serverUrl = "unified-skein-195809.appspot.com";
        try {
            final String url = String.format("https://%s/rest/build/get/%s", serverUrl, jobName);
            final HttpClient client = HttpClientBuilder.create().build();
            final HttpGet request = new HttpGet(url);
            request.addHeader("User-Agent", USER_AGENT);
            final HttpResponse response = client.execute(request);
            LOGGER.debug("Response Code: {}", response.getStatusLine().getStatusCode());
            final String string = EntityUtils.toString(response.getEntity());
            final BuildInformationDto buildInformationDto = parse(string);
            LOGGER.debug("JobInfo: {}", buildInformationDto);
            return Optional.of(buildInformationDto);
        } catch (final ParseException | IOException | NullPointerException e) {
            LOGGER.error("Can not parse BuildInformation: {}", e);
            return Optional.empty();
        }
    }

    BuildInformationDto parse(final String string) {
        final Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeserializer()).create();
        return gson.fromJson(string, BuildInformationDto.class);
    }
}
