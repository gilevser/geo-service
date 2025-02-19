import org.junit.Test;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;

import static org.junit.Assert.assertEquals;

public class GeoServiceImplTest {

    private final GeoService geoService = new GeoServiceImpl();

    @Test
    public void testGeoServiceByIp() {
        String ip = "172.0.32.11";
        Location location = geoService.byIp(ip);
        assertEquals("Moscow", location.getCity());
        assertEquals(Country.RUSSIA, location.getCountry());
        assertEquals("Lenina", location.getStreet());
        assertEquals(1, location.getBuiling());
    }
}
