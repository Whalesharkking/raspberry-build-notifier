package ch.loewenfels.raspberrybuildnotifier.serverpoller;

import java.io.IOException;
import java.util.Date;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import ch.loewenfels.raspberrybuildnotifier.BuildInformationDto;
import ch.loewenfels.raspberrybuildnotifier.BuildInformationDto.JobStatus;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

public class JsonParser {
    private static final String USER_AGENT = "Mozilla/5.0";
    private static final Logger LOGGER = Logger.getLogger(JsonParser.class);

    public BuildInformationDto get() {
        try {
            final String url = "https://unified-skein-195809.appspot.com/rest/build/get/SampleJobName";
            // final String url = "http://localhost:8080/rest/build/get/a";
            final HttpClient client = HttpClientBuilder.create().build();
            final HttpGet request = new HttpGet(url);
            request.addHeader("User-Agent", USER_AGENT);
            final HttpResponse response = client.execute(request);
            LOGGER.info("Response Code : " + response.getStatusLine().getStatusCode());
            final String string = EntityUtils.toString(response.getEntity());
            LOGGER.info(string);
            final Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, UnixEpochDateTypeAdaptersd.getUnixEpochDateTypeAdapter()).create();
            final BuildInformationDto buildInformationDto = gson.fromJson(string, BuildInformationDto.class);
            return buildInformationDto;
        } catch (final ParseException | IOException e) {
            LOGGER.error(e);
        }
        return new BuildInformationDto("ERROR", JobStatus.ERROR, new Date());
    }
}

final class UnixEpochDateTypeAdapter extends TypeAdapter<Date> {
    private static final TypeAdapter<Date> unixEpochDateTypeAdapter = new UnixEpochDateTypeAdapter();

    private UnixEpochDateTypeAdapter() {
    }

    static TypeAdapter<Date> getUnixEpochDateTypeAdapter() {
        return unixEpochDateTypeAdapter;
    }

    @Override
    public Date read(final JsonReader in) throws IOException {
        // this is where the conversion is performed
        return new Date(in.nextLong());
    }

    @Override
    @SuppressWarnings("resource")
    public void write(final JsonWriter out, final Date value) throws IOException {
        // write back if necessary or throw UnsupportedOperationException
        out.value(value.getTime());
    }
}