
import org.junit.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.i18n.LocalizationService;
import ru.netology.sender.MessageSenderImpl;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class MessageSenderImplTest {

    private GeoService geoService;
    private LocalizationService localizationService;
    private MessageSenderImpl messageSender;

    @Test
    public void testSendMessageInRussianForRussianIP() {
        geoService = Mockito.mock(GeoService.class);
        localizationService = Mockito.mock(LocalizationService.class);
        messageSender = new MessageSenderImpl(geoService, localizationService);

        String ip = "172.0.32.11";
        Location location = new Location("Moscow", Country.RUSSIA, "Ленина", 1);
        when(geoService.byIp(ip)).thenReturn(location);
        when(localizationService.locale(Country.RUSSIA)).thenReturn("Сообщение на русском");

        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, ip);

        String result = messageSender.send(headers);

        assertEquals("Сообщение на русском", result);
    }

    @Test
    public void testSendMessageInRussianForAmericanIP() {
        geoService = Mockito.mock(GeoService.class);
        localizationService = Mockito.mock(LocalizationService.class);
        messageSender = new MessageSenderImpl(geoService, localizationService);

        String ip = "96.44.183.149";
        Location location = new Location("New York", Country.USA, "Times Square", 1);
        when(geoService.byIp(ip)).thenReturn(location);
        when(localizationService.locale(Country.USA)).thenReturn("Message in English");

        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, ip);

        String result = messageSender.send(headers);

        assertEquals("Message in English", result);
    }
}
