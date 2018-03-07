package ch.loewenfels.raspberrybuildnotifier.serverpoller;

import java.lang.reflect.Type;
import java.time.LocalDateTime;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class LocalDateTimeDeserializer implements JsonDeserializer<LocalDateTime> {
    @Override
    public LocalDateTime deserialize(final JsonElement element, final Type typeOfT, final JsonDeserializationContext context) throws JsonParseException {
        final JsonObject asJsonObject = element.getAsJsonObject();
        final int year = asJsonObject.get("year").getAsInt();
        final int month = asJsonObject.get("monthValue").getAsInt();
        final int dayOfMonth = asJsonObject.get("dayOfMonth").getAsInt();
        final int hour = asJsonObject.get("hour").getAsInt();
        final int minute = asJsonObject.get("minute").getAsInt();
        final int second = asJsonObject.get("second").getAsInt();
        return LocalDateTime.of(year, month, dayOfMonth, hour, minute, second);
    }
}